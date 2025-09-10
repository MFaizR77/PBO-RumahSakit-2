import id.ac.polban.model.*;
import id.ac.polban.service.*;
import java.util.Scanner;

/**
 * Aplikasi Sistem Rumah Sakit
 *
 * Relasi antar kelas:
 * 1. Dependency: 
 *    - Dokter -> Pasien (dalam method periksaPasien)
 *    - Pembayaran -> Pasien & Dokter (dalam method bayar)
 *    - RumahSakit -> semua model classes
 *
 * 2. Aggregation:
 *    - RumahSakitService -> Dokter (memiliki List<Dokter>)
 */
public class RumahSakit {

    public static void main(String[] args) {
        // Banner aplikasi
        tampilkanBanner();

        Scanner sc = new Scanner(System.in);

        // Inisialisasi service (menggunakan Aggregation)
        RumahSakitService rumahSakitService = new RumahSakitService();

        boolean jalan = true;

        while (jalan) {
            tampilkanMenuUtama();

            // Input data pasien menggunakan InputValidatorService (static methods)
            String nama = InputValidatorService.inputNama(sc, "👤 Nama Lengkap Pasien : ");
            String penyakit = InputValidatorService.inputKeluhan(sc, "🩺 Keluhan/Penyakit     : ");
            int umur = InputValidatorService.inputInteger(sc, "🎂 Umur Pasien          : ", 1, 120);

            // Buat objek pasien
            Pasien pasien = new Pasien(nama, penyakit, umur);

            // Tampilkan informasi biaya
            int biaya = RumahSakitService.getBiayaByKeluhan(penyakit);
            System.out.println("\n💳 INFORMASI BIAYA");
            System.out.println("=".repeat(25));
            System.out.printf("💰 Total Biaya: Rp %,d\n", biaya);

            // Konfirmasi pembayaran
            boolean konfirmasiBayar = InputValidatorService.inputKonfirmasi(sc, "Konfirmasi pembayaran?");

            // Proses pasien menggunakan service
            rumahSakitService.prosesPasien(pasien, konfirmasiBayar);

            // Tampilkan statistik
            rumahSakitService.tampilkanStatistik();

            // Tanya mau input lagi atau tidak
            jalan = InputValidatorService.inputKonfirmasi(sc, "Apakah mau input pasien lagi?");
        }

        // Tampilkan statistik akhir
        System.out.println("\n🏥 STATISTIK AKHIR HARI");
        System.out.println("=".repeat(40));
        rumahSakitService.tampilkanStatistik();

        System.out.println("\nTerima kasih telah menggunakan sistem Rumah Sakit ABC.");
        System.out.println("Semoga hari Anda menyenangkan! 😊");
        sc.close();
    }

    /**
     * Static method untuk menampilkan banner aplikasi
     */
    private static void tampilkanBanner() {
        System.out.println("🏥 SELAMAT DATANG DI RUMAH SAKIT ABC 🏥");
        System.out.println("=".repeat(50));
        System.out.println("   Melayani dengan Sepenuh Hati ❤️");
        System.out.println("      Kesehatan Anda Prioritas Kami");
        System.out.println("=".repeat(50));
    }

    /**
     * Static method untuk menampilkan menu utama
     */
    private static void tampilkanMenuUtama() {
        System.out.println("\n📋 PENDAFTARAN PASIEN BARU");
        System.out.println("=".repeat(30));
        System.out.println("🔹 Dokter Umum     : Rp 50,000");
        System.out.println("🔹 Dokter THT      : Rp 75,000");
        System.out.println("🔹 Dokter Dalam    : Rp 100,000");
        System.out.println("=".repeat(30));
    }
}