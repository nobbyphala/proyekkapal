package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.StakeholderAdapter;
import widhimp.manajemenproyekreparasi.Object.stakeholder;

/**
 * Created by Widhi Mahaputra on 12/31/2016.
 */

public class AmbilStakeholder extends ArrayList<stakeholder> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, nama, stakehldr, perihal;
    private JSONArray stakeholderarr = null;
    private String json;

    public AmbilStakeholder(String json) {
        this.json = json;
    }

    public void ambilstakeholder(StakeholderAdapter stakeholderAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            stakeholderarr=jsonObject.getJSONArray(JSON_ARRAY);
            id= new String[stakeholderarr.length()];
            nama= new String[stakeholderarr.length()];
            stakehldr= new String[stakeholderarr.length()];
            perihal= new String[stakeholderarr.length()];
            for(int i=0;i<stakeholderarr.length();i++){
                JSONObject jo = stakeholderarr.getJSONObject(i);
                nama[i] = jo.getString("nama");
                id[i]=jo.getString("id_stakeholder");
                stakehldr[i]=jo.getString("stakeholder");
                perihal[i]=jo.getString("perihal");
                stakeholderAdapter.add(new stakeholder(id[i],nama[i],stakehldr[i],perihal[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
