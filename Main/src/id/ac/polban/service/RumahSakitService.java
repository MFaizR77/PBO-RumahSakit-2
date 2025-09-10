package id.ac.polban.service;

import id.ac.polban.model.Dokter;
import id.ac.polban.model.Pasien;
import id.ac.polban.model.Pembayaran;
import java.util.ArrayList;
import java.util.List;

public class RumahSakitService {
    // Aggregation: RumahSakitService memiliki koleksi Dokter
    private List<Dokter> daftarDokter;

    // Static constants untuk biaya
    private static final int BIAYA_UMUM = 50_000;
    private static final int BIAYA_THT = 75_000;
    private static final int BIAYA_DALAM = 100_000;

    public RumahSakitService() {
        // Inisialisasi agregasi dokter
        this.daftarDokter = new ArrayList<>();
        initializeDokter();
    }

    /**
     * Method untuk inisialisasi dokter (Aggregation)
     * Dokter-dokter ini dapat exist independent dari RumahSakitService
     */
    private void initializeDokter() {
        daftarDokter.add(new Dokter("Dr. Andi", "Umum"));
        daftarDokter.add(new Dokter("Dr. Siti", "THT"));
        daftarDokter.add(new Dokter("Dr. Budi", "Penyakit Dalam"));
    }

    // Static method untuk validasi keluhan umum
    public static boolean termasukKeluhanUmum(String penyakit) {
        String[] keluhanUmum = {"flu", "demam", "batuk", "pilek", "sakit kepala", "mual", "sakit perut", "gejala ringan"};
        for (String keluhan : keluhanUmum) {
            if (keluhan.equalsIgnoreCase(penyakit)) {
                return true;
            }
        }
        return false;
    }

    // Static method untuk validasi keluhan THT
    public static boolean termasukKeluhanTHT(String penyakit) {
        String[] keluhanTHT = {"telinga", "hidung", "tenggorokan", "tht", "sinusitis", "amandel", "radang", "suara serak"};
        for (String keluhan : keluhanTHT) {
            if (keluhan.equalsIgnoreCase(penyakit)) {
                return true;
            }
        }
        return false;
    }

    // Static method untuk mendapatkan biaya berdasarkan jenis keluhan
    public static int getBiayaByKeluhan(String penyakit) {
        if (termasukKeluhanUmum(penyakit)) {
            return BIAYA_UMUM;
        } else if (termasukKeluhanTHT(penyakit)) {
            return BIAYA_THT;
        } else {
            return BIAYA_DALAM;
        }
    }

    /**
     * Method untuk mendapatkan dokter berdasarkan keluhan (Aggregation)
     * Menggunakan dokter dari koleksi yang dimiliki RumahSakitService
     */
    public Dokter getDokterByKeluhan(String penyakit) {
        if (termasukKeluhanUmum(penyakit)) {
            return getDokterBySpesialis("Umum");
        } else if (termasukKeluhanTHT(penyakit)) {
            return getDokterBySpesialis("THT");
        } else {
            return getDokterBySpesialis("Penyakit Dalam");
        }
    }

    /**
     * Method untuk mendapatkan dokter berdasarkan spesialis
     */
    private Dokter getDokterBySpesialis(String spesialis) {
        for (Dokter dokter : daftarDokter) {
            if (dokter.getSpesialis().equals(spesialis)) {
                return dokter;
            }
        }
        return null; // Tidak seharusnya terjadi dengan data yang sudah diinisialisasi
    }

    /**
     * Method untuk memproses pasien (menggunakan Dependency dan Aggregation)
     */
    public void prosesPasien(Pasien pasien, boolean konfirmasiBayar) {
        // Menggunakan aggregation untuk mendapatkan dokter
        Dokter dokterTujuan = getDokterByKeluhan(pasien.getPenyakit());
        int biaya = getBiayaByKeluhan(pasien.getPenyakit());

        // Dependency: menggunakan objek dokter untuk pemeriksaan
        dokterTujuan.periksaPasien(pasien);

        // Proses pembayaran jika dikonfirmasi
        if (konfirmasiBayar) {
            Pembayaran pembayaran = new Pembayaran(biaya);
            System.out.println("✅ Pembayaran berhasil. Terima kasih!");
            // Dependency: pembayaran bergantung pada pasien dan dokter
            pembayaran.bayar(pasien, dokterTujuan);
        } else {
            System.out.println("❌ Pembayaran dibatalkan.");
        }
    }

    // Getter untuk daftar dokter (Aggregation)
    public List<Dokter> getDaftarDokter() {
        return new ArrayList<>(daftarDokter); // Return copy untuk encapsulation
    }

    // Method untuk menambah dokter baru (Aggregation)
    public void tambahDokter(Dokter dokter) {
        if (dokter != null && !daftarDokter.contains(dokter)) {
            daftarDokter.add(dokter);
        }
    }

    // Method untuk menampilkan statistik
    public void tampilkanStatistik() {
        System.out.println("\n📊 STATISTIK RUMAH SAKIT");
        System.out.println("=".repeat(30));
        System.out.println("Total Dokter: " + Dokter.getTotalDokter());
        System.out.println("Total Pasien: " + Pasien.getTotalPasien());
        System.out.println("Total Transaksi: " + Pembayaran.getTotalTransaksi());
        System.out.println("Total Pendapatan: Rp" + String.format("%,d", Pembayaran.getTotalPendapatan()));
        System.out.println("=".repeat(30));
    }
}