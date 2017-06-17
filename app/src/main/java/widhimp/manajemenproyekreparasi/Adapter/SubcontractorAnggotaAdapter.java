package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Object.subcontractoranggota;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 12/27/2016.
 */

public class SubcontractorAnggotaAdapter extends ArrayAdapter<subcontractoranggota> {
    public SubcontractorAnggotaAdapter(Context context, int resource, List<subcontractoranggota> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        subcontractoranggota list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sdmproyek_subcontractor_anggota_item,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.nama_subcontractor_anggota_item);
        name.setText(list.getNama());
        return convertView;
    }
}