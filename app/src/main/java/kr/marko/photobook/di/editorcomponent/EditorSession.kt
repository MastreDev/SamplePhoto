package kr.marko.photobook.di.editorcomponent

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.marko.photobook.domain.Initialized
import kr.marko.photobook.domain.Late
import kr.marko.photobook.domain.Uninitialized
import kr.marko.photobook.domain.asInit
import kr.marko.photobook.presentation.protocol.EditorParams
import timber.log.Timber
import javax.inject.Inject

class EditorSession @Inject constructor() {

    private val _currentEditParams = MutableStateFlow<Late<EditorParams>>(Uninitialized)
    val currentEditParams = _currentEditParams.asStateFlow()

    fun stage(params: EditorParams) {
        _currentEditParams.tryEmit(params.asInit())
    }

    fun unStage() {
        _currentEditParams.tryEmit(Uninitialized)
    }

    fun isStaged(): Boolean {
        return _currentEditParams.value is Initialized
    }

}