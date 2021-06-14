package id.stockbit.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RegisterRequestBody(
    val fullName: String = "",
    val phoneNumber: String = "",
    val address: String = "",
    val email: String = "",
    val dateOfBirth: String = "",
    val password: String = "",
    @SerializedName("NIK") val nik: String? = null,
    @SerializedName("NIP") val nip: String? = null,
    val referralCode: String? = null
) : Parcelable