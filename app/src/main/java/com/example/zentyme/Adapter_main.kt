package com.example.zentyme

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


open class Adapter

class Adapter_main(var c: Context, var apps: MutableList<Apps>, var rv:RecyclerView): RecyclerView.Adapter<Adapter_main.ViewHolder>() {

    class ViewHolder : RecyclerView.ViewHolder {
        lateinit var name: TextView
        lateinit var icon: ImageView
        lateinit var statusImg: ImageView

        constructor(itemView: View) : super(itemView){
            name = itemView.findViewById(R.id.app_name)
            icon = itemView.findViewById(R.id.app_icon)
            statusImg = itemView.findViewById(R.id.app_status)

        }

    }
    
        var click: View.OnClickListener = object:View.OnClickListener{ 
        override fun onClick(v: View) {
            var i:Int = rv.getChildLayoutPosition(v);
            println(i)
            launch(i)
        }
    }
    private fun launch(i:Int){
        var p:PackageManager= c.packageManager
        //var intent: Intent? = p.getLaunchIntentForPackage(apps[i].pgkName)
        var intent: Intent = Intent()
        intent.setClass(c, Apps_Page::class.java)
        intent.putExtra("apps", apps[i].pgkName)
        if(intent!=null) {
            c.startActivity(intent)
        }
    }
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view: View = LayoutInflater.from(c).inflate(R.layout.adapter_row,parent,false)
        var orgnz: ViewHolder = ViewHolder(view)
         view.setOnClickListener(click)
        return orgnz
    }

    override fun getItemCount(): Int {
        return apps.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var app: Apps = apps.get(position)
        holder.name.setText(app.name)
        holder.icon.setImageDrawable(app.icon)
        if(app.status != 0){
            holder.statusImg.setImageResource(R.drawable.lock)
        }

    }


}
