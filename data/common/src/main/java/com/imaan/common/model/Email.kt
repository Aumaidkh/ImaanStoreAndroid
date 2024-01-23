package com.imaan.common.model

@JvmInline
value class Email(val value: String){
    init {
        require(value.length > 1)
    }
}