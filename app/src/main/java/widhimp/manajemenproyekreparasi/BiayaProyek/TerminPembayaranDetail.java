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
import widhimp.manajemenproyekreparasi.Database.AmbilPenjadwalanDetail;
import widhimp.manajemenproyekreparasi.Database.AmbilTermin;
import widhimp.manajemenproyekreparasi.Database.AmbilTerminDetail;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmSubcontractorDetail;

public class TerminPembayaranDetail extends AppCompatActivity {
    private Button sunting, simpan;
    private String username_user, password_user, kategori_user;
    private EditText tanggal, jumlah, pembayar, penerima, catatan;
    private TextView namatermin;
    private String id_termin, id_kapal, nama_termin, tanggal_termin, jumlah_termin, pembayar_termin, penerima_termin, catatan_termin;
    public final String URL_GET="http://188.166.240.88:8000/kapal/detailtermin/";
    public final String URL_SUNTING="http://188.166.240.88:8000/kapal/termin/";
    public final String URL_SIMPAN="http://188.166.240.88:8000/kapal/catatantermin/";
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biayaproyek_termin_detail);
        sunting=(Button) findViewById(R.id.sunting_biayaproyek_termin_detail);
        simpan=(Button) findViewById(R.id.simpan_biayaproyek_termin_detail);
        tanggal=(EditText) findViewById(R.id.tglpembayaran_biayaproyek_termin_detail);
        jumlah=(EditText) findViewById(R.id.jmlpembayaran_biayaproyek_termin_detail);
        pembayar=(EditText) findViewById(R.id.pembayar_biayaproyek_termin_detail);
        penerima=(EditText) findViewById(R.id.penerima_biayaproyek_termin_detail);
        catatan=(EditText) findViewById(R.id.catatan_biayaproyek_termin_detail);
        namatermin=(TextView) findViewById(R.id.namatermin_biayaproyek_termin_detail);

        Bundle bundle = getIntent().getExtras();
        id_termin = bundle.getString("idtermin");
        nama_termin=bundle.getString("namatermin");
        namatermin.setText(nama_termin + " :");

        simpan.setOnClickListener(operasi);
        sunting.setOnClickListener(operasi);

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
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("OS")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Subcont")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Class")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Owner")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("PPC")){
//            sunting.setVisibility(View.INVISIBLE);
//            simpan.setVisibility(View.INVISIBLE);
//            tanggal.setEnabled(false);
//            jumlah.setEnabled(false);
//            pembayar.setEnabled(false);
//            penerima.setEnabled(false);
//            catatan.setEnabled(false);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.sunting_biayaproyek_termin_detail:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    cekinputsunting();
                    break;
                case R.id.simpan_penjadwalanproyek:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    cekinputsimpan();
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
        Intent intent=new Intent(getBaseContext(),TerminPembayaran.class);
        startActivity(intent);
    }

    public void cekinputsunting(){
        tanggal_termin=tanggal.getText().toString();
        jumlah_termin=jumlah.getText().toString();
        pembayar_termin=pembayar.getText().toString();
        penerima_termin=penerima.getText().toString();
        if(tanggal_termin.isEmpty()||jumlah_termin.isEmpty()||tanggal_termin.isEmpty()||pembayar_termin.isEmpty()||penerima_termin.isEmpty()){
            showResponse("Harap menginputkan data dengan lengkap");
        }
        else
            fungsisunting();
    }

    public void cekinputsimpan(){
        catatan_termin=catatan.getText().toString();
        if(catatan_termin.isEmpty()){
            showResponse("Harap menginputkan data dengan lengkap");
        }
        else
            fungsisimpan();
    }

    public void fungsisunting(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SUNTING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
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
                params.put("id_kapal",id_kapal);
                params.put("nama_termin",nama_termin);
                params.put("tanggal_pembayaran",tanggal_termin);
                params.put("jumlah_pembayaran",jumlah_termin);
                params.put("pihak_pembayaran",pembayar_termin);
                params.put("pihak_penerima",penerima_termin);
                params.put("id_termin",id_termin);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void fungsisimpan(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SIMPAN,
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
                params.put("id_termin",id_termin);
                params.put("username",username_user);
                params.put("password",password_user);
                params.put("catatan",catatan_termin);
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
                        loading.dismiss();
                        if(response.equals("Gagal")){
                            showResponse("Ambil Data Gagal");
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
                params.put("id_termin",id_termin);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilTerminDetail ambilTerminDetail=new AmbilTerminDetail(json);
        ambilTerminDetail.ambiltermindetail();
        tanggal.setText(AmbilTerminDetail.tanggal);
        jumlah.setText(AmbilTerminDetail.jumlah);
        pembayar.setText(AmbilTerminDetail.pembayar);
        penerima.setText(AmbilTerminDetail.penerima);
        catatan.setText(AmbilTerminDetail.catatan);
    }

    private void showResponse(String respon){
        loading.dismiss();
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
