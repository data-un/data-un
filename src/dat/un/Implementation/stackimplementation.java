public class stackimplementation {

    private stack<Estadisticas> statstack = new stack<Estadisticas>();
    // Metodo para pasar el ResultSet a la estructura deseada
    public void rstostat(ResultSet rs) {

        Estadisticas registro = new Estadisticas();
        ResultSetMetaData rsmetadata = rs.getMetaData();

        while (rs.next()) {

            registro.setId(rs.getInt(1));
            registro.setEdad(rs.getInt(2));
            registro.setFechaDiagnostico(rs.getDate(3));
            registro.setTipoContagio(rs.getString(4));
            registro.setSexo(rs.getString(5));
            registro.setCiudad(rs.getString(6));
            registro.setFechaSintomas(rs.getDate(7));
            registro.setUbicacion(rs.getString(8));
            registro.setEstado(rs.getString(9));
            registro.setLocalidad(rs.getString(10));

            statstack.push(registro);

        }
    }

    // Metodo para insertar
    public void insert(Estadisticas Registro) {

        statsqueue.push(Registro);

    }

    // Metodo para eliminar
    public void delete() {

        statsqueue.pop();

    }

    // Metodo para buscar
    public boolean search(int id) {

    }

}
