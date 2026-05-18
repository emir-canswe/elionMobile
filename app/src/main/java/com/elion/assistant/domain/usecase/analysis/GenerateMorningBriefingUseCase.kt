package com.elion.assistant.domain.usecase.analysis

import com.elion.assistant.domain.model.Task
import java.time.LocalDate
import javax.inject.Inject

class GenerateMorningBriefingUseCase @Inject constructor() {

    operator fun invoke(tasks: List<Task>, assistantName: String = "ELION"): String {
        val today = LocalDate.now()
        val todayTasks = tasks.filter { it.dueDate == today && !it.isCompleted }
        val overdueCount = tasks.count { t ->
            t.dueDate != null && t.dueDate < today && !t.isCompleted
        }

        val greetings = listOf(
            "Günaydın. Umarım dün geceyi iyi geçirmişsindir.",
            "Günaydın. Bir yeni gün, bir yeni şans. Harcama.",
            "Kalktın mı? İyi. Başlayalım.",
            "Günaydın. $assistantName burada, hazır.",
            "Yeni bir gün. Ne yapacaksın bugün?",
        )

        val taskInfo = when {
            todayTasks.isEmpty() && overdueCount == 0 ->
                "Bugün için görev yok. Ya çok organizesin ya da çok tembelsin."
            todayTasks.isEmpty() && overdueCount > 0 ->
                "Bugün için görev yok ama $overdueCount eski görevin bekliyor. Onları unuttun sanma."
            overdueCount > 0 ->
                "Bugün ${todayTasks.size} görevin var. Bir de $overdueCount eski görev. Onlar da sayılıyor."
            else ->
                "Bugün ${todayTasks.size} görevin var." +
                if (todayTasks.size == 1) " Tek görev. Bunu yapabileceğini umuyorum."
                else " Hepsini bitirirsen sürpriz yaparım."
        }

        return "${greetings.random()} $taskInfo"
    }
}
