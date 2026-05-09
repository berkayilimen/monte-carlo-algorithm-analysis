import java.util.*;

public class MonteCarloAnalizi {
    public static void main(String[] args) {
        // Parametreler
        long ogrenciNo = 1240505016L;
        int n = 1000000; // 10^6
        int k_iterasyon = 100; // Monte Carlo deneme sayısı
        int test_sayisi = 100;

        Random random = new Random(ogrenciNo); // Seed zorunluluğu

        // 1. Veri Seti Oluşturma (%1 oranında özel eleman '7' barındırır)
        int[] dizi = new int[n];
        for (int i = 0; i < n; i++) {
            // Rastgele sayılar, %1 ihtimalle 7 (özel eleman) üretir
            if (random.nextDouble() < 0.01) {
                dizi[i] = 7;
            } else {
                dizi[i] = random.nextInt(100) + 10; // 10-110 arası normal sayılar
            }
        }

        System.out.println("--- Monte Carlo Algoritma Analizi ---");
        System.out.println("Veri Boyutu: " + n);
        System.out.println("Hedef: '7' değerini k=" + k_iterasyon + " adımda bulmak.");

        int hataSayisi = 0;
        long toplamSure = 0;
        List<Long> sureler = new ArrayList<>();

        // 2. Deneyin 100 Kez Çalıştırılması
        for (int t = 0; t < test_sayisi; t++) {
            long baslangic = System.nanoTime();

            boolean bulundu = false;
            for (int i = 0; i < k_iterasyon; i++) {
                int rastgeleIndeks = random.nextInt(n);
                if (dizi[rastgeleIndeks] == 7) {
                    bulundu = true;
                    break;
                }
            }

            long bitis = System.nanoTime();
            long sure = bitis - baslangic;
            sureler.add(sure);
            toplamSure += sure;

            if (!bulundu) {
                hataSayisi++;
            }
        }

        // 3. İstatistiksel Sonuçlar
        double ortalamaSure = (toplamSure / (double) test_sayisi) / 1_000_000.0; // ms
        double hataOrani = (hataSayisi / (double) test_sayisi) * 100;

        // Standart Sapma Hesabı
        double kareliFarkToplami = 0;
        for (long s : sureler) {
            kareliFarkToplami += Math.pow((s / 1_000_000.0) - ortalamaSure, 2);
        }
        double standartSapma = Math.sqrt(kareliFarkToplami / test_sayisi);

        System.out.println("\n--- Deneysel Sonuçlar ---");
        System.out.println("Ortalama Çalışma Süresi: " + String.format("%.6f", ortalamaSure) + " ms");
        System.out.println("Zaman Standart Sapması: " + String.format("%.6f", standartSapma) + " ms");
        System.out.println("Gerçekleşen Hata Sayısı: " + hataSayisi + "/100");
        System.out.println("Deneysel Hata Oranı: %" + hataOrani);

        // Teorik Hesap: P(hata) = (1 - p)^k => (1 - 0.01)^100
        double teorikHata = Math.pow(0.99, 100) * 100;
        System.out.println("Teorik Hata Olasılığı: %" + String.format("%.2f", teorikHata));
    }
}
