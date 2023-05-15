package kr.marko.photobook.domain.project

import io.reactivex.rxjava3.core.Single
import kr.marko.photobook.domain.SingleUseCase
import kr.marko.photobook.domain.save.Save
import javax.inject.Inject

class LoadProjectUseCase @Inject constructor(private val repository: ProjectRepository) : SingleUseCase<LoadProjectUseCase.Params, Project> {

    override fun invoke(params: Params): Single<Project> {
        return if (params.projectCode == null) {
            require(params.projectOptionCreateData != null) { "projectOptionCreateData cannot be null." }
            Single.zip(
                repository.createProjectOption(params.projectOptionCreateData),
                repository.getProductInfo(),
                repository.getSpineInfo(),
            ) { projectOption, productInfo, spineInfo ->
                project {
                    code { projectOption.projectCode }
                    save { Save() }
                    projectOption { projectOption }
                    productInfo { productInfo }
                    spineInfo { spineInfo }
                }
            }

        } else {
            Single.zip(
                repository.getSave(params.projectCode),
                repository.getProjectOption(params.projectCode),
                repository.getProductInfo(),
                repository.getSpineInfo(),
            ) { save, projectOption, productInfo, spineInfo ->
                project {
                    code { projectOption.projectCode }
                    save { save }
                    projectOption { projectOption }
                    productInfo { productInfo }
                    spineInfo { spineInfo }
                }
            }
        }
    }

    data class Params(
        val projectCode: String?,
        val projectOptionCreateData: ProjectOption.Params?
    )

}