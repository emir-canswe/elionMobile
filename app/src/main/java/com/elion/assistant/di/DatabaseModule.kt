package com.elion.assistant.di

import android.content.Context
import androidx.room.Room
import com.elion.assistant.data.local.database.AppDatabase
import com.elion.assistant.data.local.database.dao.CategoryDao
import com.elion.assistant.data.local.database.dao.DailyStatDao
import com.elion.assistant.data.local.database.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME,
        )
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideTaskDao(db: AppDatabase): TaskDao = db.taskDao()
    @Provides fun provideCategoryDao(db: AppDatabase): CategoryDao = db.categoryDao()
    @Provides fun provideDailyStatDao(db: AppDatabase): DailyStatDao = db.dailyStatDao()
}
