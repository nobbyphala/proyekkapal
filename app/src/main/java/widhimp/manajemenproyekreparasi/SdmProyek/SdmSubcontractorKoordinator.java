package widhimp.manajemenproyekreparasi.SdmProyek;

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
import widhimp.manajemenproyekreparasi.Database.AmbilSubcontractorKoordinator;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.WaktuProyek.WaktuProyek;

public class SdmSubcontractorKoordinator extends AppCompatActivity {
    private Button sunting;
    private EditText nama, jabatan, kontak;
    private String nama_koordinator, jabatan_koordinator, kontak_koordinator;
    private String idkontraktor, namakontraktor, namakoordinator, jabatankoordinator, kontakkoordinator;
    private TextView namapt;
    private Bundle bundle;
    private String username_user, password_user, kategori_user, id_kapal;
    private final String URL_POST="http://188.166.240.88:8000/kapal/tambahsubcontractor/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdmproyek_subcontractor_koordinator);
        sunting=(Button) findViewById(R.id.sunting_subcontractor_koordinator);
        nama=(EditText) findViewById(R.id.nama_subcontractor_koordinator);
        jabatan=(EditText) findViewById(R.id.jabatan_subcontractor_koordinator);
        kontak=(EditText) findViewById(R.id.kontak_subcontractor_koordinator);
        namapt=(TextView) findViewById(R.id.namapt_subcontractor_koordinator);
        bundle = getIntent().getExtras();
        namakontraktor= bundle.getString("nama");
        idkontraktor=bundle.getString("id");

        namakoordinator=bundle.getString("koordinator");
        jabatankoordinator=bundle.getString("jabatan");
        kontakkoordinator=bundle.getString("kontak");

        nama.setText(namakoordinator);
        jabatan.setText(jabatankoordinator);
        kontak.setText(kontakkoordinator);

        namapt.setText("Koordinator "+namakontraktor + " :");
        sunting.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

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
                case R.id.sunting_subcontractor_koordinator:
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
        Intent intent=new Intent(getBaseContext(),SdmSubcontractorDetail.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void cekinput(){
        nama_koordinator=nama.getText().toString();
        jabatan_koordinator=jabatan.getText().toString();
        kontak_koordinator=kontak.getText().toString();
        if(nama_koordinator.isEmpty()||jabatan_koordinator.isEmpty()||kontak_koordinator.isEmpty()){
            showResponse("Harap menginputkan data dengan lengkap");
        }
        else
            simpankoordinator();
    }

    public void simpankoordinator(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST,
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
                params.put("password", password_user);
                params.put("id_kapal",id_kapal);
                params.put("nama_koordinator",nama_koordinator);
                params.put("nama_kontraktor",namakontraktor);
                params.put("jabatan_koordinator", jabatan_koordinator);
                params.put("kontak",kontak_koordinator);
                params.put("id_subcontractor",idkontraktor);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
