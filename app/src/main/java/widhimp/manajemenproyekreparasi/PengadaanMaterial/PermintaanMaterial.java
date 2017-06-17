package widhimp.manajemenproyekreparasi.PengadaanMaterial;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Adapter.MaterialtersediaAdapter;
import widhimp.manajemenproyekreparasi.Adapter.PermintaanMaterialAdapter;
import widhimp.manajemenproyekreparasi.Database.AmbilMaterialTersedia;
import widhimp.manajemenproyekreparasi.Database.AmbilPermintaanMaterial;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Object.materialtersedia;
import widhimp.manajemenproyekreparasi.Object.permintaanmaterial;
import widhimp.manajemenproyekreparasi.R;

public class PermintaanMaterial extends AppCompatActivity {
    private Button tambah, simpan;
    private String username_user, password_user, kategori_user;
    private String id_kapal;
    private ProgressDialog loading;
    private EditText nama, spesifikasi, jumlah, tanggal;
    private String nama_permintaan, spesifikasi_permintaan, jumlah_permintaan, tanggal_permintaan;
    public final String URL_GET="http://188.166.240.88:8000/kapal/listmintamaterial/";
    public final String URL_POST="http://188.166.240.88:8000/kapal/mintamaterial/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengadaanmaterial_permintaan);
        tambah=(Button) findViewById(R.id.tambah_permintaanmaterial);
        tambah.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("Galangan")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PM")){
//        }
//        else if(kategori_user.equals("OS")){
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Subcont")){
//        }
//        else if(kategori_user.equals("Class")){
//        }
//        else if(kategori_user.equals("QA/QC")){
//        }
//        else if(kategori_user.equals("Owner")){
//        }
//        else if(kategori_user.equals("Vendor")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PPC")){
//            tambah.setVisibility(View.INVISIBLE);
//        }

    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tambah_permintaanmaterial:
                    openTambah();
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
        Intent intent=new Intent(getBaseContext(),PengadaanMaterial.class);
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
                params.put("id_kapal",id_kapal);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilPermintaanMaterial ambilPermintaanMaterial=new AmbilPermintaanMaterial(json);
        ArrayList<permintaanmaterial> list = new ArrayList<permintaanmaterial>();
        ListView listView=(ListView) findViewById(R.id.list_permintaanmaterial);
        PermintaanMaterialAdapter permintaanMaterialAdapter = new PermintaanMaterialAdapter(this, 0, list);
        ambilPermintaanMaterial.ambilpermintaanmaterial(permintaanMaterialAdapter);
        listView.setAdapter(permintaanMaterialAdapter);
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

    public void openTambah(){
        LayoutInflater li = LayoutInflater.from(this);
        View input = li.inflate(R.layout.pengadaanmaterial_permintaan_tambah,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Button tambahmaterial=(Button)input.findViewById(R.id.tambah_permintaanmaterial_tambah);
        nama=(EditText)input.findViewById(R.id.nama_permintaanmaterial_tambah);
        spesifikasi=(EditText)input.findViewById(R.id.spesifikasi_permintaanmaterial_tambah);
        jumlah=(EditText)input.findViewById(R.id.jumlah_permintaanmaterial_tambah);
        tanggal=(EditText)input.findViewById(R.id.tanggal_permintaanmaterial_tambah);
        tambahmaterial.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                cekinput();
            }
        });
        dialog.setView(input);
        dialog.setCancelable(true);
        dialog.show();
    }

    public void cekinput(){
        nama_permintaan=nama.getText().toString();
        spesifikasi_permintaan=spesifikasi.getText().toString();
        jumlah_permintaan=jumlah.getText().toString();
        tanggal_permintaan=tanggal.getText().toString();
        if(nama_permintaan.isEmpty()||spesifikasi_permintaan.isEmpty()||jumlah_permintaan.isEmpty()||tanggal_permintaan.isEmpty())
            showResponse("Harap menginputkan data dengan lengkap");
        else{
            insert();
        }

    }
    public void insert(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        openUlang();
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
                params.put("nama_material",nama_permintaan);
                params.put("spesifikasi",spesifikasi_permintaan);
                params.put("jumlah",jumlah_permintaan);
                params.put("tanggal_dibutuhkan",tanggal_permintaan);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void openUlang() {
        Intent intentTambah = new Intent(getBaseContext(),PermintaanMaterial.class);
        startActivityForResult(intentTambah, 0);
    }
}
