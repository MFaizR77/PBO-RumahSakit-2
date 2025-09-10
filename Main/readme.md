# Rumah Sakit ABC - Sistem Pendaftaran Pasien Sederhana

Program sederhana berbasis Java yang mensimulasikan proses pendaftaran pasien di rumah sakit. Program ini menentukan dokter yang menangani berdasarkan keluhan pasien, serta melakukan simulasi pembayaran.

---

## 🎯 Fitur Utama
- Input data pasien: nama, keluhan, dan umur
- Penentuan dokter berdasarkan jenis penyakit
- Simulasi pemeriksaan oleh dokter
- Pembayaran dengan konfirmasi
- Bisa mendaftarkan lebih dari satu pasien

---

## 🩺 Spesialisasi Dokter
| Dokter | Spesialisasi | Biaya |
|--------|--------------|-------|
| Dr. Andi | Umum | Rp 50.000 |
| Dr. Siti | THT | Rp 75.000 |
| Dr. Budi | Penyakit Dalam | Rp 100.000 |

---

## 📦 Struktur Kelas
- `RumahSakit.java` – Program utama (main)
- `Pasien.java` – Menyimpan data pasien
- `Dokter.java` – Menangani pemeriksaan pasien
- `Pembayaran.java` – Menangani proses pembayaran

---

## 🖥 Cara Menjalankan
1. Pastikan Java (versi 8 atau lebih tinggi) sudah terinstall.
2. Kompilasi semua file:
   ```bash
   javac *.java