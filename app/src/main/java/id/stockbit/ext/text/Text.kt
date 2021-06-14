package id.stockbit.ext.text

import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

fun TextView.getValue(): String {
    return text.toString()
}

fun EditText.getValue(): String {
    return text.toString()
}

fun Spinner.getValue(): String {
    return selectedItem.toString()
}