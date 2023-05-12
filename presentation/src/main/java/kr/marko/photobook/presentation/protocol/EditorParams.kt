package kr.marko.photobook.presentation.protocol

import android.os.Parcelable
import kr.marko.photobook.domain.project.ProjectOption

interface EditorParams : Parcelable, ProjectOption.Params {
    val projectCode: String?
    val productCode: String
    val templateCode: String
}