package id.stockbit.ext.editable

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.*

fun EditText.phoneNumberFormatterListener(): TextWatcher {
    var before = ""
    var beforeCount = 0

    return object : TextWatcher {
        override fun beforeTextChanged(text: CharSequence?, start: Int, end: Int, count: Int) {
            before = (text ?: "").toString()
            beforeCount = before.replace("-", "").length
        }

        @SuppressLint("SetTextI18n")
        override fun onTextChanged(text: CharSequence?, start: Int, end: Int, count: Int) {
            removeTextChangedListener(this)

            val data = (text ?: "").toString()
            if (data == "0" && count == 1) setText("")

            try {
                val afterCount = data.replace("-", "").length
                val last = data.takeLast(1)
                if (beforeCount > afterCount) {
                    if (last == "-") setText(data.dropLast(1))
                } else {
                    if (afterCount % 4 == 0 && afterCount > 1 && afterCount < 9) setText("$before-$last")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            addTextChangedListener(this)
        }

        override fun afterTextChanged(editable: Editable?) {
            try {
                setSelection(text.length)
            } catch (e: Exception) {
                setSelection(text.length - 1)
            }
        }
    }
}

fun EditText.nominalFormatterListener(afterTextChanged: (data: String, num: Int) -> Unit): TextWatcher {
    return object : TextWatcher {
        override fun beforeTextChanged(text: CharSequence?, start: Int, end: Int, count: Int) {}

        @SuppressLint("SetTextI18n")
        override fun onTextChanged(text: CharSequence?, start: Int, end: Int, count: Int) {
            removeTextChangedListener(this)

            val data = (text ?: "").toString()
            if (data == "0" && count == 1) setText("")
            else try {
                val number = Integer.valueOf(text.toString().replace(".", ""))
                setText(NumberFormat.getNumberInstance(Locale.US).format(number).replace(",", "."))
            } catch (e: Exception) {
                e.printStackTrace()
            }

            addTextChangedListener(this)
        }

        override fun afterTextChanged(editable: Editable?) {
            val data = editable.toString().replace(".", "")
            val number = try {
                Integer.parseInt(data)
            } catch (e: Exception) {
                0
            }
            afterTextChanged(data, number)

            try {
                setSelection(text.length)
            } catch (e: Exception) {
                setSelection(text.length - 1)
            }
        }
    }
}