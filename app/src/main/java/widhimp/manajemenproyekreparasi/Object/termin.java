package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class termin {
    String id, nama;

    public termin(String id, String nama) {
        this.id = id;
        this.nama = nama;
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
}
