package kr.marko.photobook.presentation

import kr.marko.photobook.presentation.mvi.MviViewState
import kr.marko.photobook.presentation.protocol.EditorParams

data class EditorViewState(
    val editorParams: EditorParams
) : MviViewState {

}