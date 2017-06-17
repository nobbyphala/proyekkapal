package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Object.kapal;
import widhimp.manajemenproyekreparasi.Object.repairlist;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 12/4/2016.
 */

public class RepairlistAdapter extends ArrayAdapter<repairlist> {
    public RepairlistAdapter(Context context, int resource, List<repairlist> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        repairlist listkapal = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.repairlist_item,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.nama_itemrepairlist);
        name.setText(listkapal.getNama());
        return convertView;
    }
}
