package com.ab.fordhub

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ab.fordhub.util.Utils
import dagger.hilt.android.HiltAndroidApp
import kotlin.system.exitProcess

@HiltAndroidApp
class FordHubApplication  : Application(){
    override fun onCreate() {
        super.onCreate()
        if(!Utils.isBuildVariantDebug()){
            Thread.setDefaultUncaughtExceptionHandler { _, e ->
//                handleUncaughtException(e)
//                restartApp()
            }
        }
        appContext = applicationContext
    }


    private fun handleUncaughtException(e : Throwable){
        Log.e("app_crash",e.message.toString())
    }

    private fun restartApp() {
        val intent = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        exitProcess(1)
    }

    companion object {
        private var appContext: Context? = null

        fun appContext(): Context? {
            return appContext
        }


    }

}