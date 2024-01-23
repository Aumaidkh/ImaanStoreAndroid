package com.imaan.common.model


@JvmInline
value class FullAddress(val value: String){
    companion object{
        val Empty = FullAddress("")
    }
}