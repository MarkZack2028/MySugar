package CatatanHarian;

public class CatatanHarianData {
    private String tanggal;
    private String makanPagi;
    private String makanSiang;
    private String makanMalam;
    private String username;
    private String hari;

    public CatatanHarianData(String username, String makanPagi, String makanSiang, String makanMalam,String tanggal) {
        this.tanggal = tanggal;
        this.makanPagi = makanPagi;
        this.makanSiang = makanSiang;
        this.makanMalam = makanMalam;
        this.username = username;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getMakanPagi() {
        return makanPagi;
    }

    public String getMakanSiang() {
        return makanSiang;
    }

    public String getMakanMalam() {
        return makanMalam;
    }
    public String getUsername(){
        return username;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setMakanPagi(String makanPagi) {
        this.makanPagi = makanPagi;
    }

    public void setMakanSiang(String makanSiang) {
        this.makanSiang = makanSiang;
    }

    public void setMakanMalam(String makanMalam) {
        this.makanMalam = makanMalam;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
    public String getHari(){
        return hari;
    }
    public int getTotalKalori() {
        int pagi = KaloriHelper.getKalori(makanPagi);
        int siang = KaloriHelper.getKalori(makanSiang);
        int malam = KaloriHelper.getKalori(makanMalam);
        return pagi + siang + malam;
    }
    
}
