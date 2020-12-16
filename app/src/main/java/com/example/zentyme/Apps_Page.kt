package com.example.zentyme


import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.System.currentTimeMillis

class Apps_Page : AppCompatActivity() {
    lateinit var icon:ImageView
    lateinit var name: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_page)
        val extras = intent.extras
        if (extras != null) {
            val pkg: String = extras.getString("apps").toString()

            icon = findViewById(R.id.appIcon)
            name = findViewById(R.id.appName)
            var pm = packageManager
            name.setText(pm.getApplicationInfo(pkg, PackageManager.GET_META_DATA).loadLabel(pm))
            icon.setImageDrawable(pm.getApplicationIcon(pkg))

            /*      var beginTime: Long = currentTimeMillis()
                  var endTime: Long = currentTimeMillis() + 1000 * 60 * 60
                  var usm: UsageStatsManager =
                      applicationContext.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
                  var sMap: Map<String, UsageStats> = usm.queryAndAggregateUsageStats(beginTime, endTime)
                  var time = sMap.get(pkg)!!.totalTimeInForeground*/
        }
    }
}