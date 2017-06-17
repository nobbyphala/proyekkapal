package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Object.materialtersedia;
import widhimp.manajemenproyekreparasi.Object.resiko;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by nobby phala on 28/12/2016.
 */

public class MaterialtersediaAdapter extends ArrayAdapter<materialtersedia> {
    public MaterialtersediaAdapter(Context context, int resource, List<materialtersedia> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        materialtersedia listmaterialtersedia = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pengadaanmaterial_materialtersedia_item,parent,false);
        }
        //TextView no=(TextView) convertView.findViewById(R.id.no_materialtersedia_item);
        TextView name=(TextView) convertView.findViewById(R.id.nama_materialtersedia_item);
        TextView spesifikasi=(TextView) convertView.findViewById(R.id.spesifikasi_materialtersedia_item);
        TextView jumlah=(TextView) convertView.findViewById(R.id.jumlah_materialtersedia_item);
        //no.setText(listmaterialtersedia.getNo());
        name.setText(listmaterialtersedia.getNama());
        spesifikasi.setText(listmaterialtersedia.getSpesifikasi());
       jumlah.setText(listmaterialtersedia.getJumlah());
        return convertView;
    }
}
