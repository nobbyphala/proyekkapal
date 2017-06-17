package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 12/25/2016.
 */

public class sdmgalangandetail {
    String nama;
    String jabatan;
    String kontak;

    public sdmgalangandetail(String nama, String jabatan, String kontak) {
        this.jabatan = jabatan;
        this.kontak = kontak;
        this.nama = nama;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
