package widhimp.manajemenproyekreparasi.Database;

/**
 * Created by Widhi Mahaputra on 12/14/2016.
 */
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import widhimp.manajemenproyekreparasi.Adapter.KomunikasiAdapter;
import widhimp.manajemenproyekreparasi.Object.dokumen;

public class AmbilKomunikasi extends ArrayList<dokumen>{
    public static final String JSON_ARRAY = "result";
    public static String[] nama,id;
    private JSONArray komunikasi = null;
    private String json;

    public AmbilKomunikasi(String json) {
        this.json = json;
    }

    public void ambilkomunikasi(KomunikasiAdapter komunikasiAdapter) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            komunikasi=jsonObject.getJSONArray(JSON_ARRAY);
            nama= new String[komunikasi.length()];
            id=new String[komunikasi.length()];
            for(int i=0;i<komunikasi.length();i++){
                JSONObject jo = komunikasi.getJSONObject(i);
                nama[i] = jo.getString("nama_komunikasi");
                id[i]=jo.getString("id_komunikasi");
                komunikasiAdapter.add(new dokumen(id[i],nama[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
