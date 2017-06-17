package widhimp.manajemenproyekreparasi.BiayaProyek;

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
import widhimp.manajemenproyekreparasi.SdmProyek.SdmSubcontractorDetail;

public class BiayaProyek extends AppCompatActivity {
    private Button budget, termin, pembayaranjasa, pembayaranmaterial;
    private String username_user, password_user, kategori_user,id_kapal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biayaproyek);
        budget=(Button) findViewById(R.id.budget_biayaproyek);
        termin=(Button) findViewById(R.id.termin_biayaproyek);
        pembayaranjasa=(Button) findViewById(R.id.pembayaranjasa_biayaproyek);
        pembayaranmaterial=(Button) findViewById(R.id.pembayaranmaterial_biayaproyek);
        budget.setOnClickListener(operasi);
        termin.setOnClickListener(operasi);
        pembayaranjasa.setOnClickListener(operasi);
        pembayaranmaterial.setOnClickListener(operasi);

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
//            budget.setEnabled(false);
//            pembayaranmaterial.setEnabled(false);
//            termin.setEnabled(false);
//        }
//        else if(kategori_user.equals("Class")){
//        }
//        else if(kategori_user.equals("QA/QC")){
//        }
//        else if(kategori_user.equals("Owner")){
//            pembayaranjasa.setEnabled(false);
//            pembayaranmaterial.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//            budget.setEnabled(false);
//            pembayaranjasa.setEnabled(false);
//            termin.setEnabled(false);
//        }
//        else if(kategori_user.equals("PPC")){
//        }
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.budget_biayaproyek:
                    Intent intent=new Intent(getBaseContext(),BudgetProduksi.class);
                    startActivity(intent);
                    break;
                case R.id.termin_biayaproyek:
                    Intent intent2=new Intent(getBaseContext(),TerminPembayaran.class);
                    startActivity(intent2);
                    break;
                case R.id.pembayaranjasa_biayaproyek:
                    Intent intent3=new Intent(getBaseContext(),PembayaranJasa.class);
                    startActivity(intent3);
                    break;
                case R.id.pembayaranmaterial_biayaproyek:
                    Intent intent4=new Intent(getBaseContext(),PembayaranMaterial.class);
                    startActivity(intent4);
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
