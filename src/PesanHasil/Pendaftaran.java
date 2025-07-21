package PesanHasil;

public class Pendaftaran {
    private String namaLengkap;
    private String ttl;
    private String alamat;
    private String nomorTelepon;
    private String gender;

    public Pendaftaran(String namaLengkap, String ttl, String alamat, String nomorTelepon, String gender) {
        this.namaLengkap = namaLengkap;
        this.ttl = ttl;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.gender = gender;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getTtl() {
        return ttl;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public String getGender() {
        return gender;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
