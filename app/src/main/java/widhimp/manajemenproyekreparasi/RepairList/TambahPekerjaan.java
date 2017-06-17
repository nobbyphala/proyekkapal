package widhimp.manajemenproyekreparasi.RepairList;

import android.app.AlertDialog;
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

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.ListkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class TambahPekerjaan extends AppCompatActivity {
    private EditText dari, kepada, perihal, catatan;
    private Button kirim;
    private ProgressDialog loading;
    private String username_user, password_user, kategori_user, id_kapal;
    public final String URL="http://188.166.240.88:8000/kapal/tambahpekerjaan/";
    private String dari_pekerjaan, kepada_pekerjaan, perihal_pekerjaan, catatan_pekerjaan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repairlist_tambahpekerjaan);
        dari=(EditText) findViewById(R.id.dari_tambahpekerjaan);
        kepada=(EditText) findViewById(R.id.kepada_tambahpekerjaan);
        perihal=(EditText) findViewById(R.id.perihal_tambahpekerjaan);
        catatan=(EditText) findViewById(R.id.catatan_tambahpekerjaan);
        kirim=(Button) findViewById(R.id.kirim_tambahpekerjaan);
        kirim.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

//        if(kategori_user.equals("admin")){
//            kirim.setVisibility(View.INVISIBLE);
//            dari.setEnabled(false);
//            kepada.setEnabled(false);
//            perihal.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//
//        else if(kategori_user.equals("user")){
//            kirim.setVisibility(View.VISIBLE);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.kirim_tambahpekerjaan:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
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
        Intent intent=new Intent(getBaseContext(),RepairListMenu.class);
        startActivity(intent);
    }

    void cekinput(){
        dari_pekerjaan=dari.getText().toString();
        kepada_pekerjaan=kepada.getText().toString();
        perihal_pekerjaan=perihal.getText().toString();
        catatan_pekerjaan=catatan.getText().toString();
        if(dari_pekerjaan.isEmpty()||kepada_pekerjaan.isEmpty()||perihal_pekerjaan.isEmpty()||catatan_pekerjaan.isEmpty())
            showResponse("Harap menginputkan data dengan lengkap.");
        else
            tambahpekerjaan();
    }

    public void tambahpekerjaan(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
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
                params.put("password",password_user);
                params.put("dari",dari_pekerjaan);
                params.put("kepada",kepada_pekerjaan);
                params.put("perihal",perihal_pekerjaan);
                params.put("catatan",catatan_pekerjaan);
                params.put("id_kapal",id_kapal);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showResponse(final String respon){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(respon);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(respon.toString().equals("Berhasil")){
                    Intent intent=new Intent(getBaseContext(), RepairListMenu.class);
                    startActivity(intent);
                }

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
