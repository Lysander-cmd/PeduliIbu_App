package com.example.peduliibu_app.Authenticate

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor = prefs.edit()

    companion object {
        const val PREF_NAME = "LoginPrefs"
        const val IS_LOGIN = "IsLoggedIn"
        const val KEY_EMAIL = "email"
    }

    fun createLoginSession(email: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_EMAIL, email)
        editor.commit()
    }

    fun isLoggedIn(): Boolean {
        return prefs.getBoolean(IS_LOGIN, false)
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }

    fun getUserEmail(): String? {
        return prefs.getString(KEY_EMAIL, null)
    }
}