package com.mrabid.hhis.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mrabid.hhis.Activity.ArtikelActivity;
import com.mrabid.hhis.Activity.RiwayatPasienActivity;
import com.mrabid.hhis.R;

public class HomeFragment extends Fragment {


    RelativeLayout riwayat,artikel, graph;;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        graph = (RelativeLayout) getActivity().findViewById(R.id.rlt_graph);
        artikel = (RelativeLayout) getActivity().findViewById(R.id.rlt_artikel);
        riwayat = (RelativeLayout) getActivity().findViewById(R.id.rlt_history);


        artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ini Artikel", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ArtikelActivity.class));

            }
        });

        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RiwayatPasienActivity.class));
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }
}
