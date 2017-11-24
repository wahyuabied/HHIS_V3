package com.mrabid.hhis.Modal;

/**
 * Created by Mr Abid on 10/17/2017.
 */

public class Kota {
    private int id_kab;
    private int id_prov;
    private String nama;

    public Kota(int id_kab, int id_prov, String nama) {
        this.id_kab = id_kab;
        this.id_prov = id_prov;
        this.nama = nama;
    }

    public int getId_kab() {
        return id_kab;
    }

    public void setId_kab(int id_kab) {
        this.id_kab = id_kab;
    }

    public int getId_prov() {
        return id_prov;
    }

    public void setId_prov(int id_prov) {
        this.id_prov = id_prov;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
