package id.stockbit.app.ui.splash

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import id.stockbit.app.shared.AppPreferences
import id.stockbit.app.ui.login.LoginActivity
import id.stockbit.app.ui.main.MainActivity
import id.stockbit.ext.common.launchDelayedFunction
import java.util.*

class SplashActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Activity) {
            val starter = Intent(context, SplashActivity::class.java)
            context.startActivity(starter)
            context.finishAffinity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeLanguageSetting()
        initializeSplash()
    }

    private fun initializeSplash() {
        launchDelayedFunction(1000) {
            LoginActivity.start(this@SplashActivity)
            finish()
        }
    }

    @Suppress("DEPRECATION")
    private fun initializeLanguageSetting() {
        val appPreferences = AppPreferences(this)
        val locale = Locale(appPreferences.requireLanguageSetting().code)

        val res: Resources = resources
        val config: Configuration = res.configuration
        val metrics: DisplayMetrics = res.displayMetrics

        config.setLocale(locale)
        res.updateConfiguration(config, metrics)
    }

}