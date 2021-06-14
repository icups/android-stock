package id.stockbit.ext.validator

import android.text.TextUtils
import android.util.Patterns

fun String?.isValidPhoneNumber(): Boolean {
    return !TextUtils.isEmpty(this.orEmpty()) && Patterns.PHONE.matcher(this.orEmpty()).matches()
}