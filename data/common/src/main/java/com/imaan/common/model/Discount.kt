package com.imaan.common.model

@JvmInline
value class Discount(val value: Float){
    init {
        require(value >= 0f)
    }

    override fun toString(): String {
        return "${(value * 100).toInt()}%"
    }
}