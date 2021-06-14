package id.stockbit.model

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Success<out T>(val data: T) : State<T>()
    data class Failure(val message: String?) : State<Nothing>()
}