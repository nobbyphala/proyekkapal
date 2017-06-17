package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.RepairlistAdapter;
import widhimp.manajemenproyekreparasi.Object.repairlist;

/**
 * Created by Widhi Mahaputra on 12/23/2016.
 */

public class AmbilRepairList extends ArrayList<repairlist> {
    public static final String JSON_ARRAY = "result";
    public static String[] nama;
    public static String[] id;
    private JSONArray repairlist = null;
    private String json;

    public AmbilRepairList(String json) {
        this.json = json;
    }

    public void ambilrepairlist(RepairlistAdapter repairlistAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            repairlist=jsonObject.getJSONArray(JSON_ARRAY);
            nama= new String[repairlist.length()];
            id=new String[repairlist.length()];
            for(int i=0;i<repairlist.length();i++){
                JSONObject jo = repairlist.getJSONObject(i);
                id[i]=jo.getString("id_repairdetail");
                nama[i] = jo.getString("detail");
                repairlistAdapter.add(new repairlist(nama[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
