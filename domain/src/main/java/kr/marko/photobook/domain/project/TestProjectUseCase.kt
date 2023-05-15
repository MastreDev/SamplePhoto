package kr.marko.photobook.domain.project

import io.reactivex.rxjava3.core.Single
import kr.marko.photobook.di.Bridged
import kr.marko.photobook.domain.PLog
import kr.marko.photobook.domain.SingleUseCase
import javax.inject.Inject

class TestProjectUseCase @Inject constructor(
    @Bridged private val repository: ProjectRepository,
) : SingleUseCase<Nothing, Unit> {

    init {
        PLog.marko("TestProjectUseCase ==>>>> : ${repository.hashCode()}")
    }

    override fun invoke(params: Nothing): Single<Unit> {
        return Single.just(Unit)
    }
}