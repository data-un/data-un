package dat.un.databaseSQLite;

import dat.un.Estadisticas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLiteConnect {
    String url = "D:\\test.db";
    Connection connect;

    public SQLiteConnect() {
        try {
            String path = "jdbc:sqlite:.\\data\\v-data.sqlite3";
            connect = DriverManager.getConnection(path);
            
            if (connect != null) {
                System.out.println("Conectado");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void close(){
           try {
               connect.close();
           } catch (SQLException ex) {
                System.out.println(ex.getMessage());
           }
    }
    
    public boolean insert(Estadisticas registro) {
        try {
            connect.setAutoCommit(false);
            
            PreparedStatement st = connect.prepareStatement("INSERT INTO estadisticas "
                            + "(edad, fecha_diagnostico, tipo_contagio, sexo, ciudad, fecha_sintomas, ubicacion, estado, localidad) "
                            + " VALUES "
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setInt(1, registro.getEdad());
            st.setDate(2, registro.getFechaDiagnostico());
            st.setString(3, registro.getTipoContagio());
            st.setString(4, registro.getSexo());
            st.setString(5, registro.getCiudad());
            st.setDate(6, registro.getFechaSintomas());
            st.setString(7, registro.getUbicacion());
            st.setString(8, registro.getEstado());
            st.setString(9, registro.getLocalidad());
            st.execute();
            
            System.out.println("Datos insertados correctamente");
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Insert");
            System.out.println(ex);
            
            return false;
        }
    }
    
    public void read() {
        ResultSet result = null;
        
        try {
            PreparedStatement st = connect.prepareStatement("SELECT * FROM estadisticas");
            result = st.executeQuery();
            while (result.next()) {
                System.out.print("ID: ");
                System.out.println(result.getInt("id"));

                System.out.print("Nombre: ");
                System.out.println(result.getInt("edad"));

                System.out.println("=======================");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}