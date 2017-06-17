package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.SdmgalanganAdapter;
import widhimp.manajemenproyekreparasi.Adapter.TerminAdapter;
import widhimp.manajemenproyekreparasi.Object.sdmgalangan;
import widhimp.manajemenproyekreparasi.Object.termin;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class AmbilTermin extends ArrayList<termin> {
    public static final String JSON_ARRAY = "result";
    public static String[] nama, id;
    private JSONArray termin = null;
    private String json;

    public AmbilTermin(String json) {
        this.json = json;
    }

    public void ambiltermin(TerminAdapter terminAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            termin=jsonObject.getJSONArray(JSON_ARRAY);

            id=new String[termin.length()];
            nama= new String[termin.length()];

            for(int i=0;i<termin.length();i++){
                JSONObject jo = termin.getJSONObject(i);
                id[i]=jo.getString("id_termin");
                nama[i] = jo.getString("nama_termin");
                terminAdapter.add(new termin(id[i], nama[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
