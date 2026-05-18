package com.elion.assistant.data.repository

import com.elion.assistant.data.local.database.dao.TaskDao
import com.elion.assistant.data.local.database.toDomain
import com.elion.assistant.data.local.database.toEntity
import com.elion.assistant.domain.model.Task
import com.elion.assistant.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
) : TaskRepository {

    override fun getTodayTasks(): Flow<List<Task>> =
        taskDao.getTasksForDate(LocalDate.now()).map { list -> list.map { it.toDomain() } }

    override fun getTasksForDate(date: LocalDate): Flow<List<Task>> =
        taskDao.getTasksForDate(date).map { list -> list.map { it.toDomain() } }

    override fun getThisWeekTasks(): Flow<List<Task>> {
        val today = LocalDate.now()
        val start = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val end   = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        return taskDao.getTasksForWeek(start, end).map { list -> list.map { it.toDomain() } }
    }

    override fun getAllActiveTasks(): Flow<List<Task>> =
        taskDao.getAllActiveTasks().map { list -> list.map { it.toDomain() } }

    override fun getCompletedTasks(): Flow<List<Task>> =
        taskDao.getCompletedTasks().map { list -> list.map { it.toDomain() } }

    override fun getPostponedTasks(threshold: LocalDateTime): Flow<List<Task>> =
        taskDao.getPostponedTasks(threshold).map { list -> list.map { it.toDomain() } }

    override suspend fun insertTask(task: Task): Long = taskDao.insertTask(task.toEntity())

    override suspend fun updateTask(task: Task) = taskDao.updateTask(task.toEntity())

    override suspend fun deleteTask(task: Task) = taskDao.deleteTask(task.toEntity())

    override suspend fun completeTask(taskId: Long) = taskDao.completeTask(taskId)

    override suspend fun getCompletedCountForDate(date: LocalDate): Int =
        taskDao.getCompletedCountForDate(date)

    override suspend fun getTotalCountForDate(date: LocalDate): Int =
        taskDao.getTotalCountForDate(date)
}
