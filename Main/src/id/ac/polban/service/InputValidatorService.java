package id.ac.polban.service;

import java.util.Scanner;

public class InputValidatorService {

    // Private constructor untuk mencegah instantiation (Utility class)
    private InputValidatorService() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Static method untuk input string dengan validasi
     */
    public static String inputString(Scanner sc, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("❌ Input tidak boleh kosong. Silakan isi kembali.");
            }
        } while (input.isEmpty());
        return input;
    }

    /**
     * Static method untuk input integer dengan validasi range
     */
    public static int inputInteger(Scanner sc, String prompt, int min, int max) {
        int nilai = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print(prompt);
                nilai = Integer.parseInt(sc.nextLine().trim());
                if (!isValidUmur(nilai, min, max)) {
                    System.out.printf("❌ Nilai harus antara %d dan %d.\n", min, max);
                } else {
                    valid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Input tidak valid. Harap masukkan angka.");
            }
        }
        return nilai;
    }

    /**
     * Static method untuk konfirmasi yes/no
     */
    public static boolean inputKonfirmasi(Scanner sc, String prompt) {
        String input;
        do {
            System.out.print(prompt + " (y/n): ");
            input = sc.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("❌ Input tidak valid. Masukkan 'y' untuk ya atau 'n' untuk tidak.");
            }
        } while (true);
    }

    /**
     * Static method untuk validasi nama (minimal 2 karakter, hanya huruf dan spasi)
     */
    public static boolean isValidNama(String nama) {
        return nama != null &&
                nama.length() >= 2 &&
                nama.matches("[a-zA-Z\\s]+");
    }

    /**
     * Static method untuk validasi umur
     */
    public static boolean isValidUmur(int nilai, int min, int max){
        return nilai > min && nilai < max;
    }

    /**
     * Static method untuk validasi keluhan (minimal 3 karakter)
     */
    public static boolean isValidKeluhan(String keluhan) {
        return keluhan != null &&
                keluhan.trim().length() >= 3;
    }

    /**
     * Static method untuk input nama dengan validasi khusus
     */
    public static String inputNama(Scanner sc, String prompt) {
        String nama;
        do {
            nama = inputString(sc, prompt);
            if (!isValidNama(nama)) {
                System.out.println("❌ Nama harus minimal 2 karakter dan hanya boleh huruf dan spasi.");
            }
        } while (!isValidNama(nama));
        return nama;
    }

    /**
     * Static method untuk input keluhan dengan validasi khusus
     */
    public static String inputKeluhan(Scanner sc, String prompt) {
        String keluhan;
        do {
            keluhan = inputString(sc, prompt);
            if (!isValidKeluhan(keluhan)) {
                System.out.println("❌ Keluhan harus minimal 3 karakter.");
            }
        } while (!isValidKeluhan(keluhan));
        return keluhan;
    }
}