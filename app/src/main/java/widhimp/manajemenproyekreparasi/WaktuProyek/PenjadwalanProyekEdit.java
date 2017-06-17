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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Database.AmbilLamaProyek;
import widhimp.manajemenproyekreparasi.Database.AmbilPenjadwalan;
import widhimp.manajemenproyekreparasi.Database.AmbilPenjadwalanDetail;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class PenjadwalanProyekEdit extends AppCompatActivity {
    private String username_user, password_user, kategori_user, respon;
    private String id_repairdetail, id_penjadwalan;
    private Button simpan, sunting;
    private EditText mulai, selesai, catatan;
    private final String URL_GET="http://188.166.240.88:8000/kapal/listpenjadwalan/";
    private final String URL_KOMEN="http://188.166.240.88:8000/kapal/komenpenjadwalan/";
    private final String URL_TAMBAH="http://188.166.240.88:8000/kapal/tambahpenjadwalan/";
    private String mulai_penjadwalan, selesai_penjadwalan, catatan_penjadwalan;
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waktuproyek_penjadwalan_edit);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("nama");
        TextView nama=(TextView) findViewById(R.id.nama_editpenjadwalan);
        nama.setText(name + " :");

        id_repairdetail=bundle.getString("id");
        simpan=(Button) findViewById(R.id.simpan_penjadwalanproyek);
        sunting=(Button) findViewById(R.id.sunting_penjadwalanproyek);
        mulai=(EditText) findViewById(R.id.tanggalmulai_penjadwalan);
        selesai=(EditText) findViewById(R.id.tanggalselesai_penjadwalan);
        catatan=(EditText) findViewById(R.id.catatan_penjadwalan);

        simpan.setOnClickListener(operasi);
        sunting.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        mulai_penjadwalan=mulai.getText().toString();
        selesai_penjadwalan=selesai.getText().toString();

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("Galangan")){
//            simpan.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("PM")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("OS")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Subcont")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Class")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Owner")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Vendor")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PPC")){
//            mulai.setEnabled(false);
//            selesai.setEnabled(false);
//            sunting.setVisibility(View.INVISIBLE);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.sunting_penjadwalanproyek:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    if(respon.equals("Gagal"))
                        cekinput();
                    else
                        suntingdata();
                    break;
                case R.id.simpan_penjadwalanproyek:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    komenjadwalproyek();
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
        Intent intent=new Intent(getBaseContext(),PenjadwalanProyek.class);
        startActivity(intent);
    }

    public void cekinput(){
        mulai_penjadwalan=mulai.getText().toString();
        selesai_penjadwalan=selesai.getText().toString();
        if(mulai_penjadwalan.isEmpty()||selesai_penjadwalan.isEmpty()){
            showResponse("Harap menginputkan data dengan lengkap");
        }
        else
            tambahdata();
    }

    public void tambahdata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TAMBAH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showResponse("Data tersimpan");
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
                params.put("id_repairdetail",id_repairdetail);
                params.put("tanggal_mulai", mulai_penjadwalan);
                params.put("tanggal_selesai", selesai_penjadwalan);
                params.put("catatan","");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void suntingdata(){
        //Toast.makeText(this, id_penjadwalan, Toast.LENGTH_SHORT).show();
        mulai_penjadwalan=mulai.getText().toString();
        selesai_penjadwalan=selesai.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TAMBAH,
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
                params.put("password",password_user);
                params.put("id_repairdetail",id_repairdetail);
                params.put("tanggal_mulai", mulai_penjadwalan);
                params.put("tanggal_selesai", selesai_penjadwalan);
                params.put("catatan","");
                params.put("id_penjadwalan",id_penjadwalan);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void komenjadwalproyek(){
        catatan_penjadwalan=catatan.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_KOMEN,
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
                params.put("password",password_user);
                params.put("id_penjadwalan",id_penjadwalan);
                params.put("catatan",catatan_penjadwalan);
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
                        //Toast.makeText(PenjadwalanProyekEdit.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("id_repairdetail", id_repairdetail);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilPenjadwalanDetail ambilPenjadwalanDetail=new AmbilPenjadwalanDetail(json);
        ambilPenjadwalanDetail.ambilpenjadwalandetail();
        mulai.setText(AmbilPenjadwalanDetail.mulai);
        selesai.setText(AmbilPenjadwalanDetail.selesai);
        catatan.setText(AmbilPenjadwalanDetail.komen);
        id_penjadwalan=AmbilPenjadwalanDetail.id;
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
