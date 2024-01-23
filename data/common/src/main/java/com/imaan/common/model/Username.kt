package com.imaan.common.model

@JvmInline
value class Username(val value: String){
    init {
        require(value.length > 1)
    }
}