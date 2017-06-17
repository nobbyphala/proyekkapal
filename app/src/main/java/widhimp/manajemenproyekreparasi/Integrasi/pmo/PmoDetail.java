package widhimp.manajemenproyekreparasi.Integrasi.pmo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import widhimp.manajemenproyekreparasi.R;

public class PmoDetail extends AppCompatActivity {
    private Button tentang, tugas, fungsi, contact;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.integrasi_detail);

        tentang=(Button) findViewById(R.id.button1_integrasidetail);
        tugas=(Button) findViewById(R.id.button2_integrasidetail);
        fungsi=(Button) findViewById(R.id.button3_integrasidetail);
        contact=(Button) findViewById(R.id.button4_integrasidetail);

        tentang.setText("TENTANG");
        tugas.setText("TUGAS");
        fungsi.setText("FUNGSI");
        contact.setText("CONTACT");

        tentang.setOnClickListener(clickhandler);
        tugas.setOnClickListener(clickhandler);
        fungsi.setOnClickListener(clickhandler);
        contact.setOnClickListener(clickhandler);
    }

    View.OnClickListener clickhandler=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button1_integrasidetail:
                    intent=new Intent(view.getContext(), PmoTentang.class);
                    startActivity(intent);
                    break;
                case R.id.button2_integrasidetail:
                    intent=new Intent(view.getContext(), PmoTugas.class);
                    startActivity(intent);
                    break;
                case R.id.button3_integrasidetail:
                    intent=new Intent(view.getContext(), PmoFungsi.class);
                    startActivity(intent);
                    break;
                case R.id.button4_integrasidetail:
                    break;
            }
        }
    };
}
