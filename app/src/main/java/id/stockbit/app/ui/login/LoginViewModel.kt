package id.stockbit.app.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import id.stockbit.app.base.BaseViewModel
import id.stockbit.model.State
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    enum class UiRequest { LOG_IN, BIOMETRIC, REGISTER, FORGOT_PASSWORD }
    data class Parcel(val view: View? = null)

    private val mUiRequest = MutableLiveData<Pair<UiRequest, Parcel>>()
    val uiRequest: LiveData<Pair<UiRequest, Parcel>> = mUiRequest

    private val mUserLogin = MutableLiveData<State<String>>()
    val userLogin: LiveData<State<String>> = mUserLogin

    val textEmail = MutableLiveData<String>()
    fun requireMailAddress(): String = textEmail.value.orEmpty()

    val textPassword = MutableLiveData<String>()
    fun requirePassword(): String = textPassword.value.orEmpty()

    fun login() {
        viewModelScope.launch {
            mUiMode.postValue(UiMode.ON_PROGRESS)
            delay(2000)
            mUserLogin.postValue(State.Success("Login success!"))
            mUiMode.postValue(UiMode.SUCCESS)
        }
    }

    fun clickLogin() {
        mUiRequest.postValue(UiRequest.LOG_IN to Parcel())
    }

    fun clickBiometric() {
        mUiRequest.postValue(UiRequest.BIOMETRIC to Parcel())
    }

    fun clickRegister() {
        mUiRequest.postValue(UiRequest.REGISTER to Parcel())
    }

    fun clickForgotPassword() {
        mUiRequest.postValue(UiRequest.FORGOT_PASSWORD to Parcel())
    }

}