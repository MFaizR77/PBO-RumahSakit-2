package id.ac.polban.model;

public class Pembayaran {
    private int biaya;
    private static int totalTransaksi = 0; // Static variable untuk menghitung total transaksi
    private static int totalPendapatan = 0; // Static variable untuk total pendapatan

    public Pembayaran(int biaya) {
        this.biaya = biaya;
    }

    // Static method untuk mendapatkan total transaksi
    public static int getTotalTransaksi() {
        return totalTransaksi;
    }

    // Static method untuk mendapatkan total pendapatan
    public static int getTotalPendapatan() {
        return totalPendapatan;
    }

    // Static method untuk reset statistik
    public static void resetStatistik() {
        totalTransaksi = 0;
        totalPendapatan = 0;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

    /**
     * Method untuk memproses pembayaran (Dependency relationship)
     * Pembayaran bergantung pada objek Pasien dan Dokter
     */
    public void bayar(Pasien pasien, Dokter dokter) {
        totalTransaksi++; // Increment static variable
        totalPendapatan += biaya; // Tambah ke total pendapatan

        System.out.println("Pasien " + pasien.getNama() + " membayar biaya sebesar Rp" + String.format("%,d", biaya));
        cetakStruk(pasien, dokter);
    }

    /**
     * Method private untuk mencetak struk pembayaran
     */
    private void cetakStruk(Pasien pasien, Dokter dokter) {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           STRUK PEMBAYARAN");
        System.out.println("           RUMAH SAKIT ABC");
        System.out.println("=".repeat(40));
        System.out.println("-".repeat(40));
        System.out.println("Nama Pasien    : " + pasien.getNama());
        System.out.println("Umur Pasien    : " + pasien.getUmur() + " tahun");
        System.out.println("Kategori Pasien: " + pasien.getKategoriUmur());
        System.out.println("Penyakit       : " + pasien.getPenyakit());
        System.out.println("Dokter         : " + dokter.getNamaDokter());
        System.out.println("Spesialis      : " + dokter.getSpesialis());
        System.out.println("-".repeat(40));
        System.out.println("Biaya Konsultasi : Rp" + String.format("%,d", biaya));
        System.out.println("Status          : LUNAS");
        System.out.println("No. Transaksi   : " + totalTransaksi);
        System.out.println("=".repeat(40));
        System.out.println("    Terima kasih atas kunjungan Anda");
        System.out.println("         Semoga lekas sembuh!");
        System.out.println("=".repeat(40));
    }

    @Override
    public String toString() {
        return "Pembayaran{" +
                "biaya=" + biaya +
                '}';
    }
}