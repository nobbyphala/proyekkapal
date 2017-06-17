package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.SubcontractorAnggotaAdapter;
import widhimp.manajemenproyekreparasi.Adapter.SubcontractorAreaPekerjaanAdapter;
import widhimp.manajemenproyekreparasi.Object.subcontractoranggota;
import widhimp.manajemenproyekreparasi.Object.subcontractorareapekerjaan;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class AmbilSubcontractorAreaPekerjaan extends ArrayList<subcontractorareapekerjaan>{
    public static final String JSON_ARRAY = "result";
    public static String[] nama;
    private JSONArray subcontractorareapekerjaan = null;
    private String json;

    public AmbilSubcontractorAreaPekerjaan(String json) {
        this.json = json;
    }

    public void ambilsubcontractorareapekerjaan(SubcontractorAreaPekerjaanAdapter subcontractorAreaPekerjaanAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            subcontractorareapekerjaan=jsonObject.getJSONArray(JSON_ARRAY);

            nama= new String[subcontractorareapekerjaan.length()];

            for(int i=0;i<subcontractorareapekerjaan.length();i++){
                JSONObject jo = subcontractorareapekerjaan.getJSONObject(i);
                nama[i] = jo.getString("detail_pekerjaan");
                subcontractorAreaPekerjaanAdapter.add(new subcontractorareapekerjaan(nama[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
