package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import widhimp.manajemenproyekreparasi.Object.penjadwalan;

/**
 * Created by Widhi Mahaputra on 12/24/2016.
 */

public class AmbilPenjadwalanDetail {
    public static final String JSON_ARRAY = "result";
//    public static String komen[];
//    public static String mulai[];
//    public static String selesai[];
    public static String komen, mulai, selesai, id;
    public static int panjang;
    private JSONArray penjadwalandetail=null;
    private String json;
    public AmbilPenjadwalanDetail(String json) {
        this.json = json;
    }

    public void ambilpenjadwalandetail() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            penjadwalandetail=jsonObject.getJSONArray(JSON_ARRAY);
            panjang=penjadwalandetail.length();
            JSONObject jo = penjadwalandetail.getJSONObject(panjang-1);
            komen=jo.getString("catatan");
            mulai=jo.getString("tanggal_mulai");
            selesai=jo.getString("tanggal_selesai");
            id=jo.getString("id_penjadwalan");
//            mulai=new String[penjadwalandetail.length()];
//            selesai=new String[penjadwalandetail.length()];
//            for(int i=0;i<penjadwalandetail.length();i++){
//                JSONObject jo = penjadwalandetail.getJSONObject(i);
//                mulai[i] = jo.getString("tanggal_mulai");
//                selesai[i]=jo.getString("tanggal_selesai");
//                komen[i]=jo.getString("catatan");
            //}
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
