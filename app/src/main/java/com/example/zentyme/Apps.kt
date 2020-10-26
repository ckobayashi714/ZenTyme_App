package com.example.zentyme

import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable

class Apps(
    name: String,
    icon: Drawable,
    pgkName: String,
    status: Int
) {
    var status = status
        get() = field
        set(value) {
            field = value
        }
    var pgkName = pgkName
        get() = field
        set(value){
            field = value
        }
    var icon = icon
        get() = field
        set(value) {
            field = value
        }

    var name = name
        get() = field
        set(value) {
            field = value
        }

}