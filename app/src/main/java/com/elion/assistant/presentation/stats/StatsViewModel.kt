package com.elion.assistant.presentation.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elion.assistant.domain.model.DailyStat
import com.elion.assistant.domain.repository.StatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class StatsUiState(
    val isLoading: Boolean = true,
    val last7DaysStats: List<DailyStat> = emptyList(),
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val totalTasksEver: Int = 0,
    val completedTasksEver: Int = 0
)

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val statRepository: StatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatsUiState())
    val uiState: StateFlow<StatsUiState> = _uiState.asStateFlow()

    init {
        loadStats()
    }

    private fun loadStats() {
        viewModelScope.launch {
            val statsFlow = statRepository.getStatsForLastNDays(7)
            
            statsFlow.collect { stats ->
                val current = statRepository.getCurrentStreak()
                val longest = statRepository.getLongestStreak()
                // Simple summary for stats ever (approximated from history)
                val total = stats.sumOf { it.totalTasks }
                val completed = stats.sumOf { it.completedTasks }
                
                _uiState.value = StatsUiState(
                    isLoading = false,
                    last7DaysStats = stats,
                    currentStreak = current,
                    longestStreak = longest,
                    totalTasksEver = total,
                    completedTasksEver = completed
                )
            }
        }
    }
}
