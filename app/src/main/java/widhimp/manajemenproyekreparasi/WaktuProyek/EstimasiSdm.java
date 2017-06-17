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
import widhimp.manajemenproyekreparasi.Database.AmbilEstimasiSdm;
import widhimp.manajemenproyekreparasi.Database.AmbilInbox;
import widhimp.manajemenproyekreparasi.Database.AmbilLamaProyek;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class EstimasiSdm extends AppCompatActivity {
    private EditText internalgalangan, subkontraktor;
    private Button simpan;
    private String idestimasi, respon, username_user, password_user, kategori_user, id_kapal;
    private String internalgalangan_estimasi, subkontraktor_estimasi;
    private final String URL_AMBILDATA="http://188.166.240.88:8000/kapal/ambilestimasisdm/";
    private final String URL_SIMPANDATA="http://188.166.240.88:8000/kapal/tambahestimasisdm/";
    private ProgressDialog loading;
    private String[] kategori = {"Baik","Kurang Baik"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waktuproyek_estimasisdm);

        internalgalangan=(EditText) findViewById(R.id.internalgalangan_waktuproyek_estimasisdm);
        subkontraktor=(EditText) findViewById(R.id.subkontraktor_waktuproyek_estimasisdm);
        simpan=(Button) findViewById(R.id.simpan_waktuproyek_estimasisdm);



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
//            internalgalangan.setEnabled(false);
//            subkontraktor.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("OS")){
//            internalgalangan.setEnabled(false);
//            subkontraktor.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            internalgalangan.setEnabled(false);
//            subkontraktor.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            internalgalangan.setEnabled(false);
//            subkontraktor.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Subcont")){
//            internalgalangan.setEnabled(false);
//            subkontraktor.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Class")){
//        }
//        else if(kategori_user.equals("QA/QC")){
//            internalgalangan.setEnabled(false);
//            subkontraktor.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Owner")){
//        }
//        else if(kategori_user.equals("Vendor")){
//        }
//        else if(kategori_user.equals("PPC")){
//            internalgalangan.setEnabled(false);
//            subkontraktor.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.simpan_waktuproyek_estimasisdm:
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
        internalgalangan_estimasi=internalgalangan.getText().toString();
        subkontraktor_estimasi=subkontraktor.getText().toString();
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
                params.put("internal",internalgalangan_estimasi);
                params.put("subkontraktor",subkontraktor_estimasi);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
        Intent intent=new Intent(getBaseContext(),WaktuProyek.class);
        startActivity(intent);
    }

    public void suntingdata(){
        internalgalangan_estimasi=internalgalangan.getText().toString();
        subkontraktor_estimasi=subkontraktor.getText().toString();
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
                params.put("internal",internalgalangan_estimasi);
                params.put("subkontraktor",subkontraktor_estimasi);
                params.put("id_estimasi",idestimasi);
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
                        //Toast.makeText(EstimasiSdm.this, response, Toast.LENGTH_SHORT).show();
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
        AmbilEstimasiSdm ambilEstimasiSdm=new AmbilEstimasiSdm(json);
        ambilEstimasiSdm.ambilestimasisdm();
        internalgalangan.setText(AmbilEstimasiSdm.internal);
        subkontraktor.setText(AmbilEstimasiSdm.subkontraktor);
        idestimasi=AmbilEstimasiSdm.id;
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
