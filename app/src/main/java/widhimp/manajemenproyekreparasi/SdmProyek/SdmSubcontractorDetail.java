package widhimp.manajemenproyekreparasi.SdmProyek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import widhimp.manajemenproyekreparasi.Activity.SigninActivity;
import widhimp.manajemenproyekreparasi.Inbox;
import widhimp.manajemenproyekreparasi.KirimPesan;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.WaktuProyek.WaktuProyek;

public class SdmSubcontractorDetail extends AppCompatActivity {
    private Button koordinator, anggota, areapakerjaan;
    private TextView namasubcontractor;
    private String id_subcontractor, name, namakoordinator, jabatan, kontak;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdmproyek_subcontractor_detail);
        namasubcontractor=(TextView)findViewById(R.id.nama_subcontractor_detail);
        koordinator=(Button)findViewById(R.id.koordinator_subcontractor);
        anggota=(Button) findViewById(R.id.anggota_subcontractor);
        areapakerjaan=(Button) findViewById(R.id.areapekerjaan_subcontractor);
        koordinator.setOnClickListener(operasi);
        anggota.setOnClickListener(operasi);
        areapakerjaan.setOnClickListener(operasi);

        bundle = getIntent().getExtras();
        name = bundle.getString("nama");
        id_subcontractor=bundle.getString("id");
        namakoordinator=bundle.getString("koordinator");
        jabatan=bundle.getString("jabatan");
        kontak=bundle.getString("kontak");

        namasubcontractor.setText(name + " :");
    }

    View.OnClickListener operasi=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.koordinator_subcontractor:
                    Intent intent= new Intent(getBaseContext(), SdmSubcontractorKoordinator.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                case R.id.anggota_subcontractor:
                    Intent intent2= new Intent(getBaseContext(), SdmSubcontractorAnggota.class);
                    intent2.putExtras(bundle);
                    startActivity(intent2);
                    break;
                case R.id.areapekerjaan_subcontractor:
                    Intent intent3= new Intent(getBaseContext(), SdmSubcontractorAreapekerjaan.class);
                    intent3.putExtras(bundle);
                    startActivity(intent3);
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
        Intent intent=new Intent(getBaseContext(),SdmSubcontractor.class);
        startActivity(intent);
    }
}
