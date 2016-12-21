
import java.util.*;

public class QueueUsingStack {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
}
class MyQueue<E> {
    Stack<E> s1 = new Stack<E>();
    Stack<E> s2 = new Stack<E>();
    public void enqueue(E inp){
        s1.push(inp);
    }
    public void dequeue(){
        if(s2.empty()){
            while(!s1.empty()){
                s2.push(s1.peek());
                s1.pop();
            }
            s2.pop();
        }else{
            s2.pop();
        }
    }
    public E peek(){
        if(s2.empty()){
            while(!s1.empty()){
                s2.push(s1.peek());
                s1.pop();
            }
            return s2.peek();
        }else{
            return s2.peek();
        }
    }
}