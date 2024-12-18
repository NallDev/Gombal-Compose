package com.nalldev.gombal.presentation.screens.detail.components

import android.widget.TextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.nalldev.gombal.R

@Composable
fun HtmlText(text: String, isDarkMode: Boolean) {
    val textColor = if (isDarkMode) R.color.colorOnBackgroundNight else R.color.colorOnBackground
    AndroidView(factory = { context ->
        TextView(context).apply {
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
            setTextColor(ContextCompat.getColor(context, textColor))
        }
    })
}