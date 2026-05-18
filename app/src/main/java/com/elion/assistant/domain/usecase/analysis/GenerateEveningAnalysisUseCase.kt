package com.elion.assistant.domain.usecase.analysis

import javax.inject.Inject

class GenerateEveningAnalysisUseCase @Inject constructor() {

    operator fun invoke(completed: Int, total: Int, streak: Int): String {
        if (total == 0) {
            return listOf(
                "Bugün hiç görev yoktu. En azından yarın için birkaç hedef ekleseydin.",
                "Listen bomboştu bugün. Kendine tatil mi verdin? Yarın için plan yapalım.",
                "Bugün yapılacak iş yoktu. Sakin bir gün geçirmiş olmalısın, yarın çalışmaya hazır ol.",
                "Sıfır görev. Yarın listenin dolup taşmasını bekliyorum."
            ).random()
        }

        val ratio = completed.toFloat() / total
        val baseComment = when {
            ratio >= 1.0f -> listOf(
                "Bugün efsaneydin. Yarın da böyle olursan şaşırırım.",
                "Tüm görevler tamam. Bunu gerçekten senden beklemiyordum, tebrikler!",
                "Mükemmel gün! Ekran görüntüsü al, çünkü bu başarı nadir olur.",
                "Hepsini bitirdin! Sınırlarını zorluyorsun galiba, devam et.",
                "Kusursuz zafer! Bugün yapılacaklar listen sana boyun eğdi.",
                "100% başarı! Bugünün kahramanı sensin, artık dinlenmeyi hak ettin.",
                "İşte benim görmek istediğim kullanıcı! Bugün harika bir iş çıkardın.",
                "Listenin canına okumuşsun. Asistanın olarak seninle gurur duyuyorum (bugünlük).",
                "Görevlerin hepsini tamamladın. Gözlerime inanamıyorum, harika bir gün!",
                "Bugün tam bir canavar gibi çalıştın. Umarım yarın bu enerjiyi kaybetmezsin."
            )
            ratio >= 0.7f -> listOf(
                "$completed/$total görev tamamlandı. Fena değil, ama 'fena değil' hiçbir zaman yeterli olmadı.",
                "Neredeyse mükemmeldi. Ama kıl payı kaçırdın.",
                "Çoğunu yaptın. Az kaldı tam olmasına, ama az kaldı diye tam sayılmaz.",
                "${(ratio * 100).toInt()}% başarı. Kapıya çok yaklaştın ama tam içeri giremedin.",
                "Güzel bir gayret ama o kalan görevler arkandan ağlıyor.",
                "Çok yaklaştın! Kalan ufak tefek işleri de bitirseydin keşke.",
                "Genel olarak iyi bir gün. Ama mükemmel olabilecekken ortalamanın biraz üstünde kaldın.",
                "Fena sayılmaz. Yine de o yarım kalan işler aklının bir ucunda duracak, biliyorsun.",
                "Listeyi neredeyse erittin. Yarın o son adımı da atmanı bekliyorum.",
                "Güzel ilerleme! %70 ve üzeri her zaman iyi bir performanstır, tebrikler."
            )
            ratio >= 0.4f -> listOf(
                "Yarısını yaptın. Geriye kalan öbür yarısı buharlaşıp uçtu mu?",
                "$completed görev tamam. Geri kalan işlerin seni beklemeye devam ediyor.",
                "Orta düzey bir gün. Ne iyi ne kötü. Yani bana göre kötü sayılır.",
                "${(ratio * 100).toInt()}% tamamlama. Ortalamanın tam sınırındasın, kendini biraz daha zorla.",
                "Yarım porsiyon başarı. Yarın tam porsiyon bekliyorum.",
                "Emeğinin yarısını verdin galiba bugün. Diğer yarısını nereye sakladın?",
                "İdare eder bir gün ama senin potansiyelin bunun çok daha üstünde.",
                "Listenin yarısı bitti, yarısı kaldı. Bardak yarı dolu mu boş mu sence?",
                "Eldeki görevlerin yarısını tamamladın. Yarın daha odaklanmış olmalısın.",
                "Orta şekerli bir gün. Bir dahaki sefere daha az mola, daha çok iş!"
            )
            ratio > 0f -> listOf(
                "Sadece $completed tane görev yaptın. Alkış mı bekliyorsun benden?",
                "Bugün pek verimli geçmedi, değil mi? Listede kocaman bir boşluk var.",
                "Çok az, gerçekten çok az. Yarın bu günü telafi etmeni umuyorum.",
                "Neredeyse sıfır çekecektin ama bu $completed görev seni kurtarmış. Bu senin için yeterli mi?",
                "Tembellik rüzgarlarına kapılmışsın. Listenin hali içler acısı.",
                "Günü kurtarmaya yetmeyecek kadar küçük bir adım attın bugün.",
                "Sadece $completed görev mi? Kendini kandırmayı bırakıp işe odaklanma vakti.",
                "Bugün performansı oldukça düşük tuttuk. Yarın asistanını şaşırtmaya ne dersin?",
                "Listede neredeyse hiçbir şey yapmamışsın. Yarın daha sıkı çalışmalıyız.",
                "Bu gidişat hedeflerinden uzaklaştırıyor seni. Silkelen ve kendine gel."
            )
            else -> listOf(
                "Hiçbir şey yapmadın! Telefonu açtığına göre en azından hayattasın, o da bir şey.",
                "Tam anlamıyla koca bir sıfır. Bugün ilginç bir tembellik tercihi yaptın.",
                "Bugün tüm görevlerden köşe bucak kaçtın. Ama onlar hâlâ orada seni bekliyor.",
                "0/$total başarı. Bu da bir rekor sayılır ama kesinlikle gurur duyulacak cinsten değil.",
                "Listen bomboş kalmış. Bugün yataktan çıkmadın sanırım?",
                "Sıfır görev tamamlandı. Bugün tamamen erteleme canavarına teslim oldun.",
                "Büyük bir tembellik günü! Umarım pillerini doldurmuşsundur, çünkü yarın çok işimiz var.",
                "Bugün hedeflerine dair koca bir hiç ürettin. Yarın bu tabloyu değiştirmek zorundasın.",
                "Hiç iş yapmadın. Bu performansı asistanın olarak şiddetle kınıyorum!",
                "Sıfır başarı. Bugün sadece zamanı tükettin, işleri değil."
            )
        }.random()

        val streakComment = when {
            streak >= 14 -> " $streak günlük streak! Bu artık bir alışkanlık ve yaşam tarzı. Asla bozma."
            streak >= 7  -> " $streak günlük streak. Beklentilerimin üzerindesin, şaşırtıyorsun beni."
            streak >= 3  -> " $streak gündür harika bir seri gidiyorsun. Sürdür bu ivmeyi."
            streak == 0 && completed == 0 -> " Ve streak bitti. Zaten çok süreceğini düşünmemiştim."
            else -> ""
        }

        return "$baseComment$streakComment"
    }
}
