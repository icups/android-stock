package id.stockbit.ext.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment

fun Activity.share(url: String? = null) {
    if (url.isNullOrEmpty()) return
    ShareCompat.IntentBuilder
        .from(this)
        .setType("text/plain")
        .setChooserTitle("Share Link")
        .setText(url)
        .startChooser()
}

fun Fragment.share(url: String? = null) {
    if (url.isNullOrEmpty()) return
    ShareCompat.IntentBuilder
        .from(requireActivity())
        .setType("text/plain")
        .setChooserTitle("Share Link")
        .setText(url)
        .startChooser()
}

fun Activity.goPlayStore(packageName: String) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
}