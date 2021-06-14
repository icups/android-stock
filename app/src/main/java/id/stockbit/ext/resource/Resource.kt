package id.stockbit.ext.resource

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import id.stockbit.app.R

@ColorInt
fun Context.getColorResource(@ColorRes resId: Int): Int {
    return ContextCompat.getColor(this, resId)
}

@ColorInt
fun Fragment.getColorResource(@ColorRes resId: Int): Int {
    return requireContext().getColorResource(resId)
}

fun Context.getDrawableResource(@DrawableRes resId: Int): Drawable? {
    return ContextCompat.getDrawable(this, resId)
}

fun Fragment.getDrawableResource(@DrawableRes resId: Int): Drawable? {
    return requireContext().getDrawableResource(resId)
}

fun Context?.getResId(resName: String?): Int {
    if (this == null) return R.mipmap.ic_launcher
    return try {
        resources.getIdentifier(resName.orEmpty(), "drawable", packageName)
    } catch (e: Exception) {
        return R.mipmap.ic_launcher
    }
}