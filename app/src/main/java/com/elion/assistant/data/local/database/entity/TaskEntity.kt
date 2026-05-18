package com.elion.assistant.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val categoryId: Long = 1L,
    val priority: Int = 1,          // 0=düşük, 1=normal, 2=acil
    val dueDate: LocalDate? = null,
    val dueTime: LocalTime? = null,
    val isCompleted: Boolean = false,
    val completedAt: LocalDateTime? = null,
    val isRecurring: Boolean = false,
    val recurringType: String? = null,  // DAILY / WEEKLY / MONTHLY
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)
