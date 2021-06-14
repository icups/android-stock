package id.stockbit.ext.image

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.stockbit.app.R
import id.stockbit.ext.resource.getDrawableResource

fun ImageView.loadImage(url: String?) {
    if (url != null && url.isEmpty()) return
    Glide.with(this)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .skipMemoryCache(true)
        .placeholder(R.color.colorTransparent)
        .error(R.color.colorTransparent)
        .into(this)
}

fun ImageView.loadImage(@DrawableRes resId: Int?) {
    if (resId == null) return
    Glide.with(this)
        .load(context.getDrawableResource(resId))
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .placeholder(R.color.colorTransparent)
        .error(R.color.colorTransparent)
        .into(this)
}