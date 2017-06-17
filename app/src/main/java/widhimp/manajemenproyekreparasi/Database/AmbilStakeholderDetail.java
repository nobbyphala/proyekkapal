package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Widhi Mahaputra on 1/16/2017.
 */

public class AmbilStakeholderDetail {
    public static final String JSON_ARRAY = "result";
    public static String nama, stakeholders, id_user;
    private JSONArray stakeholder=null;
    private String json;
    public AmbilStakeholderDetail(String json) {

        this.json = json;
    }

    public void ambilstakeholder() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            stakeholder=jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject jo = stakeholder.getJSONObject(0);
            nama = jo.getString("nama");
            stakeholders=jo.getString("stakeholder");
            id_user=jo.getString("id_user");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
