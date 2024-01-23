package com.imaan.common.model


@JvmInline
value class Country(val value: String){
    companion object {
        val Empty = Country("")
    }
}