package id.stockbit.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserProfile(
    @SerializedName("_id") val id: String,
    @SerializedName("Email") val email: String,
    @SerializedName("FirstName") val firstName: String,
    @SerializedName("MiddleName") val lastName: String,
    @SerializedName("PhoneNumber") val PhoneNumber: String,
    @SerializedName("NIK") val nik: String,
    @SerializedName("IsPremium") val isPremium: Boolean
) : Parcelable