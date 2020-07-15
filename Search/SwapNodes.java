import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
     private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data = data;
        }

        void swapNodes(){
            Node temp = left;
            left = right;
            right = temp;
        }
    }

    static int[][] swapNodes(int[][] indexes, int[] queries) {
       List<Node> tree = new ArrayList<>();
       Node root = buildTree(indexes, tree, 1); 
       int[][] t = new int[queries.length][tree.size()]; 
       for(int i=0; i<queries.length; i++) {
        List<Integer> list = new ArrayList<>();
        swap(1,queries[i],root);
        inOrder(list,root); 
        fillT(i,list,t);   
       }
      return t; 
    }
    static Node buildTree(int[][] indexes, List<Node> tree, int data) {
      if(data==-1) {
        return null; 
      }
      Node node = new Node(data);
      int[] arr = indexes[data-1]; 
      node.left  =  buildTree(indexes,tree,arr[0]);
      node.right = buildTree(indexes,tree,arr[1]); 
      tree.add(node); 
      return node;  

    }

    static void swap(int level, int target, Node node) {
    if(node!=null) {
       if(level%target==0) {
         node.swapNodes();
       }
        swap(level+1,target,node.left);
        swap(level+1,target,node.right);
    }
    }

    static void inOrder(List<Integer> list, Node node) {
    if(node!=null) {
      inOrder(list,node.left);
      list.add(node.data);
      inOrder(list,node.right); 
    }
    }

    static void fillT(int j,List<Integer> list, int[][] t) {
      for(int i=0; i<list.size(); i++) {
        t[j][i] = list.get(i); 
      }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] indexes = new int[n][2];

        for (int indexesRowItr = 0; indexesRowItr < n; indexesRowItr++) {
            String[] indexesRowItems = scanner.nextLine().split(" ");

            for (int indexesColumnItr = 0; indexesColumnItr < 2; indexesColumnItr++) {
                int indexesItem = Integer.parseInt(indexesRowItems[indexesColumnItr].trim());
                indexes[indexesRowItr][indexesColumnItr] = indexesItem;
            }
        }

        int queriesCount = Integer.parseInt(scanner.nextLine().trim());

        int[] queries = new int[queriesCount];

        for (int queriesItr = 0; queriesItr < queriesCount; queriesItr++) {
            int queriesItem = Integer.parseInt(scanner.nextLine().trim());
            queries[queriesItr] = queriesItem;
        }

        int[][] result = swapNodes(indexes, queries);

        for (int resultRowItr = 0; resultRowItr < result.length; resultRowItr++) {
            for (int resultColumnItr = 0; resultColumnItr < result[resultRowItr].length; resultColumnItr++) {
                bufferedWriter.write(String.valueOf(result[resultRowItr][resultColumnItr]));

                if (resultColumnItr != result[resultRowItr].length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (resultRowItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
