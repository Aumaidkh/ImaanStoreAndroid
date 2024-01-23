package com.imaan.payments.util


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
