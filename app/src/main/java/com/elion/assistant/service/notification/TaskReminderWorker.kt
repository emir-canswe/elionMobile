package com.elion.assistant.service.notification

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.elion.assistant.data.local.preferences.AppPreferences
import com.elion.assistant.service.voice.TextToSpeechManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class TaskReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val notificationHelper: NotificationHelper,
    private val prefs: AppPreferences,
    private val ttsManager: TextToSpeechManager,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val taskTitle = inputData.getString("task_title") ?: "Yapılacak Görev"
        
        val assistantName = prefs.assistantName.first()
        val ttsEnabled = prefs.ttsEnabled.first()
        
        // Show high importance task reminder notification!
        notificationHelper.showNotification(
            channelId = NotificationHelper.TASK_REMINDER,
            title = "⏰ Görev Zamanı!",
            message = "\"$taskTitle\" görevini yapma vakti geldi.",
            notificationId = System.currentTimeMillis().toInt()
        )
        
        // If TTS is enabled, read it aloud with customized assistant tone!
        if (ttsEnabled) {
            val tone = prefs.commentTone.first()
            val textToRead = if (tone > 0.5f) {
                "Hey! \"$taskTitle\" görevini yapma vakti geldi. Daha ne kadar ertelemeyi düşünüyorsun acaba?"
            } else {
                "Şşşt! \"$taskTitle\" görevini yapma zamanı geldi. Hadi hemen halledelim!"
            }
            ttsManager.init()
            ttsManager.speak(textToRead)
        }
        
        return Result.success()
    }
}
