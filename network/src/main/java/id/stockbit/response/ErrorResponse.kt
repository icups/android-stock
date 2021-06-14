package id.stockbit.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    val message: String = "",
    val error: String = ""
) : Parcelable