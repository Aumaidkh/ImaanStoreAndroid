package com.imaan.common.model


@JvmInline
value class State(val value: String) {
    companion object {
        val Empty = State("")
    }
}
