package dat.un.databaseSQLite;

import dat.un.Estadisticas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

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
    
    public void insert(Estadisticas registro) {
        try {
            connect.setAutoCommit(true);
            
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
        } catch (SQLException ex) {
            System.out.println("Insert");
            System.out.println(ex);
        }
    }
    
    public DefaultTableModel read() throws SQLException {        
        PreparedStatement st = connect.prepareStatement("SELECT * FROM estadisticas");
        ResultSet resultSet = st.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(resultSet.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}