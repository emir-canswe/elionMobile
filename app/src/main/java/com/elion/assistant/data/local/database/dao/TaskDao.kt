package com.elion.assistant.data.local.database.dao

import androidx.room.*
import com.elion.assistant.data.local.database.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE dueDate = :date AND isCompleted = 0 ORDER BY priority DESC, dueTime ASC")
    fun getTasksForDate(date: LocalDate): Flow<List<TaskEntity>>

    @Query("""
        SELECT * FROM tasks 
        WHERE dueDate >= :weekStart AND dueDate <= :weekEnd AND isCompleted = 0 
        ORDER BY dueDate ASC, priority DESC
    """)
    fun getTasksForWeek(weekStart: LocalDate, weekEnd: LocalDate): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 0 ORDER BY priority DESC, dueDate ASC")
    fun getAllActiveTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE isCompleted = 1 ORDER BY completedAt DESC LIMIT 100")
    fun getCompletedTasks(): Flow<List<TaskEntity>>

    @Query("""
        SELECT * FROM tasks
        WHERE isCompleted = 0
        AND createdAt <= :threshold
        ORDER BY createdAt ASC
    """)
    fun getPostponedTasks(threshold: LocalDateTime): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("UPDATE tasks SET isCompleted = 1, completedAt = :now, updatedAt = :now WHERE id = :taskId")
    suspend fun completeTask(taskId: Long, now: LocalDateTime = LocalDateTime.now())

    @Query("SELECT COUNT(*) FROM tasks WHERE dueDate = :date AND isCompleted = 1")
    suspend fun getCompletedCountForDate(date: LocalDate): Int

    @Query("SELECT COUNT(*) FROM tasks WHERE dueDate = :date")
    suspend fun getTotalCountForDate(date: LocalDate): Int

    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Long): TaskEntity?
}
