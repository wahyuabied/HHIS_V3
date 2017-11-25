package com.mrabid.hhis.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrabid.hhis.Activity.DetailDokterActivity;
import com.mrabid.hhis.Modal.Dokter;
import com.mrabid.hhis.R;

import java.util.List;

public class RecyclerDokterAdapter extends RecyclerView.Adapter<RecyclerDokterAdapter.ViewHolder>{
    Context context;
    List<Dokter> listDokter;

    public RecyclerDokterAdapter(Context context, List<Dokter> listProject) {
        this.context = context;
        this.listDokter = listProject;
    }

    @Override
    public RecyclerDokterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.isi_dokter,null));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Dokter p = listDokter.get(position);
        holder.namaDokter.setText(""+p.getNama_dokter());
        holder.no_telpDokter.setText(""+p.getNo_telp_dokter());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailDokterActivity.class);
                i.putExtra("nama_dokter",p.getNama_dokter()+"");
                i.putExtra("no_telp",p.getNo_telp_dokter()+"");
                i.putExtra("email",p.getEmail()+"");
                i.putExtra("alamat_praktik",p.getAlamat_praktik()+"");
                i.putExtra("alamat_rumah",p.getAlamat_rumah()+"");
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDokter.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaDokter,no_telpDokter;
        CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
                namaDokter = (TextView)itemView.findViewById(R.id.tv_isiDokter_namaDokter);
                no_telpDokter = (TextView)itemView.findViewById(R.id.tv_isiDokter_telpDokter);
                card = (CardView) itemView.findViewById(R.id.card_isiDokter);
        }
    }
}
