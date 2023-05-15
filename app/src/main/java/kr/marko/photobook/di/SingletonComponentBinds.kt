package kr.marko.photobook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.marko.photobook.data.ProjectRepositoryImpl
import kr.marko.photobook.domain.project.ProjectRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SingletonComponentBinds {

    @Singleton
    @Binds
    internal abstract fun projectRepository(impl : ProjectRepositoryImpl) : ProjectRepository

}