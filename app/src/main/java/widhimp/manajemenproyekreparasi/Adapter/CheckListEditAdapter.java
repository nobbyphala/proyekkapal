package widhimp.manajemenproyekreparasi.Adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import widhimp.manajemenproyekreparasi.MutuProyek.CheckList;
import widhimp.manajemenproyekreparasi.MutuProyek.CheckListEdit;
import widhimp.manajemenproyekreparasi.Object.checklist;
import widhimp.manajemenproyekreparasi.Object.checklistedit;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 1/2/2017.
 */

public class CheckListEditAdapter extends ArrayAdapter<checklistedit> {
    private String username_user, password_user, kategori_user;
    private EditText status;
    private String id_kapal;
    private String id_checklist, status_checklist;
    private ProgressDialog loading;
    private final Context context;
    public final String URL="http://188.166.240.88:8000/kapal/responchecklist/";
    public CheckListEditAdapter(Context context, int resource, List<checklistedit> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final checklistedit listkapal = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mutuproyek_checklist_edit_item,parent,false);
        }
        final TextView name=(TextView) convertView.findViewById(R.id.nama_checklist_edit_item);
        status=(EditText) convertView.findViewById(R.id.status_checklist_edit_item);
        TextView approved=(TextView) convertView.findViewById(R.id.approved_checklist_edit_item);
        final TextView komen=(TextView) convertView.findViewById(R.id.komen_checklist_edit_item);
        final Button simpan=(Button) convertView.findViewById(R.id.simpan_checklist_edit_item);
        name.setText(listkapal.getNama());
        if(listkapal.getStatus().equals("true"))
            status.setText("YES");
        else
            status.setText("NO");
        approved.setText(listkapal.getApproved());
        komen.setText(listkapal.getKomentar());
        id_checklist=listkapal.getId();

        SharedPreferences preferences = context.getSharedPreferences("session", context.getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = context.getSharedPreferences("kapal", context.getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        if(kategori_user.equals("Galangan")){
            simpan.setVisibility(View.INVISIBLE);
            status.setEnabled(false);
        }
        else if(kategori_user.equals("PM")){
            simpan.setVisibility(View.INVISIBLE);
            status.setEnabled(false);
        }
        else if(kategori_user.equals("OS")){
        }
        else if(kategori_user.equals("Dept. Pengadaan")){
            simpan.setVisibility(View.INVISIBLE);
            status.setEnabled(false);
        }
        else if(kategori_user.equals("Dept. Produksi")){
            simpan.setVisibility(View.INVISIBLE);
            status.setEnabled(false);
        }
        else if(kategori_user.equals("Subcont")){
            simpan.setVisibility(View.INVISIBLE);
            status.setEnabled(false);
        }
        else if(kategori_user.equals("Class")){
        }
        else if(kategori_user.equals("QA/QC")){
        }
        else if(kategori_user.equals("Owner")){
            simpan.setVisibility(View.INVISIBLE);
            status.setEnabled(false);
        }
        else if(kategori_user.equals("Vendor")){
            simpan.setVisibility(View.INVISIBLE);
            status.setEnabled(false);
        }
        else if(kategori_user.equals("PPC")){
            simpan.setVisibility(View.INVISIBLE);
            status.setEnabled(false);
        }

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(v.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                //Toast.makeText(getContext(), id_checklist, Toast.LENGTH_SHORT).show();
                respon();
            }
        });

        return convertView;
    }

    public void respon(){
        if(status.getText().toString().equals("yes")||status.getText().toString().equals("Yes")||status.getText().toString().equals("YES"))
            status_checklist="1";
        else if(status.getText().toString().equals("no")||status.getText().toString().equals("No")||status.getText().toString().equals("NO"))
            status_checklist="0";
        //Toast.makeText(getContext(), status_checklist, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
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
                params.put("id_checklist",id_checklist);
                params.put("status",status_checklist);
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
                    Intent intent=new Intent(getContext(), CheckList.class);
                    getContext().startActivity(intent);
                }

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
