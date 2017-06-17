package widhimp.manajemenproyekreparasi.RepairList;

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

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;

public class RepairListMenu extends AppCompatActivity {
    private Button listambahpekerjaan, repairlist, tambahpekerjaan, surveykapal;
    private String username_user, password_user, kategori_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repairlist_menu);
        repairlist=(Button)findViewById(R.id.repairlist_repairlist);
        tambahpekerjaan=(Button)findViewById(R.id.tambahpekerjaan_repairlist);
        listambahpekerjaan=(Button) findViewById(R.id.listtambahpekerjaan_repairlist);
        surveykapal=(Button) findViewById(R.id.surveykapal_repairlist);

        listambahpekerjaan.setOnClickListener(operasi);
        repairlist.setOnClickListener(operasi);
        tambahpekerjaan.setOnClickListener(operasi);
        surveykapal.setOnClickListener(operasi);

        SharedPreferences preferences = getSharedPreferences("session", getApplicationContext().MODE_PRIVATE);
        username_user = preferences.getString("username", null);
        password_user=preferences.getString("password",null);
        kategori_user=preferences.getString("kategori",null);

//        if(kategori_user.equals("Galangan")){
//            tambahpekerjaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("PM")){
//            listambahpekerjaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("OS")){
//            tambahpekerjaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Pengadaan")){
//            tambahpekerjaan.setEnabled(false);
//            surveykapal.setEnabled(false);
//        }
//        else if(kategori_user.equals("Dept. Produksi")){
//            tambahpekerjaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Subcont")){
//            listambahpekerjaan.setEnabled(false);
//            surveykapal.setEnabled(false);
//        }
//        else if(kategori_user.equals("Class")){
//            listambahpekerjaan.setEnabled(false);
//            surveykapal.setEnabled(false);
//            tambahpekerjaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("QA/QC")){
//            tambahpekerjaan.setEnabled(false);
//            surveykapal.setEnabled(false);
//        }
//        else if(kategori_user.equals("Owner")){
//            tambahpekerjaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("Vendor")){
//            listambahpekerjaan.setEnabled(false);
//            surveykapal.setEnabled(false);
//            tambahpekerjaan.setEnabled(false);
//        }
//        else if(kategori_user.equals("PPC")){
//            listambahpekerjaan.setEnabled(false);
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
        public void onClick(View view){
            switch(view.getId()){
                case R.id.repairlist_repairlist:
                    openRepair();
                    break;
                case R.id.tambahpekerjaan_repairlist:
                    openTambah();
                    break;
                case R.id.listtambahpekerjaan_repairlist:
                    openList();
                    break;
                case R.id.surveykapal_repairlist:
                    openSurvey();
                    break;
            }
        }
    };
    public void openRepair(){
        Intent intent=new Intent(getBaseContext(),RepairlistActivity.class);
        startActivity(intent);
    }
    public void openTambah(){
        Intent intent=new Intent(getBaseContext(),TambahPekerjaan.class);
        startActivity(intent);
    }
    public void openList(){
        Intent intent=new Intent(getBaseContext(),ListTambahPekerjaan.class);
        startActivity(intent);
    }
    public void openSurvey(){
        LayoutInflater li = LayoutInflater.from(this);
        View input = li.inflate(R.layout.repairlist_surveykapal_menu,null);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        Button pm=(Button) input.findViewById(R.id.pm_repairlist_surverykapal_menu);
        Button os=(Button) input.findViewById(R.id.os_repairlist_surverykapal_menu);
        os.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                open("OS");
            }
        });
        pm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                open("PM");
            }
        });
        dialog.setView(input);
        dialog.setCancelable(true);
        dialog.show();
    }
    public void open(String nama){
        Bundle bundle = new Bundle();
        bundle.putString("stakeholder", nama);
        Intent intent=new Intent(getBaseContext(),SurveyKapal.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
