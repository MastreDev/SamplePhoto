package kr.marko.photobook.domain

interface PhotoBookLogger {

    fun tag(tag: String): PhotoBookLogger

    fun d(message: String)

    fun marko(message: String)

}