package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.InboxAdapter;
import widhimp.manajemenproyekreparasi.Object.inbox;

/**
 * Created by Widhi Mahaputra on 1/16/2017.
 */

public class AmbilInbox extends ArrayList<inbox> {
    public static final String JSON_ARRAY = "result";
    public static String[] dari, id, perihal;
    private JSONArray pesan = null;
    private String json;

    public AmbilInbox(String json) {
        this.json = json;
    }

    public void ambilinbox(InboxAdapter inboxAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            pesan=jsonObject.getJSONArray(JSON_ARRAY);
            dari= new String[pesan.length()];
            id=new String[pesan.length()];
            perihal=new String[pesan.length()];

            for(int i=0;i<pesan.length();i++){
                JSONObject jo = pesan.getJSONObject(i);
                id[i]=jo.getString("id_message");
                dari[i] = jo.getString("dari");
                perihal[i]=jo.getString("perihal");
                inboxAdapter.add(new inbox(id[i],dari[i],perihal[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
