package kr.marko.photobook.presentation.mvi

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kr.marko.photobook.domain.Initialized
import kr.marko.photobook.domain.Late
import org.orbitmvi.orbit.ContainerHost
import kotlin.reflect.KProperty1

interface MviView : LifecycleOwner {

    fun <S : MviViewState, E : Any, A> ContainerHost<S, E>.onEach(prop: KProperty1<S, A>, action: suspend (A) -> Unit) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    this@onEach.container.stateFlow.map { prop.get(it) }.distinctUntilChanged().collectLatest(action::invoke)
                }
            }
        }
    }

    fun <S : MviViewState, E : Any, A, B> ContainerHost<S, E>.onEach(prop: KProperty1<S, A>, prop1: KProperty1<S, B>, action: suspend (A, B) -> Unit) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    this@onEach.container.stateFlow.map { prop.get(it) to prop1.get(it) }.distinctUntilChanged().collectLatest {
                        action.invoke(it.first, it.second)
                    }
                }
            }
        }
    }

    fun <S : MviViewState, E : Any, A> ContainerHost<S, E>.onInit(prop: KProperty1<S, Late<A>>, action: suspend (A) -> Unit) {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    this@onInit.container.stateFlow.map { prop.get(it) }.distinctUntilChanged().collectLatest {
                        if (it is Initialized) action.invoke(it())
                    }
                }
            }
        }
    }
}