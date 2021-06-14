package id.stockbit.app.binding

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import id.stockbit.ext.image.loadImage
import id.stockbit.ext.resource.getResId

object ImageBinding {

    @JvmStatic
    @BindingAdapter("bind:loadBitmap")
    fun loadBitmap(view: ImageView?, bitmap: Bitmap?) {
        bitmap?.run { view?.setImageBitmap(this) }
    }

    @JvmStatic
    @BindingAdapter("bind:loadImage")
    fun loadImageFromUrl(view: ImageView?, url: String?) {
        view?.loadImage(url)
    }

    @JvmStatic
    @BindingAdapter("bind:loadResource")
    fun loadImageResource(view: ImageView?, resName: String?) {
        if (resName == null || view == null) return
        view.run { loadImage(context.getResId(resName)) }
    }

}