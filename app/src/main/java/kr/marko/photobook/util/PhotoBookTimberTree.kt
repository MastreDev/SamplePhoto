package kr.marko.photobook.util

import timber.log.Timber

class PhotoBookTimberTree : Timber.DebugTree() {

//    override fun createStackElementTag(element: StackTraceElement): String {
//        return makeClickableLineNumber(element)
//    }
//
//    private fun makeClickableLineNumber(element: StackTraceElement): String {
//        val className = element.fileName
//        val methodName = element.methodName
//        val lineNumber = element.lineNumber
//        val fileName = element.fileName
//        val stringBuilder = StringBuilder(className)
//            .append(".")
//            .append(methodName)
//            .append(" (")
//            .append(fileName)
//            .append(":")
//            .append(lineNumber)
//            .append(")  ")
//        return stringBuilder.toString()
//    }

}