package kr.marko.photobook

sealed interface MainViewEffect {
    object NavEditor : MainViewEffect
}