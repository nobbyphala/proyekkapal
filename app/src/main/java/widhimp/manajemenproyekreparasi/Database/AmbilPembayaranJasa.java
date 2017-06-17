package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 1/22/2017.
 */

public class AmbilPembayaranJasa {
    public static final String JSON_ARRAY = "result";
    public static String id, besarandana, tanggal, perihal, namaperusahaan;
    private JSONArray pembayaranjasa=null;
    private String json;
    public int length;
    public AmbilPembayaranJasa(String json) {

        this.json = json;
    }

    public void ambilpembayaranjasa() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            pembayaranjasa=jsonObject.getJSONArray(JSON_ARRAY);
            length=pembayaranjasa.length();
            JSONObject jo = pembayaranjasa.getJSONObject(length-1);
            id = jo.getString("id_pembayaran_jasan");
            besarandana= jo.getString("besaran_dana");
            tanggal=jo.getString("tanggal");
            perihal=jo.getString("perihal");
            namaperusahaan=jo.getString("nama_perusahaan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
