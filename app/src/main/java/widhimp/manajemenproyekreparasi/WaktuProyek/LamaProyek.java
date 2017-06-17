package widhimp.manajemenproyekreparasi.WaktuProyek;

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
import android.widget.RadioButton;
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
import widhimp.manajemenproyekreparasi.Database.AmbilKomunikasiEdit;
import widhimp.manajemenproyekreparasi.Database.AmbilLamaProyek;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class LamaProyek extends AppCompatActivity {
    private EditText durasi, mulai, selesai;
    private Button sunting, simpan;
    private String respon, username_user, password_user, kategori_user;
    private final String URL_GET="http://188.166.240.88:8000/kapal/lihatlamaproyek/";
    private final String URL_POST="http://188.166.240.88:8000/kapal/tambahsuntinglamaproyek/";
    private final String URL_TAMBAH="http://188.166.240.88:8000/kapal/tambahlamaproyek/";
    private String durasi_proyek, mulai_proyek, selesai_proyek;
    private String id_kapal, id_lamaproyek;
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waktuproyek_lama);
        durasi=(EditText) findViewById(R.id.durasi_lamaproyek);
        mulai=(EditText) findViewById(R.id.tanggalmulai_lamaproyek);
        selesai=(EditText) findViewById(R.id.tanggalselesai_lamaproyek);
        simpan=(Button) findViewById(R.id.simpan_lamaproyek);

        simpan.setOnClickListener(operasi);

        durasi_proyek=durasi.getText().toString();
        mulai_proyek=mulai.getText().toString();
        selesai_proyek=selesai.getText().toString();

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("Galangan")){
//
//        }
//        else if(kategori_user.equals("PM")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("OS")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Subcont")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Class")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Owner")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Vendor")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PPC")){
//            durasi.setEnabled(false);
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.simpan_lamaproyek:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    if(respon.equals("Gagal"))
                    {
                        cekinput();
                    }

                    else
                        suntinglama();
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
        Intent intent=new Intent(getBaseContext(),WaktuProyek.class);
        startActivity(intent);
    }

    public void cekinput(){
        durasi_proyek=durasi.getText().toString();
        mulai_proyek=mulai.getText().toString();
        selesai_proyek=selesai.getText().toString();
        if(durasi_proyek.isEmpty()||mulai_proyek.isEmpty()||selesai_proyek.isEmpty()){
            showResponse("Harap menginputkan data dengan lengkap");
        }
        else
            simpanlamaproyek();
    }

    public void simpanlamaproyek(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST,
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
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username_user);
                params.put("id_kapal",id_kapal);
                params.put("durasi_reparasi",durasi_proyek);
                params.put("tanggal_mulai",mulai_proyek);
                params.put("tanggal_selesai",selesai_proyek);
                params.put("password",password_user);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void suntinglama(){
        durasi_proyek=durasi.getText().toString();
        mulai_proyek=mulai.getText().toString();
        selesai_proyek=selesai.getText().toString();
        //Toast.makeText(this, id_lamaproyek, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST,
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
                        Toast.makeText(getBaseContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username_user);
                params.put("id_kapal",id_kapal);
                params.put("durasi_reparasi",durasi_proyek);
                params.put("tanggal_mulai",mulai_proyek);
                params.put("tanggal_selesai",selesai_proyek);
                params.put("password",password_user);
                params.put("id_lamaproyek",id_lamaproyek);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        respon=response;
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
                params.put("id_kapal",id_kapal);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        AmbilLamaProyek ambilLamaProyek=new AmbilLamaProyek(json);
        ambilLamaProyek.ambillamaproyek();
        durasi.setText(AmbilLamaProyek.durasi);
        mulai.setText(AmbilLamaProyek.mulai);
        selesai.setText(AmbilLamaProyek.selesai);
        id_lamaproyek=AmbilLamaProyek.id;
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
}
