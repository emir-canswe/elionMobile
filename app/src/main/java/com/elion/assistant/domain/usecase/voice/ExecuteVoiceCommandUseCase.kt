package com.elion.assistant.domain.usecase.voice

import com.elion.assistant.domain.model.Task
import com.elion.assistant.domain.repository.TaskRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ExecuteVoiceCommandUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(intent: ParsedIntent): String {
        return when (intent.type) {
            IntentType.ADD_TASK -> {
                val title = intent.taskTitle ?: return "Görev adını anlayamadım."
                val task = Task(
                    title = title,
                    dueDate = intent.date,
                    priority = intent.priority ?: com.elion.assistant.domain.model.Priority.NORMAL
                )
                taskRepository.insertTask(task)
                "Görev eklendi: $title"
            }
            IntentType.COMPLETE_TASK -> {
                val title = intent.taskTitle ?: return "Hangi görevi tamamlayacağını anlamadım."
                val activeTasks = taskRepository.getAllActiveTasks().first()
                val matchedTask = activeTasks.find { 
                    it.title.lowercase().contains(title.lowercase()) 
                }
                
                if (matchedTask != null) {
                    taskRepository.completeTask(matchedTask.id)
                    "${matchedTask.title} tamamlandı olarak işaretlendi."
                } else {
                    "Aktif görevler arasında '$title' bulunamadı."
                }
            }
            IntentType.DELETE_TASK -> {
                val title = intent.taskTitle ?: return "Hangi görevi sileceğini anlamadım."
                val activeTasks = taskRepository.getAllActiveTasks().first()
                val matchedTask = activeTasks.find { 
                    it.title.lowercase().contains(title.lowercase()) 
                }
                
                if (matchedTask != null) {
                    taskRepository.deleteTask(matchedTask)
                    "${matchedTask.title} silindi."
                } else {
                    "Aktif görevler arasında '$title' bulunamadı."
                }
            }
            IntentType.READ_LIST -> {
                "Bu özellik şu an sesli özetleniyor." // handled by UI/ViewModel mostly
            }
            IntentType.SHOW_ANALYSIS -> {
                "İstatistik ekranına yönlendiriliyorsun."
            }
            IntentType.UNKNOWN -> {
                "Ne dediğini tam anlayamadım, tekrar söyler misin?"
            }
        }
    }
}
