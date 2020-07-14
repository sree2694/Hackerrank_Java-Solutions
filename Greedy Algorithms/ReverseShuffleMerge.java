import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the reverseShuffleMerge function below.
    static String reverseShuffleMerge(String s) {

    int a = 'a';
    int m = 'z' - a + 1;
    int[] frequency = new int[m];
    s.chars().forEach(c -> frequency[c - a]++);
    int[] count = Arrays.stream(frequency).map(f -> f / 2).toArray();

    int top = -1;
    int[] stack = new int[s.length()];
    for(int n = s.length(); --n >= 0; ) {
        int c = s.charAt(n) - a;
        frequency[c]--;
        if(count[c] < 1) continue;

        count[c]--;
        while(top >= 0 &&
            stack[top] > c &&
            frequency[stack[top]] > count[stack[top]]) {
                count[stack[top--]]++; // Increment and then pop the stack
        }
        stack[++top] = c; // Push c on to the stack
    }

    return java.util.stream.IntStream.rangeClosed(0, top)
        .mapToObj(c -> Character.toString((char)(stack[c] + a)))
        .collect(java.util.stream.Collectors.joining(""));

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
