package com.imaan.design_system.components.views

import android.text.Html
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.android.material.textview.MaterialTextView
import com.imaan.common.model.HtmlText


@Composable
fun HtmlContentView(
    modifier: Modifier,
    html: HtmlText
) {
    val spannedText = HtmlCompat.fromHtml(
        html.toString(),
        0
    )
    AndroidView(
        modifier = modifier,
        factory = { MaterialTextView(it) },
        update = {
            it.text = spannedText
            //  it.setTextColor(androidx.constraintlayout.widget.R.color.error_color_material_dark)
        }
    )
}