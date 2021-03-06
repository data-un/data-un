package dat.un.Implementation;

import dat.un.Estadisticas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LinkedListimplementation {
    private LinkedList<Estadisticas> linkedlist = new LinkedList<>();

    //Metodo para pasar el ResultSet a la estructura deseada
        public void rstostat(ResultSet rs) throws SQLException{
    
            Estadisticas registro = new Estadisticas();
    
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
    
                linkedlist.addFirst(registro);
    
            }
        }

    //Metodo para insertar en posicion especifica
        public void insert(Estadisticas registro, int i){
    
            linkedlist.add(i, registro);
        }
    //Metodo para eliminar en posicion especifica
        public void delete(int p){
    
            linkedlist.remove(p);
    
        }
    //Metodo para encontrar dato dada la posición
        public Estadisticas search(int id){
            return linkedlist.get(id);
            
        }
    //Metodo para actualizar
        public void update(Estadisticas stat, int x){
            //linkedlist.upupdateAt(stat,x);
        }
    
}

