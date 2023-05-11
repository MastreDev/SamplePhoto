package kr.marko.photobook

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kr.marko.photobook.impl.CartProductOptions
import kr.marko.photobook.impl.CreateProductOptions
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel @AssistedInject constructor(
    @Assisted initState: MainViewState
) : ViewModel(), ContainerHost<MainViewState, MainViewEffect> {

    override val container: Container<MainViewState, MainViewEffect> = container(initState)

    fun onClickCreateProduct() = intent {
        postSideEffect(MainViewEffect.NavEditor(CreateProductOptions(productCode = "140252")))
    }

    fun onClickLoadProject() = intent {
        postSideEffect(MainViewEffect.NavEditor(CartProductOptions(projectCode = "20302030")))
    }

    @AssistedFactory
    interface Factory {
        fun create(initState: MainViewState): MainViewModel
    }

}