package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 1/20/2017.
 */

public class rekapchecklist {
    public String nama, status, komen, approvedby, tanggal, checklist;

    public rekapchecklist(String nama, String status, String komen, String approvedby, String tanggal, String checklist) {
        this.nama = nama;
        this.status = status;
        this.komen = komen;
        this.approvedby = approvedby;
        this.tanggal = tanggal;
        this.checklist = checklist;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getChecklist() {
        return checklist;
    }

    public void setChecklist(String checklist) {
        this.checklist = checklist;
    }
}
