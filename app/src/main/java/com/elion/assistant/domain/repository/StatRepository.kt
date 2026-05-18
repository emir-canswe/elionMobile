package com.elion.assistant.domain.repository

import com.elion.assistant.domain.model.DailyStat
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface StatRepository {
    fun getStatsForLastNDays(n: Int): Flow<List<DailyStat>>
    suspend fun getStatForDate(date: LocalDate): DailyStat?
    suspend fun saveStat(stat: DailyStat)
    suspend fun getCurrentStreak(): Int
    suspend fun getLongestStreak(): Int
}
