package com.activties

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import com.example.myapplication.R


class MyService:Service() {
    private val serviceId = 1

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel_id = "my_channel_1"
            val channel =NotificationChannel(channel_id,"dfefult",NotificationManager.IMPORTANCE_DEFAULT)
            val manger= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manger.createNotificationChannel(channel)
            val notification = Notification.Builder(this, channel_id).apply {
                setContentTitle("This my first notification")
                setContentText("Hello")
                setSmallIcon(R.drawable.ic_launcher_foreground)

            }.build()
          startForeground(serviceId, notification)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showmassage()
        return super.onStartCommand(intent, flags, startId)

    }
fun showmassage(){

    Handler(Looper.getMainLooper()).postDelayed(
        {


            Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show()
        },5000
    )
}
}












