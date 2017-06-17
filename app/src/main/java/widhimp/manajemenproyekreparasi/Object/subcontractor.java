package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 1/3/2017.
 */

public class subcontractor {
    String id, nama, koordinator, kontak, jabatan;

    public subcontractor(String id, String nama, String koordinator, String kontak, String jabatan) {
        this.id = id;
        this.nama = nama;
        this.koordinator = koordinator;
        this.kontak = kontak;
        this.jabatan = jabatan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getKoordinator() {
        return koordinator;
    }

    public void setKoordinator(String koordinator) {
        this.koordinator = koordinator;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
