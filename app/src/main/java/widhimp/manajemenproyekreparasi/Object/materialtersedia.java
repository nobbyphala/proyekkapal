package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class materialtersedia {
    String no, nama, spesifikasi, jumlah;

    public materialtersedia(String no, String nama, String spesifikasi, String jumlah) {
        this.no = no;
        this.nama = nama;
        this.spesifikasi = spesifikasi;
        this.jumlah = jumlah;
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

    public String getSpesifikasi() {
        return spesifikasi;
    }

    public void setSpesifikasi(String spesifikasi) {
        this.spesifikasi = spesifikasi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}
