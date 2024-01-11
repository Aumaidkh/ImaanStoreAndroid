package com.imaan.store.core.domain.model

@JvmInline
value class Amount(val value: Double){
    init {
        require(value >= 0)
    }

    val inRupees get() = "₹$value"

    companion object {
        val ZERO = Amount(0.0)

        fun fromDouble(value: Double): Amount {
            return Amount(value)
        }
    }
}
