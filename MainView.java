import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class MainView {
    public static void main(String[] args) {
        // Konfigurasi koneksi database
        String url = "jdbc:mysql://localhost:3306/toko";
        String user = "root";
        String pass = "";

        // Memuat driver dan menjalankan query
        try {
            // Muat driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Gunakan try-with-resources untuk memastikan koneksi ditutup otomatis
            try (Connection conn = DriverManager.getConnection(url, user, pass);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM view_barang")) {

                // Header tampilan
                System.out.println("Data Barang:");
                System.out.printf("%-10s %-20s %-10s %-10s %-15s\n", 
                                  "Kode", "Nama", "Harga", "Stok", "Total Nilai");

                // Loop data hasil query
                while (rs.next()) {
                    System.out.printf("%-10s %-20s %-10d %-10d %-15d\n",
                                      rs.getString("kode"),
                                      rs.getString("nama"),
                                      rs.getInt("harga"),
                                      rs.getInt("stok"),
                                      rs.getInt("total_nilai"));
                }

            } catch (SQLException se) {
                System.out.println("Kesalahan SQL: " + se.getMessage());
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver tidak ditemukan: " + e.getMessage());
        }
    }
}
