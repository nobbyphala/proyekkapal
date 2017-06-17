package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 1/23/2017.
 */

public class AmbilPembayaranMaterial {
    public static final String JSON_ARRAY = "result";
    public static String id, besarandana, tanggal, perihal, namaperusahaan;
    private JSONArray pembayaranmaterial=null;
    private String json;
    public int length;
    public AmbilPembayaranMaterial(String json) {

        this.json = json;
    }

    public void ambilpembayaranmaterial() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            pembayaranmaterial=jsonObject.getJSONArray(JSON_ARRAY);
            length=pembayaranmaterial.length();
            JSONObject jo = pembayaranmaterial.getJSONObject(length-1);
            id = jo.getString("id_pembayaran");
            besarandana= jo.getString("besaran_dana");
            tanggal=jo.getString("tanggal");
            perihal=jo.getString("perihal");
            namaperusahaan=jo.getString("nama_perusahaan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
