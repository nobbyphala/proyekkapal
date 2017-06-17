package widhimp.manajemenproyekreparasi.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import widhimp.manajemenproyekreparasi.Integrasi.UploadFille;
import widhimp.manajemenproyekreparasi.R;

public class SigninActivity extends AppCompatActivity {
    private TextView signup;
    private EditText username,password;
    private Button signin;
    private String username_user, password_user, kategori_user;
        private static final String URL="http://188.166.240.88:8000/kapal/login/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);
        signup=(TextView) findViewById(R.id.daftar_signin);
        username=(EditText) findViewById(R.id.username_signin);
        password=(EditText) findViewById(R.id.password_signin);
        signin=(Button) findViewById(R.id.signin_signin);
        signup.setOnClickListener(operasi);
        signin.setOnClickListener(operasi);
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.signin_signin:
                    //Intent intent=new Intent(getBaseContext(), UploadFille.class);
                    //startActivity(intent);
                    cekinput();
                    break;
                case R.id.daftar_signin:
                    openSignup();
                    break;
            }
        }
    };

    public void cekinput(){
        username_user=username.getText().toString();
        password_user=password.getText().toString();
        if(username_user.isEmpty()||password_user.isEmpty())
            showResponse("Harap menginputkan username dan password.");
        else {
            ceklogin();
        }
    }

    public void ceklogin(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(SigninActivity.this, response, Toast.LENGTH_SHORT).show();
//                        if(response.equals("1")){
//                            loginadmin();
//                        }
//                        else if(response.equals("2")){
//                            loginuser();
//                        }
//                        else
                        if(response.equals("0"))
                            showResponse("Username tidak terdaftar");
                        else if(response.equals("-2"))
                            showResponse("Password salah");
                        else
                            login(response);
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

    void login(String kategori){
        kategori_user=kategori;
        SharedPreferences preferences = getSharedPreferences("session",getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username_user);
        editor.putString("password",password_user);
        editor.putString("kategori",kategori_user);
        editor.commit();
        Intent intentLogin=new Intent(getBaseContext(),ListkapalActivity.class);
        startActivity(intentLogin);
    }

    void loginadmin(){
        kategori_user="admin";
        SharedPreferences preferences = getSharedPreferences("session",getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username_user);
        editor.putString("password",password_user);
        editor.putString("kategori",kategori_user);
        editor.commit();
        Intent intentLogin=new Intent(getBaseContext(),ListkapalActivity.class);
        startActivity(intentLogin);
    }

    void loginuser(){
        kategori_user="user";
        SharedPreferences preferences = getSharedPreferences("session",getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", username_user);
        editor.putString("password",password_user);
        editor.putString("kategori",kategori_user);
        editor.commit();
        Intent intentLogin=new Intent(getBaseContext(),ListkapalActivity.class);
        startActivity(intentLogin);
    }

    void openSignup(){
        Intent intentSignup=new Intent(getBaseContext(),SignupActivity.class);
        startActivityForResult(intentSignup,0);
    }
    @Override
    public void onBackPressed() {

    }
}
