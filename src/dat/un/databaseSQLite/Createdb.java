import java.sql.*;

public class Createdb {

    public static void createNewDatabase(String fileName) {

        String path = "jdbc:sqlite:path/to/database/" + fileName;

        try (Connection conn = DriverManager.getConnection(path)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        createNewDatabase("test.db");
    }
}