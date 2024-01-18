package com.imaan.common.model


@JvmInline
value class District(val value: String){
    companion object {
        val Empty = District("")
    }
}