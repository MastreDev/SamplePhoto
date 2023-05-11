package kr.marko.photobook.presentation.protocol

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EditorParams(
    val projectCode: String?
) : Parcelable