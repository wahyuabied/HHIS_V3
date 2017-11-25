package com.mrabid.hhis.Modal;

import java.util.ArrayList;

/**
 * Created by Mr Abid on 10/17/2017.
 */

public class Pasien {
    private int id_pasien;
    private int id_riwayat;
    private String nama_pasien;
    private int umur;
    private String jenis_kelamin;
    private String email;
    private String golongan_darah;
    private int tinggi_badan;
    private int berat_badan;
    private String no_telp;
    private String alamat;
    private String larangan;
    private String diagnosa;
    private ArrayList <Dokter> dokter;

    public Pasien(int id_pasien, int id_riwayat, String nama_pasien, int umur, String jenis_kelamin, String email, String golongan_darah, int tinggi_badan, int berat_badan, String no_telp, String alamat, String larangan, String diagnosa, ArrayList<Dokter> dokter) {
        this.id_pasien = id_pasien;
        this.id_riwayat = id_riwayat;
        this.nama_pasien = nama_pasien;
        this.umur = umur;
        this.jenis_kelamin = jenis_kelamin;
        this.email = email;
        this.golongan_darah = golongan_darah;
        this.tinggi_badan = tinggi_badan;
        this.berat_badan = berat_badan;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.larangan = larangan;
        this.diagnosa = diagnosa;
        this.dokter = dokter;
    }

    public Pasien(int id_pasien, int id_riwayat, String nama_pasien, int umur, String jenis_kelamin, String email, String golongan_darah, int tinggi_badan, int berat_badan, String no_telp, String alamat, String larangan, String diagnosa) {
        this.id_pasien = id_pasien;
        this.id_riwayat = id_riwayat;
        this.nama_pasien = nama_pasien;
        this.umur = umur;
        this.jenis_kelamin = jenis_kelamin;
        this.email = email;
        this.golongan_darah = golongan_darah;
        this.tinggi_badan = tinggi_badan;
        this.berat_badan = berat_badan;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.larangan = larangan;
        this.diagnosa = diagnosa;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Pasien() {
    }

    public int getId_pasien() {
        return id_pasien;
    }

    public void setId_pasien(int id_pasien) {
        this.id_pasien = id_pasien;
    }

    public int getId_riwayat() {
        return id_riwayat;
    }

    public void setId_riwayat(int id_riwayat) {
        this.id_riwayat = id_riwayat;
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

    public int getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(int tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public int getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(int berat_badan) {
        this.berat_badan = berat_badan;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getLarangan() {
        return larangan;
    }

    public void setLarangan(String larangan) {
        this.larangan = larangan;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public ArrayList<Dokter> getDokter() {
        return dokter;
    }

    public void setDokter(ArrayList<Dokter> dokter) {
        this.dokter = dokter;
    }
}
