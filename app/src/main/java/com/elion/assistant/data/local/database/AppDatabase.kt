package com.elion.assistant.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.elion.assistant.data.local.database.dao.CategoryDao
import com.elion.assistant.data.local.database.dao.DailyStatDao
import com.elion.assistant.data.local.database.dao.TaskDao
import com.elion.assistant.data.local.database.entity.CategoryEntity
import com.elion.assistant.data.local.database.entity.DailyStatEntity
import com.elion.assistant.data.local.database.entity.TaskEntity

@Database(
    entities = [TaskEntity::class, CategoryEntity::class, DailyStatEntity::class],
    version = 1,
    exportSchema = false,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun categoryDao(): CategoryDao
    abstract fun dailyStatDao(): DailyStatDao

    companion object {
        const val DATABASE_NAME = "elion_database"
    }
}
