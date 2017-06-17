package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 1/22/2017.
 */

public class AmbilSurveyKapal {
    public static final String JSON_ARRAY = "result";
    public static String id, tanggal, anggota, perihal, catatan;
    private int length;
    private JSONArray surveykapal=null;
    private String json;
    public AmbilSurveyKapal(String json) {

        this.json = json;
    }

    public void ambilsurveykapal() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            surveykapal=jsonObject.getJSONArray(JSON_ARRAY);
            length=surveykapal.length();
            JSONObject jo = surveykapal.getJSONObject(length-1);
            id=jo.getString("id_survey");
            tanggal=jo.getString("tanggal");
            anggota=jo.getString("anggota_survey");
            perihal=jo.getString("perihal");
            catatan=jo.getString("catatan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
