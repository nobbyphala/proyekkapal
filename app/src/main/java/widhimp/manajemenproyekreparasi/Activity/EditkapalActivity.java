package widhimp.manajemenproyekreparasi.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import widhimp.manajemenproyekreparasi.BiayaProyek.BiayaProyek;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.Integrasi.Integrasi;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.Komunikasi.KomunikasiActivity;
import widhimp.manajemenproyekreparasi.Komunikasi.KomunikasiEdit;
import widhimp.manajemenproyekreparasi.MutuProyek.CheckList;
import widhimp.manajemenproyekreparasi.MutuProyek.MutuProyek;
import widhimp.manajemenproyekreparasi.PengadaanMaterial.PengadaanMaterial;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.RepairList.RepairListMenu;
import widhimp.manajemenproyekreparasi.RepairList.RepairlistActivity;
import widhimp.manajemenproyekreparasi.ResikoProyek.ResikoProyek;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmProyek;
import widhimp.manajemenproyekreparasi.StakeholderProyek.Stakeholder;
import widhimp.manajemenproyekreparasi.WaktuProyek.WaktuProyek;

public class EditkapalActivity extends AppCompatActivity {
    private Button komunikasi,ruanglingkup,waktuproyek,sdmproyek,
            biayaproyek,mutuproyek,resikoproyek,pengadaanmaterial,stakeholder, integrasi;
    private ImageView search;
    private EditText nama_cari;
    private Button cari;
    private String username_user, password_user, kategori_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editkapal);
        integrasi=(Button) findViewById(R.id.integrasi_sow);
        komunikasi =(Button) findViewById(R.id.komunikasi_sow);
        ruanglingkup=(Button) findViewById(R.id.ruanglingkup_sow);
        waktuproyek=(Button) findViewById(R.id.waktuproyek_sow);
        sdmproyek=(Button) findViewById(R.id.sdmproyek_sow);
        biayaproyek=(Button) findViewById(R.id.biayaproyek_sow);
        mutuproyek=(Button) findViewById(R.id.mutuproyek_sow);
        resikoproyek=(Button) findViewById(R.id.resikoproyek_sow);
        pengadaanmaterial=(Button) findViewById(R.id.pengadaan_sow);
        stakeholder=(Button) findViewById(R.id.stakeholder_sow);
        komunikasi.setOnClickListener(operasi);
        ruanglingkup.setOnClickListener(operasi);
        waktuproyek.setOnClickListener(operasi);
        sdmproyek.setOnClickListener(operasi);
        biayaproyek.setOnClickListener(operasi);
        mutuproyek.setOnClickListener(operasi);
        resikoproyek.setOnClickListener(operasi);
        pengadaanmaterial.setOnClickListener(operasi);
        stakeholder.setOnClickListener(operasi);
        integrasi.setOnClickListener(operasi);
        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

//        if(kategori_user.equals("Galangan")){
//        }
//        else if(kategori_user.equals("PM")){
//        }
//        else if(kategori_user.equals("OS")){
//            biayaproyek.setEnabled(false);
//            pengadaanmaterial.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//        }
//        else if(kategori_user.equals("Subcont")){
//        }
//        else if(kategori_user.equals("Class")){
//            biayaproyek.setEnabled(false);
//            resikoproyek.setEnabled(false);
//            pengadaanmaterial.setEnabled(false);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            biayaproyek.setEnabled(false);
//        }
//        else if(kategori_user.equals("Owner")){
//            resikoproyek.setEnabled(false);
//            pengadaanmaterial.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//            sdmproyek.setEnabled(false);
//            resikoproyek.setEnabled(false);
//        }
//        else if(kategori_user.equals("PPC")){
//        }
    }

    View.OnClickListener operasi= new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.integrasi_sow:openIntegrasi();break;
                case R.id.komunikasi_sow:openKomunikasi();break;
                case R.id.ruanglingkup_sow:openRepair();break;
                case R.id.waktuproyek_sow:openWaktu();break;
                case R.id.sdmproyek_sow:openSdm();break;
                case R.id.biayaproyek_sow:openBiaya();break;
                case R.id.mutuproyek_sow:openMutu();break;
                case R.id.resikoproyek_sow:openResiko();break;
                case R.id.pengadaan_sow:openPengadaan();break;
                case R.id.stakeholder_sow:openStakeholder();break;

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
        Intent intent=new Intent(getBaseContext(),ListkapalActivity.class);
        startActivity(intent);
    }

    void openIntegrasi() {
        Intent intentIntegrasi=new Intent(getBaseContext(), Integrasi.class);
        startActivityForResult(intentIntegrasi,0);
    }

    void openKomunikasi() {
        Intent intentDokumen=new Intent(getBaseContext(), KomunikasiActivity.class);
        startActivityForResult(intentDokumen,0);
    }
    void openRepair() {
        Intent intent=new Intent(getBaseContext(), RepairListMenu.class);
        startActivityForResult(intent,0);
    }
    void openWaktu() {
        Intent intent=new Intent(getBaseContext(), WaktuProyek.class);
        startActivityForResult(intent,0);
    }
    void openSdm() {
        Intent intent=new Intent(getBaseContext(), SdmProyek.class);
        startActivity(intent);
    }
    void openBiaya() {
        Intent intent=new Intent(getBaseContext(), BiayaProyek.class);
        startActivity(intent);
    }
    void openMutu() {
        Intent intent=new Intent(getBaseContext(), MutuProyek.class);
        startActivity(intent);
    }
    void openResiko() {
        Intent intent=new Intent(getBaseContext(), ResikoProyek.class);
        startActivity(intent);
    }
    void openPengadaan() {
        Intent intent=new Intent(getBaseContext(), PengadaanMaterial.class);
        startActivity(intent);
    }
    void openStakeholder() {
        Intent intent=new Intent(getBaseContext(), Stakeholder.class);
        startActivity(intent);
    }
}
