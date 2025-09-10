package id.ac.polban.model;

public class Dokter {
    private String namaDokter;
    private String spesialis;
    private static int totalDokter = 0; // Static variable untuk menghitung jumlah dokter

    public Dokter(String namaDokter, String spesialis) {
        this.namaDokter = namaDokter;
        this.spesialis = spesialis;
        totalDokter++; // Increment static variable
    }

    // Static method untuk mendapatkan total dokter
    public static int getTotalDokter() {
        return totalDokter;
    }

    // Static method untuk validasi spesialis
    public static boolean isValidSpesialis(String spesialis) {
        return spesialis.equals("Umum") ||
                spesialis.equals("THT") ||
                spesialis.equals("Penyakit Dalam");
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public void setSpesialis(String spesialis) {
        if (isValidSpesialis(spesialis)) {
            this.spesialis = spesialis;
        }
    }

    /**
     * Method untuk memeriksa pasien (Dependency relationship)
     * Dokter bergantung pada objek Pasien untuk melakukan pemeriksaan
     */
    public void periksaPasien(Pasien pasien) {
        System.out.println("\n" + "🏥 HASIL PEMERIKSAAN".indent(5));
        System.out.println("=" .repeat(40));
        System.out.println("👨‍⚕️ Dokter: " + this.namaDokter);
        System.out.println("🩺 Spesialis: " + this.spesialis);
        System.out.println("👤 Pasien: " + pasien.getNama());
        System.out.println("🎂 Umur: " + pasien.getUmur() + " tahun (" + pasien.getKategoriUmur() + ")");
        System.out.println("🔍 Keluhan: " + pasien.getPenyakit());
        System.out.println("✅ Pemeriksaan selesai. Resep telah diberikan.");
        System.out.println("=" .repeat(40));
    }

    @Override
    public String toString() {
        return "Dokter{" +
                "namaDokter='" + namaDokter + '\'' +
                ", spesialis='" + spesialis + '\'' +
                '}';
    }
}