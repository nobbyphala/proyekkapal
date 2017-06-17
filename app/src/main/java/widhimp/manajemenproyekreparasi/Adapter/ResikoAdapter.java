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

import widhimp.manajemenproyekreparasi.MutuProyek.Qaqc_detail;
import widhimp.manajemenproyekreparasi.Object.qaqc;
import widhimp.manajemenproyekreparasi.Object.resiko;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class ResikoAdapter extends ArrayAdapter<resiko> {
    public ResikoAdapter(Context context, int resource, List<resiko> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       resiko listqaqc = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resikoproyek_item,parent,false);
        }
        TextView no=(TextView) convertView.findViewById(R.id.no_resikoproyek_item);
        TextView name=(TextView) convertView.findViewById(R.id.nama_resikoproyek_item);
        TextView potensi=(TextView) convertView.findViewById(R.id.potensi_resikoproyek_item);
        TextView resiko=(TextView) convertView.findViewById(R.id.resiko_resikoproyek_item);
        TextView penanganan=(TextView) convertView.findViewById(R.id.penanganan_resikoproyek_item);
        no.setText(String.valueOf(position+1));
        name.setText(listqaqc.getNama());
        potensi.setText(listqaqc.getPotensi());
        resiko.setText(listqaqc.getResiko());
        penanganan.setText(listqaqc.getPenanganan());
        return convertView;
    }
}
