package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Object.kapal;

/**
 * Created by Widhi Mahaputra on 12/19/2016.
 */

public class AmbilKapalDetail extends ArrayList<kapal> {
    public static final String JSON_ARRAY = "result";
    public static String nama, type, length, depth, breadth, draft, dwt, survey, clas;
    public static int jumlah;
    private JSONArray kapal = null;
    private String json;

    public AmbilKapalDetail(String json){this.json=json;}

    public void ambilkapaldetail() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            kapal=jsonObject.getJSONArray(JSON_ARRAY);
            jumlah=kapal.length();
            JSONObject jo = kapal.getJSONObject(0);
                nama= jo.getString("ship_name");
                type= jo.getString("type_of_ship");
                length=jo.getString("length_overall");
                depth=jo.getString("height");
                breadth=jo.getString("breadth");
                draft=jo.getString("draft");
                dwt=jo.getString("dwt");
                survey=jo.getString("type_of_survey");
                clas=jo.getString("kelas");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
