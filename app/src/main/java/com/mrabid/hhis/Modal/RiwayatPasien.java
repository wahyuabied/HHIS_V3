package com.mrabid.hhis.Modal;

/**
 * Created by Mr Abid on 10/24/2017.
 */

public class RiwayatPasien {
    private int id_riwayat;
    private int id_pasien;
    private String nama_pasien;
    private String nama_dokter;
    private String no_telp_dokter;
    private String alamat_praktik;
    private String umur;
    private String berat_badan;
    private String tinggi_badan;
    private String riwayat_penyakit_keluarga;
    private String keluhan_utama;
    private String diagnosa;
    private String larangan;
    private String tgl_periksa;
    private String perawatan;
    private String advis;
    private String head;
    private String neck;
    private String thorax;
    private String abdomen;
    private String ekstremitas;



    public RiwayatPasien(String nama, String noTelp, String tanggalPeriksa, String diagnosa) {
        this.nama_pasien = nama;
        this.no_telp_dokter = noTelp;
        this.tgl_periksa = tanggalPeriksa;
        this.diagnosa = diagnosa;
    }

    public RiwayatPasien(int id_riwayat, int id_pasien, String nama_pasien, String nama_dokter, String no_telp_dokter, String alamat_praktik, String umur, String berat_badan, String tinggi_badan, String riwayat_penyakit_keluarga, String keluhan_utama, String diagnosa, String larangan, String tgl_periksa, String perawatan, String advis, String head, String neck, String thorax, String abdomen, String ekstremitas) {
        this.id_riwayat = id_riwayat;
        this.id_pasien = id_pasien;
        this.nama_pasien = nama_pasien;
        this.nama_dokter = nama_dokter;
        this.no_telp_dokter = no_telp_dokter;
        this.alamat_praktik = alamat_praktik;
        this.umur = umur;
        this.berat_badan = berat_badan;
        this.tinggi_badan = tinggi_badan;
        this.riwayat_penyakit_keluarga = riwayat_penyakit_keluarga;
        this.keluhan_utama = keluhan_utama;
        this.diagnosa = diagnosa;
        this.larangan = larangan;
        this.tgl_periksa = tgl_periksa;
        this.perawatan = perawatan;
        this.advis = advis;
        this.head = head;
        this.neck = neck;
        this.thorax = thorax;
        this.abdomen = abdomen;
        this.ekstremitas = ekstremitas;
    }

    public int getId_riwayat() {
        return id_riwayat;
    }

    public void setId_riwayat(int id_riwayat) {
        this.id_riwayat = id_riwayat;
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

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(String berat_badan) {
        this.berat_badan = berat_badan;
    }

    public String getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(String tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public String getRiwayat_penyakit_keluarga() {
        return riwayat_penyakit_keluarga;
    }

    public void setRiwayat_penyakit_keluarga(String riwayat_penyakit_keluarga) {
        this.riwayat_penyakit_keluarga = riwayat_penyakit_keluarga;
    }

    public String getKeluhan_utama() {
        return keluhan_utama;
    }

    public void setKeluhan_utama(String keluhan_utama) {
        this.keluhan_utama = keluhan_utama;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getLarangan() {
        return larangan;
    }

    public void setLarangan(String larangan) {
        this.larangan = larangan;
    }

    public String getTgl_periksa() {
        return tgl_periksa;
    }

    public void setTgl_periksa(String tgl_periksa) {
        this.tgl_periksa = tgl_periksa;
    }

    public String getPerawatan() {
        return perawatan;
    }

    public void setPerawatan(String perawatan) {
        this.perawatan = perawatan;
    }

    public String getAdvis() {
        return advis;
    }

    public void setAdvis(String advis) {
        this.advis = advis;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNeck() {
        return neck;
    }

    public void setNeck(String neck) {
        this.neck = neck;
    }

    public String getThorax() {
        return thorax;
    }

    public void setThorax(String thorax) {
        this.thorax = thorax;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getEkstremitas() {
        return ekstremitas;
    }

    public void setEkstremitas(String ekstremitas) {
        this.ekstremitas = ekstremitas;
    }
}
