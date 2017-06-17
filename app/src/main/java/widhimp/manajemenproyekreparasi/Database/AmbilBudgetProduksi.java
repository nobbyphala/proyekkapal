package widhimp.manajemenproyekreparasi.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import widhimp.manajemenproyekreparasi.Adapter.BudgetProduksiAdapter;
import widhimp.manajemenproyekreparasi.Adapter.SdmgalangandetailAdapter;
import widhimp.manajemenproyekreparasi.Object.budgetproduksi;
import widhimp.manajemenproyekreparasi.Object.sdmgalangandetail;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class AmbilBudgetProduksi extends ArrayList<budgetproduksi> {
    public static final String JSON_ARRAY = "result";
    public static String[] id, nama, harga;
    private JSONArray budgetproduksi = null;
    private String json;

    public AmbilBudgetProduksi(String json) {
        this.json = json;
    }

    public void ambilbudgetproduksi(BudgetProduksiAdapter budgetProduksiAdapter){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            budgetproduksi=jsonObject.getJSONArray(JSON_ARRAY);

            nama= new String[budgetproduksi.length()];
            id=new String[budgetproduksi.length()];
            harga=new String[budgetproduksi.length()];

            for(int i=0;i<budgetproduksi.length();i++){
                JSONObject jo = budgetproduksi.getJSONObject(i);
                nama[i] = jo.getString("nama_budget");
                id[i]=jo.getString("id_budget");
                harga[i]=jo.getString("harga_budget");
                budgetProduksiAdapter.add(new budgetproduksi(id[i],nama[i],harga[i]));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
