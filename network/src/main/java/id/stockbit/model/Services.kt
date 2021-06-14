package id.stockbit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Services(
    val code: String = "",
    val name: String = "",
    val res: String = "",
) : Parcelable