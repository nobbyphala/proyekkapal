package widhimp.manajemenproyekreparasi.Integrasi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import widhimp.manajemenproyekreparasi.Integrasi.pmo.PmoDetail;
import widhimp.manajemenproyekreparasi.R;

public class Integrasi extends AppCompatActivity {
    private Button pmo, clas, qaqc, harkan, pemasaran, produksi, logistik, safety, pengadaan, subkontraktor;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.integrasi);

        pmo=(Button) findViewById(R.id.pmo_integrasi);
        clas=(Button) findViewById(R.id.class_integrasi);
        qaqc=(Button) findViewById(R.id.qaqc_integrasi);
        harkan=(Button) findViewById(R.id.harkan_integrasi);
        pemasaran=(Button) findViewById(R.id.pemasaran_integrasi);
        produksi=(Button) findViewById(R.id.produksi_integrasi);
        logistik=(Button) findViewById(R.id.logistik_integrasi);
        safety=(Button) findViewById(R.id.safety_integrasi);
        pengadaan=(Button) findViewById(R.id.pengadaan_integrasi);
        subkontraktor=(Button) findViewById(R.id.subkontraktor_integrasi);

        pmo.setOnClickListener(clickhandler);
        clas.setOnClickListener(clickhandler);
        qaqc.setOnClickListener(clickhandler);
        harkan.setOnClickListener(clickhandler);
        pemasaran.setOnClickListener(clickhandler);
        produksi.setOnClickListener(clickhandler);
        logistik.setOnClickListener(clickhandler);
        safety.setOnClickListener(clickhandler);
        pengadaan.setOnClickListener(clickhandler);
        subkontraktor.setOnClickListener(clickhandler);
    }

    View.OnClickListener clickhandler=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.pmo_integrasi:
                    intent=new Intent(view.getContext(), PmoDetail.class);
                    startActivity(intent);
                    break;
                case R.id.class_integrasi:
                    break;
                case R.id.qaqc_integrasi:
                    break;
                case R.id.harkan_integrasi:
                    break;
                case R.id.pemasaran_integrasi:
                    break;
                case R.id.produksi_integrasi:
                    break;
                case R.id.logistik_integrasi:
                    break;
                case R.id.safety_integrasi:
                    break;
                case R.id.pengadaan_integrasi:
                    break;
                case R.id.subkontraktor_integrasi:
                    break;
            }
        }
    };
}
