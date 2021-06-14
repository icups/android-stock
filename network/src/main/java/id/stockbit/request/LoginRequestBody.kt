package id.stockbit.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequestBody(val email: String = "", val password: String = "") : Parcelable