import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.util.*;

public class Solution {
    Scanner sc = new Scanner(System.in);
    
    void run() {
        int N = sc.nextInt(), K = sc.nextInt();
        boolean[] need = new boolean[N];
        E[] es = new E[N - 1];
        for (int i = 0; i < N - 1; i++) {
            es[i] = new E(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        UnionFind uf = new UnionFind(N + 1);
        for (int i = 0; i < K; i++) uf.union(N, sc.nextInt());
        sort(es);
        long res = 0;
        for (E e : es) {
            if (!uf.same(e.u, e.v)) {
                uf.union(e.u, e.v);
            } else {
                res += e.cost;
            }
        }
        System.out.println(res);
    }
    
    class E implements Comparable<E> {
        int u, v;
        int cost;
        E(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(E o) {
            return o.cost - cost;
        }
    }
    
    class UnionFind {
        int[] ps, num;
        UnionFind(int size) {
            ps = new int[size];
            num = new int[size];
            for (int i = 0; i < size; i++) {
                ps[i] = i;
                num[i] = 1;
            }
        }
        void union(int x, int y) {
            x = find(x); y = find(y);
            if (x == y) return;
            if (num[x] < num[y]) {
                int t = x; x = y; y = t;
            }
            ps[y] = x;
            num[x] += num[y];
        }
        int find(int x) {
            if (x != ps[x]) ps[x] = find(ps[x]);
            return ps[x];
        }
        boolean same(int x, int y) {
            return find(x) == find(y);
        }
    }
    
    class Scanner {
        InputStream in;
        byte[] buf = new byte[1 << 10];
        int p, m;
        boolean[] isSpace = new boolean[128];
        Scanner(InputStream in) {
            this.in = in;
            isSpace[' '] = isSpace['\n'] = isSpace['\r'] = isSpace['\t'] = true;
        }
        int read() {
            if (m == -1) return -1;
            if (p >= m) {
                p = 0;
                try {
                    m = in.read(buf);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (m <= 0) return -1;
            }
            return buf[p++];
        }
        boolean hasNext() {
            int c = read();
            while (c >= 0 && isSpace[c]) c = read();
            if (c == -1) return false;
            p--;
            return true;
        }
        String next() {
            if (!hasNext()) throw new InputMismatchException();
            StringBuilder sb = new StringBuilder();
            int c = read();
            while (c >= 0 && !isSpace[c]) {
                sb.append((char)c);
                c = read();
            }
            return sb.toString();
        }
        int nextInt() {
            if (!hasNext()) throw new InputMismatchException();
            int c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (c >= 0 && !isSpace[c]);
            return res * sgn;
        }
        long nextLong() {
            if (!hasNext()) throw new InputMismatchException();
            int c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (c >= 0 && !isSpace[c]);
            return res * sgn;
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
    
    void debug(Object...os) {
        System.err.println(deepToString(os));
    }
    
    public static void main(String[] args) {
        new Solution().run();
    }
}
