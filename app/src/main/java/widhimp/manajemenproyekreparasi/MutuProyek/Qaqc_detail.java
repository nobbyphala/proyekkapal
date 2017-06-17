package widhimp.manajemenproyekreparasi.MutuProyek;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Database.AmbilQaqcDetail;
import widhimp.manajemenproyekreparasi.Database.AmbilSubcontractorKoordinator;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class Qaqc_detail extends AppCompatActivity {
    private Button sunting;
    private EditText nama, jabatan, kontak;
    private String nama_qaqc, jabatan_qaqc, kontak_qaqc,id_kapal, id_qaqc;
    private String username_user, password_user, kategori_user;
    private final String URL_GET="http://188.166.240.88:8000/kapal/lihatqaqc/";
    private final String URL_POST="http://188.166.240.88:8000/kapal/tambahsuntingqaqc/";
    private  ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutuproyek_qaqc_detail);
        sunting=(Button) findViewById(R.id.sunting_mutuproyek_qaqc_detail);
        nama=(EditText) findViewById(R.id.nama_mutuproyek_qaqc_detail);
        jabatan=(EditText) findViewById(R.id.jabatan_mutuproyek_qaqc_detail);
        kontak=(EditText) findViewById(R.id.kontak_mutuproyek_qaqc_detail);
        sunting.setOnClickListener(operasi);

        Bundle bundle = getIntent().getExtras();
        id_qaqc = bundle.getString("id_qaqc");

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);
        ambildata();

//        if(kategori_user.equals("Galangan")){
//        }
//        else if(kategori_user.equals("PM")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("OS")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("Subcont")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("Class")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("Owner")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
//        else if(kategori_user.equals("PPC")){
//            sunting.setVisibility(View.INVISIBLE);
//            nama.setEnabled(false);
//            jabatan.setEnabled(false);
//            kontak.setEnabled(false);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.sunting_mutuproyek_qaqc_detail:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    cekinput();
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutop,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.inbox:
                Intent intentinbox=new Intent(getBaseContext(),Inbox.class);
                startActivity(intentinbox);
                break;
            case R.id.logout:
                Intent intentlogout=new Intent(getBaseContext(),SigninActivity.class);
                startActivity(intentlogout);
                break;
            case R.id.kirimpesan:
                Intent intentpesan=new Intent(getBaseContext(),KirimPesan.class);
                startActivity(intentpesan);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getBaseContext(),Qaqc.class);
        startActivity(intent);
    }

    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        if(response.equals("Gagal")){

                        }
                        else
                            showdata(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username_user);
                params.put("password",password_user);
                params.put("id_qaqc",id_qaqc);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilQaqcDetail ambilQaqcDetail=new AmbilQaqcDetail(json);
        ambilQaqcDetail.ambilqaqcdetail();
        nama.setText(AmbilQaqcDetail.nama);
        jabatan.setText(AmbilQaqcDetail.jabatan);
        kontak.setText(AmbilQaqcDetail.kontak);
    }

    private void showResponse(String respon){
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(respon);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void cekinput(){
        nama_qaqc=nama.getText().toString();
        jabatan_qaqc=jabatan.getText().toString();
        kontak_qaqc=kontak.getText().toString();
        if(nama_qaqc.isEmpty()||jabatan_qaqc.isEmpty()||kontak_qaqc.isEmpty()){
            showResponse("Harap menginputkan data dengan lengkap");
        }
        else
            simpandata();
    }

    public void simpandata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        showResponse("Data tersimpan");
                        Intent intent=new Intent(getBaseContext(),Qaqc.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("id_kapal",id_kapal);
                params.put("nama",nama_qaqc);
                params.put("jabatan",jabatan_qaqc);
                params.put("kontak",kontak_qaqc);
                params.put("username",username_user);
                params.put("password",password_user);
                params.put("id_qaqc",id_qaqc);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
