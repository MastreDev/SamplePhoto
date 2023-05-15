package kr.marko.photobook.di.editorcomponent

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.first
import kr.marko.photobook.domain.Uninitialized
import kr.marko.photobook.domain.project.LoadProjectUseCase
import kr.marko.photobook.presentation.protocol.EditorParams

class EditorSessionTest : FunSpec({

    val fixture = kotlinFixture()
    val session = EditorSession()

    test("Session 에 아무값이 없으면 false가 된다.") {
        session.isStaged() shouldBe false
    }

    test("Session 생성 후 unstage를 바로 해도 isStaged 는 false가 된다.") {
        session.unStage()

        session.isStaged() shouldBe false
    }

    test("편집기에 진입하면 최초 LoadProjectParams 를 전달하면서 시작한다. 이때 Session 을 Init 한다.") {
        val params = fixture<EditorParams>()

        session.stage(params)

        session.currentEditParams.first()() shouldBe params
    }

    test("이전에 저장된 값이 있는 상태에서 unstage를 호출하면 Unintialize 가 내려온다.") {
        val params = fixture<EditorParams>()

        session.stage(params)
        session.unStage()

        session.currentEditParams.first() shouldBe Uninitialized
    }

    test("params를 staging 하고 session이 살아있는지 물어보면 true가 된다.") {
        val params = fixture<EditorParams>()

        session.stage(params)

        session.isStaged() shouldBe true
    }

    test("unstaging 이후  session이 살아있는지 물어보면 false 된다.") {
        val params = fixture<EditorParams>()

        session.stage(params)
        session.unStage()

        session.isStaged() shouldBe false
    }

})