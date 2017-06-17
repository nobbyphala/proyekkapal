package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.QaqcAdapter;
import widhimp.manajemenproyekreparasi.Adapter.TerminAdapter;
import widhimp.manajemenproyekreparasi.Object.qaqc;
import widhimp.manajemenproyekreparasi.Object.termin;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class AmbilQaqc extends ArrayList<qaqc> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, nama;
    private JSONArray qaqcarr = null;
    private String json;

    public AmbilQaqc(String json) {
        this.json = json;
    }

    public void ambilqaqc(QaqcAdapter qaqcAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            qaqcarr=jsonObject.getJSONArray(JSON_ARRAY);

            id=new String[qaqcarr.length()];
            nama= new String[qaqcarr.length()];

            for(int i=0;i<qaqcarr.length();i++){
                JSONObject jo = qaqcarr.getJSONObject(i);
                id[i]=jo.getString("id_qaqc");
                nama[i] = jo.getString("nama");
                qaqcAdapter.add(new qaqc(id[i],nama[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
