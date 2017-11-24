package com.mrabid.hhis.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrabid.hhis.Helper.AppConfig;
import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.HelpersDialog;
import com.mrabid.hhis.Modal.Provinsi;
import com.mrabid.hhis.R;

import java.util.List;

/**
 * Created by Mr Abid on 10/17/2017.
 */

public class RecyclerProvinsiAdapter extends RecyclerView.Adapter<RecyclerProvinsiAdapter.ViewHolder>{
        Context context;
        List<Provinsi> listProjects;
        SharedPref sharedPref = new SharedPref(context);

        public RecyclerProvinsiAdapter(Context context, List<Provinsi> listProject) {
            this.context = context;
            this.listProjects = listProject;
        }



        @Override
        public RecyclerProvinsiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.isi_kota_propinsi,null));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Provinsi p = listProjects.get(position);
            final String id = String.valueOf(p.getId_prov());
            final String nama = p.getNama().toString();
            holder.nama.setText(""+p.getNama());
            holder.lnr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HelpersDialog h = (HelpersDialog)context;
                    h.getProvinsi(id,nama);
                    h.DismissDialog();
                }
            });
        }

        @Override
        public int getItemCount() {
            return listProjects.size();
        }


    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView nama;
            LinearLayout lnr;

            public ViewHolder(View itemView) {
                super(itemView);
                nama = (TextView) itemView.findViewById(R.id.txt_nama_kota_provinsi);
                lnr = (LinearLayout)itemView.findViewById(R.id.lnr_isi_jota_prpinsi);

            }
        }
}
