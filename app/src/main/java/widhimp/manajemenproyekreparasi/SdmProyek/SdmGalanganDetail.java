package widhimp.manajemenproyekreparasi.SdmProyek;

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
import android.widget.TextView;
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

import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Adapter.RepairlistAdapter;
import widhimp.manajemenproyekreparasi.Adapter.SdmgalangandetailAdapter;
import widhimp.manajemenproyekreparasi.Database.AmbilRepairList;
import widhimp.manajemenproyekreparasi.Database.AmbilSdmGalanganDetail;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Object.repairlist;
import widhimp.manajemenproyekreparasi.Object.sdmgalangandetail;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.RepairList.RepairListDetail;
import widhimp.manajemenproyekreparasi.WaktuProyek.WaktuProyek;

public class SdmGalanganDetail extends AppCompatActivity {
    private Button tambah;
    private Bundle bundle;
    private String username_user, password_user, kategori_user;
    public final String URL_GET="http://188.166.240.88:8000/kapal/detailsdmgalangan/";
    public final String URL_POST="http://188.166.240.88:8000/kapal/tambahsdmgalangan/";
    private String namagalangan, idgalangan, id_kapal;
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.sdmproyek_galangan_detail);
        super.onCreate(savedInstanceState);
        tambah=(Button) findViewById(R.id.tambah_sdmgalangandetail);
        TextView nama=(TextView)findViewById(R.id.nama_sdmgalangandetail);
        bundle = getIntent().getExtras();
        namagalangan = bundle.getString("namagalangan");
        idgalangan=bundle.getString("idgalangan");
        nama.setText(namagalangan + " :");
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
//        }
//        else if(kategori_user.equals("PM")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("OS")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Subcont")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Class")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Owner")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Vendor")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PPC")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.tambah_sdmgalangandetail:
                    openTambah();break;
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
        Intent intent=new Intent(getBaseContext(),SdmGalangan.class);
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
                params.put("id_departemen",idgalangan);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilSdmGalanganDetail ambilSdmGalanganDetail= new AmbilSdmGalanganDetail(json);
        ArrayList<sdmgalangandetail> list = new ArrayList<sdmgalangandetail>();
        ListView listView=(ListView) findViewById(R.id.list_sdmgalangandetail);
        SdmgalangandetailAdapter sdmgalangandetailAdapter = new SdmgalangandetailAdapter(this, 0, list);
        ambilSdmGalanganDetail.ambilsdmgalangandetail(sdmgalangandetailAdapter);
        listView.setAdapter(sdmgalangandetailAdapter);
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
        View input = li.inflate(R.layout.sdmproyek_galangan_detail_tambah,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Button tambahgalangandetail=(Button)input.findViewById(R.id.tambah_tambahgalangandetail);
        final EditText namagalangandetail=(EditText)input.findViewById(R.id.nama_tambahgalangandetail);
        final EditText jabatan=(EditText)input.findViewById(R.id.jabatan_tambahgalangandetail);
        final EditText kontak=(EditText)input.findViewById(R.id.kontak_tambahgalangandetail);
        tambahgalangandetail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nama=namagalangandetail.getText().toString();
                String jabatangalangandetail=jabatan.getText().toString();
                String kontakgalangandetail=kontak.getText().toString();
                insert(nama,jabatangalangandetail,kontakgalangandetail);
            }
        });
        dialog.setView(input);
        dialog.setCancelable(true);
        dialog.show();
    }

    public void insert(final String nama, final String jabatan, final String kontak){
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
                params.put("id_departemen",idgalangan);
                params.put("nama_anggota",nama);
                params.put("jabatan",jabatan);
                params.put("kontak",kontak);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void openUlang() {
        Intent intentTambah = new Intent(getBaseContext(), SdmGalanganDetail.class);
        intentTambah.putExtras(bundle);
        startActivityForResult(intentTambah, 0);
    }
}
