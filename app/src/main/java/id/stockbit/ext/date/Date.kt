package id.stockbit.ext.date

import android.app.DatePickerDialog
import android.content.Context
import id.stockbit.app.R
import id.stockbit.ext.property.orOne
import id.stockbit.ext.property.orZero
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

inline fun Context.showDateDialog(crossinline onSelectedDate: (day: String, month: String, year: String) -> Unit) {
    val calendar = Calendar.getInstance()
    val maximumYear = calendar.get(Calendar.YEAR) - 18
    calendar.set(maximumYear, 11, 31)

    DatePickerDialog(this, R.style.AppTheme_Alert_Dialog, { _, year, month, day ->
        val dayOfMonth = "%02d".format(day)
        val monthOfYear = "%02d".format(month + 1)
        onSelectedDate.invoke(dayOfMonth, monthOfYear, "$year")
    }, 1994, 0, 1).also {
        it.datePicker.maxDate = calendar.timeInMillis
        it.show()
    }
}

fun String.convertDatePattern(from: String, to: String): String {
    val dateFormat = SimpleDateFormat(from, Locale.getDefault())

    with(dateFormat) {
        applyPattern(from)
        val date = try {
            parse(this@convertDatePattern)
        } catch (e: Exception) {
            return ""
        }
        applyPattern(to)
        return format(Date(date.time))
    }
}

fun String.timeAgo(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    val dateString = replace("Z", "GMT+07:00")
    val millis = dateFormat.parse(dateString)?.time.orZero()
    val now = Date().time

    return (now - millis).run {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(this)
        val hours = TimeUnit.MILLISECONDS.toHours(this)
        val days = TimeUnit.MILLISECONDS.toDays(this)

        when {
            minutes < 60 -> "${minutes.orOne()}m"
            hours < 24 -> "${hours.orOne()}h"
            days < 7 -> "${days.orOne()}d"
            else -> {
                when {
                    days > 365 -> "${days.div(365).orOne()}y"
                    days > 30 -> "${days.div(30).orOne()}mo"
                    else -> "${days.div(7).orOne()}w"
                }
            }
        }
    }
}