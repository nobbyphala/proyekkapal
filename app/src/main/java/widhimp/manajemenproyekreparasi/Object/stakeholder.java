package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 12/31/2016.
 */

public class stakeholder {
    String id,nama,stakeholder,perihal;

    public stakeholder(String id, String nama, String stakeholder, String perihal) {
        this.id = id;
        this.nama = nama;
        this.stakeholder = stakeholder;
        this.perihal = perihal;
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

    public String getPerihal() {
        return perihal;
    }

    public void setPerihal(String perihal) {
        this.perihal = perihal;
    }

    public String getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(String stakeholder) {
        this.stakeholder = stakeholder;
    }
}
