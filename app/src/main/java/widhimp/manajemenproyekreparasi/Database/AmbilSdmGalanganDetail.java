package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.SdmgalangandetailAdapter;
import widhimp.manajemenproyekreparasi.Object.sdmgalangandetail;

/**
 * Created by Widhi Mahaputra on 12/25/2016.
 */

public class AmbilSdmGalanganDetail extends ArrayList<sdmgalangandetail>{
    public static final String JSON_ARRAY = "result";
    public static String[] nama, jabatan, kontak;
    private JSONArray sdmgalangandetail = null;
    private String json;

    public AmbilSdmGalanganDetail(String json) {
        this.json = json;
    }

    public void ambilsdmgalangandetail(SdmgalangandetailAdapter sdmgalangandetailAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            sdmgalangandetail=jsonObject.getJSONArray(JSON_ARRAY);

            nama= new String[sdmgalangandetail.length()];
            jabatan=new String[sdmgalangandetail.length()];
            kontak=new String[sdmgalangandetail.length()];

            for(int i=0;i<sdmgalangandetail.length();i++){
                JSONObject jo = sdmgalangandetail.getJSONObject(i);
                nama[i] = jo.getString("nama_anggota");
                jabatan[i]=jo.getString("jabatan");
                kontak[i]=jo.getString("kontak");
                sdmgalangandetailAdapter.add(new sdmgalangandetail(nama[i],jabatan[i],kontak[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
