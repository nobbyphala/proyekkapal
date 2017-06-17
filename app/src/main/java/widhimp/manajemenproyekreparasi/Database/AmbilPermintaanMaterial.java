package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.PermintaanMaterialAdapter;
import widhimp.manajemenproyekreparasi.Object.permintaanmaterial;

/**
 * Created by Widhi Mahaputra on 12/31/2016.
 */

public class AmbilPermintaanMaterial extends ArrayList<permintaanmaterial> {
    public static final String JSON_ARRAY = "result";
    public static String[] no, nama, spesifikasi, jumlah, tanggal;
    public int count;
    private JSONArray permintaanarr = null;
    private String json;

    public AmbilPermintaanMaterial (String json) {
        this.json = json;
    }

    public void ambilpermintaanmaterial(PermintaanMaterialAdapter permintaanMaterialAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            permintaanarr=jsonObject.getJSONArray(JSON_ARRAY);
            no= new String[permintaanarr.length()];
            nama= new String[permintaanarr.length()];
            spesifikasi= new String[permintaanarr.length()];
            jumlah= new String[permintaanarr.length()];
            tanggal=new String[permintaanarr.length()];
            count=permintaanarr.length();
            for(int i=0;i<permintaanarr.length();i++){
                JSONObject jo = permintaanarr.getJSONObject(i);
                no[i]=jo.getString("id_permintaan");
                nama[i] = jo.getString("nama_material");
                spesifikasi[i]=jo.getString("spesifikasi");
                jumlah[i]=jo.getString("jumlah");
                tanggal[i]=jo.getString("tanggal_dibutuhkan");
                permintaanMaterialAdapter.add(new permintaanmaterial(no[i], nama[i],spesifikasi[i],jumlah[i],tanggal[i],count));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
