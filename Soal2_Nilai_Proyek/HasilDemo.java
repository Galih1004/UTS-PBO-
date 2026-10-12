import java.util.ArrayList;

public class HasilDemo {

    // method untuk mengisi nilai mahasiswa
    private static void isiNilai(Mahasiswa m, double proses, double produk, double presentasi) {
        m.setNilaiProses(proses);
        m.setNilaiProduk(produk);
        m.setNilaiPresentasi(presentasi);
    }

    public static void main(String[] args) {
        // instansiasi objek Mahasiswa
        Mahasiswa kimi = new Mahasiswa("2341720101", "Kimi");
        Mahasiswa hamilton = new Mahasiswa("2341720102", "Hamilton");
        Mahasiswa verstappen = new Mahasiswa("2341720103", "Verstappen");
        Mahasiswa sainz = new Mahasiswa("2341720104", "Sainz");
        Mahasiswa russell = new Mahasiswa("2341720105", "Russell");

        // mengisi nilai mahasiswa
        isiNilai(kimi, 80, 85, 90);     // lulus
        isiNilai(hamilton, 60, 62, 55);    // nilai akhir < 70
        isiNilai(verstappen, 100, 55, 100); // nilai akhir >= 70, produk < 60
        isiNilai(sainz, 75, 70, 80);    // lulus
        isiNilai(russell, 70, 70, 70);     // tepat di batas kelulusan

        // membuat daftar mahasiswa
        ArrayList<Mahasiswa> daftar = new ArrayList<Mahasiswa>();
        daftar.add(kimi);
        daftar.add(hamilton);
        daftar.add(verstappen);
        daftar.add(sainz);
        daftar.add(russell);

        // menampilkan info semua mahasiswa
        System.out.println("=== DATA SEMUA MAHASISWA ===");
        for (Mahasiswa m : daftar) {
            System.out.println(m.getInfo());
        }

        // menampilkan status kelulusan mahasiswa
        System.out.println("=== MAHASISWA LULUS ===");
        for (Mahasiswa m : daftar) {
            if (m.isLulus()) {
                System.out.println("- " + m.getNama() + " | nilai akhir " + m.hitungNilaiAkhir() + " | " + m.getStatusKelulusan());
            }
        }

        // menampilkan mahasiswa yang tidak lulus karena nilai akhir < 70
        System.out.println("\n=== GAGAL KARENA NILAI AKHIR < 70 ===");
        for (Mahasiswa m : daftar) {
            if (!m.isNilaiAkhirCukup()) {
                System.out.println("- " + m.getNama() + " | nilai akhir " + m.hitungNilaiAkhir()
                        + " | " + m.getStatusKelulusan());
            }
        }

        // menampilkan mahasiswa yang tidak lulus karena nilai produk < 60
        System.out.println("\n=== NILAI AKHIR >= 70 TETAPI NILAI PRODUK < 60 ===");
        for (Mahasiswa m : daftar) {
            if (m.isNilaiAkhirCukup() && !m.isNilaiProdukCukup()) {
                System.out.println("- " + m.getNama() + " | nilai akhir " + m.hitungNilaiAkhir()
                        + " | nilai produk " + m.getNilaiProduk() + " | " + m.getStatusKelulusan());
            }
        }

        // menampilkan mahasiswa yang tepat di batas kelulusan
        System.out.println("\n=== UJI NILAI DI LUAR RENTANG (Sainz) ===");
        System.out.println("Sebelum : proses=" + sainz.getNilaiProses() + ", produk=" + sainz.getNilaiProduk()
                + ", presentasi=" + sainz.getNilaiPresentasi() + ", akhir=" + sainz.hitungNilaiAkhir());

        System.out.println(sainz.setNilaiProses(120).getPesan());
        System.out.println(sainz.setNilaiProduk(-5).getPesan());
        System.out.println(sainz.setNilaiPresentasi(101).getPesan());

        System.out.println("Sesudah : proses=" + sainz.getNilaiProses() + ", produk=" + sainz.getNilaiProduk()
                + ", presentasi=" + sainz.getNilaiPresentasi() + ", akhir=" + sainz.hitungNilaiAkhir());
        System.out.println("Nilai lama tetap aman, status Sainz: " + sainz.getStatusKelulusan());
    }
}
