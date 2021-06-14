package id.stockbit.app.ui.main.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.stockbit.app.base.BaseViewModel
import id.stockbit.app.repository.MainRepository
import id.stockbit.constant.Page
import id.stockbit.model.State
import id.stockbit.response.CoinResponse
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class WatchlistViewModel(private val repository: MainRepository) : BaseViewModel() {

    private val mTopCoins = MutableLiveData<State<List<CoinResponse>>>()
    val topCoins: LiveData<State<List<CoinResponse>>> = mTopCoins

    fun fetchTopCoins(page: Int = Page.FIRST) {
        viewModelScope.launch {
            mUiMode.postValue(UiMode.ON_PROGRESS)
            try {
                mTopCoins.postValue(State.Loading)
                repository.topCoins(page).run {
                    mTopCoins.postValue(State.Success(result))
                    mUiMode.postValue(UiMode.SUCCESS)
                }
            } catch (e: Exception) {
                mTopCoins.postValue(
                    when (e) {
                        is UnknownHostException -> State.Failure("Please check your internet connection and try again.")
                        else -> State.Failure(e.message)
                    }
                )
                mUiMode.postValue(UiMode.ERROR)
                e.printStackTrace()
            }
        }
    }

}