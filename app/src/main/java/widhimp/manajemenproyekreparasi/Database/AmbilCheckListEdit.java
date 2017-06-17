package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.CheckListAdapter;
import widhimp.manajemenproyekreparasi.Adapter.CheckListEditAdapter;
import widhimp.manajemenproyekreparasi.Object.checklist;
import widhimp.manajemenproyekreparasi.Object.checklistedit;

/**
 * Created by Widhi Mahaputra on 1/2/2017.
 */

public class AmbilCheckListEdit extends ArrayList<checklist> {
    public static final String JSON_ARRAY = "result";
    public static String[] nama, id, status, approved, komentar;
    private JSONArray checklistarr = null;
    private String json;

    public AmbilCheckListEdit(String json) {
        this.json = json;
    }

    public void ambilchecklistedit(CheckListEditAdapter checkListEditAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            checklistarr=jsonObject.getJSONArray(JSON_ARRAY);
            nama= new String[checklistarr.length()];
            id=new String[checklistarr.length()];
            status=new String[checklistarr.length()];
            komentar=new String[checklistarr.length()];
            approved=new String[checklistarr.length()];
            for(int i=0;i<checklistarr.length();i++){
                JSONObject jo = checklistarr.getJSONObject(i);
                id[i]=jo.getString("id_checklist");
                status[i]=jo.getString("status");
                komentar[i]=jo.getString("komentar");
                nama[i] = jo.getString("nama");
                approved[i]=jo.getString("approved_by");
                checkListEditAdapter.add(new checklistedit(id[i],nama[i],status[i],komentar[i],approved[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
