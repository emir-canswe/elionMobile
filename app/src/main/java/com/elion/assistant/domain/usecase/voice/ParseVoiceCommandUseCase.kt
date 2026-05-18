package com.elion.assistant.domain.usecase.voice

import com.elion.assistant.domain.model.Priority
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

data class ParsedIntent(
    val type: IntentType,
    val taskTitle: String? = null,
    val date: LocalDate? = null,
    val priority: Priority? = null,
)

enum class IntentType {
    ADD_TASK, COMPLETE_TASK, DELETE_TASK,
    READ_LIST, SHOW_ANALYSIS, UNKNOWN
}

class ParseVoiceCommandUseCase @Inject constructor() {

    operator fun invoke(text: String): ParsedIntent {
        val lower = text.lowercase().trim()

        // Tamamlama tetikleyicileri (önce kontrol — daha spesifik)
        val doneTriggers = listOf("tamamla", "bitti", "yaptım", "oldu", "tamam", "bitirdim", "hallettim")
        // Silme tetikleyicileri
        val deleteTriggers = listOf("sil", "iptal", "kaldır")
        // Sorgulama tetikleyicileri
        val queryTriggers = listOf("ne var", "listele", "oku", "söyle", "kaç görev", "görevlerim")
        // Analiz tetikleyicileri
        val analysisTriggers = listOf("nasıl gitti", "istatistik", "analiz", "özet", "değerlendirme")
        // Görev ekleme tetikleyicileri
        val addTriggers = listOf("ekle", "var", "yap", "hazırla", "al", "git", "öde", "gönder", "ara", "oku", "bitir")

        // Tarih çözümleme
        val date = resolveDate(lower)

        // Öncelik çözümleme
        val priority = when {
            lower.contains("acil") || lower.contains("önemli") || lower.contains("hemen") -> Priority.HIGH
            lower.contains("bekleyebilir") || lower.contains("sonra") -> Priority.LOW
            else -> Priority.NORMAL
        }

        return when {
            analysisTriggers.any { lower.contains(it) } ->
                ParsedIntent(IntentType.SHOW_ANALYSIS)

            doneTriggers.any { lower.contains(it) } ->
                ParsedIntent(IntentType.COMPLETE_TASK, extractTaskName(lower, doneTriggers))

            deleteTriggers.any { lower.contains(it) } ->
                ParsedIntent(IntentType.DELETE_TASK, extractTaskName(lower, deleteTriggers))

            queryTriggers.any { lower.contains(it) } ->
                ParsedIntent(IntentType.READ_LIST, date = date)

            addTriggers.any { lower.contains(it) } ->
                ParsedIntent(IntentType.ADD_TASK, extractTaskName(lower, addTriggers), date, priority)

            else ->
                ParsedIntent(IntentType.UNKNOWN)
        }
    }

    private fun resolveDate(text: String): LocalDate {
        val today = LocalDate.now()
        return when {
            text.contains("bugün")     -> today
            text.contains("yarın")     -> today.plusDays(1)
            text.contains("pazartesi") -> nextOrSameDayOfWeek(DayOfWeek.MONDAY)
            text.contains("salı")     -> nextOrSameDayOfWeek(DayOfWeek.TUESDAY)
            text.contains("çarşamba") -> nextOrSameDayOfWeek(DayOfWeek.WEDNESDAY)
            text.contains("perşembe") -> nextOrSameDayOfWeek(DayOfWeek.THURSDAY)
            text.contains("cuma")     -> nextOrSameDayOfWeek(DayOfWeek.FRIDAY)
            text.contains("cumartesi") -> nextOrSameDayOfWeek(DayOfWeek.SATURDAY)
            text.contains("pazar")    -> nextOrSameDayOfWeek(DayOfWeek.SUNDAY)
            else -> today
        }
    }

    private fun nextOrSameDayOfWeek(day: DayOfWeek): LocalDate =
        LocalDate.now().with(TemporalAdjusters.nextOrSame(day))

    private fun extractTaskName(text: String, triggers: List<String>): String {
        var cleaned = text
        // Tarih kelimelerini temizle
        val dateWords = listOf(
            "bugün", "yarın", "pazartesi", "salı", "çarşamba",
            "perşembe", "cuma", "cumartesi", "pazar",
            "acil", "önemli", "hemen", "bekleyebilir", "sonra",
        )
        (triggers + dateWords).forEach { word ->
            cleaned = cleaned.replace(word, "")
        }
        return cleaned.trim()
            .replace(Regex("\\s+"), " ")
            .replaceFirstChar { it.uppercaseChar() }
            .ifBlank { "Görev" }
    }
}
