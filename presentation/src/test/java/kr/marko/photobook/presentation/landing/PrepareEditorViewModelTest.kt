package kr.marko.photobook.presentation.landing

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.core.spec.style.BehaviorSpec
import io.reactivex.rxjava3.core.Single
import kotlinx.parcelize.Parcelize
import kr.marko.photobook.domain.asInit
import kr.marko.photobook.domain.error.PhotoBookException
import kr.marko.photobook.domain.error.Reason
import kr.marko.photobook.domain.project.LoadProjectUseCase
import kr.marko.photobook.domain.project.Project
import kr.marko.photobook.presentation.protocol.EditorParams
import org.mockito.kotlin.*
import org.orbitmvi.orbit.test
import java.net.SocketException

class PrepareEditorViewModelTest : BehaviorSpec({

    val fixture = kotlinFixture()

    val project = mock<Project>()
    val loadProject = mock<LoadProjectUseCase> {
        on { invoke(any()) } doReturn Single.just(project)
    }

    Given("EditorParams에 ProjectCode가 없고") {
        val editorParams = fixture<FakeEditorParams>().copy(projectCode = null)
        val initState = PrepareEditorViewState(editorParams)

        When("Vm init이 되면") {
            val vm = PrepareEditorViewModel(loadProject, initState)
            val testSubject = vm.test().runOnCreate()

            Then("Gallery 로 이동시키는 ViewEffect가 발생한다.") {
                testSubject.assert(initState) {
                    postedSideEffects(PrepareEditorViewEffect.NavGallery)
                }
            }
            Then("LoadProjectUseCase 가 호출된다.") {
                verify(loadProject).invoke(LoadProjectUseCase.Params(projectCode = null, projectOptionCreateData = editorParams))
            }
        }
    }

    Given("EditorParams에 ProjectCode가 있고") {
        val editorParams = fixture<FakeEditorParams>().copy(projectCode = fixture<String>())
        val initState = PrepareEditorViewState(editorParams)

        When("Vm init이 되면") {
            val vm = PrepareEditorViewModel(loadProject, initState)
            val testSubject = vm.test().runOnCreate()

            Then("Gallery 로 이동시키는 ViewEffect가 발생한다.") {
                testSubject.assert(initState) {
                    postedSideEffects(PrepareEditorViewEffect.NavSketch)
                }
            }
            Then("LoadProjectUseCase 가 호출된다.") {
                verify(loadProject).invoke(LoadProjectUseCase.Params(projectCode = editorParams.projectCode, projectOptionCreateData = editorParams))
            }
        }
    }

    Given("EditorParams에 ProjectCode가 있지만 찾을 수 없는 값이고") {
        val editorParams = fixture<FakeEditorParams>().copy(projectCode = "-1")
        val initState = PrepareEditorViewState(editorParams)
        val expectedException = PhotoBookException(Reason.ProjectNotFound("-1"))
        whenever(loadProject.invoke(LoadProjectUseCase.Params(projectCode = editorParams.projectCode, projectOptionCreateData = editorParams)))
            .thenReturn(Single.error(expectedException))

        When("Vm init이 되면") {
            val vm = PrepareEditorViewModel(loadProject, initState)
            val testSubject = vm.test().runOnCreate()

            Then("Error State가 된다.") {
                testSubject.assert(initState) {
                    states(
                        { copy(errorMessage = expectedException.message!!.asInit()) }
                    )
                }
            }
        }
    }

    Given("정의된 PhotobookException 외에 다른 에러가 발생하고") {
        val expectedException = SocketException()
        val editorParams = fixture<FakeEditorParams>()
        val initState = PrepareEditorViewState(editorParams)
        whenever(loadProject.invoke(LoadProjectUseCase.Params(projectCode = editorParams.projectCode, projectOptionCreateData = editorParams)))
            .thenReturn(Single.error(expectedException))

        When("Vm init이 되면") {
            val vm = PrepareEditorViewModel(loadProject, initState)
            val testSubject = vm.test().runOnCreate()

            Then("Error State가 되고, Unexpected Erro 메시지가 출력된다..") {
                testSubject.assert(initState) {
                    states(
                        { copy(errorMessage = "Unexpected Error".asInit()) }
                    )
                }
            }
        }
    }
})

@Parcelize
data class FakeEditorParams(
    override val glossyType: String?,
    override val paperCode: String?,
    override val quantity: Int?,
    override val calendarStartDate: String?,
    override val calendarEndDate: String?,
    override val sizeQuantitys: String?,
    override val frameCode: String?,
    override val frameType: String?,
    override val colorCode: String?,
    override val backType: String?,
    override val sizeCode: String?,
    override val projectAccessoryParams: String?,
    override val inflowLocation: String?,
    override val projectCode: String?,
    override val productCode: String,
    override val templateCode: String,
    override val createTime: Long = System.currentTimeMillis()
) : EditorParams
