package com.imaan.common.model


@JvmInline
value class PinCode(val value: String) {
    companion object {
        val Empty = PinCode("")
    }
}
