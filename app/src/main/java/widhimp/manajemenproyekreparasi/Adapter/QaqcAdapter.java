package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.BiayaProyek.TerminPembayaranDetail;
import widhimp.manajemenproyekreparasi.MutuProyek.Qaqc_detail;
import widhimp.manajemenproyekreparasi.Object.qaqc;
import widhimp.manajemenproyekreparasi.Object.termin;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class QaqcAdapter extends ArrayAdapter<qaqc> {
    public QaqcAdapter(Context context, int resource, List<qaqc> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final qaqc listqaqc = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mutuproyek_qaqc_item,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.nama_mutuproyek_qaqc_item);
        Button detail=(Button) convertView.findViewById(R.id.detail_mutuproyek_qaqc_item);
        name.setText(listqaqc.getNama());
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id_qaqc", listqaqc.getId());
                Intent intentTambah=new Intent(v.getContext(),Qaqc_detail.class);
                intentTambah.putExtras(bundle);
                v.getContext().startActivity(intentTambah);
            }
        });
        return convertView;
    }
}
