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
            "Sonunda uyandın. Zaman akıyor, listene bir göz atma zamanı.",
            "Günaydın! Güneş doğdu ama senin hedeflerin hâlâ uyuyor.",
            "Yeni gün başladı. Bakalım bugün listenle arandaki mücadeleyi kim kazanacak?",
            "Kalk kalk! Bugün kahveden alacağın kafeinden daha fazlasına ihtiyacın olacak.",
            "Günaydın. Bugün kendinden bir şeyler vermen gereken o büyük gün.",
            "Uyanabildin mi? Harika, şimdi o yatağı terk et ve listene saldırmaya başla.",
            "Günün ilk ışıkları listenin üzerini aydınlatıyor. Hadi o işleri bitirelim.",
            "Bugün erteleme tuşuna basmadıysan şimdiden bir sıfır öndesin!",
            "Yeni bir gün, yeni bir sayfa. Umarım üzerine koca bir 'ertelenen görevler' yazısı yazmazsın.",
            "Uyanma vakti geldi dostum! Zaman akıp gidiyor, hedeflerin seni bekliyor.",
            "Sonunda uyandın. Bugün o listeyi eritmek için muazzam bir enerjiye ihtiyacımız var.",
            "Yeni bir güne başladık. Ertelemek için hâlâ vaktin var ama yapmasan daha iyi olur.",
            "Günün bu erken saatlerinde hedeflerin seni bekliyor. Hadi yola koyulalım.",
            "Zaman akıyor, hedefler bekliyor. Bugün o büyük gün olsun!",
            "Günaydın. Bugün dünden daha iyi olmak için harika bir gün.",
            "Kalktın mı sonunda? Hadi listene bir göz at ve ilk adımı at.",
            "Günaydın! Bugün listenin canına okuma günü.",
            "Yeni bir gün, yeni heyecanlar. Tabi görevlerini bitirebilirsen...",
            "Günaydın. Zamanı boşa harcamamak için hedeflerine odaklan.",
            "Kalk ve harekete geç! Başarı erteleyenlerin değil, harekete geçenlerin olur.",
            "Sonunda aramıza katılabildin. Hadi bugünü unutulmaz bir üretkenlik günü yapalım!"
        )

        val taskInfo = when {
            todayTasks.isEmpty() && overdueCount == 0 -> {
                listOf(
                    "Bugün için hiçbir görevin yok. Ya muhteşem bir planlama yaptın ya da tam bir tembellik günündesin.",
                    "Bugün yapılacak hiçbir işin yok! Kendine bir kahve ısmarla ama rehavete kapılma.",
                    "Sıfır görev, sıfır stres. Bugün tamamen özgürsün, tadını çıkar.",
                    "Listen bomboş. Bu sakinlik beni endişelendiriyor açıkçası...",
                    "Bugün görev listen tertemiz. Dinlenmek için harika bir gün ama yarını unutma.",
                    "Planında tek bir iş bile yok. Bugün rüzgar nereden eserse oraya git.",
                    "Hiç görevin yok! Bugün zihnini boşaltmak ve dinlenmek için mükemmel bir fırsat.",
                    "Bugün listen tamamen boş. Şanslı günündesin sanırım!",
                    "Görev listen bomboş duruyor. Bugün tamamen kendine vakit ayırabilirsin.",
                    "Bugün yapılacaklar listen sıfır gösteriyor. Bu sessizliğin tadını çıkar."
                ).random()
            }
            todayTasks.isEmpty() && overdueCount > 0 -> {
                listOf(
                    "Bugün için yeni bir görevin yok ama geçmişten biriken $overdueCount adet görevin sana el sallıyor. Onlardan kaçamazsın.",
                    "Yeni işin yok ama $overdueCount adet tarihi geçmiş borcun var bana. Hadi o eski işleri hemen bitir.",
                    "Bugün listen boş gibi duruyor ama arkada bekleyen $overdueCount hayalet görev var. Onları temizleme vakti geldi.",
                    "Yeni görev yok diye sevinme, o bekleyen $overdueCount eski görev kendiliğinden yapılmayacak.",
                    "Listen güya boş ama geçmişten gelen $overdueCount görev gölge gibi seni takip ediyor.",
                    "Yeni işimiz yok ama geçmişin yükü ağır. O bekleyen $overdueCount eski göreve saldıralım.",
                    "Bugün yeni bir hedefimiz yok ama o ertelenen $overdueCount görevi eritmek için harika bir fırsat.",
                    "Listen boş görünebilir ama arkada biriken $overdueCount adet eski görev çığ gibi büyüyor.",
                    "Yeni iş yok ama $overdueCount eski görevle yüzleşme vakti geldi. Hadi erteleme artık.",
                    "Bugün yeni iş yok ama geçmişteki o $overdueCount hatayı düzeltmek ve görevleri bitirmek için tam zamanı."
                ).random()
            }
            overdueCount > 0 -> {
                listOf(
                    "Bugün yapman gereken ${todayTasks.size} yeni görev var. Üstüne bir de geçmişten sarkan $overdueCount görevin yükü ekleniyor. Kolay gelsin!",
                    "Bugün ${todayTasks.size} görevin var, ama asıl mesele o bekleyen $overdueCount eski görev. Geçmişinle yüzleşme vakti.",
                    "Planında ${todayTasks.size} iş var. Ayrıca arkada çığ gibi büyüyen $overdueCount eski görev seni bekliyor. Acele etsen iyi olur.",
                    "Bugün listende ${todayTasks.size} görev duruyor. Tabi $overdueCount adet ertelediğin eski görevi de unutmamak lazım.",
                    "Bugün listendeki ${todayTasks.size} yeni işe odaklanırken arkadan el sallayan $overdueCount eski görevi de göz ardı etme.",
                    "Tam ${todayTasks.size} yeni görevin var, ama geçmişten sarkan o $overdueCount hayalet görev aklını kurcalamaya devam edecek.",
                    "Bugün ${todayTasks.size} işimiz var. Tabii ki dünden kalan o $overdueCount borcu da ödememiz gerekiyor.",
                    "Listende ${todayTasks.size} görev var ve üstüne $overdueCount eski görev yük bindiriyor. Bugün sıkı çalışmalıyız.",
                    "Yeni ${todayTasks.size} görev ve eski $overdueCount görev... Toplamda büyük bir dağ bizi bekliyor, hadi tırmanmaya başla!",
                    "Bugün ${todayTasks.size} yeni iş ve $overdueCount ertelenmiş eski iş var. Planlı gitmezsen akşam perişan olursun."
                ).random()
            }
            else -> {
                if (todayTasks.size == 1) {
                    listOf(
                        "Sadece tek bir görevin var. Sadece bir! Bunu da yapamazsan asistanlığı bırakıyorum.",
                        "Bugün listende tek bir iş var. Bunu hallet ve günün geri kalanını huzurla geçir.",
                        "Sadece 1 görev. Çocuk oyuncağı olmalı. Hadi göreyim seni.",
                        "Önünde sadece tek bir engel var. Odaklan ve onu hemen bitir.",
                        "Bugünlük tek bir hedefin var. Bütün enerjini ona odakla ve hemen bitir.",
                        "Sadece bir işimiz var bugün. Onu da aradan çıkarıp rahat nefes alalım.",
                        "Listende sadece tek bir görev ışıldıyor. Hadi onu hemen söndürelim.",
                        "Bugün önündeki tek bir göreve odaklan. Başarı kaçınılmaz olmalı.",
                        "Sadece 1 iş. Bahaneler üretmek için hiçbir sebebin yok bugün.",
                        "Günün tek bir görevi var. Hadi hızlıca hallet ve özgürlüğünü ilan et."
                    ).random()
                } else {
                    listOf(
                        "Bugün listende ${todayTasks.size} görev var. Hepsini bitirirsen sana güzel bir asistan tebriki var.",
                        "Tam ${todayTasks.size} görevin var bugün. Planlı gidersen akşamı rahat geçirirsin.",
                        "Bugün ${todayTasks.size} iş bizi bekler. Erteleme tuşundan uzak durursan hepsini bitiririz.",
                        "Listende ${todayTasks.size} görev duruyor. Teker teker başla, akşama analizde yüzün gülsün.",
                        "Bugün tam ${todayTasks.size} farklı görevimiz var. Zamanı iyi yönetirsen hepsini eritebiliriz.",
                        "Planında ${todayTasks.size} iş parıldıyor. Hadi enerjini topla ve teker teker üzerlerini çiz.",
                        "Bugün ${todayTasks.size} yeni hedefimiz var. Hepsini tamamlayıp asistanını şaşırtmaya ne dersin?",
                        "Listede ${todayTasks.size} görev var. İşlerin birikmesine izin vermeden hemen başla.",
                        "Tam ${todayTasks.size} görevin var bugün. Sıkı çalışıp akşamı huzurla dinlenerek geçirelim.",
                        "Bugün ${todayTasks.size} işimiz var. Kendine inan, planına sadık kal ve hepsini bitir!"
                    ).random()
                }
            }
        }

        return "${greetings.random()} $taskInfo"
    }
}
