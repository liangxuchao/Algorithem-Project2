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
	
   public static void main(String[] args) 
   {
	  
	   BufferedReader br = null; 
	   int maxnode = 0;
	  
	   int source;
	   Scanner scan2 = new Scanner(System.in);
	   System.out.print ("\nWhat is the maximum node? ");
	   maxnode = scan2.nextInt();
		
	   System.out.print ("\nDefine the source node? ");
	   source = scan2.nextInt();
	   GraphModel Model = new GraphModel(maxnode);
	   Algorithm Algo = new Algorithm(maxnode, Model);
	   
	   LinkedList<Integer> templist = Algo.printShortestDistance(source);
	   int temppathcount = templist.size();
   	
	   System.out.println("Shortest path length is: " + temppathcount);
		
		// Print path
		System.out.println("Path is ::");
		for (int i = templist.size() - 1; i >= 0; i--) {
		   System.out.print(templist.get(i) + " ");
		}
    }

   
  
   
}
