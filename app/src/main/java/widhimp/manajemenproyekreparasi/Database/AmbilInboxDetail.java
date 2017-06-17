package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 1/16/2017.
 */

public class AmbilInboxDetail {
    public static final String JSON_ARRAY = "result";
    public static String isi, tanggal, perihal;
    private JSONArray inboxdetail=null;
    private String json;
    public AmbilInboxDetail(String json) {

        this.json = json;
    }

    public void ambilinboxdetail() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            inboxdetail=jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject jo = inboxdetail.getJSONObject(0);
            isi= jo.getString("isi");
            tanggal = jo.getString("tanggal");
            perihal=jo.getString("perihal");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
