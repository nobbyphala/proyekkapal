package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.KomunikasiAdapter;
import widhimp.manajemenproyekreparasi.Adapter.SubContractorAdapter;
import widhimp.manajemenproyekreparasi.Object.dokumen;
import widhimp.manajemenproyekreparasi.Object.subcontractor;

/**
 * Created by Widhi Mahaputra on 1/3/2017.
 */

public class AmbilSubContractor extends ArrayList<subcontractor> {
    public static final String JSON_ARRAY = "result";
    public static String[] nama, koordinator, kontak, jabatan, id;
    private JSONArray komunikasi = null;
    private String json;

    public AmbilSubContractor(String json) {
        this.json = json;
    }

    public void ambilsubcontractor(SubContractorAdapter subContractorAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            komunikasi=jsonObject.getJSONArray(JSON_ARRAY);
            nama= new String[komunikasi.length()];
            id=new String[komunikasi.length()];
            koordinator=new String[komunikasi.length()];
            kontak=new String[komunikasi.length()];
            jabatan=new String[komunikasi.length()];
            for(int i=0;i<komunikasi.length();i++){
                JSONObject jo = komunikasi.getJSONObject(i);
                nama[i] = jo.getString("nama_kontraktor");
                id[i]=jo.getString("id_subcontractor");
                kontak[i]=jo.getString("kontak");
                jabatan[i]=jo.getString("jabatan_koordinator");
                koordinator[i]=jo.getString("nama_koordinator");
                subContractorAdapter.add(new subcontractor(id[i],nama[i],koordinator[i],kontak[i],jabatan[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
