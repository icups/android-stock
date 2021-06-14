package id.stockbit.ext.widget

import android.graphics.Color
import androidx.cardview.widget.CardView
import id.stockbit.ext.view.visible

fun CardView?.backgroundColor(hexCode: String) {
    this?.run {
        setCardBackgroundColor(Color.parseColor(hexCode))
        visible(false)
    }
}