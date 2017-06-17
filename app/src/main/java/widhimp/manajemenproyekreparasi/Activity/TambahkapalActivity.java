/*package widhimp.manajemenproyekreparasi.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import widhimp.manajemenproyekreparasi.Database.Database;
import widhimp.manajemenproyekreparasi.R;

public class TambahkapalActivity extends AppCompatActivity {
    private EditText name,type,length,height,breadth,draft,dwt,survey,clas;
    private Button tambah;
    private int flag=0;
    private String nama_kapal,type_kapal,height_kapal,draft_kapal,dwt_kapal,length_kapal,survey_kapal,class_kapal,breadth_kapal;
    protected Database database;
    private static final String URL = "http://188.166.240.88:8000/kapal/tambahkapal/";
    public static final String KEY_NAME = "ship_name";
    public static final String KEY_TYPE = "type_of_ship";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_DRAFT ="draft";
    public static final String KEY_DWT="dwt";
    public static final String KEY_LENGTH="length_overall";
    public static final String KEY_SURVEY="type_of_survey";
    public static final String KEY_CLASS="kelas";
    public static final String KEY_BREADTH="breadth";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahkapal);
        name=(EditText) findViewById(R.id.name_tambahkapal);
        type=(EditText) findViewById(R.id.type_tambahkapal);
        length=(EditText) findViewById(R.id.length_tambahkapal);
        height=(EditText) findViewById(R.id.height_tambahkapal);
        breadth=(EditText) findViewById(R.id.breadth_tambahkapal);
        draft=(EditText) findViewById(R.id.draft_tambahkapal);
        dwt=(EditText) findViewById(R.id.dwt_tambahkapal);
        survey=(EditText) findViewById(R.id.survey_tambahkapal);
        clas=(EditText) findViewById(R.id.class_tambahkapal);
        tambah=(Button) findViewById(R.id.tambah_tambahkapal);
        tambah.setOnClickListener(operasi);
        database=new Database(this);
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.tambah_tambahkapal:
                    cekinput();
                    break;
            }
        }
    };

    void cekinput(){
        nama_kapal=name.getText().toString();
        type_kapal=type.getText().toString();
        height_kapal=height.getText().toString();
        draft_kapal=draft.getText().toString();
        dwt_kapal=dwt.getText().toString();
        length_kapal=length.getText().toString();
        survey_kapal=survey.getText().toString();
        class_kapal=clas.getText().toString();
        breadth_kapal=breadth.getText().toString();
        if(nama_kapal.isEmpty()!=true)
            tambahKapal();
        else if(nama_kapal.isEmpty()||type_kapal.isEmpty()||height_kapal.isEmpty()||draft_kapal.isEmpty()||dwt_kapal.isEmpty()||length_kapal.isEmpty()|survey_kapal.isEmpty()||class_kapal.isEmpty()||breadth_kapal.isEmpty()){
            showResponse("Harap menginputkan data dengan lengkap");
        }

    }
    void tambahKapal(){

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
                if(nama_kapal.isEmpty()!=true)
                    params.put(KEY_NAME,nama_kapal);
                if(type_kapal.isEmpty()!=true)
                    params.put(KEY_TYPE,type_kapal);
                if(height_kapal.isEmpty()!=true)
                    params.put(KEY_HEIGHT,height_kapal);
                if(draft_kapal.isEmpty()!=true)
                    params.put(KEY_DRAFT,draft_kapal);
                if(dwt_kapal.isEmpty()!=true)
                    params.put(KEY_DWT,dwt_kapal);
                if(length_kapal.isEmpty()!=true)
                    params.put(KEY_LENGTH,length_kapal);
                if(survey_kapal.isEmpty()!=true)
                    params.put(KEY_SURVEY,survey_kapal);
                if(class_kapal.isEmpty()!=true)
                    params.put(KEY_CLASS,class_kapal);
                if(breadth_kapal.isEmpty()!=true)
                    params.put(KEY_BREADTH,breadth_kapal);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showResponse(String respon){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(respon);
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public void onBackPressed() {
        Intent intentTambah=new Intent(getBaseContext(),ListkapalActivity.class);
        startActivityForResult(intentTambah,0);
    }
}
*/

package widhimp.manajemenproyekreparasi.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
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

import widhimp.manajemenproyekreparasi.Database.Database;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class TambahkapalActivity extends AppCompatActivity {
    private EditText name,type,length,height,breadth,draft,dwt,survey,clas;
    private Button tambah;
    private ProgressDialog loading;
    private String nama_kapal, type_kapal, length_kapal, height_kapal, breadth_kapal, draft_kapal, dwt_kapal, survey_kapal, clas_kapal;
    private String username_user, password_user, kategori_user;
    private static final String URL="http://188.166.240.88:8000/kapal/tambahkapal/";
    protected Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahkapal);
        name=(EditText) findViewById(R.id.name_tambahkapal);
        type=(EditText) findViewById(R.id.type_tambahkapal);
        length=(EditText) findViewById(R.id.length_tambahkapal);
        height=(EditText) findViewById(R.id.height_tambahkapal);
        breadth=(EditText) findViewById(R.id.breadth_tambahkapal);
        draft=(EditText) findViewById(R.id.draft_tambahkapal);
        dwt=(EditText) findViewById(R.id.dwt_tambahkapal);
        survey=(EditText) findViewById(R.id.survey_tambahkapal);
        clas=(EditText) findViewById(R.id.class_tambahkapal);
        tambah=(Button) findViewById(R.id.tambah_tambahkapal);
        tambah.setOnClickListener(operasi);
        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.tambah_tambahkapal:
                    loading = ProgressDialog.show(v.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
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
        Intent intent=new Intent(getBaseContext(),ListkapalActivity.class);
        startActivity(intent);
    }

    void cekinput(){
        nama_kapal=name.getText().toString();
        type_kapal=type.getText().toString();
        length_kapal=length.getText().toString();
        height_kapal=height.getText().toString();
        breadth_kapal=breadth.getText().toString();
        draft_kapal=draft.getText().toString();
        dwt_kapal=dwt.getText().toString();
        survey_kapal=survey.getText().toString();
        clas_kapal=clas.getText().toString();
        if(nama_kapal.isEmpty()||type_kapal.isEmpty()||length_kapal.isEmpty()||height_kapal.isEmpty()||breadth_kapal.isEmpty()
                ||draft_kapal.isEmpty()||dwt_kapal.isEmpty()||survey_kapal.isEmpty()||clas_kapal.isEmpty()){
            showResponse("Harap menginputkan data dengan lengkap.");
        }
        else
            tambahkapal();
    }

    public void tambahkapal(){
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
                params.put("ship_name",nama_kapal);
                params.put("type_of_ship",type_kapal);
                params.put("length_overall",length_kapal);
                params.put("height",height_kapal);
                params.put("breadth",breadth_kapal);
                params.put("draft",draft_kapal);
                params.put("dwt",dwt_kapal);
                params.put("type_of_survey",survey_kapal);
                params.put("class",clas_kapal);
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
                    Intent intent=new Intent(getBaseContext(), ListkapalActivity.class);
                    startActivity(intent);
                }

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
