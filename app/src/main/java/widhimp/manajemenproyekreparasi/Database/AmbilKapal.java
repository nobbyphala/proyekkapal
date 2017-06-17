package widhimp.manajemenproyekreparasi.Database;

/**
 * Created by Widhi Mahaputra on 12/14/2016.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.KapalAdapter;
import widhimp.manajemenproyekreparasi.Object.kapal;

public class AmbilKapal extends ArrayList<kapal>{
    public static final String JSON_ARRAY = "result";
    public static String[] id;
    public static String[] nama;
    public static String[] type;
    private JSONArray kapal = null;
    private String json;

    public AmbilKapal(String json) {
        this.json = json;
    }

    public void ambilkapal(KapalAdapter kapalAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            kapal=jsonObject.getJSONArray(JSON_ARRAY);

            id=new String[kapal.length()];
            nama= new String[kapal.length()];
            type= new String[kapal.length()];

            for(int i=0;i<kapal.length();i++){
                JSONObject jo = kapal.getJSONObject(i);
                id[i]=jo.getString("id_kapal");
                nama[i] = jo.getString("ship_name");
                type[i] = jo.getString("type_of_ship");
                kapalAdapter.add(new kapal(id[i], nama[i],type[i],"","","","","","",""));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
