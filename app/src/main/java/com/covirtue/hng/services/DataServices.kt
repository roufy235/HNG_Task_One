package com.covirtue.hng.services

import android.content.Context
import android.content.SharedPreferences
import com.covirtue.hng.models.LoginModel
import com.google.gson.Gson

object DataServices {

    private const val prefsName = "hng_pref"
    private val gson = Gson()
    private lateinit var prefs: SharedPreferences
    var userDataGlobal: LoginModel? = null
    var isLogin: Boolean = false

    fun saveLoginDetails(context: Context, isLogin: Boolean): Boolean {
        prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        return if (userDataGlobal != null) {
            val userData = gson.toJson(userDataGlobal).toString()
            prefs.edit().apply {
                putString("data", userData)
                putBoolean("isLogin", isLogin)
                apply()
            }
            true
        } else {
            false
        }
    }

    fun init(context: Context) {
        prefs = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
        val sharedData = prefs.getString("data", "")
        sharedData?.let {
            if (it.isNotEmpty()) {
                userDataGlobal = gson.fromJson(it, LoginModel::class.java)
            }
        }
        isLogin = prefs.getBoolean("isLogin", false)
    }
}