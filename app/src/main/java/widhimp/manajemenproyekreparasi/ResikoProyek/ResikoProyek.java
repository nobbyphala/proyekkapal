package widhimp.manajemenproyekreparasi.ResikoProyek;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Adapter.QaqcAdapter;
import widhimp.manajemenproyekreparasi.Adapter.ResikoAdapter;
import widhimp.manajemenproyekreparasi.Database.AmbilLamaProyek;
import widhimp.manajemenproyekreparasi.Database.AmbilQaqc;
import widhimp.manajemenproyekreparasi.Database.AmbilResiko;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.MutuProyek.Qaqc;
import widhimp.manajemenproyekreparasi.Object.qaqc;
import widhimp.manajemenproyekreparasi.Object.resiko;
import widhimp.manajemenproyekreparasi.R;

public class ResikoProyek extends AppCompatActivity {
    private String id_kapal, respon;
    private String username_user, password_user, kategori_user;
    private Button simpan;
    private EditText keterangan, dampak, penanganan;
    private String id_resiko, keterangan_resiko, dampak_resiko, penanganan_resiko, status_resiko;
    private Spinner jenis, severity, occurence;
    private String jenis_resiko;
    private TextView tingkatbahaya, statusrisk;
    private ProgressDialog loading;
    private String[] isijenis={"Resiko Operasional","Resiko Finansial","Resiko Strategic", "Hazard Risk"};
    private String[] isiseverity={"1","2","3","4","5"};
    private String[] isioccurence={"1","2","3","4","5"};
    private int severity_int, occurence_int, hasil;
    public final String URL_GET="http://188.166.240.88:8000/kapal/listresikoproyek/";
    public final String URL_POST="http://188.166.240.88:8000/kapal/tambahresikoproyek/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resikoproyek);

        simpan=(Button) findViewById(R.id.simpan_resikoproyek);
        keterangan=(EditText) findViewById(R.id.keterangan_resikoproyek);
        dampak=(EditText) findViewById(R.id.dampak_resikoproyek);
        penanganan=(EditText) findViewById(R.id.penanganan_resikoproyek);
        jenis=(Spinner) findViewById(R.id.jenisresiko_resikoproyek);
        severity=(Spinner) findViewById(R.id.severity_resikoproyek);
        occurence=(Spinner) findViewById(R.id.occurence_resikoproyek);
        tingkatbahaya=(TextView) findViewById(R.id.tingkatbahaya_resikoproyek);
        statusrisk=(TextView) findViewById(R.id.statusrisk_resikoproyek);
        simpan.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, isijenis);
        jenis.setAdapter(adapter);

        jenis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                jenis_resiko=adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //jenis.setSelection(2);

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, isiseverity);
        severity.setAdapter(adapter2);
        severity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                severity_int=Integer.parseInt(adapter2.getItem(i));
                kali();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, isioccurence);
        occurence.setAdapter(adapter3);

        occurence.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                occurence_int=Integer.parseInt(adapter3.getItem(i));
                kali();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("Galangan")){
//            jenis.setEnabled(false);
//            keterangan.setEnabled(false);
//            dampak.setEnabled(false);
//            severity.setEnabled(false);
//            occurence.setEnabled(false);
//            penanganan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("PM")){
//        }
//        else if(kategori_user.equals("OS")){
//            jenis.setEnabled(false);
//            keterangan.setEnabled(false);
//            dampak.setEnabled(false);
//            severity.setEnabled(false);
//            occurence.setEnabled(false);
//            penanganan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            jenis.setEnabled(false);
//            keterangan.setEnabled(false);
//            dampak.setEnabled(false);
//            severity.setEnabled(false);
//            occurence.setEnabled(false);
//            penanganan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            jenis.setEnabled(false);
//            keterangan.setEnabled(false);
//            dampak.setEnabled(false);
//            severity.setEnabled(false);
//            occurence.setEnabled(false);
//            penanganan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Subcont")){
//            jenis.setEnabled(false);
//            keterangan.setEnabled(false);
//            dampak.setEnabled(false);
//            severity.setEnabled(false);
//            occurence.setEnabled(false);
//            penanganan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Class")){
//        }
//        else if(kategori_user.equals("QA/QC")){
//            jenis.setEnabled(false);
//            keterangan.setEnabled(false);
//            dampak.setEnabled(false);
//            severity.setEnabled(false);
//            occurence.setEnabled(false);
//            penanganan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }
//        else if(kategori_user.equals("Owner")){
//        }
//        else if(kategori_user.equals("Vendor")){
//        }
//        else if(kategori_user.equals("PPC")){
//            jenis.setEnabled(false);
//            keterangan.setEnabled(false);
//            dampak.setEnabled(false);
//            severity.setEnabled(false);
//            occurence.setEnabled(false);
//            penanganan.setEnabled(false);
//            simpan.setVisibility(View.INVISIBLE);
//        }

    }

    private void kali(){
        
        hasil=severity_int*occurence_int;
        if(hasil>=1&&hasil<=4){
            statusrisk.setText("rendah");
            statusrisk.setBackgroundColor(getResources().getColor(R.color.hijau));
        }

        else if(hasil>=5&&hasil<=10){
            statusrisk.setText("sedang");
            statusrisk.setBackgroundColor(getResources().getColor(R.color.orange));
        }

        else if(hasil>=11&&hasil<=25) {
            statusrisk.setText("berat");
            statusrisk.setBackgroundColor(getResources().getColor(R.color.merah));
        }
        tingkatbahaya.setText(String.valueOf(hasil));
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.simpan_resikoproyek:
                    if(respon.equals("Gagal"))
                        cekinput();
                    else
                        suntingdata();
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
        Intent intent=new Intent(getBaseContext(),EditkapalActivity.class);
        startActivity(intent);
    }

    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(ResikoProyek.this, response, Toast.LENGTH_SHORT).show();
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
        loading.dismiss();
        AmbilResiko ambilResiko=new AmbilResiko(json);
        ambilResiko.ambilresiko();
        id_resiko=AmbilResiko.id;
        keterangan.setText(AmbilResiko.keterangan);
        dampak.setText(AmbilResiko.dampak);
        tingkatbahaya.setText(AmbilResiko.tingkatbahaya);
        statusrisk.setText(AmbilResiko.statusrisk);
        hasil=Integer.parseInt(AmbilResiko.tingkatbahaya);
        if(hasil>=1&&hasil<=4){
            statusrisk.setBackgroundColor(getResources().getColor(R.color.hijau));
        }

        else if(hasil>=5&&hasil<=10){
            statusrisk.setBackgroundColor(getResources().getColor(R.color.orange));
        }

        else if(hasil>=11&&hasil<=25) {
            statusrisk.setBackgroundColor(getResources().getColor(R.color.merah));
        }

        penanganan.setText(AmbilResiko.penanganan);
        severity_int=Integer.parseInt(AmbilResiko.severity);
        occurence_int=Integer.parseInt(AmbilResiko.occurence);
        if(severity_int==1)
            severity.setSelection(0);
        else if(severity_int==2)
            severity.setSelection(1);
        else if(severity_int==3)
            severity.setSelection(2);
        else if(severity_int==4)
            severity.setSelection(3);
        else if(severity_int==5)
            severity.setSelection(4);

        if(occurence_int==1)
            occurence.setSelection(0);
        else if(occurence_int==2)
            occurence.setSelection(1);
        else if(occurence_int==3)
            occurence.setSelection(2);
        else if(occurence_int==4)
            occurence.setSelection(3);
        else if(occurence_int==5)
            occurence.setSelection(4);

        jenis_resiko=AmbilResiko.jenis;
        if(jenis_resiko.equals("Resiko Operasional"))
            jenis.setSelection(0);
        else if(jenis_resiko.equals("Resiko Finansial"))
            jenis.setSelection(1);
        else if(jenis_resiko.equals("Resiko Strategic"))
            jenis.setSelection(2);
        else if(jenis_resiko.equals("Hazard Risk"))
            jenis.setSelection(3);

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

    public void cekinput(){
        keterangan_resiko=keterangan.getText().toString();
        dampak_resiko=dampak.getText().toString();
        penanganan_resiko=penanganan.getText().toString();
        status_resiko=statusrisk.getText().toString();
        if(keterangan_resiko.isEmpty()||dampak_resiko.isEmpty()||penanganan_resiko.isEmpty())
            showResponse("Harap menginputkan data dengan lengkap");
        else
            insert();
    }
    public void insert(){
        //Toast.makeText(this, keterangan_resiko, Toast.LENGTH_SHORT).show();
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
                params.put("jenis_resiko",jenis_resiko);
                params.put("keterangan", keterangan_resiko);
                params.put("dampak_resiko",dampak_resiko);
                params.put("status_risk",status_resiko);
                params.put("severity",String.valueOf(severity_int));
                params.put("occurence", String.valueOf(occurence_int));
                params.put("tingkat_bahaya",String.valueOf(hasil));
                params.put("penanganan_resiko",penanganan_resiko);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void suntingdata(){
        keterangan_resiko=keterangan.getText().toString();
        dampak_resiko=dampak.getText().toString();
        penanganan_resiko=penanganan.getText().toString();
        status_resiko=statusrisk.getText().toString();
        //Toast.makeText(this, keterangan_resiko, Toast.LENGTH_SHORT).show();
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
                params.put("jenis_resiko",jenis_resiko);
                params.put("keterangan", keterangan_resiko);
                params.put("dampak_resiko",dampak_resiko);
                params.put("status_risk",status_resiko);
                params.put("severity",String.valueOf(severity_int));
                params.put("occurence", String.valueOf(occurence_int));
                params.put("tingkat_bahaya",String.valueOf(hasil));
                params.put("penanganan_resiko",penanganan_resiko);
                params.put("id_resiko",id_resiko);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void openUlang() {
        Intent intentTambah = new Intent(getBaseContext(),ResikoProyek.class);
        startActivityForResult(intentTambah, 0);
    }
}
