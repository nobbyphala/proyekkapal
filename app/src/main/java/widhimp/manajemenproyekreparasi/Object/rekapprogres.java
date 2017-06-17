package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 1/19/2017.
 */

public class rekapprogres {
    public String id, nama, sebelum, saatini, komen, tanggal, approve;

    public rekapprogres(String id, String nama, String sebelum, String saatini, String komen, String tanggal, String approve) {
        this.id = id;
        this.nama = nama;
        this.sebelum = sebelum;
        this.saatini = saatini;
        this.komen = komen;
        this.tanggal = tanggal;
        this.approve = approve;
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

    public String getSebelum() {
        return sebelum;
    }

    public void setSebelum(String sebelum) {
        this.sebelum = sebelum;
    }

    public String getSaatini() {
        return saatini;
    }

    public void setSaatini(String saatini) {
        this.saatini = saatini;
    }

    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }
}
