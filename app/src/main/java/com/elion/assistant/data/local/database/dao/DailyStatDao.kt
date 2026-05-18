package com.elion.assistant.data.local.database.dao

import androidx.room.*
import com.elion.assistant.data.local.database.entity.DailyStatEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface DailyStatDao {
    @Query("SELECT * FROM daily_stats WHERE date >= :startDate ORDER BY date ASC")
    fun getStatsFrom(startDate: LocalDate): Flow<List<DailyStatEntity>>

    @Query("SELECT * FROM daily_stats WHERE date = :date")
    suspend fun getStatForDate(date: LocalDate): DailyStatEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(stat: DailyStatEntity)

    @Query("SELECT * FROM daily_stats ORDER BY date DESC LIMIT :limit")
    suspend fun getRecentStats(limit: Int): List<DailyStatEntity>
}
