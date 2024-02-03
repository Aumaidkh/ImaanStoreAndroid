package com.imaan.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb


fun String.toColor() = Color(android.graphics.Color.parseColor(this))

fun Color.toHex(): String {
    return String.format("#%06X", 0xFFFFFF and this.toArgb())
}