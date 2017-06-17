package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.ListTambahPekerjaanAdapter;
import widhimp.manajemenproyekreparasi.Adapter.ResikoAdapter;
import widhimp.manajemenproyekreparasi.Object.resiko;
import widhimp.manajemenproyekreparasi.Object.tambahpekerjaan;

/**
 * Created by Widhi Mahaputra on 1/19/2017.
 */

public class AmbilListTambahPekerjaan extends ArrayList<tambahpekerjaan> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, dari, kepada, perihal, catatan;
    private int jumlah;
    private JSONArray pekerjaanarr = null;
    private String json;

    public AmbilListTambahPekerjaan(String json) {
        this.json = json;
    }

    public void ambillisttambahpekerjaan(ListTambahPekerjaanAdapter listTambahPekerjaanAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            pekerjaanarr=jsonObject.getJSONArray(JSON_ARRAY);
            id= new String[pekerjaanarr.length()];
            dari=new String[pekerjaanarr.length()];
            kepada=new String[pekerjaanarr.length()];
            perihal=new String[pekerjaanarr.length()];
            catatan=new String[pekerjaanarr.length()];
            jumlah=pekerjaanarr.length();
            for(int i=0;i<pekerjaanarr.length();i++){
                JSONObject jo = pekerjaanarr.getJSONObject(i);
                id[i]=jo.getString("id_tambahanpekerjaan");
                dari[i]=jo.getString("dari");
                kepada[i]=jo.getString("kepada");
                perihal[i]=jo.getString("perihal");
                catatan[i]=jo.getString("catatan");
                listTambahPekerjaanAdapter.add(new tambahpekerjaan(id[i],dari[i],kepada[i],perihal[i],catatan[i],jumlah));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
