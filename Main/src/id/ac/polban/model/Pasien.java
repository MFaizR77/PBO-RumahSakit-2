package id.ac.polban.model;

public class Pasien {
    private String nama;
    private String penyakit;
    private int umur;
    private static int totalPasien = 0; // Static variable untuk menghitung total pasien

    public Pasien(String nama, String penyakit, int umur) {
        this.nama = nama;
        this.penyakit = penyakit;
        this.umur = umur;
        totalPasien++; // Increment static variable
    }

    // Static method untuk mendapatkan total pasien
    public static int getTotalPasien() {
        return totalPasien;
    }

    // Static method untuk validasi umur
    public static boolean isValidUmur(int umur) {
        return umur >= 1 && umur <= 120;
    }

    public String getNama() {
        return nama;
    }

    public String getPenyakit() {
        return penyakit;
    }

    public int getUmur() {
        return umur;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPenyakit(String penyakit) {
        this.penyakit = penyakit;
    }

    public void setUmur(int umur) {
        if (isValidUmur(umur)) {
            this.umur = umur;
        }
    }

    public String getKategoriUmur() {
        if (umur < 12) return "Anak-anak";
        if (umur < 18) return "Remaja";
        if (umur < 60) return "Dewasa";
        return "Lansia";
    }

    @Override
    public String toString() {
        return "Pasien{" +
                "nama='" + nama + '\'' +
                ", penyakit='" + penyakit + '\'' +
                ", umur=" + umur +
                ", kategori='" + getKategoriUmur() + '\'' +
                '}';
    }
}