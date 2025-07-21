package CatatanHarian;

import java.util.HashMap;
import java.util.Map;

public class KaloriHelper {

    private static final Map<String, Integer> kaloriMap = new HashMap<>();

    static {
        kaloriMap.put("Nasi Goreng", 350);
        kaloriMap.put("Ayam Bakar", 250);
        kaloriMap.put("Roti Bakar", 180);
        kaloriMap.put("Soto Ayam", 300);
        kaloriMap.put("Nasi Uduk", 400);
        kaloriMap.put("Telur Rebus", 100);
        kaloriMap.put("Sate Ayam", 280);
        kaloriMap.put("Mie Goreng", 370);
        kaloriMap.put("Gado-Gado", 330);
        kaloriMap.put("Omelet", 200);
        // Tambahkan sesuai kebutuhan
    }

    public static int getKalori(String makanan) {
        if (makanan == null) return 0;
        return kaloriMap.getOrDefault(makanan.trim(), 0);
    }
}

