import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the largestRectangle function below.
    static long largestRectangle(int[] h) {
  long max=0,minHeight=0,width=0;
        int lptr=0,rptr=0;
        for(int i=0; i<h.length ; i++)
        {
            minHeight = h[i];
            width=0;
            lptr = i;
            rptr = i;
            //left area
            while(lptr>=0 && (h[lptr] >= minHeight))
            {
                width++;
                lptr--;
            }
            
            //right area
            while(rptr<h.length && (h[rptr] >= minHeight))
            {
                width++;
                rptr++;    
            }
            width -= 1;
            max = Math.max(max,(minHeight*width));
        }//
        return max;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
