package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 12/24/2016.
 */

public class penjadwalan {
    private String nama;
    private String id;

    public penjadwalan(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
