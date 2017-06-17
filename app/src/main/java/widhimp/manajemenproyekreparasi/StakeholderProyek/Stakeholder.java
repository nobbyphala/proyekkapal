package widhimp.manajemenproyekreparasi.StakeholderProyek;

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

public class Stakeholder extends AppCompatActivity {
    private String username_user, password_user, kategori_user;
    private String id_kapal;
    private Button owner, konsultan, projectmanager, galangan, clas, abk, subcont, vendor, ppc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stakeholder);

        owner=(Button) findViewById(R.id.owner_stakeholder);
        konsultan=(Button) findViewById(R.id.konsultan_stakeholder);
        projectmanager=(Button) findViewById(R.id.projectmanager_stakeholder);
        galangan=(Button) findViewById(R.id.galangan_stakeholder);
        clas=(Button) findViewById(R.id.class_stakeholder);
        abk=(Button) findViewById(R.id.abk_stakeholder);
        subcont=(Button) findViewById(R.id.subcont_stakeholder);
        vendor=(Button) findViewById(R.id.vendor_stakeholder);
        ppc=(Button) findViewById(R.id.ppc_stakeholder);

        owner.setOnClickListener(operasi);
        konsultan.setOnClickListener(operasi);
        projectmanager.setOnClickListener(operasi);
        galangan.setOnClickListener(operasi);
        clas.setOnClickListener(operasi);
        abk.setOnClickListener(operasi);
        subcont.setOnClickListener(operasi);
        vendor.setOnClickListener(operasi);
        ppc.setOnClickListener(operasi);
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.owner_stakeholder:openIntent("Owner");break;
                case R.id.konsultan_stakeholder:openIntent("Konsultan");break;
                case R.id.projectmanager_stakeholder:openIntent("Project Manager");break;
                case R.id.galangan_stakeholder:openIntent("Galangan");break;
                case R.id.class_stakeholder:openIntent("Class");break;
                case R.id.abk_stakeholder:openIntent("Abk");break;
                case R.id.subcont_stakeholder:openIntent("Subcont");break;
                case R.id.vendor_stakeholder:openIntent("Vendor");break;
                case R.id.ppc_stakeholder:openIntent("PPC");break;
            }
        }
    };

    private void openIntent(String kategori){
        Intent intent;
        SharedPreferences preferences = getSharedPreferences("session",getApplicationContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if(kategori.equals("Owner")){
            editor.putString("id_stakeholder", "2");
            editor.putString("nama_stakeholder","Owner");
        }
        else if(kategori.equals("Konsultan")){
            editor.putString("id_stakeholder", "3");
            editor.putString("nama_stakeholder","OS");
        }
        else if(kategori.equals("Project Manager")){
            editor.putString("id_stakeholder", "4");
            editor.putString("nama_stakeholder","PM");
        }
        else if(kategori.equals("Galangan")){
            editor.putString("id_stakeholder", "1");
            editor.putString("nama_stakeholder","Galangan");
        }
        else if(kategori.equals("Class")){
            editor.putString("id_stakeholder", "5");
            editor.putString("nama_stakeholder","Class");
        }
        else if(kategori.equals("Abk")){
            editor.putString("id_stakeholder", "6");
            editor.putString("nama_stakeholder","ABK");
        }
        else if(kategori.equals("Subcont")){
            editor.putString("id_stakeholder", "7");
            editor.putString("nama_stakeholder","Subcont");
        }
        else if(kategori.equals("Vendor")){
            editor.putString("id_stakeholder", "8");
            editor.putString("nama_stakeholder","Vendor");
        }
        else if(kategori.equals("PPC")){
            editor.putString("id_stakeholder", "9");
            editor.putString("nama_stakeholder","PPC");
        }
        editor.commit();
        intent=new Intent(getBaseContext(), StakeholderDetail.class);
        startActivity(intent);
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
}
