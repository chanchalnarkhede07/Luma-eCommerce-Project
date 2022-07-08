package com.born.ecommerce.data.pref

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.WorkerThread
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface PreferenceStorage {
    var userToken: String

    fun clearAllPreference(context: Context)
}

@Singleton
class SharedPreferenceStorage @Inject constructor(
    context: Context
) : PreferenceStorage {
    
    val prefs: Lazy<SharedPreferences> = lazy {
        context.applicationContext.getSharedPreferences(
            "Ecom_Pref", Context.MODE_PRIVATE
        )
    }


    private fun SharedPreferences.copyTo(dest: Lazy<SharedPreferences>) {
        for (entry in all.entries) {
            val key = entry.key
            val value: Any? = entry.value
            set(dest, key, value)
        }
    }

    override var userToken by StringPreference(
        prefs, PREF_USER_TOKEN, ""
    )


    override fun clearAllPreference(context: Context) {
        try {
            prefs.value.edit().clear().apply()
        }catch (e:IllegalArgumentException){
            e.printStackTrace()
        }
    }

    companion object {
        const val PREF_USER_TOKEN = "pref_user_token"
    }
}


class StringPreference(
    private val preferences: Lazy<SharedPreferences>,
    private val name: String,
    private val defaultValue: String
) : ReadWriteProperty<Any, String?> {

    @WorkerThread
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return preferences.value.getString(name, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
        preferences.value.edit { putString(name, value) }
    }
}

fun set(preferences: Lazy<SharedPreferences>, key: String, value: Any?) {
    when (value) {
        is String? -> {
            preferences.value.edit().putString(key,value).apply()
        }
        is Int -> {
            preferences.value.edit().putInt(key,value).apply()
        }
        is Boolean -> {
            preferences.value.edit().putBoolean(key,value).apply()
        }
        else -> {

        }
    }
}
