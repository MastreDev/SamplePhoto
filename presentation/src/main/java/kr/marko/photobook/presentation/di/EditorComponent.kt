package kr.marko.photobook.presentation.di

import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@EditorScope
@DefineComponent(parent = SingletonComponent::class)
interface EditorComponent {

    @DefineComponent.Builder
    interface Builder {
        fun build(): EditorComponent
    }

}