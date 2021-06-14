package id.stockbit.app.ui.login

import android.app.Activity
import android.content.Intent
import android.util.Patterns
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import id.stockbit.app.R
import id.stockbit.app.base.BaseActivity
import id.stockbit.app.constant.App
import id.stockbit.app.databinding.ActivityLoginBinding
import id.stockbit.app.ui.login.LoginViewModel.UiRequest
import id.stockbit.app.ui.main.MainActivity
import id.stockbit.ext.alert.showToast
import id.stockbit.ext.context.canAuthenticate
import id.stockbit.ext.observer.observe
import id.stockbit.model.State

class LoginActivity : BaseActivity<LoginViewModel, ActivityLoginBinding>(LoginViewModel::class.java) {

    companion object {
        @JvmStatic
        fun start(context: Activity) {
            val starter = Intent(context, LoginActivity::class.java)
            context.startActivity(starter)
            context.finishAffinity()
        }
    }

    private val isBiometricAvailable by lazy { canAuthenticate() }

    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    override fun onViewCreated() {
        binding.run {
            lifecycleOwner = this@LoginActivity
            vm = viewModel
            biometricEnabled = isBiometricAvailable
        }

        if (isBiometricAvailable) setupBiometric()
    }

    override fun layoutResources(): Int {
        return R.layout.activity_login
    }

    override fun setupObserver() {
        viewModel.run {
            observe(uiRequest) {
                when (it.first) {
                    UiRequest.FORGOT_PASSWORD -> showToast(App.UnderDevelopment)
                    UiRequest.REGISTER -> showToast(App.UnderDevelopment)
                    UiRequest.LOG_IN -> validate { login() }
                    UiRequest.BIOMETRIC -> biometricPrompt.authenticate(promptInfo)
                }
            }

            observe(userLogin) {
                when (it) {
                    is State.Loading -> hideSoftKeyboard()
                    is State.Success -> MainActivity.start(this@LoginActivity)
                    is State.Failure -> showToast(it.message)
                    else -> return@observe
                }
            }
        }
    }

    private fun setupBiometric() {
        val executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                MainActivity.start(this@LoginActivity)
            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Verify Your Identity")
            .setNegativeButtonText("Cancel")
            .build()
    }

    private inline fun validate(action: () -> Unit) {
        if (validFormData()) action.invoke()
        hideSoftKeyboard()
    }

    private fun validFormData(): Boolean {
        val email = viewModel.requireMailAddress()
        val password = viewModel.requirePassword()

        return if (email.isEmpty() || password.isEmpty()) {
            showToast("Data can't be empty!"); false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showToast("Please input a valid email address"); false
        } else if (password.length < 8) {
            showToast("Please input a valid password"); false
        } else {
            true
        }
    }

}