package com.example.technopa.main.repo

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.technopa.R
import java.util.*

class MainRepo(application: Application) {

    private val APP_PREFERENCES = R.string.APP_PREFERENCES.toString()
    private val APP_PREFERENCES_STEPS = R.string.APP_PREFERENCES_STEPS.toString()
    private val APP_PREFERENCES_DNS_STEPS = "dayNormaSteps"
    private val APP_PREFERENCES_DATE = "date"
    private val APP_PREFERENCES_AVHIEVMENTS ="achievments"


    private val mSettings: SharedPreferences =
        application.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)
    private val date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString()


    fun saveSteps(steps: Int) {
        mSettings.edit()?.putInt(APP_PREFERENCES_STEPS, steps)?.apply()
    }

    fun saveDNS(dns: Int) {
        mSettings.edit()?.putInt(APP_PREFERENCES_DNS_STEPS, dns)?.apply()
    }

    // Возвращает шаги из памяти
    fun getSteps(
        callback: (Int) -> Unit
    ) {
        callback(mSettings.getInt(APP_PREFERENCES_STEPS, 0))
    }

    // Возвращает дневную норму шагов из памяти
    fun getDNS(
        callback: (Int) -> Unit
    ) {
        callback(mSettings.getInt(APP_PREFERENCES_DNS_STEPS, 0))
    }

    // Возвращает совпадает ли дата сегодня с датой из памяти
    fun checkDate(
        callback: (Boolean) -> Unit
    ) {

        if (mSettings.contains(APP_PREFERENCES_DATE)) {
            if (date != mSettings.getString(APP_PREFERENCES_DATE, "")) {
                saveSteps(0)
                mSettings.edit().putString(APP_PREFERENCES_DATE, date).apply()
                callback(false)
            } else {
                callback(true)
            }
        } else {
            mSettings.edit().putString(APP_PREFERENCES_DATE, date).apply()
            saveSteps(0)
            callback(false)
        }
    }


}