package com.mrabid.hhis.Modal;

/**
 * Created by Mr Abid on 10/17/2017.
 */

public class Provinsi {
    private int id_prov;
    private String nama;

    public Provinsi(int id_prov, String nama) {
        this.id_prov = id_prov;
        this.nama = nama;
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
