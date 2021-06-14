package id.stockbit.ext.widget

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.widget.TextView

fun TextView?.textColor(hexCode: String) {
    this?.run { setTextColor(Color.parseColor(hexCode)) }
}

fun TextView?.backgroundColor(hexCode: String) {
    this?.let { setBackgroundColor(Color.parseColor(hexCode)) }
}

fun TextView.applyNormal() {
    typeface = Typeface.DEFAULT
}

fun TextView.applyBold() {
    setTypeface(null, Typeface.BOLD)
}

fun TextView.applyItalic() {
    setTypeface(null, Typeface.ITALIC)
}

fun TextView.applyBoldItalic() {
    setTypeface(null, Typeface.BOLD_ITALIC)
}

fun TextView.applyStrikeThrough() {
    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}