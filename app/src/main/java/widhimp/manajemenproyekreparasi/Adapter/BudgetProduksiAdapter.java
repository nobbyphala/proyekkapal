package widhimp.manajemenproyekreparasi.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import widhimp.manajemenproyekreparasi.BiayaProyek.BudgetProduksi;
import widhimp.manajemenproyekreparasi.Object.budgetproduksi;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmGalanganDetail;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmProyek;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class BudgetProduksiAdapter extends ArrayAdapter<budgetproduksi> {
    private String username_user, password_user, kategori_user;
    private String id_kapal;
    private String id_budget;
    private ProgressDialog loading;
    private final Context context;
    public final String URL="http://188.166.240.88:8000/kapal/tambahsuntingbudget/";
    public BudgetProduksiAdapter(Context context, int resource, List<budgetproduksi> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        budgetproduksi list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.biayaproyek_budget_item,parent,false);
        }
        final TextView name=(TextView) convertView.findViewById(R.id.nama_biayaproyek_budget_item);
        final TextView no=(TextView) convertView.findViewById(R.id.no_biayaproyek_budget_item);
        final EditText harga=(EditText) convertView.findViewById(R.id.harga_biayaproyek_budget_item);
        Button sunting=(Button) convertView.findViewById(R.id.sunting_biayaproyek_budget_item);
        name.setText(list.getNama());
        harga.setText(list.getHarga());
        no.setText(list.getId());
        no.setVisibility(View.INVISIBLE);

        SharedPreferences preferences = context.getSharedPreferences("session", context.getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = context.getSharedPreferences("kapal", context.getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        if(kategori_user.equals("Galangan")){
        }
        else if(kategori_user.equals("PM")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("OS")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("Dept. Pengadaan")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("Dept. Produksi")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("Subcont")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("Class")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("QA/QC")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("Owner")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("Vendor")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        else if(kategori_user.equals("PPC")){
            sunting.setVisibility(View.INVISIBLE);
            harga.setEnabled(false);
        }
        sunting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(v.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                //Toast.makeText(getContext(), id_budget, Toast.LENGTH_SHORT).show();
                suntingbudget(name.getText().toString(),harga.getText().toString(),no.getText().toString());
            }
        });
        return convertView;
    }

    public void suntingbudget(final String nama, final String harga, final String idbudget){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username_user);
                params.put("password",password_user);
                params.put("id_kapal",id_kapal);
                params.put("nama_budget",nama);
                params.put("harga_budget",harga);
                params.put("id_budget",idbudget);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void showResponse(final String respon){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage(respon);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loading.dismiss();
                if(respon.toString().equals("Berhasil")){
                    Intent intent=new Intent(getContext(), BudgetProduksi.class);
                    getContext().startActivity(intent);
                }

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
