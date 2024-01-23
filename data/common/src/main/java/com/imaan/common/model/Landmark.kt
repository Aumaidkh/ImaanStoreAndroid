package com.imaan.common.model

@JvmInline
value class Landmark(val value: String){
    companion object {
        val Empty = Landmark("")
    }
}