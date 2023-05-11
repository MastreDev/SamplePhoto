package kr.marko.photobook.presentation.landing

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class PrepareEditorViewModel @AssistedInject constructor(
    @Assisted initState: PrepareEditorViewState
) : ViewModel(), ContainerHost<PrepareEditorViewState, PrepareEditorViewEffect> {

    override val container: Container<PrepareEditorViewState, PrepareEditorViewEffect> = container(initState) { prepareEditor() }

    private fun prepareEditor() = intent {
        delay(1000)
        if (state.editorParams.projectCode == null) {
            postSideEffect(PrepareEditorViewEffect.NavGallery)
        } else {
            postSideEffect(PrepareEditorViewEffect.NavSketch)
        }

    }

    @AssistedFactory
    interface Factory {
        fun create(initState: PrepareEditorViewState): PrepareEditorViewModel
    }

}