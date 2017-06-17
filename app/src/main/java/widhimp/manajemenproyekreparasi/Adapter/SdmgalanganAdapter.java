package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Object.sdmgalangan;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmGalanganDetail;

/**
 * Created by Widhi Mahaputra on 12/12/2016.
 */

public class SdmgalanganAdapter extends ArrayAdapter<sdmgalangan> {
    public SdmgalanganAdapter(Context context, int resource, List<sdmgalangan> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sdmgalangan listgalangan = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sdmproyek_galangan_item,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.nama_itemgalangan);
        Button detail=(Button) convertView.findViewById(R.id.detail_itemgalangan);
        name.setText(listgalangan.getNama());
        final String namagalangan=listgalangan.getNama();
        final String idgalangan=listgalangan.getId();
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("namagalangan", namagalangan);
                bundle.putString("idgalangan",idgalangan);
                Intent intentTambah=new Intent(v.getContext(),SdmGalanganDetail.class);
                intentTambah.putExtras(bundle);
                v.getContext().startActivity(intentTambah);
            }
        });
        return convertView;
    }
}
