package com.elion.assistant.presentation.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elion.assistant.domain.model.Category
import com.elion.assistant.domain.model.Task
import com.elion.assistant.domain.repository.CategoryRepository
import com.elion.assistant.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

enum class TaskFilter {
    TODAY, WEEK, ALL, COMPLETED
}

data class TasksUiState(
    val isLoading: Boolean = true,
    val tasks: List<Task> = emptyList(),
    val categories: List<Category> = emptyList(),
    val filter: TaskFilter = TaskFilter.TODAY
)

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val _filter = MutableStateFlow(TaskFilter.TODAY)
    
    val uiState: StateFlow<TasksUiState> = combine(
        _filter,
        taskRepository.getAllActiveTasks(),
        taskRepository.getCompletedTasks(),
        categoryRepository.getAllCategories()
    ) { filter, activeTasks, completedTasks, categories ->
        val filteredTasks = when (filter) {
            TaskFilter.TODAY -> activeTasks.filter { it.dueDate == LocalDate.now() }
            TaskFilter.WEEK -> {
                val today = LocalDate.now()
                val weekEnd = today.plusDays(7)
                activeTasks.filter { it.dueDate != null && it.dueDate in today..weekEnd }
            }
            TaskFilter.ALL -> activeTasks
            TaskFilter.COMPLETED -> completedTasks
        }

        TasksUiState(
            isLoading = false,
            tasks = filteredTasks,
            categories = categories,
            filter = filter
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TasksUiState())

    init {
        viewModelScope.launch {
            categoryRepository.seedDefaultCategories()
        }
    }

    fun setFilter(filter: TaskFilter) {
        _filter.value = filter
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }

    fun completeTask(task: Task) {
        viewModelScope.launch {
            taskRepository.completeTask(task.id)
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }
    }
}
