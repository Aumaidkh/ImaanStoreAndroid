package com.imaan.common.model

@JvmInline
value class Title(val value: String){
    init {
        require(value.length>2)
    }
}