import java.util.*;

public class Solution {

    public static boolean canWin(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
         int n = game.length;

        Stack<Integer> potentialIndexes = new Stack<>();
        potentialIndexes.push(0);

        while (!potentialIndexes.isEmpty()) {
            int i = potentialIndexes.pop();

            if (i >= n) return true;

            if (i < 0 || game[i] == 1) continue;

            game[i] = 1;

            potentialIndexes.push(i+1);
            potentialIndexes.push(i-1);
            potentialIndexes.push(i+leap);
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}