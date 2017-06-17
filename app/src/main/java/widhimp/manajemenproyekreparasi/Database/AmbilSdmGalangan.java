package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.SdmgalanganAdapter;
import widhimp.manajemenproyekreparasi.Object.sdmgalangan;

/**
 * Created by Widhi Mahaputra on 12/25/2016.
 */

public class AmbilSdmGalangan extends ArrayList<sdmgalangan> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, nama;
    private JSONArray sdmgalangan = null;
    private String json;

    public AmbilSdmGalangan(String json) {
        this.json = json;
    }

    public void ambilsdmgalangan(SdmgalanganAdapter sdmgalanganAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            sdmgalangan=jsonObject.getJSONArray(JSON_ARRAY);
            id=new String[sdmgalangan.length()];
            nama= new String[sdmgalangan.length()];

            for(int i=0;i<sdmgalangan.length();i++){
                JSONObject jo = sdmgalangan.getJSONObject(i);
                id[i]=jo.getString("id_sdmgalangan");
                nama[i] = jo.getString("nama_departemen");
                sdmgalanganAdapter.add(new sdmgalangan(id[i],nama[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
