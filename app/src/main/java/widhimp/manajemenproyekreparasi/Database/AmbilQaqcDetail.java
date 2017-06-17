package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class AmbilQaqcDetail {
    public static final String JSON_ARRAY = "result";
    public static String nama;
    public static String jabatan;
    public static String kontak;
    private JSONArray qaqcdetail=null;
    private String json;
    public AmbilQaqcDetail(String json) {
        this.json = json;
    }

    public void ambilqaqcdetail() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            qaqcdetail=jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject jo = qaqcdetail.getJSONObject(0);
            nama = jo.getString("nama");
            jabatan = jo.getString("jabatan");
            kontak=jo.getString("kontak");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
