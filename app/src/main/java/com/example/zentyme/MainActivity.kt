package com.example.zentyme

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import android.util.Log.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi







class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //=================Beginning the implementation
        //var p: Int =0
        // val l: mutableListOf<ApplicationInfo> = p.getPackageManager().getInstalledApps(PackageManager.PERMISSION_GRANTED)
        val start: Button = findViewById(R.id._on)
        val stop: Button = findViewById(R.id._off)
        val apps: List<ApplicationInfo> = list() //list() function returns a "List<ApplicationInfo>" data type.
        //variable "apps" is now supposed to have a list of packages

        //========================================================

        println("******************")

        start.setOnClickListener{
            var target: String = apps[16].packageName   //<-----------------------------------------------------TARGET
            // lock(this, target)
            lock2(this, target)


        }
        stop.setOnClickListener{
            unlock(apps)
        }


    }
    @RequiresApi(Build.VERSION_CODES.P)
    fun list(): List<ApplicationInfo>{

        val p: PackageManager = getPackageManager()
        var s: List<ApplicationInfo> = p.getInstalledApplications(PackageManager.GET_META_DATA)


        // val l: List<Apps> = arrayListOf<Apps>()
        //var pInfo: ApplicationInfo
        //for(pInfo in s){
        //   var app: App = App(pInfo.packageName,p.getApplicationLabel(pInfo).toString())


        //}
        var j: Int=0
        val a: ComponentName
        var name: TextView = findViewById(R.id.app_list)
        println("size:::")
        println(s.size)
        for(i in s){
            println("===========================================================================")
            println(s[j].packageName)
            println(p.getApplicationLabel(s[j]))
            println(s[j])

            println(j)
            j=j+1
        }
        println("package info of s[0]")
        //val app1: ApplicationInfo = p.getApplicationInfo(s[0].packageName, PackageManager.GET_META_DATA)
        name.text = s[16].loadLabel(packageManager).toString() //<------------------------------------------------------TARGET_NAME
        val icons: Drawable = s[16].loadIcon(packageManager) // <-------------------------------------------------------TARGET_ICON
        val image: ImageView = findViewById(R.id.app_icon)
        image.setImageDrawable(icons)

        return s
    }


    public fun lock(c: Context, s: String){
        // val a:String = getPackageManager().getApplicationInfo(s[5].packageName, GET_META_DATA).className
        //val l:Intent = Intent(this@MainActivity, a::class.java)
        //l.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //this@MainActivity.stopService(l)
        //====

        val p: PackageManager = c.getPackageManager()
        println("=====================================")
        println(s)
        // val c: ComponentName = ComponentName(n,s[5].className)
        val intent: Intent? = p.getLaunchIntentForPackage(s)
        val default: Intent? = p.getLaunchIntentForPackage(c.packageName)
        println(intent) //null
        var tempTime: Long = System.currentTimeMillis()
        var time: Long
        var am: ActivityManager = c.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        var overhead: List<ActivityManager.RunningAppProcessInfo> = am.runningAppProcesses
        //var am: ActivityManager = c.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        while(true){
            time =System.currentTimeMillis()
            if(time-tempTime > 10000){
                am = c.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                overhead = am.runningAppProcesses //return list

                // var f = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState")
                if(intent != null) { //intent
                    var j: Int = 0

                    for (i: ActivityManager.RunningAppProcessInfo in overhead) {
                        var j2: Int=0
                        if (i.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                            for (curr: String in i.pkgList) {
                                if (curr.equals(s)) {

                                    println(curr)
                                    println(s)
                                    intent.addCategory(Intent.CATEGORY_LAUNCHER) //intent
                                    c.startActivity(intent)
                                }
                                j2=j2+1
                            }
                            // intent.addCategory(Intent.CATEGORY_LAUNCHER)
                            // c.startActivity(default)
                            //c.stopService(intent)
                        }
                        j = j + 1
                        /*   println(overhead[j])
                            var flag: Int=-1
                           // flag = f.get(i)
                            if(flag != -1 && flag == 2) {
                                curr = i

                                intent.addCategory(Intent.CATEGORY_LAUNCHER)
                                c.startActivity(default)
                                c.stopService(intent)
                            }
                                j=j+1*/


                    }//for loop
                    tempTime = time
                }
            }//if
        }//end of while



    }
    //p.setComponentEnabledSetting(c,COMPONENT_ENABLED_STATE_DISABLED,0)


    public fun lock2(c: Context, s: String) {
        // val a:String = getPackageManager().getApplicationInfo(s[5].packageName, GET_META_DATA).className
        //val l:Intent = Intent(this@MainActivity, a::class.java)
        //l.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //this@MainActivity.stopService(l)
        //====

        val p: PackageManager = c.getPackageManager()
        println("=====================================")
        println(s)
        // val c: ComponentName = ComponentName(n,s[5].className)
        val intent: Intent? = p.getLaunchIntentForPackage(s)

        println(intent) //null
        var tempTime: Long = System.currentTimeMillis()

        var time: Long
        var action = true;
        //while (action) {
        //    time = System.currentTimeMillis()
        // var f = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState")
        //     if(time-tempTime > 10000) {
        if (intent != null) {


            intent.addCategory(Intent.CATEGORY_LAUNCHER)

            c.startActivity(intent)

        }
        //        tempTime = System.currentTimeMillis()
        //    }//end while

    }





    //p.setComponentEnabledSetting(c,COMPONENT_ENABLED_STATE_DISABLED,0)



    public fun unlock(s:List<ApplicationInfo>){
        val p: PackageManager = getPackageManager()
        val n: String = s[5].packageName
        // val intent: Intent? = p.getLaunchIntentForPackage(s[5].packageName)


        //p.setApplicationEnabledSetting(n, COMPONENT_ENABLED_STATE_ENABLED, SYNCHRONOUS)
    }

    public fun lock3(){

    }


    //forget about this. this was my attempt to organize
    class App {
        fun App(p: String,a: String)
        {
            var pName: String =p
            var aName: String = a
        }
    }

}