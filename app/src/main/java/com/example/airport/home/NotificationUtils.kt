package com.example.airport.home

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.airport.R
import kotlin.random.Random

/**
 * fun sendBasicNotification()
 *
 * Crea una notificacion
 */
fun sendBasicNotification(context: Context, title: String, message:String){
    val notificationManager = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notification = NotificationCompat.Builder(context,"main_notification")
        .setContentTitle(title)
        .setContentText(message)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
        .setAutoCancel(true)
        .build()

    notificationManager.notify(
        Random.nextInt(),
        notification
    )
}