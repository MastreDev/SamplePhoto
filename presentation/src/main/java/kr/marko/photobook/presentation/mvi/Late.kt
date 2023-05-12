package kr.marko.photobook.presentation.mvi

sealed class Late<out T>(private val value: T?) {

    open operator fun invoke(): T? = value

}

object Uninitialized : Late<Nothing>(value = null)

data class Initialized<out T>(private val value: T) : Late<T>(value = value) {

    override operator fun invoke(): T = value

}

fun <T> T?.asInit(default: T? = null): Late<T> {
    return default?.asInit() ?: this?.asInit() ?: Uninitialized
}

private fun <T> T.asInit(): Initialized<T> {
    return Initialized(this)
}

fun <T, R> Late<T>.runIfInit(block: T.() -> R): R? {
    return this()?.run(block)
}