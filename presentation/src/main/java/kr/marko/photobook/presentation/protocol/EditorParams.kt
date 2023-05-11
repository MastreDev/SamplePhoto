package kr.marko.photobook.presentation.protocol

import android.os.Parcelable

interface EditorParams : Parcelable {
    val projectCode: String?
}