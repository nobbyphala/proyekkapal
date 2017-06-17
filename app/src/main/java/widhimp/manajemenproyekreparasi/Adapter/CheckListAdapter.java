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

import widhimp.manajemenproyekreparasi.MutuProyek.CheckListEdit;
import widhimp.manajemenproyekreparasi.Object.checklist;
import widhimp.manajemenproyekreparasi.Object.repairlist;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.WaktuProyek.PenjadwalanProyekEdit;

/**
 * Created by Widhi Mahaputra on 12/12/2016.
 */

public class CheckListAdapter extends ArrayAdapter<checklist> {
    public CheckListAdapter(Context context, int resource, List<checklist> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final checklist listkapal = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mutuproyek_checklist_item,parent,false);
        }
        final TextView name=(TextView) convertView.findViewById(R.id.nama_mutuproyek_checklist_item);
        Button detail=(Button) convertView.findViewById(R.id.detail_mutuproyek_checklist_item);
        name.setText(listkapal.getNama());
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nama=listkapal.getNama();
                Bundle bundle = new Bundle();
                bundle.putString("nama", nama);
                bundle.putString("id", listkapal.getId());
                Intent intentTambah=new Intent(v.getContext(),CheckListEdit.class);
                intentTambah.putExtras(bundle);
                v.getContext().startActivity(intentTambah);
            }
        });
        return convertView;
    }
}