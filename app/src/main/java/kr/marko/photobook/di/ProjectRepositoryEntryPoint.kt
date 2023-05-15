package kr.marko.photobook.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import kr.marko.photobook.domain.project.ProjectRepository
import kr.marko.photobook.presentation.di.EditorComponent

@EntryPoint
@InstallIn(EditorComponent::class)
internal interface ProjectRepositoryEntryPoint {

    fun projectRepository(): ProjectRepository

}