package kr.marko.photobook.presentation

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

class EditorViewModel @AssistedInject constructor(
    @Assisted initState: EditorViewState
) : ViewModel(), ContainerHost<EditorViewState, EditorViewEffect> {

    override val container: Container<EditorViewState, EditorViewEffect> = container(initState)

}