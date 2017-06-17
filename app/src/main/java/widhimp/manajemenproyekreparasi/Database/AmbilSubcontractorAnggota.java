package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.SdmgalangandetailAdapter;
import widhimp.manajemenproyekreparasi.Adapter.SubcontractorAnggotaAdapter;
import widhimp.manajemenproyekreparasi.Object.sdmgalangandetail;
import widhimp.manajemenproyekreparasi.Object.subcontractoranggota;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class AmbilSubcontractorAnggota extends ArrayList<subcontractoranggota> {
    public static final String JSON_ARRAY = "result";
    public static String[] nama;
    private JSONArray subcontractoranggota = null;
    private String json;

    public AmbilSubcontractorAnggota(String json) {
        this.json = json;
    }

    public void ambilsubcontractoranggota(SubcontractorAnggotaAdapter subcontractorAnggotaAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            subcontractoranggota=jsonObject.getJSONArray(JSON_ARRAY);

            nama= new String[subcontractoranggota.length()];

            for(int i=0;i<subcontractoranggota.length();i++){
                JSONObject jo = subcontractoranggota.getJSONObject(i);
                nama[i] = jo.getString("nama_anggota");
                subcontractorAnggotaAdapter.add(new subcontractoranggota(nama[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
