package id.stockbit.app.binding

import android.util.TypedValue
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import id.stockbit.ext.property.orDefault
import id.stockbit.ext.view.gone
import id.stockbit.ext.view.visible
import id.stockbit.ext.widget.applyStrikeThrough
import id.stockbit.ext.widget.textColor

object TextBinding {

    @JvmStatic
    @BindingAdapter("bind:Text")
    fun bindText(textView: TextView, data: String?) {
        if (data.isNullOrEmpty()) textView.gone()
        else {
            textView.apply {
                text = data
                visible(false)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("bind:textColor")
    fun bindTextColor(textView: TextView, hexCode: String? = "#2c3e50") {
        if (hexCode == null) textView.textColor("#808080")
        else textView.textColor(hexCode)
    }

    @JvmStatic
    @BindingAdapter("bind:TextSize")
    fun bindTextSize(textView: TextView, size: Float = 14f) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
    }

    @JvmStatic
    @BindingAdapter("app:changePercentageColor")
    fun setChangePercentageColor(textView: TextView, data: String? = null) {
        textView.textColor(
            when {
                data?.contains("+").orDefault() -> "#01ab6c"
                data?.contains("-").orDefault() -> "#ff6b6b"
                else -> "#808080"
            }
        )
    }

    @JvmStatic
    @BindingAdapter("app:enableStrikeThrough")
    fun setStrikeThrough(textView: TextView, enabled: Boolean = false) {
        if (enabled) textView.applyStrikeThrough()
    }

    @JvmStatic
    @BindingAdapter("app:htmlText")
    fun setHtmlText(textView: TextView, data: String?) {
        textView.text = data?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
    }

}