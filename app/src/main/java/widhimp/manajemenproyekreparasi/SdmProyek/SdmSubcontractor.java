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
import widhimp.manajemenproyekreparasi.Adapter.KomunikasiAdapter;
import widhimp.manajemenproyekreparasi.Adapter.SubContractorAdapter;
import widhimp.manajemenproyekreparasi.Database.AmbilKomunikasi;
import widhimp.manajemenproyekreparasi.Database.AmbilSubContractor;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Komunikasi.KomunikasiTambah;
import widhimp.manajemenproyekreparasi.Object.dokumen;
import widhimp.manajemenproyekreparasi.Object.subcontractor;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.RepairList.RepairListDetail;
import widhimp.manajemenproyekreparasi.WaktuProyek.WaktuProyek;

public class SdmSubcontractor extends AppCompatActivity {
    Button tambah;
    private String username_user, password_user, kategori_user, id_kapal;
    public final String URL_GET="http://188.166.240.88:8000/kapal/listsubcontractor/";
    public final String URL_POST="http://188.166.240.88:8000/kapal/tambahsubcontractor/";
    private ProgressDialog loading;
    private String nama_subcontractor, koordinator_subcontractor, jabatan_subcontractor, kontak_subcontractor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdmproyek_subcontractor);
        tambah=(Button) findViewById(R.id.tambah_sdmproyek_subcontractor);
        tambah.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("admin"))
//            tambah.setVisibility(View.VISIBLE);
//        else if(kategori_user.equals("user"))
//            tambah.setVisibility(View.INVISIBLE);

    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.tambah_sdmproyek_subcontractor:
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
        Intent intent=new Intent(getBaseContext(),SdmProyek.class);
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
        AmbilSubContractor ambilSubContractor= new AmbilSubContractor(json);
        ArrayList<subcontractor> list = new ArrayList<subcontractor>();
        ListView listView=(ListView) findViewById(R.id.list_sdmproyek_subcontractor);
        SubContractorAdapter subContractorAdapter= new SubContractorAdapter(this, 0, list);
        ambilSubContractor.ambilsubcontractor(subContractorAdapter);
        listView.setAdapter(subContractorAdapter);
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
        View input = li.inflate(R.layout.sdmproyek_subcontractor_tambah,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Button tambah=(Button)input.findViewById(R.id.tambah_subcontractor_tambah);
        final EditText koordinator=(EditText)input.findViewById(R.id.koordinator_subcontractor_tambah);
        final EditText nama=(EditText)input.findViewById(R.id.nama_subcontractor_tambah);
        final EditText jabatan=(EditText)input.findViewById(R.id.jabatan_subcontractor_tambah);
        final EditText kontak=(EditText)input.findViewById(R.id.kontak_subcontractor_tambah);
        tambah.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                nama_subcontractor=nama.getText().toString();
                koordinator_subcontractor=koordinator.getText().toString();
                jabatan_subcontractor=jabatan.getText().toString();
                kontak_subcontractor=kontak.getText().toString();
                insert();
            }
        });
        dialog.setView(input);
        dialog.setCancelable(true);
        dialog.show();
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
                params.put("nama_koordinator",koordinator_subcontractor);
                params.put("nama_kontraktor",nama_subcontractor);
                params.put("jabatan_koordinator",jabatan_subcontractor);
                params.put("kontak",kontak_subcontractor);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void openUlang() {
        Intent intentTambah = new Intent(getBaseContext(), SdmSubcontractor.class);
        startActivityForResult(intentTambah, 0);
    }
}
