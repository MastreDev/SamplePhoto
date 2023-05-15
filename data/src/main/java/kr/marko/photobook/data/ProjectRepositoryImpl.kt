package kr.marko.photobook.data

import io.reactivex.rxjava3.core.Single
import kr.marko.photobook.domain.product.ProductInfo
import kr.marko.photobook.domain.product.SpineInfo
import kr.marko.photobook.domain.project.ProjectOption
import kr.marko.photobook.domain.project.ProjectRepository
import kr.marko.photobook.domain.save.Save
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor() : ProjectRepository {

    override fun createProjectOption(recipe: ProjectOption.Params): Single<ProjectOption> {
        return Single.just(ProjectOption("1234"))
    }

    override fun getProjectOption(projectCode: String): Single<ProjectOption> {
        return Single.just(ProjectOption(projectCode))
    }

    override fun getProductInfo(): Single<ProductInfo> {
        return Single.just(ProductInfo())
    }

    override fun getSpineInfo(): Single<SpineInfo> {
        return Single.just(SpineInfo())
    }

    override fun getSave(projectCode: String): Single<Save> {
        return Single.just(Save())
    }
}