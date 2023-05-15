package kr.marko.photobook.di.editorcomponent

import dagger.hilt.internal.GeneratedComponentManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kr.marko.photobook.ApplicationScope
import kr.marko.photobook.domain.Late
import kr.marko.photobook.presentation.di.EditorComponent
import kr.marko.photobook.presentation.protocol.EditorParams
import timber.log.Timber
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Provider

class EditorComponentManager(
    applicationScope: ApplicationScope,
    private val editorSession: EditorSession,
    private val componentProvider: Provider<EditorComponent.Builder>
) : GeneratedComponentManager<EditorComponent> {

    private val _versionState = MutableStateFlow(ComponentVersion.next())
    val versionState = _versionState.asStateFlow()

    private var editorComponent: EditorComponent = componentProvider.get().build().also { Timber.tag("#Marko").d("Init !! $it") }

    private var lastEditorParams: Late<EditorParams> = editorSession.currentEditParams.value

    init {
        applicationScope.launch {
            editorSession.currentEditParams
                .filter { lastEditorParams != it }
                .collect { editorParams ->
                    Timber.tag("#Marko").d("Hey, Version : ${versionState.value}")
                    lastEditorParams = editorParams
                    rebuildComponent()
                }
        }
    }

    private suspend fun rebuildComponent() {
        editorComponent = componentProvider.get().build()
        _versionState.emit(ComponentVersion.next())
    }

    override fun generatedComponent(): EditorComponent = editorComponent

}

data class ComponentVersion internal constructor(
    private val version: Int = versionSeq.incrementAndGet()
) {

    companion object {
        private val versionSeq = AtomicInteger(0)
        fun next(): ComponentVersion = ComponentVersion()
    }

}