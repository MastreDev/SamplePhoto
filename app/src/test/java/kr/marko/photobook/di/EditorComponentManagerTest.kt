package kr.marko.photobook.di

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kr.marko.photobook.ApplicationScope
import kr.marko.photobook.di.editorcomponent.ComponentVersion
import kr.marko.photobook.di.editorcomponent.EditorComponentManager
import kr.marko.photobook.di.editorcomponent.EditorSession
import kr.marko.photobook.presentation.di.EditorComponent
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import javax.inject.Provider

class EditorComponentManagerTest : BehaviorSpec({

    isolationMode = IsolationMode.InstancePerLeaf

    val fixture = kotlinFixture()
    val testScope = TestScope()

    Given("ApplicationScope와 EditorSession, Provider가 제공되고") {
        val applicationScope = mock<ApplicationScope> {
            on { coroutineContext } doReturn testScope.coroutineContext
        }
        val editorSession = EditorSession()
        val componentProvider = mock<Provider<EditorComponent.Builder>> {
            on { get() } doReturn FakeEditorComponentBuilder()
        }

        When("Manager가 생성되면") {
            val manager = EditorComponentManager(applicationScope, editorSession, componentProvider)
            Then("ComponentVersion은 1 이다.") {
                manager.versionState.first() shouldBe ComponentVersion(1)
            }
        }

        When("Manager 생성 후 session이 stage되면") {
            val manager = EditorComponentManager(applicationScope, editorSession, componentProvider)
            editorSession.stage(fixture())
            Then("ComponentVersion은 2 이다.") {
                manager.versionState.first() shouldBe ComponentVersion(2)
            }
        }

        When("Manager 생성 후 session이 stage되고, unstage 되면") {
            val manager = EditorComponentManager(applicationScope, editorSession, componentProvider)
            editorSession.stage(fixture())
            editorSession.unStage()
            Then("ComponentVersion은 3 이다.") {
                manager.versionState.first() shouldBe ComponentVersion(3)
            }
        }
    }
})

class FakeEditorComponent : EditorComponent

class FakeEditorComponentBuilder : EditorComponent.Builder {

    override fun build(): EditorComponent {
        return FakeEditorComponent().also { println("#Build ${it.hashCode()}") }
    }

}
