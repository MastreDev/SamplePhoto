package kr.marko.photobook.domain.project

import kr.marko.photobook.domain.product.ProductInfo
import kr.marko.photobook.domain.product.SpineInfo
import kr.marko.photobook.domain.save.Save

/**
 * Project 는 실제 존재하는 데이터가 아니고 code, save, projectOption, productInfo, spineInfo 데이터가 합쳐진 추상 객체이다.
 * 각 데이터가 가진 의미가 Business logic에 해당되므로 Repository 에게 위임 하지 않고 UseCase 에서 조합한다.
 */

class Project(
    val code: String,
    val save: Save,
    val projectOption: ProjectOption,
    val productInfo: ProductInfo,
    val spineInfo: SpineInfo,
){

}