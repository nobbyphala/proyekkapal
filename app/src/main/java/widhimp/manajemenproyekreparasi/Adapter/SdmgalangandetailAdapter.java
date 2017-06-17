package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Object.sdmgalangandetail;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 12/25/2016.
 */

public class SdmgalangandetailAdapter extends ArrayAdapter<sdmgalangandetail>{
    public SdmgalangandetailAdapter(Context context, int resource, List<sdmgalangandetail> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sdmgalangandetail list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.sdmproyek_galangan_detail_item,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.nama_sdmgalangandetail_item);
        TextView jabatan=(TextView) convertView.findViewById(R.id.jabatan_sdmgalangandetail_item);
        TextView kontak=(TextView) convertView.findViewById(R.id.kontak_sdmgalangandetail_item);
        name.setText(list.getNama());
        jabatan.setText(list.getJabatan());
        kontak.setText(list.getKontak());
        return convertView;
    }
}
