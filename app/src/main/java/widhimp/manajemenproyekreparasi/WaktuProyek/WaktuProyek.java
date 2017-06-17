package widhimp.manajemenproyekreparasi.WaktuProyek;

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
import widhimp.manajemenproyekreparasi.RepairList.RepairlistActivity;

public class WaktuProyek extends AppCompatActivity {
    private Button lama, penjadwalan, progres, estimasi, rekap;
    private String respon, username_user, password_user, kategori_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waktuproyek);
        lama=(Button) findViewById(R.id.lama_waktuproyek);
        penjadwalan=(Button) findViewById(R.id.penjadwalan_waktuproyek);
        progres=(Button) findViewById(R.id.progres_waktuproyek);
        estimasi=(Button) findViewById(R.id.estimasi_waktuproyek);
        rekap=(Button) findViewById(R.id.rekapprogres_waktuproyek);

        lama.setOnClickListener(operasi);
        penjadwalan.setOnClickListener(operasi);
        progres.setOnClickListener(operasi);
        estimasi.setOnClickListener(operasi);
        rekap.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

//        if(kategori_user.equals("Galangan")){
//            progres.setEnabled(false);
//        }
//        else if(kategori_user.equals("PM")){
//            progres.setEnabled(false);
//        }
//        else if(kategori_user.equals("OS")){
//            progres.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            progres.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            progres.setEnabled(false);
//        }
//        else if(kategori_user.equals("Subcont")){
//        }
//        else if(kategori_user.equals("Class")){
//            progres.setEnabled(false);
//            estimasi.setEnabled(false);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            progres.setEnabled(false);
//        }
//        else if(kategori_user.equals("Owner")){
//            progres.setEnabled(false);
//            estimasi.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//            progres.setEnabled(false);
//            estimasi.setEnabled(false);
//            rekap.setEnabled(false);
//        }
//        else if(kategori_user.equals("PPC")){
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener(){
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.lama_waktuproyek:openLama();break;
                case R.id.penjadwalan_waktuproyek:openPenjadwalan();break;
                case R.id.progres_waktuproyek:openProgres();break;
                case R.id.estimasi_waktuproyek:openEstimasi();break;
                case R.id.rekapprogres_waktuproyek:openRekap();break;
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

    public void openLama(){
        Intent intent=new Intent(getBaseContext(), LamaProyek.class);
        startActivityForResult(intent,0);
    }
    public void openPenjadwalan(){
        Intent intent=new Intent(getBaseContext(), PenjadwalanProyek.class);
        startActivityForResult(intent,0);
    }
    public void openProgres(){
        Intent intent=new Intent(getBaseContext(), ProgresProyek.class);
        startActivity(intent);

    }
    public void openEstimasi(){
        Intent intent=new Intent(getBaseContext(), EstimasiSdm.class);
        startActivity(intent);
    }
    public void openRekap(){
        Intent intent=new Intent(getBaseContext(), RekapProgresProyek.class);
        startActivity(intent);
    }

}
