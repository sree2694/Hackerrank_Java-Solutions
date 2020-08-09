    import java.util.*;
    public class test {
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
             Deque <Integer> deq = new ArrayDeque<>();
            int n = in.nextInt();
            int m = in.nextInt();
            int [] a = new int [ 10000001 ];
            long max = 0;
            long current = 0;

            for (int i = 0; i < n; i++) {
                   if ( i >=m ) {
                    // pop off last one
                    int f = deq.removeLast();
                    if ( a[f] >= 1 ) {
                        a[f]--;
                        if (a[f]==0)
                            current--;
                    }
                }
                
                int x = in.nextInt();
                if (  a[x] == 0 ) {
                    // new unique
                    current++;
                    max = Math.max(current, max );
                } 
                a[x]++;
                deq.addFirst(x);  
            }           
            System.out.println(max);
               
            }
        }

