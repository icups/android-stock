package id.stockbit.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Language(val id: Long = 0, val code: String = "", val label: String = "", val default: Boolean = false) : Parcelable