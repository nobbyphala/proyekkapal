package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class budgetproduksi {
    String id;
    String nama;
    String harga;

    public budgetproduksi(String id, String nama, String harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
