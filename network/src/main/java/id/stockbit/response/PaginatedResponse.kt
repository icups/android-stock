package id.stockbit.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PaginatedResponse<T>(
    @SerializedName("Message") val message: String = "",
    @SerializedName("Data") val result: List<T> = emptyList()
) : Serializable