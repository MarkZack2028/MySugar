package CatatanHarian;

public class Makanan {
    private String nama;
    private int kalori;

    public Makanan(String nama, int kalori) {
        this.nama = nama;
        this.kalori = kalori;
    }

    public String getNama() {
        return nama;
    }

    public int getKalori() {
        return kalori;
    }
}
