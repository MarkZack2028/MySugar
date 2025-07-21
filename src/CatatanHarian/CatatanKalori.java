package CatatanHarian;
public class CatatanKalori {
    private String hari;
    private int totalKalori;

    public CatatanKalori(String hari, int totalKalori) {
        this.hari = hari;
        this.totalKalori = totalKalori;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public int getTotalKalori() {
        return totalKalori;
    }

    public void setTotalKalori(int totalKalori) {
        this.totalKalori = totalKalori;
    }
}
