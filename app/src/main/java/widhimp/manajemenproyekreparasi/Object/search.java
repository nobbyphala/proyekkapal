package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 1/21/2017.
 */

public class search {
    public String id, nama;

    public search(String id, String nama) {
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
