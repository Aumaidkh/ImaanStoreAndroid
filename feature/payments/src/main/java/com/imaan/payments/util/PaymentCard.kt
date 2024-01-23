package com.imaan.payments.util

import androidx.compose.ui.graphics.Color


val dummyCard = DebitCard(
    "",
    CardType.Visa,
    "",
    "",
    "",
)

data class DebitCard(
    val name: String,
    val type: CardType,
    val cardNo: String,
    val cvv: String,
    val expiry: String
    ) {
    val cardHolderName get() = name.uppercase()
    val formattedCardNumber
        get() = run {
            val digitsOnly = cardNo.toString().filter { it.isDigit() }
            val chunks = mutableListOf<String>()
            var index = 0
            while (index < digitsOnly.length) {
                val endIndex = minOf(index + 4, digitsOnly.length)
                chunks.add(digitsOnly.substring(index, endIndex))
                index += 4
            }

            chunks.joinToString(separator = " ")
        }

    val formattedExpiry
        get() = run {
            when {
                this.expiry.length <= 2 -> this.expiry
                this.expiry.length == 3 -> {
                    val formattedValue = "${this.expiry.substring(0, 2)}/${this.expiry.substring(2)}"
                    formattedValue.removeSuffix("/")
                }
                else -> {
                    val formattedValue = "${this.expiry.substring(0, 2)}/${this.expiry.substring(2, 4)}"
                    formattedValue.removeSuffix("/")
                }
            }
        }

    val secureCvv
        get() = run {
            "*".repeat(cvv.length)
        }
}

sealed interface CardType {
    object MasterCard : CardType {
        override fun cardName(): String {
            return "Master Card"
        }

        override fun iconResId(): Int {
            return com.imaan.design_system.R.drawable.mastercard
        }

        override fun backgroundColor(): Color {
            return Color(0xff874AEA)
        }
    }

    object Visa : CardType {
        override fun cardName(): String {
            return "Visa"
        }

        override fun iconResId(): Int {
            return com.imaan.design_system.R.drawable.visa
        }

        override fun backgroundColor(): Color {
            return Color(0xffE58E0B)
        }
    }

    object RuPay : CardType {
        override fun cardName(): String {
            return "RuPay"
        }

        override fun iconResId(): Int {
            return com.imaan.design_system.R.drawable.rupay
        }

        override fun backgroundColor(): Color {
            return Color(0xff18A437)
        }
    }

    fun cardName(): String

    fun iconResId(): Int

    fun backgroundColor(): Color
}