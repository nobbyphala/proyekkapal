package widhimp.manajemenproyekreparasi.Object;

/**
 * Created by Widhi Mahaputra on 11/25/2016.
 */

public class kapal {
    private String id;
    private String name;
    private String type;
    private String length;
    private String height;
    private String breadth;
    private String draft;
    private String dwt;
    private String survey;
    private String clas;

    public kapal(String id, String name, String type, String length, String height, String breadth, String draft, String dwt, String survey, String clas) {
        this.breadth = breadth;
        this.clas = clas;
        this.draft = draft;
        this.dwt = dwt;
        this.height = height;
        this.id = id;
        this.length = length;
        this.name = name;
        this.survey = survey;
        this.type = type;
    }

    public String getBreadth() {
        return breadth;
    }

    public void setBreadth(String breadth) {
        this.breadth = breadth;
    }

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

    public String getDraft() {
        return draft;
    }

    public void setDraft(String draft) {
        this.draft = draft;
    }

    public String getDwt() {
        return dwt;
    }

    public void setDwt(String dwt) {
        this.dwt = dwt;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurvey() {
        return survey;
    }

    public void setSurvey(String survey) {
        this.survey = survey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}