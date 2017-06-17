package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.ListTambahPekerjaanAdapter;
import widhimp.manajemenproyekreparasi.Adapter.RekapProgresAdapter;
import widhimp.manajemenproyekreparasi.Object.rekapprogres;
import widhimp.manajemenproyekreparasi.Object.tambahpekerjaan;

/**
 * Created by Widhi Mahaputra on 1/19/2017.
 */

public class AmbilRekapProgres extends ArrayList<rekapprogres> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, tanggal, nama, sebelum, saatini, komen, approved;
    private JSONArray progresarr = null;
    private String json;

    public AmbilRekapProgres(String json) {
        this.json = json;
    }

    public void ambilrekapprogres(RekapProgresAdapter rekapProgresAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            progresarr=jsonObject.getJSONArray(JSON_ARRAY);
            id= new String[progresarr.length()];
            nama=new String[progresarr.length()];
            tanggal=new String[progresarr.length()];
            sebelum=new String[progresarr.length()];
            saatini=new String[progresarr.length()];
            komen=new String[progresarr.length()];
            approved=new String[progresarr.length()];
            for(int i=0;i<progresarr.length();i++){
                JSONObject jo = progresarr.getJSONObject(i);
                id[i]=jo.getString("id_progress");
                nama[i]=jo.getString("nama_repair_detail");
                sebelum[i]=jo.getString("kondisi_sebelum");
                saatini[i]=jo.getString("kondisi_saat_ini");
                komen[i]=jo.getString("komen");
                tanggal[i]=jo.getString("tanggal");
                approved[i]=jo.getString("approved_by");
                rekapProgresAdapter.add(new rekapprogres(id[i],nama[i],sebelum[i],saatini[i],komen[i],tanggal[i],approved[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
