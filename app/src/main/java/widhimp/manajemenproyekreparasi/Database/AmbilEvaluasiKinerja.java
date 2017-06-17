package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 1/19/2017.
 */

public class AmbilEvaluasiKinerja {
    public static final String JSON_ARRAY = "result";
    public static String kehadiran, tepatwaktu, kerjasama, kualitas, id, dari, tanggal, kepada, catatan, perihal;
    private int length;
    private JSONArray evaluasikinerja=null;
    private String json;
    public AmbilEvaluasiKinerja(String json) {

        this.json = json;
    }

    public void ambilevaluasikinerja() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            evaluasikinerja=jsonObject.getJSONArray(JSON_ARRAY);
            length=evaluasikinerja.length();
            JSONObject jo = evaluasikinerja.getJSONObject(length-1);
            tepatwaktu= jo.getString("tepat_waktu");
            kerjasama=jo.getString("kerjasama");
            kualitas=jo.getString("kualitas");
            id=jo.getString("id_evaluasi");
            kehadiran=jo.getString("kehadiran");
            dari=jo.getString("dari");
            tanggal=jo.getString("tanggal");
            kepada=jo.getString("kepada");
            catatan=jo.getString("catatan");
            perihal=jo.getString("perihal");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
