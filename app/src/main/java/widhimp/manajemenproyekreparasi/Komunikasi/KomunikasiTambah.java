package widhimp.manajemenproyekreparasi.Komunikasi;

import android.app.ProgressDialog;
import android.content.ContentValues;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Adapter.ProgresAdapter;
import widhimp.manajemenproyekreparasi.Database.Database;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class KomunikasiTambah extends AppCompatActivity {
    private TextView namadokumen;
    private Button pilihfile, tambah;
    private EditText catatan, nama, dari, perihal, tanggal;
    private Bitmap bitmap;
    private Uri uri;
    private ImageView imageview;
    private String id_kapal, username_user, password_user, kategori_user;
    private final String URL_POST = "http://188.166.240.88:8000/kapal/komunikasi/";
    private String catatan_komunikasi, nama_komunikasi, dari_komunikasi, kepada_komunikasi, perihal_komunikasi, tanggal_komunikasi;
    private String gambar;
    private boolean zoomOut = false;
    private int PICK_IMAGE_REQUEST = 1;
    private ProgressDialog loading;
    private String strfoto;
    private Spinner kepada;
    private String[] isikepada={"Galangan", "PM", "OS", "Dept. Pengadaan", "Dept. Produksi", "Subcont", "Class", "QA/QC", "Owner", "Vendor", "PPC"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.komunikasi_tambah);
        tambah = (Button) findViewById(R.id.tambah_komunikasi_tambah);
        nama = (EditText) findViewById(R.id.nama_komunikasi_tambah);
        dari = (EditText) findViewById(R.id.dari_komunikasi_tambah);
        kepada = (Spinner) findViewById(R.id.kepada_komunikasi_tambah);
        perihal = (EditText) findViewById(R.id.perihal_komunikasi_tambah);
        tanggal = (EditText) findViewById(R.id.tanggal_komunikasi_tambah);
        catatan=(EditText) findViewById(R.id.catatan_komunikasi_tambah);
        imageview = (ImageView) findViewById(R.id.gambar_komunikasi_tambah);
        pilihfile=(Button) findViewById(R.id.pilihfile_komunikasi_tambah);

        pilihfile.setOnClickListener(operasi);
        tambah.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user = preferences.getString("password", null);
        kategori_user = preferences.getString("kategori", null);

        SharedPreferences preferences2 = getSharedPreferences("kapal", getApplicationContext().MODE_PRIVATE);
        id_kapal=preferences2.getString("idkapal",null);

        dari.setText(kategori_user);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_spinner_item, isikepada);
        kepada.setAdapter(adapter);

        kepada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                kepada_komunikasi=adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    View.OnClickListener operasi = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.pilihfile_komunikasi_tambah:
                    picture_choice();
                    break;
                case R.id.tambah_komunikasi_tambah:
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
        Intent intent=new Intent(getBaseContext(),KomunikasiActivity.class);
        startActivity(intent);
    }

    public void cekinput(){
        nama_komunikasi=nama.getText().toString();
        dari_komunikasi=dari.getText().toString();
        perihal_komunikasi=perihal.getText().toString();
        tanggal_komunikasi=tanggal.getText().toString();
        catatan_komunikasi=catatan.getText().toString();
        if(catatan_komunikasi.isEmpty()||dari_komunikasi.isEmpty()||kepada_komunikasi.isEmpty()||perihal_komunikasi.isEmpty()||tanggal_komunikasi.isEmpty())
            showResponse("Harap menginputkan data dengan lengkap.");
        else {
            tambahkomunikasi();
        }
    }

    public void tambahkomunikasi(){

        strfoto=getStringImage(bitmap);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(KomunikasiTambah.this, response, Toast.LENGTH_SHORT).show();
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
        //potret.putExtra(MediaStore.EXTRA_SIZE_LIMIT,50000);
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
//                        bitmap.compress(Bitmap.CompressFormat.JPEG,30,out);
//                        byte[] byteArray = out.toByteArray();
//                        Bitmap updatedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
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
