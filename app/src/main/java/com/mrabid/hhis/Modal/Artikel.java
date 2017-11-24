package com.mrabid.hhis.Modal;

import android.graphics.drawable.Drawable;

/**
 * Created by Mr.Abid on 6/13/2017.
 */

public class Artikel {
    private String image;
    private String judul;
    private String abstrak;
    private String deskripsi;

    public Artikel(String judul,  String deskripsi, String image, String abstrak) {
        this.image = image;
        this.judul = judul;
        this.abstrak = abstrak;
        this.deskripsi = deskripsi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getAbstrak() {
        return abstrak;
    }

    public void setAbstrak(String abstrak) {
        this.abstrak = abstrak;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
