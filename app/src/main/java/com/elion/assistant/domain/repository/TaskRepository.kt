package com.elion.assistant.domain.repository

import com.elion.assistant.domain.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface TaskRepository {
    fun getTodayTasks(): Flow<List<Task>>
    fun getTasksForDate(date: LocalDate): Flow<List<Task>>
    fun getThisWeekTasks(): Flow<List<Task>>
    fun getAllActiveTasks(): Flow<List<Task>>
    fun getCompletedTasks(): Flow<List<Task>>
    fun getPostponedTasks(threshold: LocalDateTime): Flow<List<Task>>
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
    suspend fun completeTask(taskId: Long)
    suspend fun getCompletedCountForDate(date: LocalDate): Int
    suspend fun getTotalCountForDate(date: LocalDate): Int
}
