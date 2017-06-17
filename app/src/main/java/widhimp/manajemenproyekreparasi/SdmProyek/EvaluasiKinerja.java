package widhimp.manajemenproyekreparasi.SdmProyek;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Database.AmbilEstimasiSdm;
import widhimp.manajemenproyekreparasi.Database.AmbilEvaluasiKinerja;
import widhimp.manajemenproyekreparasi.R;

public class EvaluasiKinerja extends AppCompatActivity {
    private EditText dari, kepada, perihal, tanggal,catatan;
    private String dari_evaluasi, kepada_evaluasi, perihal_evaluasi, tanggal_evaluasi, catatan_evaluasi;
    private Spinner tepatwaktu, kehadiran, kerjasama, kualitas;
    private Button simpan;
    private String idestimasi, respon, username_user, password_user, kategori_user, id_kapal;
    private String internalgalangan_estimasi, subkontraktor_estimasi;
    private final String URL_AMBILDATA="http://188.166.240.88:8000/kapal/ambilevaluasi/";
    private final String URL_SIMPANDATA="http://188.166.240.88:8000/kapal/tambahevaluasi/";
    private ProgressDialog loading;
    private String[] galangan = {"baik","kurang baik"};
    private String tepatwaktuvalue, kehadiranvalue, kerjasamavalue, kualitasvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdmproyek_evaluasikinerja);

        dari=(EditText) findViewById(R.id.dari_evaluasikinerja);
        kepada=(EditText) findViewById(R.id.kepada_evaluasikinerja);
        perihal=(EditText) findViewById(R.id.perihal_evaluasikinerja);
        tanggal=(EditText) findViewById(R.id.tanggal_evaluasikinerja);
        catatan=(EditText) findViewById(R.id.catatan_evaluasikinerja);
        simpan=(Button) findViewById(R.id.simpan_evaluasikinerja);
        tepatwaktu=(Spinner) findViewById(R.id.tepatwaktu_evaluasikinerja);
        kehadiran=(Spinner) findViewById(R.id.kehadiran_evaluasikinerja);
        kerjasama=(Spinner) findViewById(R.id.kerjasama_evaluasikinerja);
        kualitas=(Spinner) findViewById(R.id.kualitas_evaluasikinerja);
        simpan=(Button) findViewById(R.id.simpan_evaluasikinerja);
        simpan.setOnClickListener(operasi);



        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, galangan);
        tepatwaktu.setAdapter(adapter);
        kehadiran.setAdapter(adapter);
        kerjasama.setAdapter(adapter);
        kualitas.setAdapter(adapter);

        tepatwaktu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tepatwaktuvalue=adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        kehadiran.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kehadiranvalue=adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        kerjasama.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kerjasamavalue=adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        kualitas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kualitasvalue=adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        if(kategori_user.equals("Galangan")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PM")){
//        }
//        else if(kategori_user.equals("OS")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Subcont")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Class")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Owner")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Vendor")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PPC")){
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            tanggal.setEnabled(false);
//            tepatwaktu.setEnabled(false);
//            kehadiran.setEnabled(false);
//            kerjasama.setEnabled(false);
//            kualitas.setEnabled(false);
//            catatan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.simpan_evaluasikinerja:
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
        dari_evaluasi=dari.getText().toString();
        kepada_evaluasi=kepada.getText().toString();
        perihal_evaluasi=perihal.getText().toString();
        tanggal_evaluasi=tanggal.getText().toString();
        catatan_evaluasi=catatan.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SIMPANDATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                params.put("dari",dari_evaluasi);
                params.put("kepada",kepada_evaluasi);
                params.put("perihal",perihal_evaluasi);
                params.put("tanggal",tanggal_evaluasi);
                params.put("tepat_waktu",tepatwaktuvalue);
                params.put("kehadiran",kehadiranvalue);
                params.put("kerjasama",kerjasamavalue);
                params.put("kualitas",kualitasvalue);
                params.put("catatan",catatan_evaluasi);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void suntingdata(){
        dari_evaluasi=dari.getText().toString();
        kepada_evaluasi=kepada.getText().toString();
        perihal_evaluasi=perihal.getText().toString();
        tanggal_evaluasi=tanggal.getText().toString();
        catatan_evaluasi=catatan.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SIMPANDATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                params.put("dari",dari_evaluasi);
                params.put("kepada",kepada_evaluasi);
                params.put("perihal",perihal_evaluasi);
                params.put("tanggal",tanggal_evaluasi);
                params.put("tepat_waktu",tepatwaktuvalue);
                params.put("kehadiran",kehadiranvalue);
                params.put("kerjasama",kerjasamavalue);
                params.put("kualitas",kualitasvalue);
                params.put("catatan",catatan_evaluasi);
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
        AmbilEvaluasiKinerja ambilEvaluasiKinerja=new AmbilEvaluasiKinerja(json);
        ambilEvaluasiKinerja.ambilevaluasikinerja();
        dari.setText(AmbilEvaluasiKinerja.dari);
        kepada.setText(AmbilEvaluasiKinerja.kepada);
        perihal.setText(AmbilEvaluasiKinerja.perihal);
        tanggal.setText(AmbilEvaluasiKinerja.tanggal);
        if(AmbilEvaluasiKinerja.tepatwaktu.equals("baik"))
            tepatwaktu.setSelection(0);
        else
            tepatwaktu.setSelection(1);

        if(AmbilEvaluasiKinerja.kehadiran.equals("baik"))
            kehadiran.setSelection(0);
        else
            kehadiran.setSelection(1);

        if(AmbilEvaluasiKinerja.kerjasama.equals("baik"))
            kerjasama.setSelection(0);
        else
            kerjasama.setSelection(1);

        if(AmbilEvaluasiKinerja.kualitas.equals("baik"))
            kualitas.setSelection(0);
        else
            kualitas.setSelection(1);

        catatan.setText(AmbilEvaluasiKinerja.catatan);
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
