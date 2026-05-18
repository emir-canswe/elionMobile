package com.elion.assistant.domain.model

import java.time.LocalDate

data class DailyStat(
    val date: LocalDate,
    val totalTasks: Int,
    val completedTasks: Int,
    val postponedTasks: Int = 0,
    val streakDay: Int = 0,
    val morningBriefingShown: Boolean = false,
    val eveningAnalysisShown: Boolean = false,
) {
    val completionRatio: Float
        get() = if (totalTasks == 0) 0f else completedTasks.toFloat() / totalTasks
}

data class PostponeAlert(
    val task: Task,
    val comment: String,
    val daysSinceCreated: Long,
)
