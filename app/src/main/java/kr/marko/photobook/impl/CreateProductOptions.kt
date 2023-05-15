package kr.marko.photobook.impl

import kotlinx.parcelize.Parcelize
import kr.marko.photobook.presentation.protocol.EditorParams

@Parcelize
data class CreateProductOptions(
    override val projectCode: String? = null,
    override val productCode: String,
    override val templateCode: String,
    override val glossyType: String?,
    override val paperCode: String?,
    override val quantity: Int?,
    override val calendarStartDate: String?,
    override val calendarEndDate: String?,
    override val sizeQuantitys: String?,
    override val frameCode: String?,
    override val frameType: String?,
    override val colorCode: String?,
    override val backType: String?,
    override val sizeCode: String?,
    override val projectAccessoryParams: String?,
    override val inflowLocation: String?,
) : EditorParams