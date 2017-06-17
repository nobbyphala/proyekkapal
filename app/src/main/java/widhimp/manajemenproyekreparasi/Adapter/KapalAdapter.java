package widhimp.manajemenproyekreparasi.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import widhimp.manajemenproyekreparasi.Activity.EditkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.ListkapalActivity;
import widhimp.manajemenproyekreparasi.Activity.ShipIdentity;
import widhimp.manajemenproyekreparasi.Activity.TambahkapalActivity;
import widhimp.manajemenproyekreparasi.Object.kapal;
import widhimp.manajemenproyekreparasi.R;

/**
 * Created by Widhi Mahaputra on 11/25/2016.
 */


public class KapalAdapter extends ArrayAdapter<kapal> {
    private Context context;
    public KapalAdapter(Context context, int resource, List<kapal> objects) {
        super(context, resource, objects);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       final kapal listkapal = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_kapal,parent,false);
        }
        TextView name=(TextView) convertView.findViewById(R.id.name);
        TextView type=(TextView) convertView.findViewById(R.id.type);
        Button edit=(Button) convertView.findViewById(R.id.edit);
        name.setText(listkapal.getName());
        type.setText(listkapal.getType());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = v.getContext().getSharedPreferences("kapal",v.getContext().getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("idkapal", listkapal.getId());
                editor.commit();
                Intent intent=new Intent(v.getContext(),ShipIdentity.class);
                v.getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
