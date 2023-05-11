package kr.marko.photobook.domain

import io.reactivex.rxjava3.core.Single

interface SingleUseCase<in Params, Type : Any> {

    operator fun invoke(params: Params): Single<Type>

}