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
            "Günaydın! Umarım bugün dünden daha üretken olursun (çıta çok düşük zaten).",
            "Gözlerini açabildin mi sonunda? Harika, şimdi çalışma vakti.",
            "Yeni bir gün, yeni hedefler. Bakalım bugün hangilerini erteleyeceksin?",
            "Günaydın şampiyon... demek isterdim ama henüz ortada bir şampiyonluk göremiyorum.",
            "Kalktın mı? Güzel. Telefonu bırak ve listene odaklan.",
            "Günaydın! $assistantName senin için iş başı yaptı, sen de yap artık.",
            "Güneş doğdu, kuşlar ötüyor ve yapılacak işler dağ gibi birikmiş bekliyor.",
            "Uyanma vaktin geldi! Bugün erteleme tuşuna basmaman dileğiyle...",
            "Günaydın. Umarım bugün kahven kadar güçlü ve kararlı olursun.",
            "Yeni bir gün başladı. Zaman daralıyor, hedeflerin seni bekliyor.",
            "Gözlerini açtın, peki zihnini açtın mı? Hadi başlayalım.",
            "Günaydın! Bugün asistanın olarak seni izliyor olacağım, ona göre davran.",
            "Uyan uyan! Bugün bahaneler üretmek yerine iş yapacağın gün olsun.",
            "Günaydın. Bugün yapılacaklar listen ile aranda küçük bir savaş olacak, umarım kazanırsın.",
            "Sonunda uyandın. Zaman akıyor, listene bir göz atma zamanı."
        )

        val taskInfo = when {
            todayTasks.isEmpty() && overdueCount == 0 -> {
                listOf(
                    "Bugün için hiçbir görevin yok. Ya muhteşem bir planlama yaptın ya da tam bir tembellik günündesin.",
                    "Bugün yapılacak hiçbir işin yok! Kendine bir kahve ısmarla ama rehavete kapılma.",
                    "Sıfır görev, sıfır stres. Bugün tamamen özgürsün, tadını çıkar.",
                    "Listen bomboş. Bu sakinlik beni endişelendiriyor açıkçası..."
                ).random()
            }
            todayTasks.isEmpty() && overdueCount > 0 -> {
                listOf(
                    "Bugün için yeni bir görevin yok ama geçmişten biriken $overdueCount adet görevin sana el sallıyor. Onlardan kaçamazsın.",
                    "Yeni işin yok ama $overdueCount adet tarihi geçmiş borcun var bana. Hadi o eski işleri hemen bitir.",
                    "Bugün listen boş gibi duruyor ama arkada bekleyen $overdueCount hayalet görev var. Onları temizleme vakti geldi.",
                    "Yeni görev yok diye sevinme, o bekleyen $overdueCount eski görev kendiliğinden yapılmayacak."
                ).random()
            }
            overdueCount > 0 -> {
                listOf(
                    "Bugün yapman gereken ${todayTasks.size} yeni görev var. Üstüne bir de geçmişten sarkan $overdueCount görevin yükü ekleniyor. Kolay gelsin!",
                    "Bugün ${todayTasks.size} görevin var, ama asıl mesele o bekleyen $overdueCount eski görev. Geçmişinle yüzleşme vakti.",
                    "Planında ${todayTasks.size} iş var. Ayrıca arkada çığ gibi büyüyen $overdueCount eski görev seni bekliyor. Acele etsen iyi olur.",
                    "Bugün listende ${todayTasks.size} görev duruyor. Tabi $overdueCount adet ertelediğin eski görevi de unutmamak lazım."
                ).random()
            }
            else -> {
                if (todayTasks.size == 1) {
                    listOf(
                        "Sadece tek bir görevin var. Sadece bir! Bunu da yapamazsan asistanlığı bırakıyorum.",
                        "Bugün listende tek bir iş var. Bunu hallet ve günün geri kalanını huzurla geçir.",
                        "Sadece 1 görev. Çocuk oyuncağı olmalı. Hadi göreyim seni.",
                        "Önünde sadece tek bir engel var. Odaklan ve onu hemen bitir."
                    ).random()
                } else {
                    listOf(
                        "Bugün listende ${todayTasks.size} görev var. Hepsini bitirirsen sana güzel bir asistan tebriki var.",
                        "Tam ${todayTasks.size} görevin var bugün. Planlı gidersen akşamı rahat geçirirsin.",
                        "Bugün ${todayTasks.size} iş bizi bekler. Erteleme tuşundan uzak durursan hepsini bitiririz.",
                        "Listende ${todayTasks.size} görev duruyor. Teker teker başla, akşama analizde yüzün gülsün."
                    ).random()
                }
            }
        }

        return "${greetings.random()} $taskInfo"
    }
}
