package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Object.rekapchecklist;
import widhimp.manajemenproyekreparasi.Object.rekapprogres;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 1/20/2017.
 */

public class RekapChecklistAdapter extends ArrayAdapter<rekapchecklist> {
    public RekapChecklistAdapter(Context context, int resource, List<rekapchecklist> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        rekapchecklist list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.mutuproyek_rekapchecklist_item,parent,false);
        }
        TextView nama=(TextView) convertView.findViewById(R.id.nama_rekapchecklist_item);
        TextView status=(TextView) convertView.findViewById(R.id.status_rekapchecklist_item);
        TextView komen=(TextView) convertView.findViewById(R.id.komen_rekapchecklist_item);
        TextView tanggal=(TextView) convertView.findViewById(R.id.tanggal_rekapchecklist_item);
        TextView approved=(TextView) convertView.findViewById(R.id.approved_rekapchecklist_item);
        TextView checklist=(TextView) convertView.findViewById(R.id.checklist_rekapchecklist_item);
        nama.setText(list.getNama());
        status.setText(list.getStatus());
        komen.setText(list.getKomen());
        tanggal.setText(list.getTanggal());
        if(list.getApprovedby().equals("null")){
            approved.setText("Belum dicek");
        }
        else
            approved.setText(list.getApprovedby());
        checklist.setText(list.getChecklist());
        return convertView;
    }
}
