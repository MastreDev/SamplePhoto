package kr.marko.photobook.presentation.landing

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.rx3.await
import kr.marko.photobook.domain.error.PhotoBookException
import kr.marko.photobook.domain.project.LoadProjectUseCase
import kr.marko.photobook.presentation.mvi.asInit
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PrepareEditorViewModel @AssistedInject constructor(
    private val loadProject: LoadProjectUseCase,
    @Assisted initState: PrepareEditorViewState
) : ViewModel(), ContainerHost<PrepareEditorViewState, PrepareEditorViewEffect> {

    override val container: Container<PrepareEditorViewState, PrepareEditorViewEffect> = container(initState) { prepareEditor() }

    private fun prepareEditor() = intent {
        runCatching {
            loadProject.invoke(LoadProjectUseCase.Params(state.editorParams.projectCode, state.editorParams)).await()
        }.onSuccess {
            val viewEffect = state.editorParams.projectCode?.run { PrepareEditorViewEffect.NavSketch } ?: PrepareEditorViewEffect.NavGallery
            postSideEffect(viewEffect)
        }.onFailure {
            val errorMessage = when (it) {
                is PhotoBookException -> it.message.asInit(it.reason.message)
                else -> it.message.asInit("Unexpected Error")
            }
            reduce { state.copy(errorMessage = errorMessage) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(initState: PrepareEditorViewState): PrepareEditorViewModel
    }

}