package examples.visualization;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;   // Import the FileWriter class


public class MainProgram {

    public static void main(String[] args) {

        BufferedReader br = null;
        int MAX_NODE = 2000;
        int source;
        int n = 1;
        int inputfileoption = 1;
        String inputfilename = "karate/Demo.txt";
        Scanner scan2 = new Scanner(System.in);

        System.out.print("\nDemo mode to execute(user input source)? (0: no / 1: yes)");
        int isDemo = scan2.nextInt();

        System.out.print("\nChoose which file to execute? ");
        System.out.print("\n1.Small file (100 nodes) 2.Full Data (over 1 million nodes) ");
        inputfileoption = scan2.nextInt();
        if (inputfileoption == 2) {
            inputfilename = "karate/oregon2_010331.txt";
            MAX_NODE = 65600;
        }

        System.out.print("\nInput n for top n path to the hospital? ");
        n = scan2.nextInt();
        if (n < 1 || n >= MAX_NODE) {
            System.out.println("\n Your input is invalid!");
            return;
        }

        GraphModel model = new GraphModel(MAX_NODE, inputfilename);
        Algorithm algo = new Algorithm(MAX_NODE, model);

        if (isDemo == 1) {
            System.out.print("\nDefine the source node? ");
            source = scan2.nextInt();
            if (source < 0 || source >= MAX_NODE) {
                System.out.println("\n Your input is invalid!");
                return;
            }


            NodeResult nodeResult = algo.getTopNShortestFromSource(source, n);
            String resultString = printResult(nodeResult);
            WriteToFile(resultString);

            if (inputfileoption == 1) {

                DisplayGraph Display = new DisplayGraph(model, nodeResult.PathList, MAX_NODE, source);

            }

        } else {
            ArrayList<NodeResult> eachTopNList = algo.getEachTopNShortest(n);
            String output = "";
            for (NodeResult nodeResult : eachTopNList) {
                if (nodeResult.NodeId ==0 && nodeResult.PathList.size()==0)
                    continue;

                String resultString = printResult(nodeResult);
                output += resultString;

            }
            WriteToFile(output);
        }
    }

    private static void WriteToFile(String text) {
        try {
            FileWriter myWriter = new FileWriter("OutPut/OutPut.txt");
            myWriter.write(text);
            myWriter.close();
            System.out.println("\nSuccessfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("\nAn error occurred.");
            e.printStackTrace();
        }
    }

    private static String printResult(NodeResult topNList) {
        int topN = 0;
        String resultString = "";
        for (ArrayList<Integer> item : topNList.PathList) {
            int pathCount = item.size();
            System.out.println(String.format("NodeId: %d", topNList.NodeId));
            System.out.println(String.format("\nTop %d path", ++topN));
            System.out.println("Destinated hospital: " + item.get(0));
            System.out.println("Shortest path length is: " + pathCount);
            System.out.println("Path is :");



            resultString += String.format("NodeId: %d\n", topNList.NodeId);
            resultString += "\nTop " + (topN) + " path";
            resultString += "\nDestinated hospital: " + item.get(0);
            resultString += "\nShortest path length is: " + pathCount;
            resultString += "\nPath is : ";

            for (int i = pathCount - 1; i >= 0; i--) {
                System.out.print(item.get(i) + " ");
                resultString += item.get(i) + " ";
            }

        }
        resultString += "\n=======================================\n";
        System.out.println("\n=======================================");
        return resultString;
    }
}
