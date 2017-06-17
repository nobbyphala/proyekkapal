package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 1/16/2017.
 */

public class inbox {
    private String dari, id, perihal;

    public inbox(String id, String dari, String perihal) {
        this.id = id;
        this.dari = dari;
        this.perihal = perihal;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }
}
