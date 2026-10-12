public class PeminjamanDemo {

    public static void cetakHasil(int no, String deskripsi, HasilTransaksi hasil, Peralatan alat, int stokSebelum) {
        System.out.println(no + ". " + deskripsi);
        System.out.println("   Status : " + (hasil.isBerhasil() ? "BERHASIL" : "DITOLAK"));
        System.out.println("   Pesan  : " + hasil.getPesan());
        System.out.println("   Stok " + alat.getNama() + ": " + stokSebelum + " -> " + alat.getStokTersedia());
        System.out.println();
    }

    public static void main(String[] args) {
        Laboratorium lab = new Laboratorium("Laboratorium Media Bersama");
        lab.tambahPeralatan(new Peralatan("SKP", "Stetoskop", 5));
        lab.tambahPeralatan(new Peralatan("GLP", "Gelas Percobaan", 10));
        lab.tambahPeralatan(new Peralatan("PSU", "Power Supply", 3));

        Mahasiswa galih = new Mahasiswa("2341720001", "Galih");
        Mahasiswa leclerc = new Mahasiswa("2341720002", "Leclerc");

        Peralatan Stetoskop = lab.cariPeralatan("SKP");
        Peralatan GelasPercobaan = lab.cariPeralatan("GLP");
        Peralatan PowerSupply = lab.cariPeralatan("PSU");

        System.out.println("=== " + lab.getNama() + " ===");
        System.out.println("DATA AWAL");
        System.out.print(lab.getInfoStok());
        System.out.println();

        System.out.println("HASIL TRANSAKSI");
        int sebelum;
        HasilTransaksi hasil;

        // 1. Peminjaman berhasil (stok cukup)
        sebelum = Stetoskop.getStokTersedia();
        hasil = lab.pinjam(galih, "SKP", 2);
        cetakHasil(1, "Galih meminjam Stetoskop x2", hasil, Stetoskop, sebelum);

        // 2. Peminjaman ditolak (stok tidak cukup)
        sebelum = PowerSupply.getStokTersedia();
        hasil = lab.pinjam(leclerc, "PSU", 5);
        cetakHasil(2, "Leclerc meminjam Power Supply x5", hasil, PowerSupply, sebelum);

        // 3. Jumlah nol ditolak
        sebelum = GelasPercobaan.getStokTersedia();
        hasil = lab.pinjam(leclerc, "GLP", 0);
        cetakHasil(3, "Leclerc meminjam Gelas Percobaan x0", hasil, GelasPercobaan, sebelum);

        // 4. Jumlah negatif ditolak
        sebelum = GelasPercobaan.getStokTersedia();
        hasil = lab.pinjam(leclerc, "GLP", -3);
        cetakHasil(4, "Leclerc meminjam Gelas Percobaan x(-3)", hasil, GelasPercobaan, sebelum);

        // 5. Pengembalian berhasil menambah stok
        sebelum = Stetoskop.getStokTersedia();
        hasil = lab.kembalikan(galih, "SKP", 1);
        cetakHasil(5, "Galih mengembalikan Stetoskop x1", hasil, Stetoskop, sebelum);

        // 6. Pengembalian melebihi jumlah pinjaman ditolak (Saya masih meminjam 1)
        sebelum = Stetoskop.getStokTersedia();
        hasil = lab.kembalikan(galih, "SKP", 5);
        cetakHasil(6, "Galih mengembalikan Stetoskop x5 (sedang dipinjam: "+ Stetoskop.getJumlahDipinjam(galih) + ")", hasil, Stetoskop, sebelum);

        // 7. mahasiswa yang tidak meminjam mengembalikan alat
        sebelum = Stetoskop.getStokTersedia();
        hasil = lab.kembalikan(leclerc, "SKP", 1);
        cetakHasil(7, "Leclerc mengembalikan Stetoskop x1 (tidak pernah meminjam)", hasil, Stetoskop, sebelum);

        // 8. jika kode peralatan tidak ditemukan
        hasil = lab.pinjam(leclerc, "XYZ", 1);
        System.out.println("8. Leclerc meminjam peralatan kode XYZ");
        System.out.println("   Status : " + (hasil.isBerhasil() ? "BERHASIL" : "DITOLAK"));
        System.out.println("   Pesan  : " + hasil.getPesan());
        System.out.println();

        System.out.println("STOK AKHIR");
        System.out.print(lab.getInfoStok());
    }
}
