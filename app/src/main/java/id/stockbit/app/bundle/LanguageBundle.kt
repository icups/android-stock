package id.stockbit.app.bundle

import android.content.Context
import id.stockbit.app.R
import id.stockbit.ext.jsonToClass
import id.stockbit.model.Language

class LanguageBundle {

    companion object {
        fun get(context: Context): List<Language> {
            return context.jsonToClass(R.raw.language_setting)
        }
    }

}