import java.util.ArrayList;

public class Peralatan {
    // Deklarasi Atribut 
    private String kode;
    private String nama;
    private int stokTersedia;
    private ArrayList<Peminjaman> daftarPeminjaman;


    // class Peminjaman
    public static class Peminjaman {
        private Mahasiswa peminjam;
        private int jumlahDipinjam;

        private Peminjaman(Mahasiswa peminjam, int jumlahDipinjam) {
            this.peminjam = peminjam;
            this.jumlahDipinjam = jumlahDipinjam;
        }
    }

    // constructor berparameter dari kelas Peralatan
    public Peralatan(String kode, String nama, int stokAwal) {
        this.kode = kode;
        this.nama = nama;
        this.stokTersedia = (stokAwal < 0) ? 0 : stokAwal;
        this.daftarPeminjaman = new ArrayList<Peminjaman>();
    }

    // getter kode
    public String getKode() {
        return kode;
    }

    // getter nama
    public String getNama() {
        return nama;
    }

    // getter stokTersedia
    public int getStokTersedia() {
        return stokTersedia;
    }

    // getter jumlahDipinjam
    public int getJumlahDipinjam(Mahasiswa mahasiswa) {
        Peminjaman p = cariPeminjaman(mahasiswa);
        return (p == null) ? 0 : p.jumlahDipinjam;
    }

    // method untuk mencari peminjaman berdasarkan mahasiswa
    public Peminjaman cariPeminjaman(Mahasiswa mahasiswa) {
        for (Peminjaman p : daftarPeminjaman) {
            if (p.peminjam.getNim().equals(mahasiswa.getNim())) {
                return p;
            }
        }
        return null;
    }

    // method pinjam peralatan
    public HasilTransaksi pinjam(Mahasiswa mahasiswa, int jumlah) {
        if (mahasiswa == null) {
            return new HasilTransaksi(false, "Peminjam tidak valid");
        }
        if (jumlah <= 0) {
            return new HasilTransaksi(false,
                    "Jumlah peminjaman harus lebih dari 0 (diminta: " + jumlah + ")");
        }
        if (jumlah > stokTersedia) {
            return new HasilTransaksi(false,
                    "Stok tidak mencukupi (diminta: " + jumlah + ", tersedia: " + stokTersedia + ")");
        }

        Peminjaman p = cariPeminjaman(mahasiswa);
        if (p == null) {
            daftarPeminjaman.add(new Peminjaman(mahasiswa, jumlah));
        } else {
            p.jumlahDipinjam += jumlah;
        }
        stokTersedia -= jumlah;

        return new HasilTransaksi(true, "Peminjaman diterima");
    }

    // method kembalikan peralatan
    public HasilTransaksi kembalikan(Mahasiswa mahasiswa, int jumlah) {
        if (mahasiswa == null) {
            return new HasilTransaksi(false, "Peminjam tidak valid");
        }
        if (jumlah <= 0) {
            return new HasilTransaksi(false,
                    "Jumlah pengembalian harus lebih dari 0 (dikembalikan: " + jumlah + ")");
        }

        Peminjaman p = cariPeminjaman(mahasiswa);
        int sedangDipinjam = (p == null) ? 0 : p.jumlahDipinjam;
        if (jumlah > sedangDipinjam) {
            return new HasilTransaksi(false, "Jumlah pengembalian (" + jumlah + ") melebihi jumlah yang sedang dipinjam ("+ sedangDipinjam + ")");
        }

        p.jumlahDipinjam -= jumlah;
        if (p.jumlahDipinjam == 0) {
            daftarPeminjaman.remove(p);
        }
        stokTersedia += jumlah;

        return new HasilTransaksi(true, "Pengembalian diterima");
    }
}
