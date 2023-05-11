package kr.marko.photobook.impl

import kotlinx.parcelize.Parcelize
import kr.marko.photobook.presentation.protocol.EditorParams

@Parcelize
data class CartProductOptions(
    override val projectCode: String?
) : EditorParams