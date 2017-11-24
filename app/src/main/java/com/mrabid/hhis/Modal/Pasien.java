package com.mrabid.hhis.Modal;

import java.util.ArrayList;

/**
 * Created by Mr Abid on 10/17/2017.
 */

public class Pasien {
    private int id_pasien;
    private String nama_pasien;
    private int umur;
    private String jenis_kelamin;
    private String email;
    private String golongan_darah;
    private int berat_badan;
    private int tinggi_badan;
    private String riwayat_penyakit_keluarga;
    private String alamat;
    private String larangan;
    private String pemeriksaan_terbaru;
    private ArrayList<Dokter> dokter;

    public Pasien(int id_pasien, String nama_pasien, int umur, String jenis_kelamin, String email, String golongan_darah, int berat_badan, int tinggi_badan, String riwayat_penyakit_keluarga, String alamat, String larangan, String pemeriksaan_terbaru, ArrayList<Dokter> dokter) {
        this.id_pasien = id_pasien;
        this.nama_pasien = nama_pasien;
        this.umur = umur;
        this.jenis_kelamin = jenis_kelamin;
        this.email = email;
        this.golongan_darah = golongan_darah;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.riwayat_penyakit_keluarga = riwayat_penyakit_keluarga;
        this.alamat = alamat;
        this.larangan = larangan;
        this.pemeriksaan_terbaru = pemeriksaan_terbaru;
        this.dokter = dokter;
    }

    public Pasien() {
    }

    public int getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(int id_pasien) {
        this.id_pasien = id_pasien;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public void setNama_pasien(String nama_pasien) {
        this.nama_pasien = nama_pasien;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGolongan_darah() {
        return golongan_darah;
    }

    public void setGolongan_darah(String golongan_darah) {
        this.golongan_darah = golongan_darah;
    }

    public int getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(int berat_badan) {
        this.berat_badan = berat_badan;
    }

    public int getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(int tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public String getRiwayat_penyakit_keluarga() {
        return riwayat_penyakit_keluarga;
    }

    public void setRiwayat_penyakit_keluarga(String riwayat_penyakit_keluarga) {
        this.riwayat_penyakit_keluarga = riwayat_penyakit_keluarga;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLarangan() {
        return larangan;
    }

    public void setLarangan(String larangan) {
        this.larangan = larangan;
    }

    public String getPemeriksaan_terbaru() {
        return pemeriksaan_terbaru;
    }

    public void setPemeriksaan_terbaru(String pemeriksaan_terbaru) {
        this.pemeriksaan_terbaru = pemeriksaan_terbaru;
    }

    public ArrayList<Dokter> getDokter() {
        return dokter;
    }

    public void setDokter(ArrayList<Dokter> dokter) {
        this.dokter = dokter;
    }
}
