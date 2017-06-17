package widhimp.manajemenproyekreparasi.SdmProyek;

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
import widhimp.manajemenproyekreparasi.WaktuProyek.WaktuProyek;

public class SdmProyek extends AppCompatActivity {
    private Button galangan, subcontractor, permintaan, listpermintaan, evaluasikinerja;
    private String username_user, password_user, kategori_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdmproyek);
        galangan=(Button) findViewById(R.id.galangan_sdm);
        subcontractor=(Button) findViewById(R.id.subcontractor_sdm);
        permintaan=(Button) findViewById(R.id.permintaan_sdm);
        listpermintaan=(Button) findViewById(R.id.listpermintaan_sdm);
        evaluasikinerja=(Button) findViewById(R.id.evaluasikinerja_sdm);
        galangan.setOnClickListener(operasi);
        subcontractor.setOnClickListener(operasi);
        permintaan.setOnClickListener(operasi);
        evaluasikinerja.setOnClickListener(operasi);
        listpermintaan.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

//        if(kategori_user.equals("Galangan")){
//            permintaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("PM")){
//            listpermintaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("OS")){
//            permintaan.setEnabled(false);
//            listpermintaan.setEnabled(false);
//            evaluasikinerja.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            permintaan.setEnabled(false);
//            evaluasikinerja.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            permintaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Subcont")){
//            permintaan.setEnabled(false);
//            listpermintaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Class")){
//            permintaan.setEnabled(false);
//            listpermintaan.setEnabled(false);
//            evaluasikinerja.setEnabled(false);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            permintaan.setEnabled(false);
//            listpermintaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Owner")){
//            permintaan.setEnabled(false);
//            listpermintaan.setEnabled(false);
//            evaluasikinerja.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//        }
//        else if(kategori_user.equals("PPC")){
//            permintaan.setEnabled(false);
//            listpermintaan.setEnabled(false);
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
        Intent intent=new Intent(getBaseContext(),EditkapalActivity.class);
        startActivity(intent);
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.galangan_sdm:
                    Intent intent=new Intent(getBaseContext(), SdmGalangan.class);
                    startActivity(intent);
                    break;
                case R.id.subcontractor_sdm:
                    Intent intent2= new Intent(getBaseContext(), SdmSubcontractor.class);
                    startActivity(intent2);
                    break;
                case R.id.permintaan_sdm:
                    Intent intent3= new Intent(getBaseContext(), SdmPermintaan.class);
                    startActivity(intent3);
                    break;
                case R.id.listpermintaan_sdm:
                    Intent intent5= new Intent(getBaseContext(), ListPermintaanSdm.class);
                    startActivity(intent5);
                    break;
                case R.id.evaluasikinerja_sdm:
                    Intent intent4= new Intent(getBaseContext(), EvaluasiKinerja.class);
                    startActivity(intent4);
                    break;
            }
        }
    };
}
