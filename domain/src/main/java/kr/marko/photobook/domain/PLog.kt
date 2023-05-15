package kr.marko.photobook.domain

object PLog {

    private var mTag: ((String) -> Unit) = { }
    private var mDebug: ((String) -> Unit) = { println(it) }

    fun tagBlock(block: (String) -> Unit): PLog {
        mTag = block
        return this
    }

    fun debugBlock(block: (String) -> Unit): PLog {
        mDebug = block
        return this
    }

    fun tag(wantTag: String): PLog {
        mTag.invoke(wantTag)
        return this
    }

    fun d(message: Any) {
        mDebug.invoke(message.toString())
    }

    fun marko(message: Any) {
        tag("#Marko").d(message.toString())
    }

}