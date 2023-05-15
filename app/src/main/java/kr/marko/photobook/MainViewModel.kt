package kr.marko.photobook

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kr.marko.photobook.di.editorcomponent.EditorSession
import kr.marko.photobook.impl.CartProductOptions
import kr.marko.photobook.impl.CreateProductOptions
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel @AssistedInject constructor(
    private val editorSession: EditorSession,
    @Assisted initState: MainViewState
) : ViewModel(), ContainerHost<MainViewState, MainViewEffect> {

    override val container: Container<MainViewState, MainViewEffect> = container(initState)

    fun onClickCreateProduct() = intent {
        val editorParams = CreateProductOptions(
            productCode = "140252",
            sizeCode = "",
            projectAccessoryParams = "",
            inflowLocation = "",
            templateCode = "",
            glossyType = "",
            paperCode = "",
            quantity = 1,
            calendarStartDate = "",
            calendarEndDate = "",
            sizeQuantitys = "",
            frameCode = "",
            frameType = "",
            colorCode = "",
            backType = ""
        )
        editorSession.stage(editorParams)
        postSideEffect(MainViewEffect.NavEditor(editorParams))
    }

    fun onClickLoadProject() = intent {
        val editorParams = CartProductOptions(projectCode = "20302030", productCode = "140252", templateCode = "234252")
        editorSession.stage(editorParams)
        postSideEffect(MainViewEffect.NavEditor(editorParams))
    }

    @AssistedFactory
    interface Factory {
        fun create(initState: MainViewState): MainViewModel
    }

}