import java.util.*;

public class Solution {

    public static int fibonacci(int n) {

   int[] fib = new int[2]; 
    fib[0] = 0; fib[1] = 1;
    for (int i = 2; i <= n; ++i) {
        fib[i % 2] = fib[0] + fib[1];
    }
    return fib[n % 2];
        // Complete the function.
    }
