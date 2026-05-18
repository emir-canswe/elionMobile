package com.elion.assistant.service.notification

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.elion.assistant.data.local.preferences.AppPreferences
import com.elion.assistant.domain.repository.TaskRepository
import com.elion.assistant.domain.usecase.analysis.GenerateMorningBriefingUseCase
import com.elion.assistant.service.voice.TextToSpeechManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first

@HiltWorker
class MorningBriefingWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val taskRepository: TaskRepository,
    private val generateBriefing: GenerateMorningBriefingUseCase,
    private val notificationHelper: NotificationHelper,
    private val prefs: AppPreferences,
    private val ttsManager: TextToSpeechManager,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val tasks = taskRepository.getAllActiveTasks().first()
        val name = prefs.assistantName.first()
        val message = generateBriefing(tasks, name)

        notificationHelper.showNotification(
            channelId = NotificationHelper.MORNING_BRIEFING,
            title = "☀️ Günaydın — $name",
            message = message,
            notificationId = 1001,
        )

        val ttsEnabled = prefs.ttsEnabled.first()
        if (ttsEnabled) {
            ttsManager.init()
            ttsManager.speak(message)
        }

        return Result.success()
    }
}
