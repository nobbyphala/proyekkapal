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

import widhimp.manajemenproyekreparasi.Object.sdmgalangan;
import widhimp.manajemenproyekreparasi.Object.stakeholder;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.SdmProyek.SdmGalanganDetail;

/**
 * Created by Widhi Mahaputra on 12/31/2016.
 */

public class StakeholderAdapter extends ArrayAdapter<stakeholder> {
    public StakeholderAdapter(Context context, int resource, List<stakeholder> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        stakeholder list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stakeholder_item,parent,false);
        }
        TextView stakehldr=(TextView)convertView.findViewById(R.id.stakeholder_stakeholder_item);
        TextView name=(TextView) convertView.findViewById(R.id.nama_stakeholder_item);
        TextView perihal=(TextView) convertView.findViewById(R.id.perihal_stakeholder_item);

        name.setText(list.getNama());
        stakehldr.setText(list.getStakeholder());
        perihal.setText(list.getPerihal());
        return convertView;
    }
}
