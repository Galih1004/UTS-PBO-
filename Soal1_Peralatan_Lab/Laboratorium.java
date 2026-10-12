import java.util.ArrayList;

public class Laboratorium {
    // Deklarasi private atribut
    private String nama;
    private ArrayList<Peralatan> daftarPeralatan;

    // constructor berparameter dari kelas Laboratorium
    public Laboratorium(String nama) {
        this.nama = nama;
        this.daftarPeralatan = new ArrayList<Peralatan>();
    }

    // getter nama
    public String getNama() {
        return nama;
    }

    // method untuk menambahkan peralatan ke daftarPeralatan
    public void tambahPeralatan(Peralatan peralatan) {
        if (peralatan != null && cariPeralatan(peralatan.getKode()) == null) {
            daftarPeralatan.add(peralatan);
        }
    }

    // method mencari peralatan
    public Peralatan cariPeralatan(String kode) {
        for (Peralatan p : daftarPeralatan) {
            if (p.getKode().equals(kode)) {
                return p;
            }
        }
        return null;
    }

    // method pinjam peralatan
    public HasilTransaksi pinjam(Mahasiswa mahasiswa, String kodePeralatan, int jumlah) {
        Peralatan peralatan = cariPeralatan(kodePeralatan);
        if (peralatan == null) {
            return new HasilTransaksi(false, "Peralatan dengan kode " + kodePeralatan + " tidak ditemukan");
        }
        return peralatan.pinjam(mahasiswa, jumlah);
    }

    // method kembalikan peralatan
    public HasilTransaksi kembalikan(Mahasiswa mahasiswa, String kodePeralatan, int jumlah) {
        Peralatan peralatan = cariPeralatan(kodePeralatan);
        if (peralatan == null) {
            return new HasilTransaksi(false, "Peralatan dengan kode " + kodePeralatan + " tidak ditemukan");
        }
        return peralatan.kembalikan(mahasiswa, jumlah);
    }

    // method untuk menampilkan info stok peralatan
    public String getInfoStok() {
        String info = "";
        for (Peralatan p : daftarPeralatan) {
            info += String.format("  %-6s %-14s stok tersedia: %d\n", p.getKode(), p.getNama(), p.getStokTersedia());
        }
        return info;
    }
}
