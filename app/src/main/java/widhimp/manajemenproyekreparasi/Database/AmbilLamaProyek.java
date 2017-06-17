package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 12/24/2016.
 */

public class AmbilLamaProyek {
    public static final String JSON_ARRAY = "result";
    public static String durasi;
    public static String mulai;
    public static String selesai;
    public static String id;
    private int length;
    private JSONArray lamaproyek=null;
    private String json;
    public AmbilLamaProyek(String json) {

        this.json = json;
    }

    public void ambillamaproyek() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            lamaproyek=jsonObject.getJSONArray(JSON_ARRAY);
            length=lamaproyek.length();
            JSONObject jo = lamaproyek.getJSONObject(length-1);
            durasi = jo.getString("durasi_reparasi");
            mulai = jo.getString("tanggal_mulai");
            selesai=jo.getString("tanggal_selesai");
            id=jo.getString("id_lamaproyek");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
