
public class stack<T> {
    static final int MAX = 100000000;
    int top;
    private Object[] array;
    public stack(){
        this.array = new Object[MAX];
        this.top = -1;
    }
    public boolean isEmpty(){
        return this.top < 0;
    }
    public T peek(){
        if(this.isEmpty()){
            System.out.println("Este stack está vacío!");
            return null;
        }
        return (T)this.array[this.top];
    }
    public T pop(){
        if(this.isEmpty()){
            System.out.println("Este stack está vacío!!!!");
            return null;
        }
        return (T)this.array[this.top--];
    }
    public void push(T element){
        if(this.top == MAX - 1){
            System.out.println("Stack overflow!");
        }
        this.array[++top] = element;
    }
}
