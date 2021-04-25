import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectdb {

    public static void connect() {
        Connection conn = null;
        try {
            String path = "jdbc:sqlite:dbase.db";

            conn = DriverManager.getConnection(path);
            
            System.out.println("Conexión establecida con SQLite");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        connect();
    }
}