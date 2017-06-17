package widhimp.manajemenproyekreparasi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.Activity.ShipIdentity;
import widhimp.manajemenproyekreparasi.InboxDetail;
import widhimp.manajemenproyekreparasi.Object.inbox;
import widhimp.manajemenproyekreparasi.Object.repairlist;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 1/16/2017.
 */

public class InboxAdapter extends ArrayAdapter<inbox> {
    public InboxAdapter(Context context, int resource, List<inbox> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final inbox listinbox= getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.inbox_item,parent,false);
        }
        TextView perihal=(TextView) convertView.findViewById(R.id.perihal_inbox);
        TextView dari=(TextView) convertView.findViewById(R.id.dari_inbox);
        ImageButton read=(ImageButton) convertView.findViewById(R.id.baca_inbox);

        perihal.setText(listinbox.getPerihal());
        dari.setText(listinbox.getDari());
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = v.getContext().getSharedPreferences("session",v.getContext().getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("idpesan", listinbox.getId());
                editor.putString("dari",listinbox.getDari());
                editor.commit();
                Intent intent=new Intent(v.getContext(),InboxDetail.class);
                v.getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
