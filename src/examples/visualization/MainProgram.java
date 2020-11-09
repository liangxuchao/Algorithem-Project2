package examples.visualization;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {

        BufferedReader br = null;
        final int MAX_NODE = 1100000;
        int source;
        int n=1;

        Scanner scan2 = new Scanner(System.in);
        System.out.print("\nWhat is the top N nearest? ");
        n = scan2.nextInt();
        if(n < 1 || n>=MAX_NODE){
            System.out.println("\n Your input is invalid!");
            return;
        }

        System.out.print("\nDefine the source node? ");
        source = scan2.nextInt();
        if(source < 1 || source>=MAX_NODE){
            System.out.println("\n Your input is invalid!");
            return;
        }

        GraphModel model = new GraphModel(MAX_NODE);
        Algorithm algo = new Algorithm(MAX_NODE, model);

        ArrayList<ArrayList<Integer>> topNList = algo.printTopNShortest(source, n);

        int topN = 0;
        for (ArrayList<Integer> item : topNList) {
            int pathCount = item.size();
            System.out.println(String.format("\n\nTop %d route", ++topN));
            System.out.println("Shortest path length is: " + pathCount);

            System.out.println("Path is :");
            for (int i = pathCount - 1; i >= 0; i--) {
                System.out.print(item.get(i) + " ");
            }
        }

    }


}
