import java.util.ArrayList;
import java.util.Scanner;

public class Pengguna {
  ArrayList<String> usernames = new ArrayList<>();
  ArrayList<String> passwords = new ArrayList<>();
  ArrayList<String> roles = new ArrayList<>();
  ArrayList<Integer> ids = new ArrayList<>();
  ArrayList<String> emails = new ArrayList<>();
  ArrayList<String> nomorTelepons = new ArrayList<>();
  ArrayList<String> niks = new ArrayList<>();
  Integer penggunaSekarang;
  String menu = null;

  public Pengguna() {
    usernames.add("admin");
    passwords.add("admin");
    roles.add("admin");
    emails.add("admin@example.com");
    nomorTelepons.add("08123456789");
    niks.add("1234567890");
    ids.add(usernames.size());
  }

  public Integer Menu() {
    Scanner input = new Scanner(System.in);
    try {
      System.out.println("====================================");
      System.out.println("Selamat datang di aplikasi kami");
      System.out.println("");
      System.out.println("1. Masuk");
      System.out.println("2. Daftar");
      System.out.println("3. Keluar");
      System.out.println("");
      menu = input.nextLine();
      switch (menu) {
        case "1":
          System.out.println("Masuk");
          System.out.print("Nama Pengguna: ");
          String username = input.nextLine();
          System.out.print("Kata Sandi: ");
          String password = input.nextLine();
          Integer hasilMasuk = masuk(username, password);
          if (hasilMasuk == 1) { // Admin
            return 2;
          } else if (hasilMasuk == 2) { // Pengguna
            return 3;
          } else {
            System.out.println("Gagal masuk.");
            break;
          }
        case "2":
          System.out.println("Daftar");
          System.out.print("NIK: ");
          String nik = input.nextLine();
          System.out.print("Nama Pengguna: ");
          String usernameBaru = input.nextLine();
          System.out.print("Email: ");
          String email = input.nextLine();
          System.out.print("Nomor Telepon: ");
          String nomorTelepon = input.nextLine();
          System.out.print("Kata Sandi: ");
          String passwordBaru = input.nextLine();
          daftar(nik, usernameBaru, email, nomorTelepon, passwordBaru, "pengguna");
          break;
        case "3":
          System.out.println("Keluar");
          return 0;
        default:
          System.out.println("Masukan tidak valid");
      }
      return 1;
    } finally {
      input.close();
    }
  }

  void daftar(String nik, String username, String email, String nomorTelepon, String password, String role) {
    if (usernames.contains(username)) {
      System.out.println("Nama pengguna sudah ada.");
    } else {
      usernames.add(username);
      passwords.add(password);
      roles.add(role);
      emails.add(email);
      nomorTelepons.add(nomorTelepon);
      niks.add(nik);
      ids.add(usernames.size());
      System.out.println("Pendaftaran berhasil.");
    }
  }

  Integer masuk(String username, String password) {
    if (usernames.contains(username) && passwords.contains(password)) {
      int index = usernames.indexOf(username);
      String role = roles.get(index);
      int id = ids.get(index);
      penggunaSekarang = id;
      if (role.equals("admin")) { // Admin
        System.out.println("Berhasil masuk sebagai admin.");
        return 1;
      } else { // Pengguna
        System.out.println("Berhasil masuk sebagai pengguna.");
        return 2;
      }
    } else {
      System.out.println("Nama pengguna atau kata sandi salah.");
    }
    return 0;
  }
}

class Admin extends Pengguna {
  @Override
  public Integer Menu() {
    System.out.println("Menu Admin");
    System.out.println("1. Tambah Pengguna");
    System.out.println("2. Hapus Pengguna");
    System.out.println("3. Lihat Pengguna");
    System.out.println("4. Keluar");
    Scanner input = new Scanner(System.in);
    try {
      super.menu = input.nextLine();
      switch (super.menu) {
        case "1":
          System.out.println("Tambah Pengguna");
          break;
        case "2":
          System.out.println("Hapus Pengguna");
          break;
        case "3":
          System.out.println("Lihat Pengguna");
          break;
        case "4":
          System.out.println("Keluar");
          return 1;
        default:
          System.out.println("Masukan tidak valid");
      }
      return 0;
    } finally {
      input.close();
    }
  }
}

public class User extends Pengguna {
  @Override
  public Integer Menu() {
    Scanner input = new Scanner(System.in);
    try {
      System.out.println("====================================");
      System.out.println("Menu Pengguna");
      System.out.println("1. Lihat Profil");
      System.out.println("2. Ajukan Pinjaman");
      System.out.println("3. Lihat Status Pengajuan");
      System.out.println("4. Edit Pengajuan");
      System.out.println("5. Batalkan Pengajuan");
      System.out.println("6. Catat Pembayaran");
      System.out.println("7. Keluar");
      System.out.println("");
      super.menu = input.nextLine();
      switch (super.menu) {
        case "1":
          System.out.println("Lihat Profil");
          if (penggunaSekarang != null) {
            int index = ids.indexOf(penggunaSekarang);
            System.out.println("NIK: " + niks.get(index));
            System.out.println("Nama Pengguna: " + usernames.get(index));
            System.out.println("Email: " + emails.get(index));
            System.out.println("Nomor Telepon: " + nomorTelepons.get(index));
            System.out.println("Peran: " + roles.get(index));
            System.out.println("ID: " + ids.get(index));
          } else {
            System.out.println("Tidak ada pengguna yang sedang masuk.");
          }
          break;
        case "2":
          System.out.println("Ajukan Pinjaman");
          System.out.print("Jumlah Pinjaman: ");
          double jumlah = Double.parseDouble(input.nextLine());
          System.out.println("Pilih jangka waktu (1: 3 bulan, 2: 6 bulan, 3: 1 tahun): ");
          int jangkaWaktu = Integer.parseInt(input.nextLine());
          String periode = "";
          double bunga = 0.0;
          switch (jangkaWaktu) {
            case 1: periode = "3 bulan"; bunga = 0.05; break;
            case 2: periode = "6 bulan"; bunga = 0.10; break;
            case 3: periode = "1 tahun"; bunga = 0.15; break;
            default: System.out.println("Jangka waktu tidak valid."); break;
          }
          double total = jumlah + (jumlah * bunga);
          System.out.println("Alasan Pinjaman: ");
          String alasan = input.nextLine();
          System.out.println("Total dengan bunga (" + (bunga * 100) + "%): " + total);
          System.out.println("Pengajuan berhasil diajukan. Status: Menunggu");
          break;
        case "3":
          System.out.println("Lihat Status Pengajuan");
          System.out.println("Status: Menunggu (Contoh, perlu implementasi data pengajuan)");
          break;
        case "4":
          System.out.println("Edit Pengajuan");
          if (penggunaSekarang != null) {
            System.out.println("Masukkan jumlah baru: ");
            double jumlahBaru = Double.parseDouble(input.nextLine());
            System.out.println("Pilih jangka waktu baru (1: 3 bulan, 2: 6 bulan, 3: 1 tahun): ");
            int jangkaWaktuBaru = Integer.parseInt(input.nextLine());
            System.out.println("Pengajuan diperbarui. Status: Menunggu");
          } else {
            System.out.println("Masuk terlebih dahulu.");
          }
          break;
        case "5":
          System.out.println("Batalkan Pengajuan");
          System.out.println("Pengajuan dibatalkan.");
          break;
        case "6":
          System.out.println("Catat Pembayaran");
          System.out.print("Jumlah Pembayaran: ");
          double pembayaran = Double.parseDouble(input.nextLine());
          System.out.println("Pembayaran sebesar " + pembayaran + " berhasil dicatat. Status: Diperbarui");
          break;
        case "7":
          System.out.println("Keluar");
          penggunaSekarang = null;
          return 1;
        default:
          System.out.println("Masukan tidak valid");
      }
      return 0;
    } finally {
      input.close();
    }
  }
}