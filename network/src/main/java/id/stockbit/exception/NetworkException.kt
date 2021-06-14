package id.stockbit.exception

import id.stockbit.response.ErrorResponse
import java.io.IOException

class NetworkException(private val error: ErrorResponse?) : IOException() {

    override val message: String?
        get() = error?.error

}