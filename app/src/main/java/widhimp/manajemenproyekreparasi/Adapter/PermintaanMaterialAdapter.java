package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Object.permintaanmaterial;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 12/31/2016.
 */

public class PermintaanMaterialAdapter extends ArrayAdapter<permintaanmaterial> {
    public PermintaanMaterialAdapter(Context context, int resource, List<permintaanmaterial> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        permintaanmaterial list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pengadaanmaterial_permintaan_item,parent,false);
        }
        TextView no=(TextView) convertView.findViewById(R.id.no_permintaanmaterial_item);
        TextView name=(TextView) convertView.findViewById(R.id.nama_permintaanmaterial_item);
        TextView spesifikasi=(TextView) convertView.findViewById(R.id.spesifikasi_permintaanmaterial_item);
        TextView jumlah=(TextView) convertView.findViewById(R.id.jumlah_permintaanmaterial_item);
        TextView tanggal=(TextView) convertView.findViewById(R.id.tanggal_permintaanmaterial_item);
        TextView baru=(TextView) convertView.findViewById(R.id.new_permintaanmaterial_item);
        int count=list.getCount();
        baru.setVisibility(View.INVISIBLE);
        no.setText(String.valueOf(position+1));
        name.setText(list.getNama());
        spesifikasi.setText(list.getSpesifikasi());
        jumlah.setText(list.getJumlah());
        tanggal.setText(list.getTanggaldibutuhkan());
        if(position==count-1){
            baru.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}
