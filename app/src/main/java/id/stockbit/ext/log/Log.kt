package id.stockbit.ext.log

import android.util.Log
import id.stockbit.app.BuildConfig

fun logException(e: Exception) {
    if (BuildConfig.DEBUG) Log.e("Exception", "Result > ${e.message}")
    e.printStackTrace()
}

fun logError(msg: String?) {
    if (BuildConfig.DEBUG) Log.e("Error", "Result > $msg")
}

fun logSuccess(msg: String?) {
    if (BuildConfig.DEBUG) Log.i("Success", "Result > $msg")
}

fun logInfo(msg: String?) {
    if (BuildConfig.DEBUG) Log.i("Info", "Result > $msg")
}