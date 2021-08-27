
import java.util.ArrayList;
import java.util.Objects;

// Nodos
class HashNode<K, V> {
    K key;
    V value;
    final int hashCode;
    HashNode<K, V> next;
 
    // Constructor
    public HashNode(K key, V value, int hashCode){
    	this.key = key;
        this.value = value;
        this.hashCode = hashCode;
    }
}
 
// HashTable
class HashTable<K, V> {
    // Arreglo para almacenar las cadenas
    private ArrayList<HashNode<K, V> > arreglo;
 
    // Capacidad actual
    private int capacidad;
 
    // Tamaño del arreglo
    private int size;
 
    // Constructor
    public HashTable()
    {
    	arreglo = new ArrayList<>();
        capacidad = 10;
        size = 0;
        for (int i = 0; i < capacidad; i++)
        	arreglo.add(null);
    }
 
    public int size() {
    	return size;
    }
    
    public boolean isEmpty() {
    	return size() == 0;
    }
     
    private final int hashCode (K key) {
        return Objects.hashCode(key);
    }
   
    // Implementacion de la Hash function
    private int getIndex(K key)
    {
        int hashCode = hashCode(key);
        int index = hashCode % capacidad;
        // Correccion por si el resultado es negativo
        index = index < 0 ? index * -1 : index;
        return index;
    }
 
    public V remove(K key)
    {
        int indice = getIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = arreglo.get(indice);
 
        // Buscar key en cadena
        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;
            
            prev = head;
            head = head.next;
        }
 
        // No se encuentra
        if (head == null)
            return null;
 
        size--;
 
        // Remover y retornar de la cadena
        if (prev != null)
            prev.next = head.next;
        else
        	arreglo.set(indice, head.next);
 
        return head.value;
    }
 
    
    public V get(K key)
    {
        // Encontrar head
        int indice = getIndex(key);
        int hashCode = hashCode(key);
       
        HashNode<K, V> head = arreglo.get(indice);
 
        // Buscar key en cadena
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }
 
        // No se encuentra
        return null;
    }
 
    public void add(K key, V value)
    {
    	// Encontrar head
        int indice = getIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = arreglo.get(indice);
 
        // Checkear si esta en la cadena
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }
 
        // Insert key en la cadena
        size++;
        head = arreglo.get(indice);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        arreglo.set(indice, newNode);
 
        // Aumentar tamano si factor de carga sobrepasa el limite
        if ((1.0 * size) / capacidad >= 0.7) {
            ArrayList<HashNode<K, V> > temp = arreglo;
            arreglo = new ArrayList<>();
            capacidad = 2 * capacidad;
            size = 0;
            for (int i = 0; i < capacidad; i++)
            	arreglo.add(null);
 
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 10000000;
		System.out.println("Numero de datos: "+ n);
		
		HashTable<String, Integer> map = new HashTable<>();
		
		long start1 = System.nanoTime();
		
		for(int i = 0; i < (n/10); i++) {
			map.add("papa", 152252);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
			map.add(RandomString.getAlphaNumericString(10), 12544);
		}
		long end1 = System.nanoTime();
		System.out.println("Tiempo de add(string,int): "+ (end1-start1));
		
		System.out.println();
        
        long start2 = System.nanoTime();
        map.get("papa");
		long end2 = System.nanoTime();
		System.out.println("Tiempo de get(string): "+ (end2-start2));
		
        System.out.println();
        
        long start3 = System.nanoTime();
        map.isEmpty();
		long end3 = System.nanoTime();
		System.out.println("Tiempo de isEmpty(): "+ (end3-start3));
		
        System.out.println();
        
        long start4 = System.nanoTime();
        map.remove("papa");
		long end4 = System.nanoTime();
		System.out.println("Tiempo de remove(string): "+ (end4-start4));
		
        System.out.println();
        
        long start5 = System.nanoTime();
        map.size();
		long end5 = System.nanoTime();
		System.out.println("Tiempo de size(): "+ (end5-start5));
		
		System.out.println();
      
        
	}

}
