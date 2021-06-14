package id.stockbit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crypto(
    @SerializedName("Name") val name: String? = null,
    @SerializedName("FullName") val fullName: String? = null
) : Parcelable