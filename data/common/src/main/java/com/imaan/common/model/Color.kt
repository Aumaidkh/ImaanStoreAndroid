package com.imaan.common.model


@JvmInline
value class Color (val hexString: String){

    companion object {
        val White = Color( "#ffffff")
    }
}