public class Pinjaman {
    private int jumlahPinjaman;
    private int jangkaWaktuPinjaman;
    private double bungaPinjaman;

    public void catatPembayaran(double jumlahPembayaran) {
        System.out.println("Pembayaran: $" + jumlahPembayaran);
    }

    public Pinjaman(int jumlahPinjaman, int jangkaWaktuPinjaman, double bungaPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
        this.jangkaWaktuPinjaman = jangkaWaktuPinjaman;
        this.bungaPinjaman = bungaPinjaman;
    }

    public int getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(int jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }

    public int getJangkaWaktuPinjaman() {
        return jangkaWaktuPinjaman;
    }

    public void setJangkaWaktuPinjaman(int jangkaWaktuPinjaman) {
        this.jangkaWaktuPinjaman = jangkaWaktuPinjaman;
    }

    public double getBungaPinjaman() {
        return bungaPinjaman;
    }

    public void setBungaPinjaman(double bungaPinjaman) {
        this.bungaPinjaman = bungaPinjaman;
    }
}
