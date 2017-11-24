package com.mrabid.hhis.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrabid.hhis.Helper.SharedPref;
import com.mrabid.hhis.HelpersDialog;
import com.mrabid.hhis.Modal.Kota;
import com.mrabid.hhis.Modal.Provinsi;
import com.mrabid.hhis.R;

import java.util.List;

/**
 * Created by Mr Abid on 10/17/2017.
 */

public class RecyclerKotaAdapter extends RecyclerView.Adapter<RecyclerKotaAdapter.ViewHolder>{
        Context context;
        List<Kota> listKota;
        SharedPref sharedPref = new SharedPref(context);

        public RecyclerKotaAdapter(Context context, List<Kota> listProject) {
            this.context = context;
            this.listKota = listProject;
        }



        @Override
        public RecyclerKotaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.isi_kota_propinsi,null));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Kota p = listKota.get(position);
            final String id = String.valueOf(p.getId_kab());
            final String nama = p.getNama().toString();
            holder.nama.setText(""+p.getNama());
            holder.lnr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HelpersDialog h = (HelpersDialog)context;
                    h.getKota(id,nama);
                    h.DismissDialogKota();
                }
            });
        }

        @Override
        public int getItemCount() {
            return listKota.size();
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
