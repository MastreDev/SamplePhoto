package kr.marko.photobook.domain.error

sealed interface Reason {

    val message: String

    data class Extra(override val message: String) : Reason

    data class ProjectNotFound(val requestCode: String) : Reason {
        override val message: String get() = "Not found save by $requestCode"
    }

}