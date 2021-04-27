import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

public class queueimplementation{

    private static queue<Estadisticas> statsqueue = new queue<Estadisticas>();

    public static Estadisticas rstostat(ResultSet rs){

        Estadisticas registro = new Estadisticas();
        ResultSetMetaData rsmetadata = rs.getMetaData();

        while(rs.next()){

            registro.setEdad(rs.getInt(1));
            registro.setFechaDiagnostico(rs.getDate(2));
            registro.setTipoContagio(rs.getString(3));
            registro.setSexo(rs.getString(4));
            registro.setCiudad(rs.getString(5));
            registro.setFechaSintomas(rs.getDate(6));
            registro.setUbicacion(rs.getString(7));
            registro.setEstado(rs.getString(8));
            registro.setLocalidad(rs.getString(9));

            statsqueue.enqueue(registro);

        }
    }






}