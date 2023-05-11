package kr.marko.photobook.domain.project

import kr.marko.photobook.domain.product.ProductInfo
import kr.marko.photobook.domain.product.SpineInfo
import kr.marko.photobook.domain.save.Save

class ProjectBuilder {

    private lateinit var code: String
    private lateinit var save: Save
    private lateinit var projectOption: ProjectOption
    private lateinit var productInfo: ProductInfo
    private lateinit var spineInfo: SpineInfo

    fun code(block: () -> String) {
        code = block()
    }

    fun save(block: () -> Save) {
        save = block()
    }

    fun projectOption(block: () -> ProjectOption) {
        projectOption = block()
    }

    fun productInfo(block: () -> ProductInfo) {
        productInfo = block()
    }

    fun spineInfo(block: () -> SpineInfo) {
        spineInfo = block()
    }

    fun build(): Project = Project(code, save, projectOption, productInfo, spineInfo)

}

fun project(block: ProjectBuilder.() -> Unit): Project = ProjectBuilder().apply(block).build()