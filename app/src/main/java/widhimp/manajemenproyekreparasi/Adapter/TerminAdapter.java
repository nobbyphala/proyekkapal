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
import widhimp.manajemenproyekreparasi.Object.sdmgalangan;
import widhimp.manajemenproyekreparasi.Object.termin;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmGalanganDetail;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class TerminAdapter extends ArrayAdapter<termin> {
    public TerminAdapter(Context context, int resource, List<termin> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        termin listgalangan = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.biayaproyek_termin_item,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.nama_biayaproyek_termin_item);
        Button detail=(Button) convertView.findViewById(R.id.detail_biayaproyek_termin_item);
        name.setText(listgalangan.getNama());
        final String namatermin=listgalangan.getNama();
        final String idtermin=listgalangan.getId();
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("namatermin", namatermin);
                bundle.putString("idtermin",idtermin);
                Intent intentTambah=new Intent(v.getContext(),TerminPembayaranDetail.class);
                intentTambah.putExtras(bundle);
                v.getContext().startActivity(intentTambah);
            }
        });
        return convertView;
    }
}
