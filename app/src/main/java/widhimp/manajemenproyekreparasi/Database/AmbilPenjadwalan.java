package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.PenjadwalanAdapter;
import widhimp.manajemenproyekreparasi.Object.penjadwalan;
import widhimp.manajemenproyekreparasi.Object.repairlist;

/**
 * Created by Widhi Mahaputra on 12/24/2016.
 */

public class AmbilPenjadwalan extends ArrayList<penjadwalan> {
    public static final String JSON_ARRAY = "result";
    public static String[] nama, id;
    private JSONArray penjadwalanarr = null;
    private String json;

    public AmbilPenjadwalan(String json) {
        this.json = json;
    }

    public void ambilpenjadwalan(PenjadwalanAdapter penjadwalanAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            penjadwalanarr=jsonObject.getJSONArray(JSON_ARRAY);
            nama= new String[penjadwalanarr.length()];
            id=new String[penjadwalanarr.length()];
            for(int i=0;i<penjadwalanarr.length();i++){
                JSONObject jo = penjadwalanarr.getJSONObject(i);
                id[i]=jo.getString("id_repairdetail");
                nama[i] = jo.getString("detail");
                penjadwalanAdapter.add(new penjadwalan(nama[i],id[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
