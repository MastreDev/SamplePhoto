package kr.marko.photobook.presentation

sealed interface EditorViewEffect  {

    object NavGallery : EditorViewEffect
    object NavSketch : EditorViewEffect

}