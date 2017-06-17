package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class checklist {
    private String nama;
    private String id;

    public checklist(String nama, String id) {
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
