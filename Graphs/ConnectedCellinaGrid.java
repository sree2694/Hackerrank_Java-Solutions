 import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class Pair{
        public int r;
        public int c;

        public Pair(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static List<Pair> neighbors(int r, int c, int[][] arr){
        int n = arr.length;
        int m = arr[0].length;
        List<Pair> nbs = new ArrayList<>();
        for(int i = r-1; i <= r+1; i++){
            for(int j = c-1; j <= c+1; j++){
                if((i >= 0 && i < n) && (j >= 0 && j < m)){
                    if(!(i == r && j == c))
                        nbs.add(new Pair(i, j));
                }
            }
        }
        return nbs;
    }

    static int dfs(int r, int c, int[][] arr){
        arr[r][c] = 0;
        int count = 1;
        for(Pair nbr : neighbors(r,c,arr)){
            if(arr[nbr.r][nbr.c] == 1){
                count += dfs(nbr.r, nbr.c, arr);
            }
        }
        return count;
    }

    static int solve(int n, int m, int[][] arr) {
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == 1){
                    ans = Math.max(ans, dfs(i,j, arr));
                }
            }
        }
        return ans;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                arr[i][j] = scanner.nextInt();
            }
        }
        int result = solve(n, m, arr);
        System.out.println(result);
        scanner.close();
    }
}