public class Mahasiswa {
    // Deklarasi atribut
    private int BOBOT_PROSES = 30;
    private int BOBOT_PRODUK = 45;
    private int BOBOT_PRESENTASI = 25;
    private double NILAI_AKHIR_MINIMAL = 70;
    private double NILAI_PRODUK_MINIMAL = 60;
    private final String nim; // final untuk memastikan nim tidak bisa diubah
    private final String nama; // final untuk memastikan nama tidak bisa diubah
    private double nilaiProses;
    private double nilaiProduk;
    private double nilaiPresentasi;

    // constructor berparameter dari kelas Mahasiswa
    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    // getter nim
    public String getNim() {
        return nim;
    }

    // getter nama
    public String getNama() {
        return nama;
    }

    // getter nilaiProses
    public double getNilaiProses() {
        return nilaiProses;
    }

    // getter nilaiProduk
    public double getNilaiProduk() {
        return nilaiProduk;
    }

    // getter nilaiPresentasi
    public double getNilaiPresentasi() {
        return nilaiPresentasi;
    }

    // method untuk validasi nilai
    private HasilValidasi validasi(String komponen, double nilai) {
        if (Double.isNaN(nilai) || nilai < 0 || nilai > 100) {
            return new HasilValidasi(false,
                    "Nilai " + komponen + " " + nilai + " ditolak (harus 0-100), nilai lama tetap dipertahankan");
        }
        return new HasilValidasi(true, "Nilai " + komponen + " " + nilai + " diterima");
    }

    // method untuk set nilai proses
    public HasilValidasi setNilaiProses(double nilai) {
        HasilValidasi hasil = validasi("proses", nilai);
        if (hasil.isValid()) {
            this.nilaiProses = nilai;
        }
        return hasil;
    }

    // method untuk set nilai produk
    public HasilValidasi setNilaiProduk(double nilai) {
        HasilValidasi hasil = validasi("produk", nilai);
        if (hasil.isValid()) {
            this.nilaiProduk = nilai;
        }
        return hasil;
    }

    // method untuk set nilai presentasi
    public HasilValidasi setNilaiPresentasi(double nilai) {
        HasilValidasi hasil = validasi("presentasi", nilai);
        if (hasil.isValid()) {
            this.nilaiPresentasi = nilai;
        }
        return hasil;
    }

    // method hitung nilai akhir dibuat pembulatan 2 angka di belakang koma
    public double hitungNilaiAkhir() {
        double total = (nilaiProses * BOBOT_PROSES+ nilaiProduk * BOBOT_PRODUK+ nilaiPresentasi * BOBOT_PRESENTASI) / 100.0;
        return Math.round(total * 100) / 100.0;
    }

    // method untuk mengecek apakah nilai akhir cukup
    public boolean isNilaiAkhirCukup() {
        return hitungNilaiAkhir() >= NILAI_AKHIR_MINIMAL;
    }

    // method untuk mengecek apakah nilai produk cukup
    public boolean isNilaiProdukCukup() {
        return nilaiProduk >= NILAI_PRODUK_MINIMAL;
    }

    // method untuk mengecek apakah mahasiswa lulus
    public boolean isLulus() {
        return isNilaiAkhirCukup() && isNilaiProdukCukup();
    }

    // method untuk mendapatkan status kelulusan mahasiswa
    public String getStatusKelulusan() {
        if (isLulus()) {
            return "LULUS";
        }
        String alasan = "";
        if (!isNilaiAkhirCukup()) {
            alasan += "nilai akhir < " + (int) NILAI_AKHIR_MINIMAL;
        }
        if (!isNilaiProdukCukup()) {
            if (!alasan.isEmpty()) {
                alasan += ", ";
            }
            alasan += "nilai produk < " + (int) NILAI_PRODUK_MINIMAL;
        }
        return "TIDAK LULUS (" + alasan + ")";
    }

    // method untuk menampilkan info mahasiswa
    public String getInfo() {
        String info = "";
        info += "NIM / Nama      : " + nim + " / " + nama + "\n";
        info += "Nilai Proses    : " + nilaiProses + "\n";
        info += "Nilai Produk    : " + nilaiProduk + "\n";
        info += "Nilai Presentasi: " + nilaiPresentasi + "\n";
        info += "Nilai Akhir     : " + hitungNilaiAkhir() + "\n";
        info += "Status          : " + getStatusKelulusan() + "\n";

        return info;
    }
}
