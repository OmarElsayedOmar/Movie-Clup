package com.helpel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBrodcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
when (p1?.action){
Intent.ACTION_AIRPLANE_MODE_CHANGED ->{
    if (p1.extras?.getBoolean("state")==true){
        Toast.makeText(p0," رحله سعيدة",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(p0," مرحبا بعودتك من جديد ",Toast.LENGTH_LONG).show()
    }
}

    }
    }
}