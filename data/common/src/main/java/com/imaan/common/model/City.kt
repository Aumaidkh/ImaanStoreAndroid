package com.imaan.common.model


@JvmInline
value class City(val value: String) {
    companion object {
        val Empty = City("")
    }
}
