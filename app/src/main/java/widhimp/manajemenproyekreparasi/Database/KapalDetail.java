package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.KapalAdapter;
import widhimp.manajemenproyekreparasi.Object.kapal;

/**
 * Created by Widhi Mahaputra on 12/15/2016.
 */

public class KapalDetail extends ArrayList<kapal> {
    public static final String JSON_ARRAY = "result";
    public static String[] nama;
    public static String[] type;
    private JSONArray kapal = null;
    private String json;

    public KapalDetail(String json) {
        this.json = json;
    }

    public void KapalDetail() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            kapal=jsonObject.getJSONArray(JSON_ARRAY);

            nama= new String[kapal.length()];
            type= new String[kapal.length()];

            for(int i=0;i<kapal.length();i++){
                JSONObject jo = kapal.getJSONObject(i);
                nama[i] = jo.getString("ship_name");
                type[i] = jo.getString("type_of_ship");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}