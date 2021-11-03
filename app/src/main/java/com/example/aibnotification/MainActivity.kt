package com.example.aibnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var EDT:EditText
    lateinit var BTN:Button
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    private val notificationId = 1234
    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    lateinit var builder: Notification.Builder

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        EDT=findViewById(R.id.edtNotifi)
        BTN=findViewById(R.id.btnNotifi)

        BTN.setOnClickListener{
            if(EDT.text.isNotBlank())
            {
                val intent = Intent(this, NotificationActivity::class.java)

                val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
                    notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                    notificationManager.createNotificationChannel(notificationChannel)

                    builder = Notification.Builder(this, channelId)
                        .setSmallIcon(R.drawable.img)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.largimg))
                        .setContentIntent(pendingIntent)
                        .setContentTitle("My Notification")
                        .setContentText(EDT.text!!)
                }
                else
                {


                    builder = Notification.Builder(this)
                        .setSmallIcon(R.drawable.img)
                        .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.largimg))
                        .setContentIntent(pendingIntent)
                        .setContentTitle("My Notification")
                        .setContentText(EDT.text!!)
                }
                //executing the notification
                notificationManager.notify(notificationId, builder.build())
            }
            else
            {
                Toast.makeText(applicationContext,"Enter Message Pls",Toast.LENGTH_LONG).show()
            }




        }



    }
}