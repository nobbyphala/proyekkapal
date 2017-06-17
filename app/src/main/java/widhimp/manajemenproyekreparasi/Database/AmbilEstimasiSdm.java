package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 1/15/2017.
 */

public class AmbilEstimasiSdm {
    public static final String JSON_ARRAY = "result";
    public static String id, internal, subkontraktor;
    private JSONArray estimasisdm=null;
    private String json;
    public AmbilEstimasiSdm(String json) {

        this.json = json;
    }

    public void ambilestimasisdm() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            estimasisdm=jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject jo = estimasisdm.getJSONObject(0);
            id = jo.getString("id_estimasi");
            internal= jo.getString("internal_galangan");
            subkontraktor=jo.getString("subkontraktor");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
