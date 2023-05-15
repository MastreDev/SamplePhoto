package kr.marko.photobook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import kr.marko.photobook.data.ProjectRepositoryImpl
import kr.marko.photobook.domain.project.ProjectRepository
import kr.marko.photobook.presentation.di.EditorComponent
import kr.marko.photobook.presentation.di.EditorScope

@Module
@InstallIn(EditorComponent::class)
abstract class EditorModule {

    @EditorScope
    @Binds
    internal abstract fun bindProjectRepository(impl: ProjectRepositoryImpl): ProjectRepository

}