public class HasilTransaksi {
    // Deklarasi atribut
    private boolean berhasil;
    private String pesan;

    // constructor berparameter dari kelas HasilTransaksi
    public HasilTransaksi(boolean berhasil, String pesan) {
        this.berhasil = berhasil;
        this.pesan = pesan;
    }

    // getter berhasil
    public boolean isBerhasil() {
        return berhasil;
    }

    // getter pesan
    public String getPesan() {
        return pesan;
    }
}
