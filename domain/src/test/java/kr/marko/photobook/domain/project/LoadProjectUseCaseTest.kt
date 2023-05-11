package kr.marko.photobook.domain.project

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.rx3.await
import kr.marko.photobook.domain.error.PhotoBookException
import kr.marko.photobook.domain.error.Reason
import kr.marko.photobook.domain.product.ProductInfo
import kr.marko.photobook.domain.product.SpineInfo
import kr.marko.photobook.domain.save.Save
import org.mockito.kotlin.*

class LoadProjectUseCaseTest : FunSpec({

    val fixture = kotlinFixture { subType<ProjectOption.Params, FakeProjectOptionParams>() }
    val productInfo: ProductInfo = mock()
    val spineInfo: SpineInfo = mock()
    val save: Save = mock()
    val repository = mock<ProjectRepository>()

    afterTest {
        reset(productInfo)
        reset(spineInfo)
        reset(save)
        reset(repository)
    }

    test("Params에 projectCode 가 없으면 새로운 Project를 만든다.") {
        val prepareData = fixture<ProjectOption.Params>()
        val newProjectOption = fixture<ProjectOption>()
        whenever(repository.createProjectOption(prepareData)) doReturn Single.just(newProjectOption)
        whenever(repository.getProductInfo()) doReturn Single.just(productInfo)
        whenever(repository.getSpineInfo()) doReturn Single.just(spineInfo)

        val useCase = LoadProjectUseCase(repository)
        val result = useCase.invoke(LoadProjectUseCase.Params(null, prepareData)).await()

        result.code shouldBe newProjectOption.projectCode
        result.save shouldNotBe save
        result.productInfo shouldBe productInfo
        result.projectOption shouldBe newProjectOption
        result.spineInfo shouldBe spineInfo

        verify(repository).createProjectOption(prepareData)
        verify(repository).getProductInfo()
        verify(repository).getSpineInfo()
        verify(repository, never()).getProjectOption(any())
    }

    test("Params에 projectCode가 있으면 해당 projectCode를 가진 Project를 리턴한다.") {
        val projectCode = "1234"
        val projectOption: ProjectOption = mock()
        whenever(projectOption.projectCode).thenReturn(projectCode)
        whenever(repository.getSave(projectCode)) doReturn Single.just(save)
        whenever(repository.getProductInfo()) doReturn Single.just(productInfo)
        whenever(repository.getProjectOption(projectCode)) doReturn Single.just(projectOption)
        whenever(repository.getSpineInfo()) doReturn Single.just(spineInfo)
        val useCase = LoadProjectUseCase(repository)
        val result = useCase.invoke(LoadProjectUseCase.Params(projectCode, null)).await()

        result.code shouldBe projectCode
        result.save shouldBe save
        result.productInfo shouldBe productInfo
        result.projectOption shouldBe projectOption
        result.spineInfo shouldBe spineInfo

        verify(repository).getSave(projectCode)
        verify(repository).getProjectOption(projectCode)
        verify(repository).getProductInfo()
        verify(repository).getSpineInfo()
        verify(repository, never()).createProjectOption(any())
    }

    test("Params에 projectCode가 있고, 해당 Save 를 찾지 못하는 경우 Exception이 발생한다.") {
        val projectCode = "-1"
        val projectOption: ProjectOption = mock()
        whenever(projectOption.projectCode).thenReturn(projectCode)
        whenever(repository.getSave(projectCode)) doReturn Single.error(PhotoBookException(Reason.ProjectNotFound(projectCode)))
        whenever(repository.getProductInfo()) doReturn Single.just(productInfo)
        whenever(repository.getProjectOption(projectCode)) doReturn Single.just(projectOption)
        whenever(repository.getSpineInfo()) doReturn Single.just(spineInfo)

        val useCase = LoadProjectUseCase(repository)
        val exception = shouldThrow<PhotoBookException> {
            useCase.invoke(LoadProjectUseCase.Params(projectCode, null)).await()
        }
        exception.message shouldBe Reason.ProjectNotFound(projectCode).message
    }

    test("Param에 projectCode, projectOptionCreateData 둘 다 없는 경우 IllegalArgumentException이 발생한다.") {
        val useCase = LoadProjectUseCase(repository)
        val params = LoadProjectUseCase.Params(null, null)

        val exception = shouldThrow<IllegalArgumentException> { useCase.invoke(params).await() }

        exception.shouldBeInstanceOf<IllegalArgumentException>()
    }

})

data class FakeProjectOptionParams(
    override val deviceId: String,
    override val userNo: String,
    override val productCode: String,
    override val templateCode: String,
    override val glossyType: String?,
    override val paperCode: String,
    override val quantity: Int,
    override val calendarStartDate: String,
    override val calendarEndDate: String,
    override val sizeQuantitys: String?,
    override val frameCode: String?,
    override val frameType: String?,
    override val colorCode: String?,
    override val backType: String?,
    override val sizeCode: String?,
    override val projectAccessoryParams: String?,
    override val inflowLocation: String?
) : ProjectOption.Params