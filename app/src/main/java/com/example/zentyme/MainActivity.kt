package com.example.zentyme



import android.app.ActivityManager
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.util.Log.*
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.P)

    lateinit var recyclerView: RecyclerView
    var list: MutableList<Apps> = mutableListOf<Apps>()
    var adptr: Adapter_main? = null

    //creates the 3 dots menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu, menu)//creates the options in the 3 dots menu
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.actionbar)) //creates the actionbar found in res/layout/actionbar.xml

        //=================Beginning the implementation
        recyclerView = findViewById(R.id.recycler_view)
        getApps()
        println("done with getApps()...")
        adptr = Adapter_main(this, list,recyclerView)
        println("done creating Adapter...")
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        println("done creating Linear Layout for recycleView...")

        recyclerView.adapter = adptr
        // recyclerView.setBackgroundColor(Color.rgb(207,207,207))
        println("*************** RECYCLER_VIEW DESIGN: COMPLETE **********************")


    }
    @RequiresApi(Build.VERSION_CODES.P)
    public fun getApps(){
        var name: String
        var icon: Drawable
        var pkgName: String
        val p: PackageManager = getPackageManager()
        var s: List<ApplicationInfo> = p.getInstalledApplications(GET_META_DATA)
        println("getting apps...")
   
        var j: Int=0
        for(i in s){
            if((i.flags and ApplicationInfo.FLAG_SYSTEM) ==0) {
                name = i.loadLabel(p).toString()
                icon = i.loadIcon(p)
                pkgName = i.packageName
                println("loaded app info...")
                list.add(Apps(name, icon, pkgName,0))
            }

            println("===========================================================================")
            println(i.packageName)
            println(p.getApplicationLabel(i))
            println(i)



        }
        println("done loading into list...")
        adptr?.notifyDataSetChanged()



    }




}

