package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.ProgresAdapter;
import widhimp.manajemenproyekreparasi.Object.penjadwalan;
import widhimp.manajemenproyekreparasi.Object.repairlist;

/**
 * Created by Widhi Mahaputra on 12/24/2016.
 */

public class AmbilProgres extends ArrayList<penjadwalan>{
    public static final String JSON_ARRAY = "result";
    public static String[] nama, id;
    private JSONArray progres = null;
    private String json;

    public AmbilProgres(String json) {
        this.json = json;
    }

    public void ambilprogres(ProgresAdapter progresAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            progres=jsonObject.getJSONArray(JSON_ARRAY);
            nama= new String[progres.length()];
            id=new String[progres.length()];
            for(int i=0;i<progres.length();i++){
                JSONObject jo = progres.getJSONObject(i);
                id[i]=jo.getString("id_repairdetail");
                nama[i] = jo.getString("detail");
                progresAdapter.add(new penjadwalan(nama[i],id[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
