package id.stockbit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Display(
    @SerializedName("USD") val currency: Currency? = null
) : Parcelable