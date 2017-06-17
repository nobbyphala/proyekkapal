package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class resiko {
    String no, nama, potensi, resiko, penanganan;

    public resiko(String no, String nama, String potensi, String resiko, String penanganan) {
        this.no = no;
        this.nama = nama;
        this.potensi = potensi;
        this.resiko = resiko;
        this.penanganan = penanganan;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPotensi() {
        return potensi;
    }

    public void setPotensi(String potensi) {
        this.potensi = potensi;
    }

    public String getResiko() {
        return resiko;
    }

    public void setResiko(String resiko) {
        this.resiko = resiko;
    }

    public String getPenanganan() {
        return penanganan;
    }

    public void setPenanganan(String penanganan) {
        this.penanganan = penanganan;
    }
}
