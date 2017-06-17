package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import widhimp.manajemenproyekreparasi.Object.resiko;
import widhimp.manajemenproyekreparasi.Object.tambahpekerjaan;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 1/19/2017.
 */

public class ListTambahPekerjaanAdapter extends ArrayAdapter<tambahpekerjaan> {
    private Context context;
    private int jumlah;
    public ListTambahPekerjaanAdapter(Context context, int resource, List<tambahpekerjaan> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        tambahpekerjaan list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.repairlist_listtambahpekerjaan_item,parent,false);
        }
        TextView dari=(TextView) convertView.findViewById(R.id.dari_listtambahpekerjaan_item);
        TextView kepada=(TextView) convertView.findViewById(R.id.kepada_listtambahpekerjaan_item);
        TextView perihal=(TextView) convertView.findViewById(R.id.perihal_listtambahpekerjaan_item);
        TextView catatan=(TextView) convertView.findViewById(R.id.catatan_listtambahpekerjaan_item);
        TextView baru=(TextView) convertView.findViewById(R.id.new_listtambahpekerjaan_item);
        baru.setVisibility(View.INVISIBLE);
        jumlah=list.getJumlah();
        if(position==jumlah-1)
            baru.setVisibility(View.VISIBLE);
        dari.setText(list.getDari());
        kepada.setText(list.getKepada());
        perihal.setText(list.getPerihal());
        catatan.setText(list.getCatatan());
        //Toast.makeText(context, String.valueOf(position), Toast.LENGTH_SHORT).show();
        return convertView;
    }
}
