package examples.visualization;

import java.io.BufferedReader;
import java.util.Scanner;

public class MainProgram_new {
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
	    Algorithm_multiBFS algo = new Algorithm_multiBFS(MAX_NODE, model);
	    int sources[] = {  5 };  
	    
	    int S = 1;  
	  
	    algo.nearestTown(model, MAX_NODE, sources, S); 
	    
	 }

	
}
