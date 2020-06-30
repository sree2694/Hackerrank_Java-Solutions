import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
  int count = 0;
    // create freq array as exp <= 200 always
    //maintain a queue to find ontgoing and incoming exp
    //get median from freq array

    int freq[] = new int[201];
    Queue<Integer> q = new LinkedList<Integer>();

    for(int i=0;i<expenditure.length;i++)
    {
        while(i<d)
        {
            q.add(expenditure[i]);
            freq[expenditure[i]] = freq[expenditure[i]]+1;
            i++;
        }


        float median = getMedian(freq,d);

        if(expenditure[i] >= 2*median)
        {
            count++;
        }


        // removing outing going element freq
        int elem = q.remove();
        freq[elem] = freq[elem] - 1;

        // adding incoming element to freq
        q.add(expenditure[i]);
        freq[expenditure[i]] = freq[expenditure[i]]+1;
    }

    return count;

    }
    private static float getMedian(int[] freq,int d)
{
    if(d%2 == 1)
    {
        int center = 0;
        for(int i=0;i<freq.length;i++)
        {
            center = center + freq[i];

            if(center > d/2)
            {
                return i;
            }
        }
    }else{
        int count = 0;
        int first = -1;
        int second = -1;
        for(int i=0;i<freq.length;i++)
        {
            count = count + freq[i];

            if(count == d/2)
            {
                first = i;
            }else if(count > d/2)
            {
                if(first < 0 ) first = i;
                second = i;

                return ((float)first + (float)second)/2; 
            }
        }
    }
    return 0f;
}

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
