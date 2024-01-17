package com.imaan.common.model


@JvmInline
value class Amount(val value: Double){
    init {
        require(value >= 0)
    }

    val inRupees get() = "₹$value"

    infix fun multiply (value: Int): Amount{
        return Amount(value * this.value)
    }

    infix operator fun plus(amount: Amount): Amount {
        return fromDouble(this.value + amount.value)
    }
    inline fun <T> Iterable<T>.sumOf(selector: (T) -> Long): Long {
        var sum: Long = 0.toLong()
        for (element in this) {
            sum += selector(element)
        }
        return sum
    }
    companion object {
        val ZERO = Amount(0.0)

        fun fromDouble(value: Double): Amount {
            return Amount(value)
        }
    }

}

infix fun Double.plus(amount: Amount): Amount {
    return Amount(value = amount.value + this)
}

inline fun <T> Iterable<T>.sumOfAmounts(selector: (T) -> Amount): Amount {
    var sum: Amount = Amount.ZERO
    for (element in this) {
        sum += selector(element)
    }
    return sum
}