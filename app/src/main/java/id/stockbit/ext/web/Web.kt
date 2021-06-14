package id.stockbit.ext.web

import android.annotation.SuppressLint
import android.webkit.WebView

@SuppressLint("SetJavaScriptEnabled")
fun WebView.enableJavaScript() {
    settings.apply {
        javaScriptCanOpenWindowsAutomatically = true
        javaScriptEnabled = true
    }
}