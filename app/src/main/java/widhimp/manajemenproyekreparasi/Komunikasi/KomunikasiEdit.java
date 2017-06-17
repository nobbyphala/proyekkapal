package widhimp.manajemenproyekreparasi.Komunikasi;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Activity.ListkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Adapter.KomunikasiAdapter;
import widhimp.manajemenproyekreparasi.Database.AmbilKapal;
import widhimp.manajemenproyekreparasi.Database.AmbilKomunikasi;
import widhimp.manajemenproyekreparasi.Database.AmbilKomunikasiEdit;
import widhimp.manajemenproyekreparasi.Database.AmbilResiko;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Object.dokumen;
import widhimp.manajemenproyekreparasi.R;

import static java.lang.Boolean.TRUE;

public class KomunikasiEdit extends AppCompatActivity {
    private TextView namadokumen;
    private Button pilihfile, simpan;
    private EditText nama, catatan, dari, kepada, perihal, tanggal;
    private Bitmap bitmap;
    private Uri uri;
    private ImageView imageview;
    private String username_user, password_user, kategori_user;
    private final String URL_GET="http://188.166.240.88:8000/kapal/detailkomunikasi/";
    private final String URL_POST="http://188.166.240.88:8000/kapal/komunikasi/";
    private String catatan_komunikasi, id_komunikasi, nama_komunikasi, dari_komunikasi, kepada_komunikasi, perihal_komunikasi, tanggal_komunikasi;
    private String id_kapal;
    private String strfoto;
    private ProgressDialog loading;
    private boolean zoomOut=false;
    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.komunikasi_edit);
        pilihfile = (Button) findViewById(R.id.pilihfile_komunikasi);
        simpan = (Button) findViewById(R.id.simpan_komunikasi);

        nama=(EditText) findViewById(R.id.nama_komunikasi);
        catatan=(EditText) findViewById(R.id.catatan_komunikasi);
        dari = (EditText) findViewById(R.id.dari_komunikasi);
        kepada = (EditText) findViewById(R.id.kepada_komunikasi);
        perihal = (EditText) findViewById(R.id.perihal_komunikasi);
        tanggal = (EditText) findViewById(R.id.tanggal_komunikasi);
        imageview = (ImageView) findViewById(R.id.gambar_komunikasiedit);

        Bundle bundle = getIntent().getExtras();
        //nama_komunikasi = bundle.getString("namadokumen");
        id_komunikasi=bundle.getString("idkomunikasi");

        pilihfile.setOnClickListener(operasi);
        simpan.setOnClickListener(operasi);
        imageview.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        ambildata();
        loading = ProgressDialog.show(this,"Mengambil data...","Harap tunggu...",false,false);
//        if(kategori_user.equals("Galangan")){
//            simpan.setVisibility(View.VISIBLE);
//            pilihfile.setVisibility(View.VISIBLE);
//        }

    }

    private void keluar(String respon){
        //loading.dismiss();
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(respon);
        alertDialogBuilder.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(getBaseContext(),KomunikasiActivity.class);
                startActivity(intent);
            }
        });
        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
        Intent intent=new Intent(getBaseContext(),KomunikasiActivity.class);
        startActivity(intent);
    }

    View.OnClickListener operasi = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.pilihfile_komunikasi:
                    picture_choice();
                    break;
                case R.id.simpan_komunikasi:
                    loading = ProgressDialog.show(view.getContext(),"Menyimpan data...","Harap tunggu...",false,false);
                    cekinput();
                    break;
                case R.id.gambar_komunikasiedit:
                    openGambar();
//                    if(zoomOut) {
//                        //Toast.makeText(getApplicationContext(), "NORMAL SIZE!", Toast.LENGTH_LONG).show();
//                        imageview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                        imageview.setAdjustViewBounds(true);
//                        zoomOut =false;
//                    }else{
//                        //Toast.makeText(getApplicationContext(), "FULLSCREEN!", Toast.LENGTH_LONG).show();
//                        imageview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                        imageview.setScaleType(ImageView.ScaleType.FIT_XY);
//                        zoomOut = true;
//                    }
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
    public void cekinput(){
        nama_komunikasi=nama.getText().toString();
        dari_komunikasi=dari.getText().toString();
        kepada_komunikasi=kepada.getText().toString();
        perihal_komunikasi=perihal.getText().toString();
        tanggal_komunikasi=tanggal.getText().toString();
        catatan_komunikasi=catatan.getText().toString();
        if(catatan_komunikasi.isEmpty()||dari_komunikasi.isEmpty()||kepada_komunikasi.isEmpty()||perihal_komunikasi.isEmpty()||tanggal_komunikasi.isEmpty())
            showResponse("Harap menginputkan data dengan lengkap.");
        else {
            simpankomunikasi();
        }
    }
    public void ambildata(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
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
                params.put("id_komunikasi",id_komunikasi);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showdata(String json){
        loading.dismiss();
        AmbilKomunikasiEdit ambilKomunikasiEdit=new AmbilKomunikasiEdit(json);
        ambilKomunikasiEdit.AmbilKomunikasiEdit();
        kepada_komunikasi=AmbilKomunikasiEdit.kepada;
        if(kategori_user.equals(kepada_komunikasi)||kategori_user.equals("Galangan")){

            if(kategori_user.equals(kepada_komunikasi)){
                dari.setEnabled(false);
                simpan.setVisibility(View.INVISIBLE);
                pilihfile.setVisibility(View.INVISIBLE);
                kepada.setEnabled(false);
                nama.setEnabled(false);
                perihal.setEnabled(false);
                tanggal.setEnabled(false);
                catatan.setEnabled(false);

            }

            dari.setText(AmbilKomunikasiEdit.dari);
            kepada.setText(AmbilKomunikasiEdit.kepada);
            perihal.setText(AmbilKomunikasiEdit.perihal);
            tanggal.setText(AmbilKomunikasiEdit.tanggal);
            nama.setText(AmbilKomunikasiEdit.nama);
            catatan.setText(AmbilKomunikasiEdit.catatan);
            String strimage=AmbilKomunikasiEdit.file;
            byte[] decodedString = Base64.decode(strimage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageview.setImageBitmap(bitmap);
        }

        else{
            keluar("Anda bukan pihak yang bersangkutan");
        }
    }

    private void showResponse(String respon){
        loading.dismiss();
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


    public void simpankomunikasi(){
        if(bitmap==null){
            bitmap=imageview.getDrawingCache();
        }

        strfoto=getStringImage(bitmap);
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
                params.put("username", username_user);
                params.put("password", password_user);
                params.put("id_kapal", id_kapal);
                params.put("nama_komunikasi", nama_komunikasi);
                params.put("perihal",perihal_komunikasi);
                params.put("gambar",strfoto);
                params.put("dari",dari_komunikasi);
                params.put("kepada",kepada_komunikasi);
                params.put("tanggal",tanggal_komunikasi);
                params.put("catatan",catatan_komunikasi);
                params.put("id_komunikasi", id_komunikasi);
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

    private void picture_choice() {
        CharSequence menu[] = new CharSequence[]{"From camera", "From gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Take picture from");
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
        //potret.putExtra(MediaStore.EXTRA_SIZE_LIMIT,10000);
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
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        //Setting the Bitmap to ImageView
                        imageview.setImageBitmap(bitmap);
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
                        imageview.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                break;
        }
    }

    public void openUlang() {
        Intent intentTambah = new Intent(getBaseContext(), KomunikasiActivity.class);
        startActivityForResult(intentTambah, 0);
    }
}
