    package widhimp.manajemenproyekreparasi.MutuProyek;

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

    public class MutuProyek extends AppCompatActivity {
    private Button qaqc, checklist, rekap;
        private String username_user, password_user, kategori_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutuproyek);
        qaqc=(Button) findViewById(R.id.qaqc_mutuproyek);
        checklist=(Button) findViewById(R.id.checklist_mutuproyek);
        rekap=(Button) findViewById(R.id.rekapchecklist_mutuproyek);

        rekap.setOnClickListener(operasi);
        qaqc.setOnClickListener(operasi);
        checklist.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

//        if(kategori_user.equals("Galangan")){
//            checklist.setEnabled(false);
//        }
//        else if(kategori_user.equals("PM")){
//            checklist.setEnabled(false);
//        }
//        else if(kategori_user.equals("OS")){
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            checklist.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            checklist.setEnabled(false);
//        }
//        else if(kategori_user.equals("Subcont")){
//            checklist.setEnabled(false);
//        }
//        else if(kategori_user.equals("Class")){
//        }
//        else if(kategori_user.equals("QA/QC")){
//        }
//        else if(kategori_user.equals("Owner")){
//            checklist.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//            checklist.setEnabled(false);
//        }
//        else if(kategori_user.equals("PPC")){
//            checklist.setEnabled(false);
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

        View.OnClickListener operasi= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.qaqc_mutuproyek:
                        Intent intent=new Intent(getBaseContext(), Qaqc.class);
                        startActivity(intent);
                        break;
                    case R.id.checklist_mutuproyek:
                        Intent intent2=new Intent(getBaseContext(), CheckList.class);
                        startActivity(intent2);
                        break;
                    case R.id.rekapchecklist_mutuproyek:
                        Intent intent3=new Intent(getBaseContext(), RekapCheckList.class);
                        startActivity(intent3);
                        break;
                }
            }
        };
}
