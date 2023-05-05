package com.kg.love_calculator_beta.preference

import android.content.Context
import android.content.SharedPreferences

class PrefHelper(context: Context) {
    private var pref: SharedPreferences? =
        context.getSharedPreferences("setting", Context.MODE_PRIVATE)

    fun isUserSeen(): Boolean {
        return pref!!.getBoolean(BOOLEAN_KEY, false)
    }

    fun saveSeen() {
        pref?.edit()?.putBoolean(PREF_NAME, true)?.apply()
    }

    companion object{
        const val PREF_NAME="Салю"
        const val BOOLEAN_KEY="ключ"
    }
}