package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class AmbilTerminDetail {
    public static final String JSON_ARRAY = "result";
    public static String tanggal;
    public static String jumlah;
    public static String pembayar;
    public static String penerima;
    public static String catatan;
    private JSONArray termindetail=null;
    private String json;
    public AmbilTerminDetail(String json) {
        this.json = json;
    }

    public void ambiltermindetail() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            termindetail=jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject jo = termindetail.getJSONObject(0);
            tanggal = jo.getString("tanggal_pembayaran");
            jumlah=jo.getString("jumlah_pembayaran");
            pembayar=jo.getString("pihak_pembayar");
            penerima=jo.getString("pihak_penerima");
            catatan=jo.getString("catatan");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
