package com.mrabid.hhis.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrabid.hhis.DetailArtikelActivity;
import com.mrabid.hhis.Modal.Artikel;
import com.mrabid.hhis.R;

import java.util.List;

/**
 * Created by Mr.Abid on 6/14/2017.
 */

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ViewHolder> {
    Context context;
    List<Artikel> listArtikel;

    public ArtikelAdapter(Context context, List<Artikel> listArtikel) {
        this.context = context;
        this.listArtikel = listArtikel;
    }

    @Override
    public ArtikelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_artikel,null));
    }

    @Override
    public void onBindViewHolder(ArtikelAdapter.ViewHolder holder, int position) {
        final Artikel p = listArtikel.get(position);
        holder.judul.setText(""+p.getJudul());
        holder.abstrak.setText(""+p.getAbstrak().substring(0,100)+".....");
        Glide.with(context).load("http://hhis.tk/backend/web/"+p.getImage()).into(holder.image);

        holder.cvArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent (context, DetailArtikelActivity.class);
                a.putExtra("judul", (p.getJudul()));
                a.putExtra("deskripsi",(p.getDeskripsi()));
                a.putExtra("image", p.getImage());
                a.putExtra("abstrak",(p.getAbstrak()));
                context.startActivity(a);
            }
        });
    }

    public int getItemCount() {
        return listArtikel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView judul,abstrak;
        public ImageView image;
        CardView cvArtikel;

        public ViewHolder(View itemView) {
            super(itemView);
            judul = (TextView)itemView.findViewById(R.id.txt_judulArtikel);
            abstrak = (TextView)itemView.findViewById(R.id.txt_sinposis);
            image = (ImageView) itemView.findViewById(R.id.img_artikel);

            cvArtikel = (CardView)itemView.findViewById(R.id.cv_artikel);
        }
    }
}
