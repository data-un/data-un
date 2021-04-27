
public class queue <T> {
    private  stack pila1 = new stack();
    private  stack pila2 = new stack();
    public queue(){
        this.pila1 = pila1;
        this.pila1 = pila2;
    }
    public void enqueue(T element){
        this.pila2.push(element);
    }
    public T dequeue(){
        
        if(this.isEmpty()){
            System.out.println("Esta cola está vacía!!!");
            return null;
        }
        
        if(this.pila1.isEmpty()){
            while(!this.pila2.isEmpty())
                this.pila1.push(this.pila2.pop());
            return (T) this.pila1.pop();
        }else{
            return (T) this.pila1.pop();
        }

    }
    public boolean isEmpty(){
        return this.pila1.isEmpty() && this.pila2.isEmpty();
    }
    
}
