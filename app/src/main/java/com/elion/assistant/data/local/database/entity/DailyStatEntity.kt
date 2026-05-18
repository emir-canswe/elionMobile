package com.elion.assistant.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "daily_stats")
data class DailyStatEntity(
    @PrimaryKey
    val date: LocalDate,
    val totalTasks: Int = 0,
    val completedTasks: Int = 0,
    val postponedTasks: Int = 0,
    val streakDay: Int = 0,
    val morningBriefingShown: Boolean = false,
    val eveningAnalysisShown: Boolean = false,
)
