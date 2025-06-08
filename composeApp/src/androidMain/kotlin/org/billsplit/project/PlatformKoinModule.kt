package org.billsplit.project

import android.preference.PreferenceManager
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun platformKoinModule() = module {
    single<Settings> {
        val delegate = PreferenceManager.getDefaultSharedPreferences(androidApplication())
        SharedPreferencesSettings(delegate)
    }
}
