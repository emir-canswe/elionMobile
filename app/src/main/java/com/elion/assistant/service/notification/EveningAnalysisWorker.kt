package com.elion.assistant.service.notification

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.elion.assistant.data.local.preferences.AppPreferences
import com.elion.assistant.domain.model.DailyStat
import com.elion.assistant.domain.repository.StatRepository
import com.elion.assistant.domain.repository.TaskRepository
import com.elion.assistant.domain.usecase.analysis.GenerateEveningAnalysisUseCase
import com.elion.assistant.service.voice.TextToSpeechManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import java.time.LocalDate

@HiltWorker
class EveningAnalysisWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val taskRepository: TaskRepository,
    private val statRepository: StatRepository,
    private val generateAnalysis: GenerateEveningAnalysisUseCase,
    private val notificationHelper: NotificationHelper,
    private val prefs: AppPreferences,
    private val ttsManager: TextToSpeechManager,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val today = LocalDate.now()
        val completed = taskRepository.getCompletedCountForDate(today)
        val total = taskRepository.getTotalCountForDate(today)
        val streak = statRepository.getCurrentStreak()

        // Save daily stat
        val stat = DailyStat(
            date = today,
            totalTasks = total,
            completedTasks = completed,
            streakDay = if (total > 0 && completed.toFloat() / total >= 0.5f) streak + 1 else 0,
            eveningAnalysisShown = true,
        )
        statRepository.saveStat(stat)

        val name = prefs.assistantName.first()
        val message = generateAnalysis(completed, total, streak)

        notificationHelper.showNotification(
            channelId = NotificationHelper.EVENING_ANALYSIS,
            title = "🌙 Gün Sonu — $name",
            message = message,
            notificationId = 1002,
        )

        val ttsEnabled = prefs.ttsEnabled.first()
        if (ttsEnabled) {
            ttsManager.init()
            ttsManager.speak(message)
        }

        return Result.success()
    }
}
