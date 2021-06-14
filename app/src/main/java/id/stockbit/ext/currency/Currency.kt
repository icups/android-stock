package id.stockbit.ext.currency

import id.stockbit.ext.property.orZero
import java.text.NumberFormat
import java.util.*

fun Int?.toRupiah(): String {
    return try {
        "Rp ${NumberFormat.getNumberInstance(Locale.US).format(orZero()).replace(",", ".")}"
    } catch (e: Exception) {
        "Rp 0"
    }
}