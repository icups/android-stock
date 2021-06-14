package id.stockbit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class Currency(
    @SerializedName("PRICE") val price: String? = null,
    @SerializedName("CHANGEHOUR") val hour: String? = null,
    @SerializedName("CHANGEPCTHOUR") val pctHour: String? = null
) : Parcelable {

    private fun requirePercentage(): Double = pctHour?.toDoubleOrNull() ?: 0.0
    private fun requirePrefix(): String = if (requirePercentage() > 0) "+" else ""

    fun formattedChangeHour(): String {
        return "${requirePrefix()}${hour?.replace("$ ", "")}"
    }

    fun formattedPctHour(): String {
        return "${requirePrefix()}$pctHour"
    }

}