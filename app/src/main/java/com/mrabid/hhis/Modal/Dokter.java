package com.mrabid.hhis.Modal;

/**
 * Created by Mr.Abid on 7/15/2017.
 */

public class Dokter {
    private String nama_dokter;
    private String no_telp_dokter;
    private String alamat_praktik;
    private String alamat_rumah;
    private String email;

    public Dokter(String nama_dokter, String no_telp_dokter, String alamat_praktik, String alamat_rumah, String email) {
        this.nama_dokter = nama_dokter;
        this.no_telp_dokter = no_telp_dokter;
        this.alamat_praktik = alamat_praktik;
        this.alamat_rumah = alamat_rumah;
        this.email = email;
    }

    public Dokter() {
        this.nama_dokter = "Anda tidak memiliki riwayat Penyakit";
        this.no_telp_dokter = "";
        this.alamat_praktik = "";
        this.alamat_rumah = "";
        this.email = "";
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getNo_telp_dokter() {
        return no_telp_dokter;
    }

    public void setNo_telp_dokter(String no_telp_dokter) {
        this.no_telp_dokter = no_telp_dokter;
    }

    public String getAlamat_praktik() {
        return alamat_praktik;
    }

    public void setAlamat_praktik(String alamat_praktik) {
        this.alamat_praktik = alamat_praktik;
    }

    public String getAlamat_rumah() {
        return alamat_rumah;
    }

    public void setAlamat_rumah(String alamat_rumah) {
        this.alamat_rumah = alamat_rumah;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
