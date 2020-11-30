package com.example.zentyme

import android.app.ActivityManager
import android.app.Service
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.annotation.MainThread
import java.nio.file.Files.size
import java.util.*

class MyService : Service() {
    val TAG = "MyService"
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    fun oncreate() {
        ShowLog("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ShowLog("onStartCommnad")

        val runnable = Runnable {
            while (true) {
                println(getForegroundApp())
            }
            Thread.sleep(10)
        }

        val thread = Thread(runnable)
        thread.start()
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onDestroy() {
        ShowLog("onDestroy")
        super.onDestroy()
    }

    fun ShowLog(message: String) {

        Log.d(TAG, message)
    }
// trying to implement the app checker


    fun getForegroundApp(): String {


        var activityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        var processes1 = activityManager.getRunningTasks(1)
        var componentInfo = processes1.get(0).topActivity
        var classname = processes1.get(0).topActivity!!.getClassName()
        var packagename = processes1.get(0).topActivity!!.getPackageName()

        return packagename
    }

}