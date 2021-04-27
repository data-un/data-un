import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

public class queueimplementation{

    private queue<Estadisticas> statqueue = new queue<Estadisticas>();

//Metodo para pasar el ResultSet a la estructura deseada
    public void rstostat(ResultSet rs){

        Estadisticas registro = new Estadisticas();
        ResultSetMetaData rsmetadata = rs.getMetaData();

        while(rs.next()){

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

            statqueue.enqueue(registro);

        }
    }
//Metodo para insertar
    public void insert(Estadisticas Registro){

        statqueue.enqueue(Registro);

    }
//Metodo para eliminar
    public void delete(){

        statqueue.dequeue();

    }
//Metodo para buscar
    public boolean search(int id){

    }







}