package id.stockbit.ext.runner

import android.text.format.DateUtils

private var lastActionMillis = System.currentTimeMillis()

fun Any?.runOnce(block: () -> Unit) {
    if (null == this) return
    val diff = DateUtils.SECOND_IN_MILLIS
    val currentMillis = System.currentTimeMillis()
    if ((lastActionMillis + diff) < currentMillis) {
        block.invoke()
        lastActionMillis = currentMillis
    }
}