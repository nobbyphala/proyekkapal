package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.CheckListAdapter;
import widhimp.manajemenproyekreparasi.Adapter.PenjadwalanAdapter;
import widhimp.manajemenproyekreparasi.Object.checklist;
import widhimp.manajemenproyekreparasi.Object.penjadwalan;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class AmbilCheckList extends ArrayList<checklist> {
    public static final String JSON_ARRAY = "result";
    public static String[] nama, id;
    private JSONArray checklistarr = null;
    private String json;

    public AmbilCheckList(String json) {
        this.json = json;
    }

    public void ambilchecklist(CheckListAdapter checkListAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            checklistarr=jsonObject.getJSONArray(JSON_ARRAY);
            nama= new String[checklistarr.length()];
            id=new String[checklistarr.length()];
            for(int i=0;i<checklistarr.length();i++){
                JSONObject jo = checklistarr.getJSONObject(i);
                id[i]=jo.getString("id_repairdetail");
                nama[i] = jo.getString("detail");
                checkListAdapter.add(new checklist(nama[i],id[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
