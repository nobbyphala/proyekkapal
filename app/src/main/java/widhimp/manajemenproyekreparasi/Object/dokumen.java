package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 11/28/2016.
 */

public class dokumen {
    private String id;
    private String nama;
    //private String status;
    //private String instruksi;

    public dokumen(String id, String nama) {
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
