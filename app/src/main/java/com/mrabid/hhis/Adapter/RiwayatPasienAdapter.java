package com.mrabid.hhis.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mrabid.hhis.DetailRiwayatPasienActivity;
import com.mrabid.hhis.Modal.RiwayatPasien;
import com.mrabid.hhis.R;

import java.util.List;

/**
 * Created by Mr.Abid on 5/12/2017.
 */

public class RiwayatPasienAdapter extends RecyclerView.Adapter<RiwayatPasienAdapter.ViewHolder> {
    Context context;
    List<RiwayatPasien> listPasien;

    public RiwayatPasienAdapter(Context context, List<RiwayatPasien> listPasien) {
        this.context = context;
        this.listPasien = listPasien;
    }

    @Override
    public RiwayatPasienAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_pasien,null));
    }

    @Override
    public void onBindViewHolder(RiwayatPasienAdapter.ViewHolder holder, int position) {
        final RiwayatPasien p = listPasien.get(position);
        holder.nama_dokter.setText(""+p.getNama_dokter());
        holder.tgl_periksa.setText(""+p.getTgl_periksa());
        holder.no_telp.setText(""+p.getNo_telp_dokter());
        holder.diagnosa.setText(""+p.getDiagnosa());
        holder.cvPasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailRiwayatPasienActivity.class);
                i.putExtra("id_riwayat",String.valueOf(p.getId_riwayat()));
                i.putExtra("id_pasien",String.valueOf(p.getId_pasien()));
                i.putExtra("nama_pasien",p.getNama_pasien());
                i.putExtra("nama_dokter",p.getNama_dokter());
                i.putExtra("no_telp_dokter",p.getNo_telp_dokter());
                i.putExtra("alamat_praktik",p.getAlamat_praktik());
                i.putExtra("umur",p.getUmur());
                i.putExtra("berat_badan",p.getBerat_badan());
                i.putExtra("tinggi_badan",p.getTinggi_badan());
                i.putExtra("riwayat_penyakit_keluarga",p.getRiwayat_penyakit_keluarga());
                i.putExtra("keluhan_utama",p.getKeluhan_utama());
                i.putExtra("diagnosa",p.getDiagnosa());
                i.putExtra("larangan",p.getLarangan());
                i.putExtra("tgl_periksa",p.getTgl_periksa());
                i.putExtra("perawatan",p.getPerawatan());
                i.putExtra("advis",p.getAdvis());
                i.putExtra("head",p.getHead());
                i.putExtra("neck",p.getNeck());
                i.putExtra("thorax",p.getThorax());
                i.putExtra("abdomen",p.getAbdomen());
                i.putExtra("ekstremitas",p.getEkstremitas());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPasien.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nama_dokter,no_telp,tgl_periksa,diagnosa;
        public CardView cvPasien;

        public ViewHolder(View itemView) {
            super(itemView);

            nama_dokter = (TextView)itemView.findViewById(R.id.txt_namaDokter);
            tgl_periksa = (TextView)itemView.findViewById(R.id.txt_tgl);
            no_telp=(TextView)itemView.findViewById(R.id.txt_noTelp);
            diagnosa=(TextView)itemView.findViewById(R.id.txt_dignosa);
            cvPasien = (CardView)itemView.findViewById(R.id.cv_pasien);
        }
    }
}
