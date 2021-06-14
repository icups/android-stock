package id.stockbit.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import id.stockbit.model.Crypto
import id.stockbit.model.Display
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoinResponse(
    @SerializedName("Id") val id: String? = null,
    @SerializedName("CoinInfo") val crypto: Crypto? = null,
    @SerializedName("DISPLAY") val display: Display? = null
) : Parcelable