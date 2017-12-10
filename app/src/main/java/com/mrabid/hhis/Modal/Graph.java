package com.mrabid.hhis.Modal;

import java.util.List;

/**
 * Created by Mr Abid on 11/25/2017.
 */

public class Graph {
    private int id_pasien;
    private List<Integer> jumlah;
    private List<String> nama;

    public Graph(int id_pasien, List<Integer> jumlah, List<String> nama) {
        this.id_pasien = id_pasien;
        this.jumlah = jumlah;
        this.nama = nama;
    }

    public Graph() {
    }

    public int getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(int id_pasien) {
        this.id_pasien = id_pasien;
    }

    public List<Integer> getJumlah() {
        return jumlah;
    }

    public void setJumlah(List<Integer> jumlah) {
        this.jumlah = jumlah;
    }

    public List<String> getNama() {
        return nama;
    }

    public void setNama(List<String> nama) {
        this.nama = nama;
    }
}
