import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
class Solution{
	
	public static void main(String []argh)
	{
		Scanner sc = new Scanner(System.in);
		
		while (sc.hasNext()) {
		   while(sc.hasNextLine()) {
            Stack<Character> stack = new Stack<>();
            String line = sc.nextLine();
            for(char c : line.toCharArray()) {
                if(c == '{' || c == '(' || c == '[') {
                   stack.push(c);
                   continue;
                }                    
                
                if(c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                    continue;
                }
                
                if(c == ')' && !stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                    continue;
                }
                
                if(c == ']' && !stack.isEmpty() && stack.peek() == '['){
                    stack.pop();
                    continue;
                }
                
                if(c == '}' || c == ')' || c == ']') {
                    stack.push(c);
                    break;
                }   
            }
            System.out.println(stack.isEmpty());

            
		}
		
	}
    }
}



