import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class MainInsert {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Ambil data dari user
        System.out.print("Masukkan kode barang: ");
        String kode = input.nextLine();

        System.out.print("Masukkan nama barang: ");
        String nama = input.nextLine();

        System.out.print("Masukkan harga: ");
        int harga = input.nextInt();

        System.out.print("Masukkan stok: ");
        int stok = input.nextInt();

        // Koneksi database
        String url = "jdbc:mysql://localhost:3306/toko";
        String user = "root";     // ganti kalau username MySQL kamu beda
        String pass = "";         // ganti kalau ada password MySQL

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pass);

            // Panggil stored procedure
            CallableStatement cs = conn.prepareCall("{CALL insert_barang(?, ?, ?, ?)}");
            cs.setString(1, kode);
            cs.setString(2, nama);
            cs.setInt(3, harga);
            cs.setInt(4, stok);

            cs.execute();

            System.out.println("Data berhasil dimasukkan.");
            conn.close();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
