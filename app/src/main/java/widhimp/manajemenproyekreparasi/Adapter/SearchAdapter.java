package widhimp.manajemenproyekreparasi.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import widhimp.manajemenproyekreparasi.MutuProyek.CheckListEdit;
import widhimp.manajemenproyekreparasi.Object.budgetproduksi;
import widhimp.manajemenproyekreparasi.Object.search;
import widhimp.manajemenproyekreparasi.R;
import widhimp.manajemenproyekreparasi.WaktuProyek.PenjadwalanProyekEdit;
import widhimp.manajemenproyekreparasi.WaktuProyek.ProgresProyekEdit;

/**
 * Created by Widhi Mahaputra on 1/21/2017.
 */

public class SearchAdapter extends ArrayAdapter<search> {
    private String kategori;
    private Context context;
    public SearchAdapter(Context context, int resource, List<search> objects) {
        super(context, resource, objects);
        this.context=context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final search list = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_search_item,parent,false);
        }
        final TextView name=(TextView) convertView.findViewById(R.id.nama_search_item);
        Button detail=(Button) convertView.findViewById(R.id.detail_search_item);
        name.setText(list.getNama());

        SharedPreferences preferences = context.getSharedPreferences("session", context.getApplicationContext().MODE_PRIVATE);
        kategori=preferences.getString("menu",null);

        if(kategori.equals("repairlist"))
            detail.setVisibility(View.INVISIBLE);
        else if(kategori.equals("progress")){
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String nama=list.getNama();
                    Bundle bundle = new Bundle();
                    bundle.putString("nama", nama);
                    bundle.putString("id", list.getId());
                    Intent intent=new Intent(v.getContext(),ProgresProyekEdit.class);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });
        }
        else if(kategori.equals("penjadwalan")){
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String nama=list.getNama();
                    Bundle bundle = new Bundle();
                    bundle.putString("nama", nama);
                    bundle.putString("id", list.getId());
                    Intent intent=new Intent(v.getContext(),PenjadwalanProyekEdit.class);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });
        }
        else if(kategori.equals("checklist")){
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String nama=list.getNama();
                    Bundle bundle = new Bundle();
                    bundle.putString("nama", nama);
                    bundle.putString("id", list.getId());
                    Intent intent=new Intent(v.getContext(),CheckListEdit.class);
                    intent.putExtras(bundle);
                    v.getContext().startActivity(intent);
                }
            });
        }

        return convertView;
    }
}
