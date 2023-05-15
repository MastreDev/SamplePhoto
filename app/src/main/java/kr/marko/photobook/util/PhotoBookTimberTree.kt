package kr.marko.photobook.util

import kr.marko.photobook.domain.PLog
import timber.log.Timber

class PhotoBookTimberTree : Timber.DebugTree() {

    init {
        PLog
            .tagBlock { tag ->
                Timber.tag(tag)
            }
            .debugBlock {
                Timber.d(it)
            }
    }

}