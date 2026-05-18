package com.elion.assistant.domain.usecase.analysis

import com.elion.assistant.domain.model.PostponeAlert
import com.elion.assistant.domain.model.Task
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class CheckPostponedTasksUseCase @Inject constructor() {

    operator fun invoke(tasks: List<Task>): List<PostponeAlert> {
        val today = LocalDate.now()
        return tasks
            .filter { !it.isCompleted }
            .mapNotNull { task ->
                val days = ChronoUnit.DAYS.between(task.createdAt.toLocalDate(), today)
                if (days < 2) return@mapNotNull null

                val comment = when {
                    days >= 7 -> listOf(
                        "Bu görev burada yaşıyor artık. $days gündür.",
                        "$days gün oldu. Bu görev seninle yaşlanıyor.",
                        "Bu görev kalıcı dekor oldu sanırım. $days gündür burada.",
                    )
                    days >= 4 -> listOf(
                        "$days gündür bu görevi görüyorum. Sen görmüyor musun?",
                        "Bu görev $days gündür burada. Haberin var mı?",
                        "$days gün. Bu görevi görmezden geliyorsun.",
                    )
                    else -> listOf(
                        "2 gündür bekliyor. Bugün yaparsan iyi olur.",
                        "Bu görev 2 gündür kuyrukta. Ne yapacaksın?",
                        "İki gün oldu. Erteleme mi, unutma mı?",
                    )
                }.random()

                PostponeAlert(task, comment, days)
            }
    }
}
