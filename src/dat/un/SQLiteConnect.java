package dat.un;

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
                            + "(id, edad, fecha_diagnostico, tipo_contagio, sexo, ciudad, fecha_sintomas, ubicacion, estado, localidad) "
                            + " VALUES "
                            + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            st.setInt(1, registro.getId());
            st.setInt(2, registro.getEdad());
            st.setDate(3, registro.getFechaDiagnostico());
            st.setString(4, registro.getTipoContagio());
            st.setString(5, registro.getSexo());
            st.setString(6, registro.getCiudad());
            st.setDate(7, registro.getFechaSintomas());
            st.setString(8, registro.getUbicacion());
            st.setString(9, registro.getEstado());
            st.setString(10, registro.getLocalidad());
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

        columnNames.add("Caso");
        columnNames.add("Edad");
        columnNames.add("Fecha diagnostico");
        columnNames.add("Tipo contagio");
        columnNames.add("Sexo");
        columnNames.add("Ciudad");
        columnNames.add("Fecha sintomas");
        columnNames.add("Ubicacion");
        columnNames.add("Estado");
        columnNames.add("Localidad");

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                switch (columnIndex) {
                    case 1:
                    case 2:
                        vector.add(resultSet.getInt(columnIndex));
                        break;
                    case 3:
                    case 7:
                        vector.add(resultSet.getDate(columnIndex));
                        break;
                    default:
                        vector.add(resultSet.getString(columnIndex));
                        break;
                }
            }

            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}