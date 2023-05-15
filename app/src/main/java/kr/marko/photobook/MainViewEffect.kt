package kr.marko.photobook

import kr.marko.photobook.presentation.protocol.EditorParams

sealed interface MainViewEffect {
    data class NavEditor(val params: EditorParams) : MainViewEffect
}