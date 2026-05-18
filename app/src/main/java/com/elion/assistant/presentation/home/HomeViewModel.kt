package com.elion.assistant.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elion.assistant.data.local.preferences.AppPreferences
import com.elion.assistant.domain.model.PostponeAlert
import com.elion.assistant.domain.model.Task
import com.elion.assistant.domain.model.Category
import com.elion.assistant.domain.repository.StatRepository
import com.elion.assistant.domain.repository.TaskRepository
import com.elion.assistant.domain.repository.CategoryRepository
import com.elion.assistant.domain.usecase.analysis.CheckPostponedTasksUseCase
import com.elion.assistant.domain.usecase.analysis.GenerateMorningBriefingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = true,
    val todayTasks: List<Task> = emptyList(),
    val completedCount: Int = 0,
    val totalCount: Int = 0,
    val briefingMessage: String = "",
    val postponeAlerts: List<PostponeAlert> = emptyList(),
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val assistantName: String = "ELION",
    val categories: List<Category> = emptyList()
) {
    val completionPercentage: Int
        get() = if (totalCount == 0) 0 else ((completedCount.toFloat() / totalCount) * 100).toInt()
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository,
    private val statRepository: StatRepository,
    private val checkPostponedTasks: CheckPostponedTasksUseCase,
    private val generateMorningBriefing: GenerateMorningBriefingUseCase,
    private val prefs: AppPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val assistantNameFlow = prefs.assistantName
            val tasksFlow = taskRepository.getTodayTasks()
            val allActiveTasksFlow = taskRepository.getAllActiveTasks()
            val categoriesFlow = categoryRepository.getAllCategories()

            combine(
                assistantNameFlow,
                tasksFlow,
                allActiveTasksFlow,
                categoriesFlow
            ) { name, tasks, allActive, categories ->
                val completed = tasks.count { it.isCompleted }
                val total = tasks.size
                val briefing = generateMorningBriefing(allActive, name)
                val postponeAlerts = checkPostponedTasks(allActive)
                val currentStreak = statRepository.getCurrentStreak()
                val longestStreak = statRepository.getLongestStreak()

                HomeUiState(
                    isLoading = false,
                    todayTasks = tasks,
                    completedCount = completed,
                    totalCount = total,
                    briefingMessage = briefing,
                    postponeAlerts = postponeAlerts,
                    currentStreak = currentStreak,
                    longestStreak = longestStreak,
                    assistantName = name,
                    categories = categories
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun toggleTaskCompletion(task: Task) {
        viewModelScope.launch {
            if (task.isCompleted) {
                val updated = task.copy(isCompleted = false, completedAt = null)
                taskRepository.updateTask(updated)
            } else {
                taskRepository.completeTask(task.id)
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }

    fun addQuickTask(title: String, categoryId: Long?) {
        viewModelScope.launch {
            val task = Task(
                title = title,
                categoryId = categoryId,
                dueDate = LocalDate.now(),
                createdAt = java.time.LocalDateTime.now()
            )
            taskRepository.insertTask(task)
        }
    }
}
