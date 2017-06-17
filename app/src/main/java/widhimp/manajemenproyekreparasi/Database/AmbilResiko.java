package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.QaqcAdapter;
import widhimp.manajemenproyekreparasi.Adapter.ResikoAdapter;
import widhimp.manajemenproyekreparasi.Object.qaqc;
import widhimp.manajemenproyekreparasi.Object.resiko;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class AmbilResiko {
    public static final String JSON_ARRAY = "result";
    public static String id, jenis, keterangan, dampak, severity, occurence, tingkatbahaya, statusrisk, penanganan;
    private JSONArray resiko=null;
    private String json;
    private int length;
    public AmbilResiko(String json) {
        this.json = json;
    }

    public void ambilresiko() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            resiko=jsonObject.getJSONArray(JSON_ARRAY);
            length=resiko.length();
            JSONObject jo = resiko.getJSONObject(length-1);
            jenis = jo.getString("jenis_resiko");
            keterangan=jo.getString("keterangan");
            dampak=jo.getString("dampak_resiko");
            severity=jo.getString("severity");
            occurence=jo.getString("occurence");
            tingkatbahaya=jo.getString("tingkat_bahaya");
            statusrisk=jo.getString("status_risk");
            penanganan=jo.getString("penanganan_resiko");
            id=jo.getString("id_resiko");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
