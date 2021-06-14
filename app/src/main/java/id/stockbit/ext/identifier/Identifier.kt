package id.stockbit.ext.identifier

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.provider.Settings.Secure
import androidx.fragment.app.Fragment
import java.util.*


fun Fragment.getDeviceID(): String = context?.getDeviceID().orEmpty()
fun Fragment.getAppVersion(): String = context?.getAppVersion() ?: "1.0.0"

@SuppressLint("HardwareIds")
fun Context.getDeviceID(): String {
    return Secure.getString(this.contentResolver, Secure.ANDROID_ID)
}

fun Context.getAppVersion(): String {
    try {
        packageManager.getPackageInfo(this.packageName, 0).apply {
            return versionName
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }

    return "1.0.0"
}

fun getDeviceName(): String {
    return android.os.Build.MANUFACTURER
}

fun getDeviceVersion(): String {
    return android.os.Build.VERSION.RELEASE
}

fun getDeviceModel(): String {
    return android.os.Build.MODEL
}

fun getTimeZone(): String {
    return TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
}