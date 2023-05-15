package kr.marko.photobook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kr.marko.photobook.util.PhotoBookTimberTree
import timber.log.Timber

@HiltAndroidApp
class PhotoBookApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(PhotoBookTimberTree())
    }

}