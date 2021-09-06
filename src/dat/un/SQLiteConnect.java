package dat.un;

import dat.un.Implementation.AVLTree;
import dat.un.Implementation.HashTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class SQLiteConnect {
    Connection connect;
    HashTable<String, Integer> hashTable;

    public SQLiteConnect() {
        try {
            String path = "jdbc:sqlite:.\\data\\v-data.sqlite3";
            connect = DriverManager.getConnection(path);

            if (connect != null) {
                System.out.println("Conectado");
                this.get_birth_dates();
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
                            + "(id, edad, fecha_diagnostico, fuente_tipo_contagio, sexo, ciudad_nombre, fecha_inicio_sintomas, ubicacion, estado, tipo_diagnostico) "
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
            System.out.println(ex.getMessage());
        }
    }

    public DefaultTableModel read() throws SQLException {
        PreparedStatement st = connect.prepareStatement("SELECT id, edad, fecha_diagnostico, fuente_tipo_contagio," +
                "sexo, ciudad_nombre, fecha_inicio_sintomas, ubicacion, estado, tipo_diagnostico FROM estadisticas");
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
        columnNames.add("Tipo diagnostico");

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(resultSet.getString(columnIndex));
            }

            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public DefaultTableModel casesByCity() throws SQLException {
        PreparedStatement st = connect.prepareStatement("SELECT ciudad_nombre, COUNT(*) AS Counter FROM estadisticas GROUP BY ciudad_nombre");
        ResultSet resultSet = st.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();

        columnNames.add("Ciudad");
        columnNames.add("No de casos");

        hashTable = new HashTable<>();

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            hashTable.add(resultSet.getString(1), resultSet.getInt(2));

            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(resultSet.getString(columnIndex));
            }

            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public int get_cases_by_city(String city) {
        return hashTable.get(city);
    }

    public double get_birth_dates() {
        try {
            PreparedStatement st = connect.prepareStatement("SELECT edad FROM estadisticas");
            ResultSet resultSet = st.executeQuery();

            AVLTree tree = new AVLTree();

            while (resultSet.next()) {
                tree.setRoot(tree.insert(tree.getRoot(), resultSet.getInt(1)));
            }

            return tree.getMean();
        } catch (SQLException ex) {
            System.out.println("get_birth_date");
            System.out.println(ex.getMessage());
        }

        return -1;
    }
}
