import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxXor function below.
    static int[] maxXor(int[] arr, int[] queries) {
        // solve here
 class IntegerTrie {
        class Bit {
            private Bit zero;
            private Bit one;
        }

        private Bit root = new Bit();

        public void insert(int value) {
            Bit current = root;
            for (int i = Integer.SIZE - 1; i >= 0; --i)
                switch ((value >> i) & 1) {
                    case 0:
                        if (current.zero == null)
                            current.zero = new Bit();
                        current = current.zero;
                        break;

                    case 1:
                        if (current.one == null)
                            current.one = new Bit();
                        current = current.one;
                        break;

                    default:
                        throw new UnknownError();
                }
        }

        public int maxXor(int value) {
            int maxXor = 0;
            Bit current = root;
            for (int i = Integer.SIZE - 1; i >= 0; --i)
                switch ((value >> i) & 1) {
                    case 0:
                        if (current.one == null)
                            current = current.zero;
                        else {
                            current = current.one;
                            maxXor += Math.pow(2, i);
                        }
                        break;

                    case 1:
                        if (current.zero == null)
                            current = current.one;
                        else {
                            current = current.zero;
                            maxXor += Math.pow(2, i);
                        }
                        break;

                    default:
                        throw new UnknownError();
                }
            return maxXor;
        }
    }

    IntegerTrie trie = new IntegerTrie();
    for (int value : arr)
        trie.insert(value);
    
    int[] result = new int[queries.length];
    int index = 0;
    for (int query : queries)
        result[index++] = trie.maxXor(query);
    return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[m];

        for (int i = 0; i < m; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        int[] result = maxXor(arr, queries);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
