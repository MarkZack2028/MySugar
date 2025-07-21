package CatatanHarian;

public class CatatanMakanan {
    private String tanggal;
    private String menuPagi;
    private String menuSiang;
    private String menuMalam;

    public CatatanMakanan(String tanggal, String menuPagi, String menuSiang, String menuMalam) {
        this.tanggal = tanggal;
        this.menuPagi = menuPagi;
        this.menuSiang = menuSiang;
        this.menuMalam = menuMalam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getMenuPagi() {
        return menuPagi;
    }

    public String getMenuSiang() {
        return menuSiang;
    }

    public String getMenuMalam() {
        return menuMalam;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setMenuPagi(String menuPagi) {
        this.menuPagi = menuPagi;
    }

    public void setMenuSiang(String menuSiang) {
        this.menuSiang = menuSiang;
    }

    public void setMenuMalam(String menuMalam) {
        this.menuMalam = menuMalam;
    }

}
