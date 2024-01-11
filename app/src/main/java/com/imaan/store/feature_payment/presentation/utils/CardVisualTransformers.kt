package com.imaan.store.feature_payment.presentation.utils


import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

private const val TAG = "CardExpiryVisualTransfo"
class CardExpiryVisualTransformer: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i == 1) out += "/"
        }
        return TransformedText(
            text = AnnotatedString(out),
            object : OffsetMapping{
                override fun originalToTransformed(offset: Int): Int {
                    return when {
                        offset <= 1 -> offset//1 -> 1, 12 -> 12
                        offset <= 4 -> offset + 1//123 -> 12/3,1234 -> 12/34
                        else -> 5
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return when {
                        offset <= 2 -> offset
                        offset <= 5 -> offset - 1
                        else -> 4
                    }
                }
            }
        )
    }

}

class CardNumberVisualTransformer: VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        // 1234567890123456
        // 1234-5678-9012-3456
        val trimmed = if (text.text.length>=16) text.text.substring(0..15) else text.text
        var out = ""
        for (i in trimmed.indices){
            out += trimmed[i]
            if (i % 4 == 3) out += "-"
        }

        return TransformedText(
            text = AnnotatedString(out),
            object : OffsetMapping{
                override fun originalToTransformed(offset: Int): Int {
                    if (offset <= 3) return offset
                    if (offset <= 7) return offset + 1
                    if (offset <= 11) return offset + 2
                    if (offset <= 15) return offset + 3
                    else return 19
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset <= 4) return offset
                    if (offset <= 9) return offset - 1
                    if (offset <= 14) return offset - 2
                    if (offset <= 19) return offset - 3
                    else return 16
                }
            }
        )
    }
}
