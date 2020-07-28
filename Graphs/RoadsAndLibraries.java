import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
  // implement hybrid Union-find with nodes count
    // NOTE: parent[0], rank[0] and cityCount[0] are ignored for indexing simplicity
    final int[] parent = new int[n + 1];
    final int[] rank = new int[n + 1]; // Union-find subtree rank to merge shorter to larger
    final int[] cityCount = new int[n + 1];
    for (int i = 1; i < n + 1; i++) {
        parent[i] = i;
        cityCount[i] = 1;
    }

    for (final int[] conn : cities) {
        final int v = conn[0];
        final int q = conn[1];
        final int rootV = find(v, parent);
        final int rootQ = find(q, parent);
        if (rootV != rootQ) {
            if (rank[rootV] > rank[rootQ]) {
                parent[rootQ] = rootV;
                cityCount[rootV] += cityCount[rootQ];
            } else {
                parent[rootV] = rootQ;
                cityCount[rootQ] += cityCount[rootV];

                if (rank[rootV] == rank[rootQ]) {
                    rank[rootQ]++;
                }
            }
        }
    }

    // find all islands (connected sub-graphs) and calculate minimal cost for each cities island
    // consider each "island" as a spanning tree, e.g. it has minimal roads/nodes to connect all of them
    long totalCost = 0;
    for (int i = 1; i < n + 1; i++) {
        if (parent[i] == i) {
            final long roadsCount = cityCount[i] - 1; // number of edges in spanning sub-tree of a graph with cityCount[i] nodes
            final long buildRoadsAndOneLib = roadsCount * c_road + c_lib;
            final int buildLibsInEachCity = cityCount[i] * c_lib;
            totalCost += Math.min(buildRoadsAndOneLib, buildLibsInEachCity);
        }
    }

    return totalCost;
}

// Union find with pass compression
private static int find(int val, final int[] parents) {
    while (parents[val] != val) {
        parents[val] = parents[parents[val]];
        val = parents[val];
    }

    return val;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
