package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 12/26/2016.
 */

public class AmbilSubcontractorKoordinator {
    public static final String JSON_ARRAY = "result";
    public static String nama;
    public static String jabatan;
    public static String kontak;
    private JSONArray koordinator=null;
    private String json;
    public AmbilSubcontractorKoordinator(String json) {
        this.json = json;
    }

    public void ambilsubcontractorkoordinator() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            koordinator=jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject jo = koordinator.getJSONObject(0);
            nama = jo.getString("nama_koordinator");
            jabatan = jo.getString("jabatan_koordinator");
            kontak=jo.getString("kontak");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
