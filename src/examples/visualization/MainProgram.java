package examples.visualization;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class MainProgram {

    public static void main(String[] args) {

        BufferedReader br = null;
        int MAX_NODE = 100;
        int source;
        int n=1;
        int inputfileoption = 1;
        String inputfilename = "karate/Demo.txt";
        Scanner scan2 = new Scanner(System.in);
        
        System.out.print("\nChoose the mode of execution? ");

        System.out.print("\n1.Demo (100 nodes) 2.Full Data (over 1 million nodes) ");
        inputfileoption = scan2.nextInt();
        if(inputfileoption == 2){
        	inputfilename = "karate/roadmap_fullData.txt";
        	MAX_NODE = 1500000;
        }
        
        System.out.print("\nInput n for top n path to the hospital? ");
        n = scan2.nextInt();
        if(n < 1 || n>=MAX_NODE){
            System.out.println("\n Your input is invalid!");
            return;
        }
        
        System.out.print("\nDefine the source node? ");
        source = scan2.nextInt();
        if(source < 0 || source>=MAX_NODE){
            System.out.println("\n Your input is invalid!");
            return;
        }

        
        GraphModel model = new GraphModel(MAX_NODE, inputfilename);
        Algorithm algo = new Algorithm(MAX_NODE, model);
        
        for(int z=0; z<MAX_NODE; z++) {
        	
	        ArrayList<ArrayList<Integer>> topNList = algo.printTopNShortest(source, n);
	        System.out.println("Source Node: " + z);
	        int topN = 0;
	        String resultString = "";
	        for (ArrayList<Integer> item : topNList) {
	            int pathCount = item.size();
	            System.out.println(String.format("\n\nRank %d shortest hospital", ++topN));
	            System.out.println("Destinated hospital: " + item.get(0));
	            System.out.println("Shortest path length is: " + (pathCount - 1));
	            System.out.println("Path is :");
	            
	            resultString += "Rank " + (topN) + " shortest hospital";
	            resultString += "\nDestinated hospital: " + item.get(0);
	            resultString += "\nShortest path length is: " + (pathCount - 1);
	            resultString += "\nPath is : ";
	            
	            for (int i = pathCount - 1; i >= 0; i--) {
	                System.out.print(item.get(i) + " ");
	                resultString += item.get(i) + " ";
	            }
	            
	            resultString += "\n\n";
	        }
	        if(z == 0) {

		        WriteToFile(resultString, true);
		        
	        }else {
	        	 WriteToFile(resultString, false);
			        
	        }
	    //    if(inputfileoption == 1) {
	
	    //        DisplayGraph Display = new DisplayGraph(model,topNList,MAX_NODE,source);
	          
	    //    }

        }
        
    }
    
    public static void WriteToFile (String text, boolean isNew) {
    	 try {
    	      FileWriter myWriter = new FileWriter("OutPut/OutPut.txt");
    	      if(isNew) {

        	      myWriter.write(text);
    	      }else {
    	    	  BufferedWriter br = new BufferedWriter(myWriter);
    	    	  PrintWriter pr = new PrintWriter(br);
    	    	  pr.println(text);
    	    	  pr.close();
    	    	  br.close();
    	      }
    	      myWriter.close();
    	      System.out.println("\nSuccessfully wrote to the file.");
    	    } catch (IOException e) {
    	      System.out.println("\nAn error occurred.");
    	      e.printStackTrace();
    	    }
    }


}
