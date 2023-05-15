package kr.marko.photobook.util

import kr.marko.photobook.domain.PhotoBookLogger
import timber.log.Timber
import javax.inject.Inject

class PhotoBookLoggerImpl @Inject constructor() : PhotoBookLogger {

    override fun tag(tag: String): PhotoBookLogger {
        Timber.tag(tag)
        return this
    }

    override fun d(message: String) {
        Timber.d(message)
    }

    override fun marko(message: String) {
        Timber.tag("#Marko").d(message)
    }

}