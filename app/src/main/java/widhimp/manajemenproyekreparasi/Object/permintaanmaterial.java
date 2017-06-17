package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 12/31/2016.
 */

public class permintaanmaterial {
    String no, nama, spesifikasi, jumlah, tanggaldibutuhkan;
    public int count;

    public permintaanmaterial(String no, String nama, String spesifikasi, String jumlah, String tanggaldibutuhkan, int count) {
        this.no = no;
        this.nama = nama;
        this.spesifikasi = spesifikasi;
        this.jumlah = jumlah;
        this.tanggaldibutuhkan = tanggaldibutuhkan;
        this.count = count;
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

    public String getTanggaldibutuhkan() {
        return tanggaldibutuhkan;
    }

    public void setTanggaldibutuhkan(String tanggaldibutuhkan) {
        this.tanggaldibutuhkan = tanggaldibutuhkan;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
