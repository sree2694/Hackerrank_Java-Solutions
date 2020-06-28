import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ans;
        int n = sc.nextInt();
        ans = factorial(n);
        System.out.println(ans);
    }
       public static int factorial(int n){
            if(n == 1){
                return 1;
            }
            else{
                return (n * factorial(n-1));
            }
        }
    }
