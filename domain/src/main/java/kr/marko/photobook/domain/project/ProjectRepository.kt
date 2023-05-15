package kr.marko.photobook.domain.project

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import kr.marko.photobook.domain.product.ProductInfo
import kr.marko.photobook.domain.product.SpineInfo
import kr.marko.photobook.domain.save.Save

interface ProjectRepository {

    fun createProjectOption(recipe: ProjectOption.Params): Single<ProjectOption>

    fun getProjectOption(projectCode: String): Single<ProjectOption>

    fun getProductInfo(): Single<ProductInfo>

    fun getSpineInfo(): Single<SpineInfo>

    fun getSave(projectCode: String): Single<Save>

}