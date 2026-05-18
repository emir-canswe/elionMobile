package com.elion.assistant.data.repository

import com.elion.assistant.data.local.database.dao.DailyStatDao
import com.elion.assistant.data.local.database.toDomain
import com.elion.assistant.data.local.database.toEntity
import com.elion.assistant.domain.model.DailyStat
import com.elion.assistant.domain.repository.StatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StatRepositoryImpl @Inject constructor(
    private val dailyStatDao: DailyStatDao,
) : StatRepository {

    override fun getStatsForLastNDays(n: Int): Flow<List<DailyStat>> {
        val startDate = LocalDate.now().minusDays(n.toLong())
        return dailyStatDao.getStatsFrom(startDate).map { list -> list.map { it.toDomain() } }
    }

    override suspend fun getStatForDate(date: LocalDate): DailyStat? =
        dailyStatDao.getStatForDate(date)?.toDomain()

    override suspend fun saveStat(stat: DailyStat) =
        dailyStatDao.insertOrUpdate(stat.toEntity())

    override suspend fun getCurrentStreak(): Int {
        val stats = dailyStatDao.getRecentStats(365)
        var streak = 0
        var checkDate = LocalDate.now().minusDays(1)
        for (stat in stats) {
            if (stat.date == checkDate && stat.completedTasks > 0 &&
                stat.completedTasks.toFloat() / stat.totalTasks.coerceAtLeast(1) >= 0.5f
            ) {
                streak++
                checkDate = checkDate.minusDays(1)
            } else {
                break
            }
        }
        return streak
    }

    override suspend fun getLongestStreak(): Int {
        val stats = dailyStatDao.getRecentStats(365).sortedBy { it.date }
        var longest = 0
        var current = 0
        for (stat in stats) {
            if (stat.totalTasks > 0 &&
                stat.completedTasks.toFloat() / stat.totalTasks >= 0.5f
            ) {
                current++
                if (current > longest) longest = current
            } else {
                current = 0
            }
        }
        return longest
    }
}
