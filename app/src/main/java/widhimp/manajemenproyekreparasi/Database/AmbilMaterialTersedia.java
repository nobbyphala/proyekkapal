package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.MaterialtersediaAdapter;
import widhimp.manajemenproyekreparasi.Object.materialtersedia;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class AmbilMaterialTersedia  extends ArrayList<materialtersedia> {
    public static final String JSON_ARRAY = "result";
    public static String[] no, nama, spesifikasi, jumlah;
    private JSONArray resikoarr = null;
    private String json;

    public AmbilMaterialTersedia(String json) {
        this.json = json;
    }

    public void ambilmaterialtersedia(MaterialtersediaAdapter materialtersediaAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            resikoarr=jsonObject.getJSONArray(JSON_ARRAY);
            no= new String[resikoarr.length()];
            nama= new String[resikoarr.length()];
            spesifikasi= new String[resikoarr.length()];
            jumlah= new String[resikoarr.length()];
            for(int i=0;i<resikoarr.length();i++){
                JSONObject jo = resikoarr.getJSONObject(i);
                no[i]=jo.getString("id_material");
                nama[i] = jo.getString("nama_material");
                spesifikasi[i]=jo.getString("spesifikasi");
                jumlah[i]=jo.getString("jumlah");
                materialtersediaAdapter.add(new materialtersedia(no[i], nama[i],spesifikasi[i],jumlah[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
