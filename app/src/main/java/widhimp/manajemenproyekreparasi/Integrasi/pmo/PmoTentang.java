package widhimp.manajemenproyekreparasi.Integrasi.pmo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import widhimp.manajemenproyekreparasi.R;

public class PmoTentang extends AppCompatActivity {
    private TextView teks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.integrasi_keterangan);

        teks=(TextView) findViewById(R.id.teks_integrasi_keterangan);

        teks.setText("Project Manager Officer (PMO) adalah seorang profesional yang dapat mendukung " +
                "proyek dan program secara independen, bertindak sebagai manajer dari manajer proyek. " +
                "\n\nKegiatan PMO dipusatkan di seputar perencanaan dan pengendalian sumber daya dan pekerjaan, " +
                "pengelolaan anggaran, manajemen perubahan dan pengendalian kualitas produk. " +
                "\n\nSelain itu, seorang PMO dapat memimpin tim kecil dalam mengontrol, menjadwalkan dan " +
                "menetapkan pekerjaan, menetapkan tujuan, memberikan umpan balik mengenai kinerja dan motivasi." +
                "\n\nSingkatnya, PMO berada di jantung proyek yang membantu manajer proyek untuk mengelola " +
                "dan beroperasi di dalam area pengelolaan proyek yang berbeda dan mengelola orang untuk " +
                "memberikan layanan terjadwal dan sesuai permintaan dalam waktu, cakupan dan anggaran " +
                "yang disepakati bersama klien.");
    }
}
