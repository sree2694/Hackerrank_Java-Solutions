import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
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
public static class MyQueue<T>{
        
        Stack<Integer> enqueueStack = new Stack<Integer>();
        Stack<Integer> dequeueStack = new Stack<Integer>();
        
        void enqueue(int n){
            enqueueStack.push(n);
        }
        int dequeue(){
           if(dequeueStack.empty()) {
               shiftStack();
           }
           return dequeueStack.pop();
        }
        
        public void shiftStack(){
            while(!enqueueStack.empty()){
               int x = enqueueStack.pop();
               dequeueStack.push(x);
           }
        }
        int peek(){
            if(dequeueStack.empty()) {
                shiftStack();
            }
            return dequeueStack.peek();
        }
    }
}