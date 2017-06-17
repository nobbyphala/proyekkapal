package widhimp.manajemenproyekreparasi.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import widhimp.manajemenproyekreparasi.Object.rekapprogres;
import widhimp.manajemenproyekreparasi.Object.tambahpekerjaan;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.WaktuProyek.RekapProgresProyek;

/**
 * Created by Widhi Mahaputra on 1/19/2017.
 */

public class RekapProgresAdapter extends ArrayAdapter<rekapprogres> {
    private String username_user, password_user, kategori_user;
    private String id_kapal, id_progres;
    private Context context;
    private ProgressDialog loading;
    public final String URL="http://188.166.240.88:8000/kapal/responprogress/";
    public RekapProgresAdapter(Context context, int resource, List<rekapprogres> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        rekapprogres list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.waktuproyek_rekapprogres_item,parent,false);
        }
        TextView nama=(TextView) convertView.findViewById(R.id.nama_rekapprogres_item);
        TextView sebelum=(TextView) convertView.findViewById(R.id.sebelum_rekapprogres_item);
        TextView saatini=(TextView) convertView.findViewById(R.id.saatini_rekapprogres_item);
        TextView komen=(TextView) convertView.findViewById(R.id.komen_rekapprogres_item);
        TextView tanggal=(TextView) convertView.findViewById(R.id.tanggal_rekapprogres_item);
        final TextView id=(TextView) convertView.findViewById(R.id.id_rekapprogres_item);
        TextView approvedby=(TextView) convertView.findViewById(R.id.approvedby_rekapprogres_item);
        id.setText(list.getId());
        id.setVisibility(View.INVISIBLE);
        Button approved=(Button) convertView.findViewById(R.id.approved_rekapprogress_item);
        nama.setText(list.getNama());
        sebelum.setText(list.getSebelum());
        saatini.setText(list.getSaatini());
        komen.setText(list.getKomen());
        tanggal.setText(list.getTanggal());
        if(list.getApprove().equals("null"))
            approvedby.setText("Belum diapprove");
        else{
            approved.setVisibility(View.INVISIBLE);
            approvedby.setText("PM");
        }


        SharedPreferences preferences = context.getSharedPreferences("session", context.getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = context.getSharedPreferences("kapal", context.getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        if(kategori_user.equals("Galangan")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("PM")){

        }
        else if(kategori_user.equals("OS")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("Dept. Pengadaan")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("Dept. Produksi")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("Subcont")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("Class")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("QA/QC")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("Owner")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("Vendor")){
            approved.setVisibility(View.INVISIBLE);
        }
        else if(kategori_user.equals("PPC")){
            approved.setVisibility(View.INVISIBLE);
        }
        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(v.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                //Toast.makeText(getContext(), id_budget, Toast.LENGTH_SHORT).show();

                approve(id.getText().toString());
            }
        });
        return convertView;
    }
    public void approve(final String idprogres){
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
                params.put("id_progress",idprogres);
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
                    Intent intent=new Intent(getContext(), RekapProgresProyek.class);
                    getContext().startActivity(intent);
                }

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
