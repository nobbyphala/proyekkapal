package widhimp.manajemenproyekreparasi.Activity;

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

import widhimp.manajemenproyekreparasi.Adapter.KapalAdapter;
import widhimp.manajemenproyekreparasi.Database.AmbilKapal;
import widhimp.manajemenproyekreparasi.Database.AmbilKapalDetail;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Object.kapal;
import widhimp.manajemenproyekreparasi.R;

public class ShipIdentity extends AppCompatActivity {
    private String id_kapal;
    private TextView name, type, length, depth, breadth, draft, dwt, survey, clas;
    public ProgressDialog loading;
    public final String URL="http://188.166.240.88:8000/kapal/kapaldetail/";
    private String username_user, password_user;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ship_identity);
        name=(TextView) findViewById(R.id.name_shipidentity);
        type=(TextView) findViewById(R.id.type_shipidentity);
        length=(TextView) findViewById(R.id.length_shipidentity);
        depth=(TextView) findViewById(R.id.depth_shipidentity);
        breadth=(TextView) findViewById(R.id.breadth_shipidentity);
        draft=(TextView) findViewById(R.id.draft_shipidentity);
        dwt=(TextView) findViewById(R.id.dwt_shipidentity);
        survey=(TextView) findViewById(R.id.survey_shipidentity);
        clas=(TextView) findViewById(R.id.class_shipidentity);
        next=(Button) findViewById(R.id.next_shipidentity);
        next.setOnClickListener(operasi);
        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);
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
        Intent intent=new Intent(getBaseContext(),ListkapalActivity.class);
        startActivity(intent);
    }

    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
        AmbilKapalDetail ambilKapalDetail = new AmbilKapalDetail(json);
        ambilKapalDetail.ambilkapaldetail();
        SharedPreferences preferences = getSharedPreferences("kapal",getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("namakapal", AmbilKapalDetail.nama);
        editor.commit();
        name.setText(AmbilKapalDetail.nama);
        type.setText(AmbilKapalDetail.type);
        length.setText(AmbilKapalDetail.length);
        depth.setText(AmbilKapalDetail.depth);
        breadth.setText(AmbilKapalDetail.breadth);
        draft.setText(AmbilKapalDetail.draft);
        dwt.setText(AmbilKapalDetail.dwt);
        survey.setText(AmbilKapalDetail.survey);
        clas.setText(AmbilKapalDetail.clas);
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
    View.OnClickListener operasi= new View.OnClickListener(){
      @Override
        public void onClick(View view){
          switch (view.getId()){
              case R.id.next_shipidentity:
                  Intent intent= new Intent(getBaseContext(), EditkapalActivity.class);
                  startActivity(intent);
          }
      }
    };
}
