package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Object.dokumen;

/**
 * Created by Widhi Mahaputra on 12/23/2016.
 */

public class AmbilKomunikasiEdit extends ArrayList<dokumen> {
    public static final String JSON_ARRAY = "result";
    public static String dari;
    public static String kepada;
    public static String perihal;
    public static String tanggal;
    public static String file;
    public static String nama;
    public static String id;
    public static String catatan;
    private JSONArray komunikasi=null;
    private String json;
    public AmbilKomunikasiEdit(String json) {
        this.json = json;
    }

    public void AmbilKomunikasiEdit() {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            komunikasi=jsonObject.getJSONArray(JSON_ARRAY);
            JSONObject jo = komunikasi.getJSONObject(0);
            file=jo.getString("gambar");
            nama=jo.getString("nama_komunikasi");
            id=jo.getString("id_kapal");
            catatan=jo.getString("catatan");
            dari=jo.getString("dari");
            kepada=jo.getString("kepada");
            perihal=jo.getString("perihal");
            tanggal=jo.getString("tanggal");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

/*
JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            komunikasi=jsonObject.getJSONArray(JSON_ARRAY);
            adatidak=new String[komunikasi.length()];
            status= new String[komunikasi.length()];
            dari= new String[komunikasi.length()];
            kepada=new String[komunikasi.length()];
            perihal=new String[komunikasi.length()];
            tanggal=new String[komunikasi.length()];
            file=new String[komunikasi.length()];
            for(int i=0;i<komunikasi.length();i++){
                JSONObject jo = komunikasi.getJSONObject(i);
                adatidak[i] = jo.getString("adatidak");
                status[i] = jo.getString("status");
                dari[i]=jo.getString("dari");
                kepada[i]=jo.getString("kepada");
                perihal[i]=jo.getString("perihal");
                tanggal[i]=jo.getString("tanggal");
                file[i]=jo.getString("file");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }*/