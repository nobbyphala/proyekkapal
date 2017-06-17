package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.RekapChecklistAdapter;
import widhimp.manajemenproyekreparasi.Adapter.RekapProgresAdapter;
import widhimp.manajemenproyekreparasi.Object.rekapchecklist;

/**
 * Created by Widhi Mahaputra on 1/20/2017.
 */

public class AmbilRekapChecklist extends ArrayList<rekapchecklist> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, repairdetail, status, nama, tanggal, approved, komen;
    private JSONArray checklistarr = null;
    private String json;

    public AmbilRekapChecklist(String json) {
        this.json = json;
    }

    public void ambilrekapchecklist(RekapChecklistAdapter rekapChecklistAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            checklistarr=jsonObject.getJSONArray(JSON_ARRAY);
            id= new String[checklistarr.length()];
            repairdetail=new String[checklistarr.length()];
            status=new String[checklistarr.length()];
            nama=new String[checklistarr.length()];
            tanggal=new String[checklistarr.length()];
            approved=new String[checklistarr.length()];
            komen=new String[checklistarr.length()];
            for(int i=0;i<checklistarr.length();i++){
                JSONObject jo = checklistarr.getJSONObject(i);
                id[i]=jo.getString("id_checklist");
                status[i]=jo.getString("status");
                nama[i]=jo.getString("nama");
                tanggal[i]=jo.getString("tanggal_ditambah");
                approved[i]=jo.getString("approved_by");
                komen[i]=jo.getString("komentar");
                repairdetail[i]=jo.getString("repair_detail");
                rekapChecklistAdapter.add(new rekapchecklist(nama[i],status[i],komen[i],approved[i],tanggal[i],repairdetail[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
