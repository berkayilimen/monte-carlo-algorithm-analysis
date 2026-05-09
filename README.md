# Monte Carlo Algoritma Analizi

Bu depo, büyük veri setleri üzerinde rastgeleliğin (randomness) zaman karmaşıklığı ve doğruluk payı üzerindeki etkisini inceleyen akademik bir algoritma analizi projesini içermektedir.

## 📌 Proje Hakkında
Proje kapsamında, klasik deterministik arama yöntemlerinin donanımsal yükünü ve O(n) maliyetini hafifletmek amacıyla olasılıksal bir yaklaşım olan Monte Carlo randomize algoritması uygulanmıştır. 

Algoritma, içerisinde belirli bir oranda "özel eleman" barındıran devasa bir veri seti üzerinde test edilmiş, teorik hata olasılığı ile deneysel sonuçlar zaman karmaşıklığı ve standart sapma metrikleriyle birlikte karşılaştırmalı olarak analiz edilmiştir.

## ⚙️ Parametreler ve Veri Seti
Proje konfigürasyonu, testlerin güvenilirliği için belirli parametrelere sabitlenmiştir:
* Algoritma Tipi: Monte Carlo (Sınırlı iterasyon, olasılıksal doğruluk)
* Veri Hacmi (n): 1.000.000
* Özel Eleman Oranı (p): %1 (0.01)
* İterasyon Limiti (k): 100
* Rastgelelik Tohumu (Seed): 1240505016 (Bilimsel tekrarlanabilirlik ve stabil deney ortamı için zorunlu kılınmıştır.)

## 🧮 Teorik ve Deneysel Hata Analizi
Monte Carlo algoritmaları, kısıtlı zaman zarfında kesin olmayan ancak yüksek doğruluk oranına sahip sonuçlar üretir. Özel elemanın dizide bulunmasına rağmen algoritmanın bulamaması durumu "False Negative" (Yanlış Negatif) olarak adlandırılır. Hata yapma olasılığı aşağıdaki formülle hesaplanır:

P(error) = (1 - p)^k

Bu projedeki parametreler uygulandığında teorik hata beklentisi:
P(error) = (1 - 0.01)^100 = 0.99^100 = %36.60

Yazılan Java kodu ile gerçekleştirilen 100 deneysel test sonucunda ortaya çıkan hata oranı, matematiksel beklentiyle (~%36) kusursuz bir uyum göstermektedir. Bu durum olasılıksal algoritmaların istatistiksel güvenilirliğini ispatlamaktadır.

## 🚀 Kurulum ve Çalıştırma
Projeyi yerel makinenizde derleyip çalıştırabilmek için terminalinizde (komut satırında) sırasıyla şu komutları tek tek çalıştırın:

git clone https://github.com/berkayilimen/monte-carlo-algorithm-analysis.git
cd monte-carlo-algorithm-analysis
javac MonteCarloAnalizi.java
java MonteCarloAnalizi

## 📊 Analiz Çıktıları ve Zaman Karmaşıklığı
Program çalıştırıldığında konsol üzerinde aşağıdaki metrikler görüntülenecektir:
* 100 test için ortalama çalışma süresi (ms)
* Zaman standart sapması
* Deneysel hata oranı ve hata sayısı
* Teorik hata olasılığı

Zaman standart sapmasının yüksek çıkmasının temel nedeni, rastgele seçimlerin O(1) ile O(k) arasında değişen arama süreleri üretmesidir. Ancak en kötü durum (worst-case) senaryosunda dahi zaman karmaşıklığı mutlak suretle O(k) ile sınırlandırılmış olup, O(n) donanım kilitleme riski tamamen ortadan kaldırılmıştır.
