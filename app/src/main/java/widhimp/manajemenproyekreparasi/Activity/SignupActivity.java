package widhimp.manajemenproyekreparasi.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import widhimp.manajemenproyekreparasi.R;

public class SignupActivity extends AppCompatActivity {
    private EditText nama, company, direksi, email, city, state, country, telephone, phone, username, password, retypepassword;
    private Button signup;
    private ProgressDialog loading;
    private static final String URL="http://188.166.240.88:8000/kapal/registeruser/";
    private String nama_user, company_user, direksi_user, email_user, city_user, state_user, country_user, telephone_user, phone_user, username_user, password_user, retypepassword_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        nama=(EditText) findViewById(R.id.nama_signup);
        company=(EditText) findViewById(R.id.company_signup);
        direksi=(EditText) findViewById(R.id.direksi_signup);
        email=(EditText) findViewById(R.id.email_signup);
        city=(EditText) findViewById(R.id.city_signup);
        state=(EditText) findViewById(R.id.state_signup);
        country=(EditText) findViewById(R.id.country_signup);
        telephone=(EditText) findViewById(R.id.telephone_signup);
        phone=(EditText) findViewById(R.id.phone_signup);
        username=(EditText) findViewById(R.id.username_signup);
        password=(EditText) findViewById(R.id.password_signup);
        retypepassword=(EditText) findViewById(R.id.retype_signup);
        signup=(Button) findViewById(R.id.signup_signup);
        signup.setOnClickListener(operasi);
    }

    View.OnClickListener operasi= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.signup_signup:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    cekinput();
                    break;
            }
        }
    };

    public void cekinput(){
        company_user=company.getText().toString();
        direksi_user=direksi.getText().toString();
        email_user=email.getText().toString();
        city_user=city.getText().toString();
        state_user=state.getText().toString();
        country_user=country.getText().toString();
        telephone_user=telephone.getText().toString();
        phone_user=phone.getText().toString();
        username_user=username.getText().toString();
        password_user=password.getText().toString();
        nama_user=nama.getText().toString();
        retypepassword_user=retypepassword.getText().toString();
        if(company_user.isEmpty()||direksi_user.isEmpty()||email_user.isEmpty()
                ||city_user.isEmpty()||state_user.isEmpty()||country_user.isEmpty()||telephone_user.isEmpty()
                ||phone_user.isEmpty()||username_user.isEmpty()||password_user.isEmpty()||retypepassword_user.isEmpty())
            showResponse("Harap menginputkan data dengan lengkap.");
        else
            if(password_user.equals(retypepassword_user)){
                signup();
            }

            else
                showResponse("Password dan Retype Password tidak sesuai.");

    }

    public void signup(){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
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
                    params.put("company",company_user);
                    params.put("direksi",direksi_user);
                    params.put("email",email_user);
                    params.put("city",city_user);
                    params.put("state",state_user);
                    params.put("coutry",country_user);
                    params.put("telephone",telephone_user);
                    params.put("handphone",phone_user);
                    params.put("nama",nama_user);
                    params.put("username",username_user);
                    params.put("password",password_user);
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
                    Intent intent=new Intent(getBaseContext(), SigninActivity.class);
                    startActivity(intent);
                }

            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
