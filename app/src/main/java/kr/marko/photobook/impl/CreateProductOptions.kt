package kr.marko.photobook.impl

import kotlinx.parcelize.Parcelize
import kr.marko.photobook.presentation.protocol.EditorParams

@Parcelize
data class CreateProductOptions(
    val productCode: String
) : EditorParams {

    override val projectCode: String? get() = null
    
}