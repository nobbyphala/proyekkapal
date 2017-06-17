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
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Komunikasi.KomunikasiEdit;
import widhimp.manajemenproyekreparasi.Object.dokumen;
import widhimp.manajemenproyekreparasi.Object.subcontractor;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmSubcontractorDetail;

/**
 * Created by Widhi Mahaputra on 1/3/2017.
 */

public class SubContractorAdapter extends ArrayAdapter<subcontractor> {
    public SubContractorAdapter(Context context, int resource, List<subcontractor> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        subcontractor list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sdmproyek_subcontractor_item,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.nama_subcontractor_item);
        Button edit=(Button) convertView.findViewById(R.id.detail_subcontractor_item);
        name.setText(list.getNama());
        final String id=list.getId();
        final String nama=list.getNama();
        final String koordinator=list.getKoordinator();
        final String jabatan=list.getJabatan();
        final String kontak=list.getKontak();
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", id);
                bundle.putString("nama",nama);
                bundle.putString("koordinator",koordinator);
                bundle.putString("jabatan",jabatan);
                bundle.putString("kontak",kontak);
                Intent intentTambah=new Intent(v.getContext(),SdmSubcontractorDetail.class);
                intentTambah.putExtras(bundle);
                v.getContext().startActivity(intentTambah);
            }
        });
        return convertView;
    }
}
