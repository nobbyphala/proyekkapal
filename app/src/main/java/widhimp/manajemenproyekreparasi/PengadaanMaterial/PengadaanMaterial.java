package widhimp.manajemenproyekreparasi.PengadaanMaterial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class PengadaanMaterial extends AppCompatActivity {
    private Button tersedia, permintaan;
    private String username_user, password_user, kategori_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengadaanmaterial);
        tersedia=(Button) findViewById(R.id.materialtersedia_pengadaanmaterial);
        permintaan=(Button) findViewById(R.id.permintaan_pengadaanmaterial);
        tersedia.setOnClickListener(operasi);
        permintaan.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

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
//            permintaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Owner")){
//        }
//        else if(kategori_user.equals("Vendor")){
//        }
//        else if(kategori_user.equals("PPC")){
//        }

    }

    View.OnClickListener operasi= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.materialtersedia_pengadaanmaterial:
                    Intent intent=new Intent(getBaseContext(), MaterialTersedia.class);
                    startActivity(intent);
                    break;
                case R.id.permintaan_pengadaanmaterial:
                    Intent intent2=new Intent(getBaseContext(),PermintaanMaterial.class);
                    startActivity(intent2);
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
        Intent intent=new Intent(getBaseContext(),EditkapalActivity.class);
        startActivity(intent);
    }
}
