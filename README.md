# 🤖 ELION — Kişisel Asistan Android Uygulaması

> **"Sıradan bir yapılacaklar listesi değil. Seni tanıyan, dürüst, eleştirel ve biraz da komik bir kişisel asistan."**

ELION, yerel veritabanında (Room DB) çalışan, sesli komut algılama yeteneğine sahip, görev takibi ve gün sonu akıllı analiz (sabah brifingi ve akşam değerlendirmesi) sunan, tamamen kişisel kullanım için tasarlanmış modern bir Android uygulamasıdır.

---

## 🌟 Projenin Amacı ve Vizyonu

*   **Tek Kullanıcı:** Hesap oluşturma yok, bulut yok, veri paylaşımı yok. Her şey tamamen telefonunuzda güvenle saklanır.
*   **Akıllı Sabah Brifingi:** Güne başlarken asistanınızdan o günkü planları ve tatlı sert motive edici yorumları dinleyin.
*   **Dürüst Akşam Analizi:** Gün sonunda asistanınız sizi analiz eder; dürüst, eleştirel ve eğlenceli bir dille ne kadar verimli olduğunuzu yüzünüze vurur.
*   **Hız ve Performans:** Uygulamayı açmak 3 saniyeden kısa sürer. Sıfır gecikme, üst düzey akıcılık.

---

## 🛠️ Teknolojik Altyapı (Tech Stack)

*   **Dil:** Kotlin
*   **UI Framework:** Jetpack Compose (Modern Bildirimsel Arayüz, HSL Temelli Koyu Tema tasarımı)
*   **Mimari:** MVVM (Model-View-ViewModel) + Clean Architecture
*   **Veritabanı:** Room Database (Tamamen yerel, SQL tabanlı veri saklama)
*   **Bağımlılık Enjeksiyonu (DI):** Dagger Hilt
*   **Asenkron Programlama:** Kotlin Coroutines & Flow (Reaktif veri akışı)
*   **Grafikler:** Vico Charts (Haftalık performans analiz grafiği)
*   **Arka Plan İşleri:** WorkManager (Akşam analizleri ve alarm tetikleyicileri)

---

## 🚀 Temel Özellikler (Features)

### 1. 📅 Görev Yönetimi & Dinamik Kategoriler
*   **Zengin Görev Formu:** Görev başlığı, detaylı açıklama, tarih ve hatırlatıcı saat seçimi.
*   **Dinamik Kategori Sistemi:** Kategorileri istediğiniz isimle ve sunduğumuz özel **Renk Paleti (Color Presets)** ile özelleştirin.
*   **Tekrarlayan Görevler:** Günlük, Haftalık veya Aylık tekrarlayan rutin görevler tanımlayın.
*   **Öncelik Seviyeleri:** Düşük, Normal ve Yüksek öncelik renkleriyle görevlerinizi görselleştirin.
*   **Hızlı Silme:** Görev kartlarındaki çöp kutusu 🗑️ ikonuyla tek dokunuşla görevi kaldırın.

### 🎤 2. Sesli Komut Modülü
*   **Mikrofon Kontrolü:** Ana ekranda yer alan ortalanmış büyük mikrofon butonuna basarak asistana sesli komutlar verin.
*   *Örn: "Yarın saat 9'da spora git"* komutunu algılar, saati ve tarihi ayrıştırarak otomatik olarak görevi oluşturur.

### 📊 3. Gelişmiş İstatistikler & Grafik
*   **Streak (Seri) Sistemi:** Görevleri arka arkaya tamamlayarak serinizi (streak) koruyun ve asistanınızın gözüne girin!
*   **Vico Grafik:** Son 7 güne ait tamamlanma oranlarını görselleştiren modern çubuk grafik.

### ⚙️ 4. Kişiselleştirilmiş Ayarlar
*   **Asistan Adı:** Asistanınızın ismini dilediğiniz gibi değiştirin (örn: "ELION").
*   **Kategori Yönetimi:** Yeni kategoriler ekleyin, renklerini belirleyin veya kullanılmayanları silin (Varsayılan kategoriler silinmeye karşı korumalıdır).
*   **Analiz Saatleri:** Sabah brifingi ve Akşam analizi bildirim saatlerini kendinize göre ayarlayın.
*   **Verileri Sıfırla:** Tek dokunuşla tüm veritabanını temizleyip sıfırdan başlayın.

---

## 🛠️ Kurulum ve Çalıştırma

1.  Bu depoyu bilgisayarınıza klonlayın:
    ```bash
    git clone https://github.com/emir-canswe/elionMobile.git
    ```
2.  **Android Studio**'yu (Koala veya daha yeni bir sürüm) açın.
3.  `Open Project` seçeneğiyle bu klasörü seçin.
4.  Gradle senkronizasyonunun tamamlanmasını bekleyin.
5.  Uyumlu bir Android Emülatör veya fiziksel cihaz bağlayarak **Run (Çalıştır)** butonuna basın.

---

## 🔒 Güvenlik & Gizlilik

Bu uygulama **%100 Çevrimdışı (Offline-First)** çalışacak şekilde tasarlanmıştır. Ses verileriniz, görevleriniz, kişisel analizleriniz ve ayarlarınız hiçbir sunucuya gönderilmez, üçüncü şahıslarla paylaşılmaz. Telefonunuz sizin kalenizdir!

---

*ELION ile günlerinizi daha planlı, eğlenceli ve verimli hale getirin!* 🌟
