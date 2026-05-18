package com.elion.assistant.data.local.database

import com.elion.assistant.data.local.database.entity.CategoryEntity
import com.elion.assistant.data.local.database.entity.DailyStatEntity
import com.elion.assistant.data.local.database.entity.TaskEntity
import com.elion.assistant.domain.model.Category
import com.elion.assistant.domain.model.DailyStat
import com.elion.assistant.domain.model.Priority
import com.elion.assistant.domain.model.RecurringType
import com.elion.assistant.domain.model.Task

// ───── Task Mappers ─────
fun TaskEntity.toDomain() = Task(
    id = id,
    title = title,
    description = description,
    categoryId = categoryId,
    priority = Priority.fromValue(priority),
    dueDate = dueDate,
    dueTime = dueTime,
    isCompleted = isCompleted,
    completedAt = completedAt,
    isRecurring = isRecurring,
    recurringType = recurringType?.let { RecurringType.valueOf(it) },
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun Task.toEntity() = TaskEntity(
    id = id,
    title = title,
    description = description,
    categoryId = categoryId,
    priority = priority.value,
    dueDate = dueDate,
    dueTime = dueTime,
    isCompleted = isCompleted,
    completedAt = completedAt,
    isRecurring = isRecurring,
    recurringType = recurringType?.name,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

// ───── Category Mappers ─────
fun CategoryEntity.toDomain() = Category(
    id = id,
    name = name,
    colorHex = colorHex,
    iconName = iconName,
    isDefault = isDefault,
    sortOrder = sortOrder,
)

fun Category.toEntity() = CategoryEntity(
    id = id,
    name = name,
    colorHex = colorHex,
    iconName = iconName,
    isDefault = isDefault,
    sortOrder = sortOrder,
)

// ───── DailyStat Mappers ─────
fun DailyStatEntity.toDomain() = DailyStat(
    date = date,
    totalTasks = totalTasks,
    completedTasks = completedTasks,
    postponedTasks = postponedTasks,
    streakDay = streakDay,
    morningBriefingShown = morningBriefingShown,
    eveningAnalysisShown = eveningAnalysisShown,
)

fun DailyStat.toEntity() = DailyStatEntity(
    date = date,
    totalTasks = totalTasks,
    completedTasks = completedTasks,
    postponedTasks = postponedTasks,
    streakDay = streakDay,
    morningBriefingShown = morningBriefingShown,
    eveningAnalysisShown = eveningAnalysisShown,
)
