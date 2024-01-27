package com.imaan.common.model

@JvmInline
value class Description(val value: String){
    init {
        require(value.length>=10)
    }

    fun toShortDescription(): String {
        return if (value.length > 20) value.substring(0,19) else value
    }
}