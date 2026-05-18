package com.elion.assistant.service.notification

import android.content.Context
import androidx.work.*
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.concurrent.TimeUnit

object WorkScheduler {

    fun scheduleDailyNotifications(
        context: Context,
        morningHour: Int,
        morningMinute: Int,
        eveningHour: Int,
        eveningMinute: Int,
    ) {
        val workManager = WorkManager.getInstance(context)

        // Morning briefing
        val morningDelay = calculateDelay(morningHour, morningMinute)
        val morningWork = PeriodicWorkRequestBuilder<MorningBriefingWorker>(
            1, TimeUnit.DAYS,
        )
            .setInitialDelay(morningDelay, TimeUnit.MILLISECONDS)
            .addTag("morning_briefing")
            .build()

        workManager.enqueueUniquePeriodicWork(
            "morning_briefing",
            ExistingPeriodicWorkPolicy.UPDATE,
            morningWork,
        )

        // Evening analysis
        val eveningDelay = calculateDelay(eveningHour, eveningMinute)
        val eveningWork = PeriodicWorkRequestBuilder<EveningAnalysisWorker>(
            1, TimeUnit.DAYS,
        )
            .setInitialDelay(eveningDelay, TimeUnit.MILLISECONDS)
            .addTag("evening_analysis")
            .build()

        workManager.enqueueUniquePeriodicWork(
            "evening_analysis",
            ExistingPeriodicWorkPolicy.UPDATE,
            eveningWork,
        )
    }

    private fun calculateDelay(targetHour: Int, targetMinute: Int): Long {
        val now = LocalDateTime.now()
        var target = now.toLocalDate().atTime(LocalTime.of(targetHour, targetMinute))
        if (target.isBefore(now)) {
            target = target.plusDays(1)
        }
        return Duration.between(now, target).toMillis()
    }

    fun cancelAll(context: Context) {
        WorkManager.getInstance(context).cancelAllWork()
    }

    fun scheduleTaskReminder(
        context: Context,
        taskId: Long,
        title: String,
        description: String,
        dueDate: java.time.LocalDate,
        dueTime: java.time.LocalTime
    ) {
        val workManager = WorkManager.getInstance(context)
        val targetDateTime = dueDate.atTime(dueTime)
        val now = LocalDateTime.now()
        
        if (targetDateTime.isBefore(now)) return
        
        val initialDelay = Duration.between(now, targetDateTime).toMillis()
        
        val data = workDataOf(
            "task_title" to title,
            "task_description" to description
        )
        
        val reminderWork = OneTimeWorkRequestBuilder<TaskReminderWorker>()
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .addTag("task_reminder_$taskId")
            .build()
            
        workManager.enqueueUniqueWork(
            "task_reminder_$taskId",
            ExistingWorkPolicy.REPLACE,
            reminderWork
        )
    }

    fun cancelTaskReminder(context: Context, taskId: Long) {
        WorkManager.getInstance(context).cancelUniqueWork("task_reminder_$taskId")
    }
}
