package widhimp.manajemenproyekreparasi.RepairList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Database.AmbilLamaProyek;
import widhimp.manajemenproyekreparasi.Database.AmbilSurveyKapal;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.Integrasi.FilePath;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.WaktuProyek.WaktuProyek;

public class SurveyKapal extends AppCompatActivity {
    private EditText tanggal, perihal, anggotasurvey, catatan;
    private TextView approved, uri;
    private String id_survey, tanggal_survey, perihal_survey, anggotasurvey_survey, catatan_survey;
    private Button pilihfile, simpan;
    private String stakeholder;
    private String username_user, password_user, kategori_user, id_kapal;
    private ProgressDialog loading;
    public final String URL_TAMBAH="http://188.166.240.88:8000/kapal/tambahsurvey/";
    public final String URL_GET="http://188.166.240.88:8000/kapal/listsurvey/";
    public final String URL_DOWNLOAD="http://188.166.240.88:8000/kapal/filesurvey/";
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = SurveyKapal.class.getSimpleName();
    private String selectedFilePath;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repairlist_surveykapal);

        tanggal=(EditText) findViewById(R.id.tanggal_repairlist_surveykapal);
        perihal=(EditText) findViewById(R.id.perihal_repairlist_surveykapal);
        anggotasurvey=(EditText) findViewById(R.id.anggotasurvey_repairlist_surveykapal);
        catatan=(EditText) findViewById(R.id.catatan_repairlist_surveykapal);
        approved=(TextView) findViewById(R.id.approved_repairlist_surveykapal);
        uri=(TextView) findViewById(R.id.url_repairlist_surveykapal); 
        pilihfile=(Button) findViewById(R.id.pilihfile_repairlist_surveykapal);
        simpan=(Button) findViewById(R.id.simpan_repairlist_surveykapal);

        tanggal_survey=tanggal.getText().toString();
        perihal_survey=perihal.getText().toString();
        anggotasurvey_survey=anggotasurvey.getText().toString();
        catatan_survey=catatan.getText().toString();

        pilihfile.setOnClickListener(operasi);
        simpan.setOnClickListener(operasi);
        
        Bundle bundle = getIntent().getExtras();
        stakeholder= bundle.getString("stakeholder");

        approved.setText(stakeholder);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("Galangan")){
//            simpan.setEnabled(false);
//            pilihfile.setEnabled(false);
//            tanggal.setEnabled(false);
//            perihal.setEnabled(false);
//            anggotasurvey.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("PM")){
//
//        }
//        else if(kategori_user.equals("OS")){
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            simpan.setEnabled(false);
//            pilihfile.setEnabled(false);
//            tanggal.setEnabled(false);
//            perihal.setEnabled(false);
//            anggotasurvey.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Subcont")){
//        }
//        else if(kategori_user.equals("Class")){
//        }
//        else if(kategori_user.equals("QA/QC")){
//        }
//        else if(kategori_user.equals("Owner")){
//            simpan.setEnabled(false);
//            pilihfile.setEnabled(false);
//            tanggal.setEnabled(false);
//            perihal.setEnabled(false);
//            anggotasurvey.setEnabled(false);
//            catatan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//        }
//        else if(kategori_user.equals("PPC")){
//            simpan.setEnabled(false);
//            pilihfile.setEnabled(false);
//            tanggal.setEnabled(false);
//            perihal.setEnabled(false);
//            anggotasurvey.setEnabled(false);
//            catatan.setEnabled(false);
//        }

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
        Intent intent=new Intent(getBaseContext(),RepairListMenu.class);
        startActivity(intent);
    }

    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(SurveyKapal.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("approved_by",stakeholder);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        AmbilSurveyKapal ambilSurveyKapal=new AmbilSurveyKapal(json);
        ambilSurveyKapal.ambilsurveykapal();
        id_survey=AmbilSurveyKapal.id;
        tanggal.setText(AmbilSurveyKapal.tanggal);
        anggotasurvey.setText(AmbilSurveyKapal.anggota);
        perihal.setText(AmbilSurveyKapal.perihal);
        catatan.setText(AmbilSurveyKapal.catatan);
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

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==pilihfile){
                showFileChooser();
            }
            if(v==simpan){

                //on upload button Click
                if(selectedFilePath != null){
                    dialog = ProgressDialog.show(SurveyKapal.this,"","Mengirim Data...",true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //creating new thread to handle Http Operations
                            simpan(selectedFilePath);
                        }
                    }).start();
                }else{
                    Toast.makeText(SurveyKapal.this,"Please choose a File First",Toast.LENGTH_SHORT).show();
                }

            }
        }
    };

    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == PICK_FILE_REQUEST){
                if(data == null){
                    //no data present
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this,selectedFileUri);
                Log.i(TAG,"Selected File Path:" + selectedFilePath);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    uri.setText(selectedFilePath);
                }else{
                    Toast.makeText(this,"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //android upload file to server
    public int simpan(final String selectedFilePath){

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length-1];

        if (!selectedFile.isFile()){
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    uri.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        }else{
            try{
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(URL_TAMBAH);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                connection.setRequestProperty("username",username_user);
                connection.setRequestProperty("password",password_user);
                connection.setRequestProperty("id_kapal",id_kapal);
                connection.setRequestProperty("tanggal",tanggal_survey);
                connection.setRequestProperty("perihal",perihal_survey);
                connection.setRequestProperty("anggota",anggotasurvey_survey);
                connection.setRequestProperty("catatan",catatan_survey);
                connection.setRequestProperty("approved_by",stakeholder);
                connection.setRequestProperty("berkas",selectedFilePath);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"username\""+lineEnd+lineEnd);
                dataOutputStream.writeBytes(username_user+lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"password\""+lineEnd+lineEnd);
                dataOutputStream.writeBytes(password_user+lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"id_kapal\""+lineEnd+lineEnd);
                dataOutputStream.writeBytes(id_kapal+lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"tanggal\""+lineEnd+lineEnd);
                dataOutputStream.writeBytes(tanggal_survey+lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"perihal\""+lineEnd+lineEnd);
                dataOutputStream.writeBytes(perihal_survey+lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"anggota\""+lineEnd+lineEnd);
                dataOutputStream.writeBytes(anggotasurvey_survey+lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"catatan\""+lineEnd+lineEnd);
                dataOutputStream.writeBytes(catatan_survey+lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"approved_by\""+lineEnd+lineEnd);
                dataOutputStream.writeBytes(stakeholder+lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"berkas\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);
                dataOutputStream.writeBytes(lineEnd);

                dataOutputStream.toString();
                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0){
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if(serverResponseCode == 200){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showResponse("Berhasil");
                        }
                    });
                }

                //closing the input and output streams 
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SurveyKapal.this,"File Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(SurveyKapal.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(SurveyKapal.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
            return serverResponseCode;
        }

    }
}
