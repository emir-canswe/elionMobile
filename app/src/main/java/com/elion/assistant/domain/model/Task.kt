package com.elion.assistant.domain.model

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Task(
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val categoryId: Long = 1L,
    val priority: Priority = Priority.NORMAL,
    val dueDate: LocalDate? = null,
    val dueTime: LocalTime? = null,
    val isCompleted: Boolean = false,
    val completedAt: LocalDateTime? = null,
    val isRecurring: Boolean = false,
    val recurringType: RecurringType? = null,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)

enum class Priority(val value: Int, val label: String) {
    LOW(0, "Bekleyebilir"),
    NORMAL(1, "Normal"),
    HIGH(2, "Acil");

    companion object {
        fun fromValue(v: Int) = entries.firstOrNull { it.value == v } ?: NORMAL
    }
}

enum class RecurringType(val label: String) {
    DAILY("Günlük"),
    WEEKLY("Haftalık"),
    MONTHLY("Aylık"),
}
