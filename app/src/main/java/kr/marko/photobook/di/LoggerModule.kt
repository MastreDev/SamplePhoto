package kr.marko.photobook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.marko.photobook.domain.PhotoBookLogger
import kr.marko.photobook.util.PhotoBookLoggerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoggerModule {

    @Binds
    @Singleton
    abstract fun logger(impl : PhotoBookLoggerImpl) : PhotoBookLogger

}