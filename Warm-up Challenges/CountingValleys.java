import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.HashMap;

public class Solution {

    public static void main(String[] args) {
 Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int counter = 0;
        int total = 0;
        String str = null;
        in.nextLine();
        str = in.nextLine();
        Character c1 = new Character('U');
        Character c2 = new Character('D');
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c1.equals(c)) {
                counter++;
            } else if (c2.equals(c)) {
                counter--;
                if (counter == -1) {
                    total++;
                }
            }
        }
        System.out.print(total);
}
}
