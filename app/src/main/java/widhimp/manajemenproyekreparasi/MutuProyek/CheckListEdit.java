package widhimp.manajemenproyekreparasi.MutuProyek;

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

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Adapter.CheckListEditAdapter;
import widhimp.manajemenproyekreparasi.Adapter.SubcontractorAreaPekerjaanAdapter;
import widhimp.manajemenproyekreparasi.Database.AmbilCheckListEdit;
import widhimp.manajemenproyekreparasi.Database.AmbilSubcontractorAreaPekerjaan;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Object.checklistedit;
import widhimp.manajemenproyekreparasi.Object.subcontractorareapekerjaan;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmSubcontractorAreapekerjaan;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmSubcontractorDetail;

public class CheckListEdit extends AppCompatActivity {
    private Button tambah;
    private String username_user, password_user, kategori_user;
    private TextView namapt;
    private String nama_checklist, id_checklist, id_kapal;
    private String namachecklist, statuschecklist, komenchecklist;
    public final String URL_GET="http://188.166.240.88:8000/kapal/listchecklist/";
    public final String URL_POST="http://188.166.240.88:8000/kapal/tambahchecklist/";
    private ProgressDialog loading;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutuproyek_checklist_edit);
        tambah=(Button) findViewById(R.id.tambah_checklist_edit);
        namapt=(TextView) findViewById(R.id.nama_checklist_edit);

        bundle = getIntent().getExtras();
        nama_checklist = bundle.getString("nama");
        id_checklist=bundle.getString("id");

        namapt.setText("Check List "+nama_checklist + " :");

        tambah.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("admin")){
//            tambah.setVisibility(View.VISIBLE);
//        }
//        else if(kategori_user.equals("user")){
//            tambah.setVisibility(View.INVISIBLE);
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch (view.getId()){
                case R.id.tambah_checklist_edit:
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
        Intent intent=new Intent(getBaseContext(),CheckList.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(getBaseContext(), response, Toast.LENGTH_SHORT).show();
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
                params.put("id_repairdetail",id_checklist);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilCheckListEdit ambilCheckListEdit=new AmbilCheckListEdit(json);
        ArrayList<checklistedit> list=new ArrayList<checklistedit>();
        ListView listView=(ListView) findViewById(R.id.list_checklist_edit);
        CheckListEditAdapter checkListEditAdapter=new CheckListEditAdapter(this,0,list);
        ambilCheckListEdit.ambilchecklistedit(checkListEditAdapter);
        listView.setAdapter(checkListEditAdapter);
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
        View input = li.inflate(R.layout.mutuproyek_checklist_edit_tambah,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Button tambahareapekerjaan=(Button)input.findViewById(R.id.tambah_checklist_edit_tambah);
        final EditText etNama=(EditText)input.findViewById(R.id.nama_checklist_edit_tambah);
        final EditText etStatus=(EditText)input.findViewById(R.id.status_checklist_edit_tambah);
        etStatus.setEnabled(false);
        final EditText etKomen=(EditText)input.findViewById(R.id.komentar_checklist_edit_tambah);
        tambahareapekerjaan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                namachecklist=etNama.getText().toString();
                statuschecklist=etStatus.getText().toString();
                komenchecklist=etKomen.getText().toString();
                loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
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
                params.put("id_repairdetail",id_checklist);
                params.put("nama",namachecklist);
                params.put("status","0");
                params.put("komentar",komenchecklist);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void openUlang() {
        Intent intentTambah = new Intent(getBaseContext(), CheckListEdit.class);
        intentTambah.putExtras(bundle);
        startActivityForResult(intentTambah, 0);
    }
}
