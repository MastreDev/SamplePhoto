package kr.marko.photobook.impl

import kotlinx.parcelize.Parcelize
import kr.marko.photobook.presentation.protocol.EditorParams

@Parcelize
data class CartProductOptions(
    override val projectCode: String,
    override val productCode: String,
    override val templateCode: String,
    override val glossyType: String? = null,
    override val paperCode: String? = null,
    override val quantity: Int? = null,
    override val calendarStartDate: String? = null,
    override val calendarEndDate: String? = null,
    override val sizeQuantitys: String? = null,
    override val frameCode: String? = null,
    override val frameType: String? = null,
    override val colorCode: String? = null,
    override val backType: String? = null,
    override val sizeCode: String? = null,
    override val projectAccessoryParams: String? = null,
    override val inflowLocation: String? = null,
    override val createTime: Long = System.currentTimeMillis()
) : EditorParams {

}