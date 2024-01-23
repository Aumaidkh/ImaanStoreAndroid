package com.imaan.common.model

@JvmInline
value class Stocks(val value: Int) {
    init {
        require(value >= 0)
    }
}