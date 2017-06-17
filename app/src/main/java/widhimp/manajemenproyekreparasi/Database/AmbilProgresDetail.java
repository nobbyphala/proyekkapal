package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 12/24/2016.
 */

public class AmbilProgresDetail {
    public static final String JSON_ARRAY = "result";
    public static String id;
    public static String sebelum;
    public static String saatini;
    public static String catatan;
    public static String gambar;
    private JSONArray progresdetail=null;
    private String json;
    public AmbilProgresDetail(String json) {
        this.json = json;
    }

    public void ambilprogresdetail() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            progresdetail=jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject jo = progresdetail.getJSONObject(0);
            id=jo.getString("id_progress");
            sebelum=jo.getString("kondisi_sebelum");
            saatini=jo.getString("kondisi_saat_ini");
            catatan=jo.getString("catatan");
            gambar=jo.getString("gambar");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
