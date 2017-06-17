package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.ListPermintaanSdmAdapter;
import widhimp.manajemenproyekreparasi.Adapter.ListTambahPekerjaanAdapter;
import widhimp.manajemenproyekreparasi.Object.permintaansdm;
import widhimp.manajemenproyekreparasi.Object.tambahpekerjaan;

/**
 * Created by Widhi Mahaputra on 1/22/2017.
 */

public class AmbilListPermintaanSdm extends ArrayList<permintaansdm> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, dari, kepada, perihal, catatan;
    private int jumlah;
    private JSONArray permintaanarr = null;
    private String json;

    public AmbilListPermintaanSdm(String json) {
        this.json = json;
    }

    public void ambillistpermintaansdm(ListPermintaanSdmAdapter listPermintaanSdmAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            permintaanarr=jsonObject.getJSONArray(JSON_ARRAY);
            id= new String[permintaanarr.length()];
            dari=new String[permintaanarr.length()];
            kepada=new String[permintaanarr.length()];
            perihal=new String[permintaanarr.length()];
            catatan=new String[permintaanarr.length()];
            jumlah=permintaanarr.length();
            for(int i=0;i<permintaanarr.length();i++){
                JSONObject jo = permintaanarr.getJSONObject(i);
                id[i]=jo.getString("id_permintaan_sdm");
                dari[i]=jo.getString("dari");
                kepada[i]=jo.getString("kepada");
                perihal[i]=jo.getString("perihal");
                catatan[i]=jo.getString("catatan");
                listPermintaanSdmAdapter.add(new permintaansdm(id[i],dari[i],kepada[i],perihal[i],catatan[i],jumlah));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
