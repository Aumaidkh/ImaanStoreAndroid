package com.imaan.util

import androidx.compose.runtime.Composable

@Composable
fun <T> List<T>.IfNotEmpty(
    default: @Composable (List<T>) -> Unit
){
    if (this.isEmpty()){
        return
    }
    default(this)
}