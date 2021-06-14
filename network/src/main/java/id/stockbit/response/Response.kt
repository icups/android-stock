package id.stockbit.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Response<T>(
    val token: String = "",
    val message: String = "",
    @SerializedName("data") val result: T
) : Serializable