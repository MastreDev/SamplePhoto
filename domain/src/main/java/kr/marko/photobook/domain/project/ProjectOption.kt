package kr.marko.photobook.domain.project

class ProjectOption(
    val projectCode: String
) {
    interface Params {
        val deviceId: String
        val userNo: String
        val productCode: String
        val templateCode: String
        val glossyType: String?
        val paperCode: String
        val quantity: Int
        val calendarStartDate: String
        val calendarEndDate: String
        val sizeQuantitys: String?
        val frameCode: String?
        val frameType: String?
        val colorCode: String?
        val backType: String?
        val sizeCode: String?
        val projectAccessoryParams: String?
        val inflowLocation: String?
    }

}