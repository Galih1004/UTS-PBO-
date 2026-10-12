public class HasilValidasi {
    // Deklarasi atribut
    private boolean valid;
    private String pesan;

    // constructor berparameter dari kelas HasilValidasi
    public HasilValidasi(boolean valid, String pesan) {
        this.valid = valid;
        this.pesan = pesan;
    }

    // getter valid
    public boolean isValid() {
        return valid;
    }

    // getter pesan
    public String getPesan() {
        return pesan;
    }
}
