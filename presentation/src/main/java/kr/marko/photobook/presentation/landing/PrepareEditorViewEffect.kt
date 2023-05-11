package kr.marko.photobook.presentation.landing

sealed interface PrepareEditorViewEffect {
    object NavGallery : PrepareEditorViewEffect
    object NavSketch : PrepareEditorViewEffect
}