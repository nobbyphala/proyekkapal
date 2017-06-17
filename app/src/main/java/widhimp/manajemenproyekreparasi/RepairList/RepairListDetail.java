package widhimp.manajemenproyekreparasi.RepairList;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.ListkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Adapter.KomunikasiAdapter;
import widhimp.manajemenproyekreparasi.Adapter.RepairlistAdapter;
import widhimp.manajemenproyekreparasi.Database.AmbilKomunikasi;
import widhimp.manajemenproyekreparasi.Database.AmbilRepairList;
import widhimp.manajemenproyekreparasi.Database.Database;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Komunikasi.KomunikasiActivity;
import widhimp.manajemenproyekreparasi.Object.dokumen;
import widhimp.manajemenproyekreparasi.Object.repairlist;
import widhimp.manajemenproyekreparasi.R;

public class RepairListDetail extends AppCompatActivity {
    private String id_repairlist, namarepairlist, id_kapal;
    private TextView nama, namarepair;
    private Button tambah, tambahrepair;
    private ProgressDialog loading;
    private String username_user, password_user, kategori_user;
    public final String URL_GET="http://188.166.240.88:8000/kapal/ambilrepairlist/";
    public final String URL_POST="http://188.166.240.88:8000/kapal/tambahrepairlist/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repairlist_detail);
        nama=(TextView)findViewById(R.id.nama_repairlist);
        tambah=(Button)findViewById(R.id.tambah_repairlistdetail);

        Bundle bundle = getIntent().getExtras();
        namarepairlist = bundle.getString("namarepairlist");
        nama.setText(namarepairlist + " :");
        tambah.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        if(namarepairlist.equals("Pengedokan"))
            id_repairlist="1";
        else if(namarepairlist.equals("Pelayanan Umum"))
            id_repairlist="2";
        else if(namarepairlist.equals("Pembersihan dan Pengecatan"))
            id_repairlist="3";
        else if(namarepairlist.equals("Replating"))
            id_repairlist="4";
        else if(namarepairlist.equals("Cathodic Protection"))
            id_repairlist="5";
        else if(namarepairlist.equals("Sumbat Lunas, Almari Lambung, dan Katub"))
            id_repairlist="6";
        else if(namarepairlist.equals("Valves"))
            id_repairlist="7";
        else if(namarepairlist.equals("Peralatan Lambung dan Kelengkapannya"))
            id_repairlist="8";
        else if(namarepairlist.equals("Deck Outfitting"))
            id_repairlist="9";
        else if(namarepairlist.equals("Permesinan dan Kelengkapan"))
            id_repairlist="10";
        else if(namarepairlist.equals("Navigation Equipment"))
            id_repairlist="11";
        else if(namarepairlist.equals("Sistem Kelistrikan"))
            id_repairlist="12";
        else if(namarepairlist.equals("Peralatan Keselamatan"))
            id_repairlist="13";
        else if(namarepairlist.equals("Tangki - Tangki"))
            id_repairlist="14";
        else if(namarepairlist.equals("Perpipaan"))
            id_repairlist="15";

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("Galangan")){
//            tambah.setVisibility(View.VISIBLE);
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
                case R.id.tambah_repairlistdetail:openTambah();break;
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
        Intent intent=new Intent(getBaseContext(),RepairlistActivity.class);
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
                params.put("id_repairlist", id_repairlist);
                params.put("id_kapal",id_kapal);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilRepairList ambilRepairList= new AmbilRepairList(json);
        ArrayList<repairlist> listrepair = new ArrayList<repairlist>();
        ListView listView=(ListView) findViewById(R.id.list_repairlist);
        RepairlistAdapter repairlistAdapter = new RepairlistAdapter(this, 0, listrepair);
        ambilRepairList.ambilrepairlist(repairlistAdapter);
        listView.setAdapter(repairlistAdapter);
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
        View input = li.inflate(R.layout.repairlist_tambah,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        tambahrepair=(Button)input.findViewById(R.id.tambah_tambahrepair);
        namarepair=(EditText)input.findViewById(R.id.nama_tambahrepairlist);
        tambahrepair.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String nama=namarepair.getText().toString();
                insert(nama);
                openUlang();
            }
        });
        dialog.setView(input);
        dialog.setCancelable(true);
        dialog.show();
    }
    public void insert(final String nama){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(RepairListDetail.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("id_repairlist",id_repairlist);
                params.put("id_kapal",id_kapal);
                params.put("detail",nama);
                params.put("username",username_user);
                params.put("password",password_user);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void openUlang() {
        Bundle bundle = new Bundle();
        bundle.putString("namarepairlist", namarepairlist);
        Intent intent=new Intent(getBaseContext(),RepairListDetail.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, 0);
    }
}
