package com.elion.assistant.service.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.elion.assistant.MainActivity
import com.elion.assistant.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        const val MORNING_BRIEFING = "morning_briefing"
        const val EVENING_ANALYSIS = "evening_analysis"
        const val POSTPONE_ALERT   = "postpone_alert"
        const val TASK_REMINDER    = "task_reminder"
    }

    fun createChannels() {
        val manager = context.getSystemService(NotificationManager::class.java)
        val channels = listOf(
            NotificationChannel(MORNING_BRIEFING, "Sabah Brifing", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Her sabah günlük görev özeti"
            },
            NotificationChannel(EVENING_ANALYSIS, "Akşam Analizi", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Her akşam gün sonu değerlendirmesi"
            },
            NotificationChannel(POSTPONE_ALERT, "Erteleme Uyarısı", NotificationManager.IMPORTANCE_HIGH).apply {
                description = "Uzun süredir bekleyen görevler"
            },
            NotificationChannel(TASK_REMINDER, "Görev Hatırlatıcı", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Görev hatırlatmaları"
            },
        )
        channels.forEach { manager.createNotificationChannel(it) }
    }

    fun showNotification(
        channelId: String,
        title: String,
        message: String,
        notificationId: Int = System.currentTimeMillis().toInt(),
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_splash_logo)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.notify(notificationId, notification)
    }
}
