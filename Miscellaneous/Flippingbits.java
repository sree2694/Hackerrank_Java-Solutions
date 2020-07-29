import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the flippingBits function below.
    static long flippingBits(long n) {

    long[] b = new long[32]; 
      int j = b.length-1; 
      while(n!=0) {
        b[j] = n%2;
        n = n/2;
        j--;
      }
      for(int i=0; i<b.length; i++) {
        if(b[i]==0) {
          b[i] = 1;
        }
        else {
          b[i] = 0; 
        }
      }
      long num = 2*b[0]; 
      for(int i=1; i<b.length-1; i++) {
          num += b[i];
          num = num * 2;   
      }
      return num + b[b.length-1];  
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            long n = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            long result = flippingBits(n);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
