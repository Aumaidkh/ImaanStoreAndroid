package com.imaan.store.feature_payment.presentation.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation: VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        //1234567890
        //+91-788-953-4384
        val trimmed = if (text.text.length>=10) text.text.substring(0..9) else text.text
        var out = "+91-"
        for (i in trimmed.indices){
            out += trimmed[i]
            if (i==2||i==5) out += "-"
        }

        return TransformedText(
            text = AnnotatedString(out),
            object : OffsetMapping{
                override fun originalToTransformed(offset: Int): Int {
                    if (offset==0) return offset + 4
                    if (offset<=2) return offset + 5
                    if (offset<=5) return offset + 6
                    else return 16
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset==0) return offset - 4
                    if (offset<=3) return offset - 5
                    if (offset<=7) return offset - 6
                    return 10
                }
            }
        )
    }
}