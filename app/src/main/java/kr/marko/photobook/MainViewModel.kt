package kr.marko.photobook

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel @AssistedInject constructor(
    @Assisted initState: MainViewState
) : ViewModel(), ContainerHost<MainViewState, MainViewEffect> {

    override val container: Container<MainViewState, MainViewEffect> = container(initState)

    fun onClickWelcomeText() = intent {
        postSideEffect(MainViewEffect.NavEditor)
    }

    @AssistedFactory
    interface Factory {
        fun create(initState: MainViewState): MainViewModel
    }

}