package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Komunikasi.KomunikasiEdit;
import widhimp.manajemenproyekreparasi.Object.dokumen;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 11/28/2016.
 */

public class KomunikasiAdapter extends ArrayAdapter<dokumen> {
    public KomunikasiAdapter(Context context, int resource, List<dokumen> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        dokumen listdokumen = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_komunikasi,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.item_komunikasi);
        ImageButton edit=(ImageButton) convertView.findViewById(R.id.sunting_komunikasi);
        name.setText(listdokumen.getNama());
        final String idkomunikasi=listdokumen.getId();
        final String namadokumen=listdokumen.getNama();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("namadokumen", namadokumen);
                bundle.putString("idkomunikasi",idkomunikasi);
                Intent intentTambah=new Intent(v.getContext(),KomunikasiEdit.class);
                intentTambah.putExtras(bundle);
                v.getContext().startActivity(intentTambah);
            }
        });
        return convertView;
    }
}