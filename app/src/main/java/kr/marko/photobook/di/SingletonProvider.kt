package kr.marko.photobook.di

import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.marko.photobook.ApplicationScope
import kr.marko.photobook.di.editorcomponent.EditorComponentManager
import kr.marko.photobook.di.editorcomponent.EditorSession
import kr.marko.photobook.domain.project.ProjectRepository
import kr.marko.photobook.presentation.di.EditorComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object SingletonProvider {

    @Singleton
    @Provides
    fun editorComponentManager(
        applicationScope: ApplicationScope,
        editorSession: EditorSession,
        componentProvider: Provider<EditorComponent.Builder>
    ): EditorComponentManager {
        return EditorComponentManager(applicationScope, editorSession, componentProvider)
    }

    @Singleton
    @Provides
    fun editorSession(): EditorSession = EditorSession()

    @Bridged
    @Provides
    internal fun provideRepo(manager: EditorComponentManager): ProjectRepository {
        return EntryPoints
            .get(manager, ProjectRepositoryEntryPoint::class.java)
            .projectRepository()
    }

}