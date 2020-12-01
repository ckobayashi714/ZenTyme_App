package com.example.zentyme

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Service
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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


    @SuppressLint("WrongConstant")
    fun getForegroundApp(): String {




        val pm = this.getPackageManager()
        val intent = Intent(Intent.ACTION_MAIN,null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)

        val list = pm.queryIntentActivities(intent, PackageManager.PERMISSION_GRANTED)

        for(rInfo in list){
            Log.w("List of Running Apps",rInfo.activityInfo.applicationInfo.loadLabel(pm).toString() )
        }
    return " "
    }

}