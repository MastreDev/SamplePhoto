package kr.marko.photobook.presentation.landing

import kr.marko.photobook.presentation.mvi.Late
import kr.marko.photobook.presentation.mvi.MviViewState
import kr.marko.photobook.presentation.mvi.Uninitialized
import kr.marko.photobook.presentation.protocol.EditorParams

data class PrepareEditorViewState(
    val editorParams: EditorParams,
    val errorMessage : Late<String> = Uninitialized
) : MviViewState {

}