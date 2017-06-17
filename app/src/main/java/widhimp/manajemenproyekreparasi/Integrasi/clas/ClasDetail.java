package widhimp.manajemenproyekreparasi.Integrasi.clas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import widhimp.manajemenproyekreparasi.R;

public class ClasDetail extends AppCompatActivity {
    private Button bki, lr, bv, abs, dnv, nk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clas_detail);

        bki=(Button) findViewById(R.id.bki_clas_detail);
        lr=(Button) findViewById(R.id.lr_clas_detail);
        bv=(Button) findViewById(R.id.bv_clas_detail);
        abs=(Button) findViewById(R.id.abs_clas_detail);
        dnv=(Button) findViewById(R.id.dnv_clas_detail);
        nk=(Button) findViewById(R.id.nk_clas_detail);

        bki.setOnClickListener(clickHandler);
        lr.setOnClickListener(clickHandler);
        bv.setOnClickListener(clickHandler);
        abs.setOnClickListener(clickHandler);
        dnv.setOnClickListener(clickHandler);
        nk.setOnClickListener(clickHandler);
    }

    View.OnClickListener clickHandler=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bki_clas_detail:
                    break;
                case R.id.lr_clas_detail:
                    break;
                case R.id.bv_clas_detail:
                    break;
                case R.id.abs_clas_detail:
                    break;
                case R.id.dnv_clas_detail:
                    break;
                case R.id.nk_clas_detail:
                    break;
            }
        }
    };
}
