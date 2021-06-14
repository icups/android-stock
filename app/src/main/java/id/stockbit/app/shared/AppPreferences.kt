package id.stockbit.app.shared

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import id.stockbit.app.BuildConfig
import id.stockbit.ext.fromJson
import id.stockbit.ext.toJson
import id.stockbit.model.Language

class AppPreferences(val context: Context) {

    companion object {
        const val TOKEN_ACCESS = "token_access"
        const val TOKEN_FIREBASE = "token_firebase"
        const val TOOLTIP_SWIPE = "tooltip_swipe"
        const val SETTING_LANGUAGE = "setting_language"
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    var accessToken: String
        get() = findPreference(TOKEN_ACCESS, "")
        set(value) {
            putPreference(TOKEN_ACCESS, value)
        }

    var fireBaseToken: String
        get() = findPreference(TOKEN_FIREBASE, "")
        set(value) {
            putPreference(TOKEN_FIREBASE, value)
        }

    var languageSetting: String
        get() = findPreference(SETTING_LANGUAGE, "")
        set(value) {
            putPreference(SETTING_LANGUAGE, value)
        }

    var tooltipSwipeSeen: Boolean
        get() = findPreference(TOOLTIP_SWIPE, false)
        set(value) {
            putPreference(TOOLTIP_SWIPE, value)
        }

    fun requireLanguageSetting(): Language {
        return if (languageSetting.isEmpty()) return Language(2, "en", "English", true) else languageSetting.fromJson()
    }

    fun saveLanguageSetting(data: Language?) {
        languageSetting = data?.toJson().orEmpty()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> findPreference(name: String, default: T?): T = with(prefs) {
        val res: Any? = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException("Type is unknown")
        }
        res as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun <T> putPreference(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can't be saved into Preferences")
        }.apply()
    }


}