package id.stockbit.ext.bitmap

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream

fun Bitmap.toByteArray(): ByteArray {
    return ByteArrayOutputStream().apply {
        compress(Bitmap.CompressFormat.JPEG, 70, this)
    }.toByteArray()
}