import java.util.Arrays;
public class DynamicArrayGeneric<T>{
    private T[] array;
    
    private int size;
    
    private int capacity;
     
    // GENERAR EL ARRAY
    @SuppressWarnings("unchecked")
	public DynamicArrayGeneric(){
        array =  (T[]) new Object[2];
        size=0;
        capacity=2;
    }
     
    // AGREGAR DATO AL FINAL
    public void addElement(T element){
        // Duplicar la capacidad si se llena
        if (size == capacity){
            ensureCapacity(2); 
        }
        array[size] = element;
        size++;
    }
     
    // AGREGAR DATO EN POSICION ESPECIFICA
    
    public void addElement(int index, T element){
        // Duplicar la capacidad si se llena
        if (size == capacity){
            ensureCapacity(2); 
        }
        // Correr los elementos a la derecha
        for(int i=size-1;i>=index;i--){
            array[i+1] = array[i];
        }
        // Insertar elemento
        array[index] = element;
        size++;
    }
 
    // ENCONTRAR DATO DADA LA POSICION
    public T getElement(int index){
        return array[index];
    }
     
    // ELIMINAR DATO EN POSICION ESPECIFICA
    public void remove(int index){
        if(index>=size || index<0){
            System.out.println("No hay elemento en esta posicion");
        }else{
            for(int i=index;i<size-1;i++){
                array[i] = array[i+1];
            }
            array[size-1]=null;
            size--;
        }
    }
     
    //METODO PARA AUMENTAR CAPACIDAD
    
    public void ensureCapacity(int minCapacity){
        @SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[capacity*minCapacity];
        for (int i=0; i < capacity; i++){
            temp[i] = array[i];
        }
        array = temp;
        capacity = capacity * minCapacity;
    }
     
    //METODO PARA BAJAR CAPACIDAD NO UTILIZADA
    
    public void trimToSize(){
        System.out.println("Recortando arreglo");
        @SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[size];
        for (int i=0; i < size; i++){
            temp[i] = (T) array[i];
        }
        array = temp;
        capacity = array.length;
         
    }
     
    // MOSTRAR TAMANO
    public int size(){
        return size;
    }
     
    // MOSTRAR CAPACIDAD
    public int capacity(){
        return capacity;
    }
     
    // IMPRIMIR TODO EL ARREGLO
    public void printElements(){
        System.out.println("Arreglo :"+Arrays.toString(array));
    }
}