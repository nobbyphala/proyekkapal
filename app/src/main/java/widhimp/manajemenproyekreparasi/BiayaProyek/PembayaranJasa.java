package widhimp.manajemenproyekreparasi.BiayaProyek;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import widhimp.manajemenproyekreparasi.Database.AmbilEstimasiSdm;
import widhimp.manajemenproyekreparasi.Database.AmbilPembayaranJasa;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class PembayaranJasa extends AppCompatActivity {
    private EditText nama, dana, perihal, tanggal;
    private Button simpan;
    private String id_pembayaranjasa, nama_pembayaranjasa, dana_pembayaranjasa, perihal_pembayaranjasa, tanggal_pembayaranjasa;
    private String idjasa, respon, username_user, password_user, kategori_user, id_kapal;
    private final String URL_AMBILDATA="http://188.166.240.88:8000/kapal/ambilpjasa/";
    private final String URL_SIMPANDATA="http://188.166.240.88:8000/kapal/tambahpembayaranjasa/";
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biayaproyek_pembayaranjasa);

        nama=(EditText) findViewById(R.id.namaperusahaan_pembayaranjasa);
        dana=(EditText) findViewById(R.id.besarandana_pembayaranjasa);
        perihal=(EditText) findViewById(R.id.perihal_pembayaranjasa);
        tanggal=(EditText) findViewById(R.id.tanggal_pembayaranjasa);
        simpan=(Button) findViewById(R.id.simpan_pembayaranjasa);
        simpan.setOnClickListener(operasi);



        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("Galangan")){
//        }
//        else if(kategori_user.equals("PM")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("OS")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Subcont")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Class")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Owner")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Vendor")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PPC")){
//            nama.setEnabled(false);
//            dana.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.simpan_pembayaranjasa:
                    loading = ProgressDialog.show(v.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    if(respon.equals("Gagal"))
                        simpandata();
                    else
                        suntingdata();
                    break;
            }
        }
    };

    public void simpandata(){
        nama_pembayaranjasa=nama.getText().toString();
        dana_pembayaranjasa=dana.getText().toString();
        perihal_pembayaranjasa=perihal.getText().toString();
        tanggal_pembayaranjasa=tanggal.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SIMPANDATA,
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
                params.put("password",password_user);
                params.put("nama_perusahaan",nama_pembayaranjasa);
                params.put("besaran_dana",dana_pembayaranjasa);
                params.put("perihal",perihal_pembayaranjasa);
                params.put("tanggal",tanggal_pembayaranjasa);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void suntingdata(){
        nama_pembayaranjasa=nama.getText().toString();
        dana_pembayaranjasa=dana.getText().toString();
        perihal_pembayaranjasa=perihal.getText().toString();
        tanggal_pembayaranjasa=tanggal.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SIMPANDATA,
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
                params.put("password",password_user);
                params.put("nama_perusahaan",nama_pembayaranjasa);
                params.put("besaran_dana",dana_pembayaranjasa);
                params.put("tanggal",tanggal_pembayaranjasa);
                params.put("perihal",perihal_pembayaranjasa);
                params.put("id_pembayaran_jasan",id_pembayaranjasa);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_AMBILDATA,
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
        AmbilPembayaranJasa ambilPembayaranJasa=new AmbilPembayaranJasa(json);
        ambilPembayaranJasa.ambilpembayaranjasa();
        nama.setText(AmbilPembayaranJasa.namaperusahaan);
        dana.setText(AmbilPembayaranJasa.besarandana);
        perihal.setText(AmbilPembayaranJasa.perihal);
        tanggal.setText(AmbilPembayaranJasa.tanggal);
        id_pembayaranjasa=AmbilPembayaranJasa.id;
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
        Intent intent=new Intent(getBaseContext(),BiayaProyek.class);
        startActivity(intent);
    }
}
