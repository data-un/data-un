public class Arrayimplementation {
    private DynamicArrayGeneric<Estadisticas> array = new DynamicArrayGeneric<Estadisticas>();

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
    
                array.addElement(registro);
    
            }
        }

    //Metodo para insertar en posicion especifica
        public void insert(int i, Estadisticas Registro){
    
            array.addElement(i,Registro);
        }
    //Metodo para eliminar en posicion especifica
        public void delete(int i){
    
            array.remove(i);
    
        }
    //Metodo para encontrar dato dada la posición
        public Estadisticas search(int id){
            return array.getElement(id);
            
        }
    
}
