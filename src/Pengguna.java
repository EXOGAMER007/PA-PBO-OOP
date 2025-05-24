import java.util.ArrayList;
import java.util.Scanner;

public class Pengguna {
  protected ArrayList<String> usernames = new ArrayList<>();
  protected ArrayList<String> passwords = new ArrayList<>();
  protected ArrayList<String> roles = new ArrayList<>();
  protected ArrayList<Integer> ids = new ArrayList<>();
  protected ArrayList<String> emails = new ArrayList<>();
  protected ArrayList<String> nomorTelepons = new ArrayList<>();
  protected ArrayList<String> niks = new ArrayList<>();
  protected Scanner input = new Scanner(System.in);
  protected ArrayList<Pinjaman> pinjaman = new ArrayList<>();
  protected Integer penggunaSekarang;
  protected String menu = null;

  public ArrayList<String> getUsernames() {
    return usernames;
  }

  public ArrayList<String> getPasswords() {
    return passwords;
  }

  public ArrayList<String> getRoles() {
    return roles;
  }

  public ArrayList<Integer> getIds() {
    return ids;
  }

  public ArrayList<String> getEmails() {
    return emails;
  }

  public ArrayList<String> getNomorTelepons() {
    return nomorTelepons;
  }

  public ArrayList<String> getNiks() {
    return niks;
  }

  public ArrayList<Pinjaman> getPinjaman() {
    return pinjaman;
  }

  public Integer getPenggunaSekarang() {
    return penggunaSekarang;
  }

  public Pengguna() {
    usernames.add("admin");
    passwords.add("admin");
    roles.add("admin");
    emails.add("admin@example.com");
    nomorTelepons.add("08123456789");
    niks.add("1234567890");
    ids.add(usernames.size());

    // Default user
    usernames.add("daffa");
    passwords.add("daffa");
    roles.add("peminjam");
    emails.add("daffa@example.com");
    nomorTelepons.add("08223456789");
    niks.add("1234567892");
    ids.add(usernames.size());
  }

  public Integer Menu() {
    try {
      System.out.println("====================================");
      System.out.println("Selamat datang di aplikasi kami");
      System.out.println("");
      System.out.println("1. login");
      System.out.println("2. register");
      System.out.println("3. Keluar");
      System.out.println("");
      menu = input.nextLine();
      switch (menu) {
        case "1":
          System.out.println("login");
          System.out.print("Nama Pengguna: ");
          String username = input.nextLine();
          System.out.print("Kata Sandi: ");
          String password = input.nextLine();
          Integer hasillogin = login(username, password);
          if (hasillogin == 1) { // Admin
            return 2;
          } else if (hasillogin == 2) { // Pengguna
            return 3;
          } else {
            System.out.println("Gagal login.");
            break;
          }
        case "2":
          System.out.println("register");
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
          register(nik, usernameBaru, email, nomorTelepon, passwordBaru, "pengguna");
          break;
        case "3":
          System.out.println("Keluar");
          return 0;
        default:
          System.out.println("loginan tidak valid");
      }
      return 1;
    } finally {
      // input.close();
    }
  }

  public void register(String nik, String username, String email, String nomorTelepon, String password, String role) {
    if (usernames.contains(username)) {
      System.out.println("Username already exists.");
    } else {
      usernames.add(username);
      passwords.add(password);
      roles.add(role);
      ids.add(usernames.size());
      emails.add(email);
      nomorTelepons.add(nomorTelepon);
      niks.add(nik);
      System.out.println("Registration successful.");
    }
  }

  public Integer login(String username, String password) {
    if (usernames.contains(username) && passwords.contains(password)) {
      int index = usernames.indexOf(username);
      String role = roles.get(index);
      int id = ids.get(index);
      penggunaSekarang = id;
      if (role.equals("admin")) { // Admin
        System.out.println("Login successful as admin.");
        return 1;
      } else { // User
        System.out.println("Login successful as user.");
        return 2;
      }
    } else {
      System.out.println("Invalid username or password.");
      return 0;
    }
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
          System.out.println("loginan tidak valid");
      }
      return 0;
    } finally {
      // input.close();
    }
  }
}

class User extends Pengguna {
  @Override
  public Integer Menu() {
    Scanner input = new Scanner(System.in);
    try {
      while (true) {
        printMenu();
        String pilihan = input.nextLine();
        switch (pilihan) {
          case "1":
            lihatProfil();
            break;
          case "2":
            ajukanPinjaman();
            break;
          case "3":
            lihatStatus();
            break;
          case "4":
            editPengajuan();
            break;
          case "5":
            batalkanPengajuan();
            break;
          case "6":
            catatPembayaran();
            break;
          case "7":
            System.out.println("Keluar");
            return 1;
          default:
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
        }
      }
    } finally {
      // input.close();
    }
  }

  private void printMenu() {
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
  }

  private void lihatProfil() {
    System.out.println("Profil Pengguna");
    System.out.println("NIK: " + niks.get(penggunaSekarang - 1));
    System.out.println("Nama Pengguna: " + usernames.get(penggunaSekarang - 1));
    System.out.println("Email: " + emails.get(penggunaSekarang - 1));
    System.out.println("Nomor Telepon: " + nomorTelepons.get(penggunaSekarang - 1));
  }

  private void ajukanPinjaman() {
    System.out.println("Ajukan Pinjaman");
    System.out.print("Jumlah Pinjaman: ");
    int jumlahPinjaman = input.nextInt();
    System.out.print("Jangka Waktu Pinjaman (bulan): ");
    int jangkaWaktuPinjaman = input.nextInt();
    System.out.print("Bunga Pinjaman (%): ");
    double bungaPinjaman = input.nextDouble();

    // Simpan data pinjaman ke dalam array
    pinjaman.add(new Pinjaman(jumlahPinjaman, jangkaWaktuPinjaman, bungaPinjaman));

    System.out.println("Pinjaman berhasil diajukan.");
  }

  private void lihatStatus() {
    System.out.println("Status Pengajuan");
    for (int i = 0; i < pinjaman.size(); i++) {
      System.out.println("Pengajuan " + (i + 1));
      System.out.println("Jumlah Pinjaman: " + pinjaman.get(i).getJumlahPinjaman());
      System.out.println("Jangka Waktu Pinjaman: " + pinjaman.get(i).getJangkaWaktuPinjaman() + " bulan");
      System.out.println("Bunga Pinjaman: " + pinjaman.get(i).getBungaPinjaman() + "%");
    }
  }

  private void editPengajuan() {
    System.out.println("Edit Pengajuan");
    System.out.print("Nomor Pengajuan: ");
    int nomorPengajuan = input.nextInt();
    if (nomorPengajuan > 0 && nomorPengajuan <= pinjaman.size()) {
      System.out.print("Jumlah Pinjaman: ");
      int jumlahPinjaman = input.nextInt();
      System.out.print("Jangka Waktu Pinjaman (bulan): ");
      int jangkaWaktuPinjaman = input.nextInt();
      System.out.print("Bunga Pinjaman (%): ");
      double bungaPinjaman = input.nextDouble();

      // Update data pinjaman
      pinjaman.get(nomorPengajuan - 1).setJumlahPinjaman(jumlahPinjaman);
      pinjaman.get(nomorPengajuan - 1).setJangkaWaktuPinjaman(jangkaWaktuPinjaman);
      pinjaman.get(nomorPengajuan - 1).setBungaPinjaman(bungaPinjaman);

      System.out.println("Pengajuan berhasil diubah.");
    } else {
      System.out.println("Nomor pengajuan tidak valid.");
    }
  }

  private void batalkanPengajuan() {
    System.out.println("Batalkan Pengajuan");
    System.out.print("Nomor Pengajuan: ");
    int nomorPengajuan = input.nextInt();
    if (nomorPengajuan > 0 && nomorPengajuan <= pinjaman.size()) {
      pinjaman.remove(nomorPengajuan - 1);
      System.out.println("Pengajuan berhasil dibatalkan.");
    } else {
      System.out.println("Nomor pengajuan tidak valid.");
    }
  }

  private void catatPembayaran() {
    System.out.println("Catat Pembayaran");
    System.out.print("Nomor Pengajuan: ");
    int nomorPengajuan = input.nextInt();
    if (nomorPengajuan > 0 && nomorPengajuan <= pinjaman.size()) {
      System.out.print("Jumlah Pembayaran: ");
      double jumlahPembayaran = input.nextDouble();
      pinjaman.get(nomorPengajuan - 1).catatPembayaran(jumlahPembayaran);
      System.out.println("Pembayaran berhasil dicatat.");
    } else {
      System.out.println("Nomor pengajuan tidak valid.");
    }
  }

  public void setUsername(ArrayList<String> usernames) {
    this.usernames = usernames;
    // super.usernames = usernames;
  }

  public void setPasswords(ArrayList<String> passwords) {
    this.passwords = passwords;
    // super.passwords = passwords;
  }

  public void setRoles(ArrayList<String> roles) {
    this.roles = roles;
    // super.roles = roles;
  }

  public void setIds(ArrayList<Integer> ids) {
    this.ids = ids;
    // super.ids = ids;
  }

  public void setEmails(ArrayList<String> emails) {
    this.emails = emails;
    // super.emails = emails;
  }

  public void setNomorTelepons(ArrayList<String> nomorTelepons) {
    this.nomorTelepons = nomorTelepons;
    // super.nomorTelepons = nomorTelepons;
  }

  public void setNiks(ArrayList<String> niks) {
    this.niks = niks;
    // super.niks = niks;
  }

  public void setPinjaman(ArrayList<Pinjaman> pinjaman) {
    this.pinjaman = pinjaman;
    // super.pinjaman = pinjaman;
  }

  public void setPenggunaSekarang(Integer penggunaSekarang) {
    this.penggunaSekarang = penggunaSekarang;
    // super.penggunaSekarang = penggunaSekarang;
  }
}
