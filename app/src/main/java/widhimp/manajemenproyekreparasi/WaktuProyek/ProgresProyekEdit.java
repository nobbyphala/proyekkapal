package widhimp.manajemenproyekreparasi.WaktuProyek;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Database.AmbilKomunikasiEdit;
import widhimp.manajemenproyekreparasi.Database.AmbilProgresDetail;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Komunikasi.KomunikasiActivity;
import widhimp.manajemenproyekreparasi.R;

public class ProgresProyekEdit extends AppCompatActivity {
    private Uri uri;
    private Bitmap bitmap;
    private EditText sebelum, saatini, catatan;
    private Button ambilgambar, simpan, sunting;
    private ImageView gambar;
    private String id_repairdetail, username_user, password_user, kategori_user, respon;
    private final String URL_GET="http://188.166.240.88:8000/kapal/listprogress/";
    private final String URL_TAMBAH="http://188.166.240.88:8000/kapal/tambahprogress/";
    private String id_progres, sebelum_progres, saatini_progres, catatan_progres, strfoto;
    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waktuproyek_progres_edit);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("nama");
        id_repairdetail=bundle.getString("id");

        TextView nama=(TextView) findViewById(R.id.nama_editprogres);
        nama.setText(name + " :");



        sebelum=(EditText) findViewById(R.id.sebelum_progres);
        saatini=(EditText) findViewById(R.id.saatini_progres);
        catatan=(EditText) findViewById(R.id.catatan_progres);
        ambilgambar=(Button) findViewById(R.id.ambilgambar_progres);
        simpan=(Button) findViewById(R.id.simpan_progres);
        sunting=(Button) findViewById(R.id.sunting_progres);
        gambar=(ImageView) findViewById(R.id.gambar_progres);

        gambar.setOnClickListener(operasi);
        simpan.setOnClickListener(operasi);
        ambilgambar.setOnClickListener(operasi);
        sunting.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        bitmap=gambar.getDrawingCache();

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);

//        if(kategori_user.equals("admin")){
//            simpan.setVisibility(View.INVISIBLE);
//            sebelum.setEnabled(false);
//            saatini.setEnabled(false);
//            catatan.setEnabled(false);
//            ambilgambar.setVisibility(View.INVISIBLE);
//        }
//
//        else if(kategori_user.equals("user")){
//            simpan.setVisibility(View.VISIBLE);
//        }
//
//        if(kategori_user.equals("Galangan")){
//        }
//        else if(kategori_user.equals("PM")){
//        }
//        else if(kategori_user.equals("OS")){
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//        }
//        else if(kategori_user.equals("Subcont")){
//        }
//        else if(kategori_user.equals("Class")){
//        }
//        else if(kategori_user.equals("QA/QC")){
//        }
//        else if(kategori_user.equals("Owner")){
//        }
//        else if(kategori_user.equals("Vendor")){
//        }
//        else if(kategori_user.equals("PPC")){
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.simpan_progres:
                    tambahdata();
                    break;
                case R.id.ambilgambar_progres:
                    picture_choice();
                    break;
                case R.id.sunting_progres:
                    suntingdata();
                    break;
                case R.id.gambar_progres:
                    openGambar();
                    break;
            }
        }
    };

    public void openGambar(){
        LayoutInflater li = LayoutInflater.from(this);
        View input = li.inflate(R.layout.gambarfullscreen,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        ImageView gambar=(ImageView) input.findViewById(R.id.gambar_fullscreen);
        gambar.setImageBitmap(bitmap);
        gambar.setScaleType(ImageView.ScaleType.FIT_XY);
        dialog.setView(input);
        dialog.setCancelable(true);
        dialog.show();
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
        Intent intent=new Intent(getBaseContext(),ProgresProyek.class);
        startActivity(intent);
    }

    private void suntingdata(){
            sebelum_progres=sebelum.getText().toString();
            saatini_progres=saatini.getText().toString();
            catatan_progres=catatan.getText().toString();
            strfoto=getStringImage(bitmap);
            loading = ProgressDialog.show(this,"Mengirim data...","Harap tunggu...",false,false);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TAMBAH,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            showResponse(response);
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
                    params.put("id_repairdetail",id_repairdetail);
                    params.put("id_progress",id_progres);
                    params.put("kondisi_sebelum",sebelum_progres);
                    params.put("kondisi_saat_ini",saatini_progres);
                    params.put("catatan",catatan_progres);
                    params.put("gambar",strfoto);
                    return params;
                }

            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
    }

    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET,
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
                params.put("id_repairdetail",id_repairdetail);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilProgresDetail ambilProgresDetail=new AmbilProgresDetail(json);
        ambilProgresDetail.ambilprogresdetail();
        id_progres=AmbilProgresDetail.id;
        if(kategori_user.equals("admin")){
            sebelum.setText(AmbilProgresDetail.sebelum);
            saatini.setText(AmbilProgresDetail.saatini);
            catatan.setText(AmbilProgresDetail.catatan);
            String strimage=AmbilProgresDetail.gambar;
            byte[] decodedString = Base64.decode(strimage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            gambar.setImageBitmap(bitmap);
        }
        else{
            sebelum.setText(AmbilProgresDetail.saatini);
            saatini.setText(AmbilProgresDetail.saatini);
            catatan.setText(AmbilProgresDetail.catatan);
           String strimage=AmbilProgresDetail.gambar;
           byte[] decodedString = Base64.decode(strimage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            gambar.setImageBitmap(bitmap);
        }
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

    private void picture_choice() {
        CharSequence menu[] = new CharSequence[]{"dari camera", "dari gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ambil gambar");
        builder.setItems(menu, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        take_camera();
                        break;
                    case 1:
                        take_gallery();
                        break;
                }
            }
        });
        builder.show();
    }

    public void take_camera() {
        Intent potret = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagesFolder = new File(Environment.getExternalStorageDirectory(), "abc");
        imagesFolder.mkdirs();
        File image = new File(imagesFolder, "a" + ".jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        uri = uriSavedImage;
        potret.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        //potret.putExtra(MediaStore.EXTRA_SIZE_LIMIT,5000);
        startActivityForResult(potret, 0);
    }

    public void take_gallery() {
        Intent ambil_foto = new Intent(Intent.ACTION_GET_CONTENT, null);
        ambil_foto.setType("image/*");
        startActivityForResult(ambil_foto, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    //Toast.makeText(getBaseContext(), "masuk ok", Toast.LENGTH_SHORT).show();
                    Uri selectedImage = uri;
                    try {
                        //Getting the Bitmap from Gallery
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
//                        ByteArrayOutputStream out = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.PNG,50,out);
//                        byte[] byteArray = out.toByteArray();
//                        Bitmap updatedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
//                        //Setting the Bitmap to ImageView
                        gambar.setImageBitmap(bitmap);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    //Toast.makeText(getBaseContext(), "masuk 1", Toast.LENGTH_SHORT).show();
                    Uri selectedImage = data.getData();
                    try {
                        //Getting the Bitmap from Gallery
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        //Setting the Bitmap to ImageView
                        gambar.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void tambahdata(){
        sebelum_progres=sebelum.getText().toString();
        saatini_progres=saatini.getText().toString();
        catatan_progres=catatan.getText().toString();
        if(bitmap==null){
            bitmap=gambar.getDrawingCache();
        }
        strfoto=getStringImage(bitmap);
        loading = ProgressDialog.show(this,"Mengirim data...","Harap tunggu...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_TAMBAH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showResponse(response);
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
                params.put("id_repairdetail",id_repairdetail);
                params.put("kondisi_sebelum",sebelum_progres);
                params.put("kondisi_saat_ini",saatini_progres);
                params.put("catatan",catatan_progres);
                params.put("gambar",strfoto);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 30, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void openUlang() {
        loading.dismiss();
        Intent intent = new Intent(getBaseContext(), ProgresProyek.class);
        startActivity(intent);
    }
}