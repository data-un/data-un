import java.sql.*;

public class CreateTable {

    public static void Table()

    {

        Connection c = null;
        Statement stmt = null;
        
        try {

            String path = "jdbc:sqlite:dbase.db";
            c = DriverManager.getConnection(path);

            System.out.println("Conexión establecida con SQLite");

            stmt = c.createStatement();

            String sql = "CREATE TABLE SampleTable " +

                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +

                    " s_name TEXT NOT NULL)";

            stmt.executeUpdate(sql);

            stmt.close();

            c.close();

        }

        catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());

            System.exit(0);

        }

        System.out.println("Tabla creada con exito");

    }

}