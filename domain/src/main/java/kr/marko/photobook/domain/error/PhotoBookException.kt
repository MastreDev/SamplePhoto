package kr.marko.photobook.domain.error

class PhotoBookException(val reason: Reason) : RuntimeException(reason.message) {

    constructor(message: String) : this(Reason.Extra(message))

}