package com.elion.assistant.domain.usecase.analysis

import javax.inject.Inject

class GenerateEveningAnalysisUseCase @Inject constructor() {

    operator fun invoke(completed: Int, total: Int, streak: Int): String {
        if (total == 0) return "Bugün hiç görev yoktu. Yarın için ekle en azından."

        val ratio = completed.toFloat() / total
        val baseComment = when {
            ratio >= 1.0f -> listOf(
                "Bugün efsaneydin. Yarın da böyle olursan şaşırırım.",
                "Tüm görevler tamam. Bu senden beklemiyordum.",
                "Mükemmel gün. Ekran görüntüsü al, nadir olur.",
                "Hepsini bitirdin. Sınırlarını zorluyorsun galiba.",
            )
            ratio >= 0.7f -> listOf(
                "$completed/$total görev. Fena değil. Ama 'fena değil' hiçbir zaman yeterli olmadı.",
                "Neredeyse. Neredeyse yeterliydi.",
                "Çoğunu yaptın. Az kaldı tam olmasına, ama az kaldı diye tam sayılmaz.",
                "${(ratio * 100).toInt()}%. Kapıya yakın ama içeri giremiyorsun.",
            )
            ratio >= 0.4f -> listOf(
                "Yarısını yaptın. Öbür yarısı hava mı oldu?",
                "$completed tane. Geri kalanlar seni bekliyor, biliyorsun.",
                "Orta düzey bir gün. Ne iyi ne kötü. Yani kötü sayılır.",
                "${(ratio * 100).toInt()}% tamamlama. Ortadan biraz aşağı. Düşün bunu.",
            )
            ratio > 0f -> listOf(
                "Bir tane yaptın. Bir tane. Alkış mı bekliyorsun?",
                "$completed görev. Bugün pek verimli geçmedi, değil mi?",
                "Az. Çok az. Yarın telafi edersin umarım.",
                "Neredeyse sıfır. Ama sıfır değil. Bu senin için yeterince iyi mi?",
            )
            else -> listOf(
                "Hiçbir şey yapmadın. Telefonu açtığına göre en azından hayattasın.",
                "Sıfır. Tam anlamıyla sıfır. İlginç bir tercih.",
                "Bugün görevlerden kaçtın. Onlar hâlâ orada.",
                "0/$total. Bu bir rekord, ama gurur duyulacak türden değil.",
            )
        }.random()

        val streakComment = when {
            streak >= 14 -> " $streak günlük streak. Bu artık bir alışkanlık. Sürdür."
            streak >= 7  -> " $streak günlük streak. Bunu beklemiyordum açıkçası."
            streak >= 3  -> " $streak gündür devam ediyor. Sürdür."
            streak == 0  -> " Streak bitti. Çok sürmedi, değil mi?"
            else -> ""
        }

        return "$baseComment$streakComment"
    }
}
