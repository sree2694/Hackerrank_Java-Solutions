import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        String[] a = new String[N];
        for(int i=0; i<N; i++){
            a[i] = sc.next();
        }
        
        int Q = sc.nextInt();
        int[] result = new int[Q];
        int i=0;
        
        while(sc.hasNext()){
            String query = sc.next();
            int count = 0;
            for(int j=0; j<N; j++){
                if (a[j].equals(query)){
                    count++;
                }
            }
            result[i++] = count;
        }
        
        for(i=0; i<Q; i++){
            System.out.println(result[i]);
        }

        scanner.close();
    }
}
