package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.SearchAdapter;
import widhimp.manajemenproyekreparasi.Object.search;

/**
 * Created by Widhi Mahaputra on 1/21/2017.
 */

public class AmbilSearch extends ArrayList<search> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, nama;
    private JSONArray search = null;
    private String json;

    public AmbilSearch(String json) {
        this.json = json;
    }

    public void ambilsearch(SearchAdapter searchAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            search=jsonObject.getJSONArray(JSON_ARRAY);
            nama= new String[search.length()];
            id=new String[search.length()];
            for(int i=0;i<search.length();i++){
                JSONObject jo = search.getJSONObject(i);
                id[i]=jo.getString("id_repairdetail");
                nama[i] = jo.getString("detail");
                searchAdapter.add(new search(id[i],nama[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
