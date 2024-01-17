package com.imaan.common.model

@JvmInline
value class Description(val value: String){
    init {
        require(value.length>=10)
    }
}