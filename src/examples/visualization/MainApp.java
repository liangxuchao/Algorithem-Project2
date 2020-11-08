package examples.visualization;
import java.util.*;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.lang.*; 
import java.io.*; 
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;

public class MainApp {

	    
	    // Driver method 
	    public static void main(String[] args) 
	    { 
	    	//display graph for demo purpose - time complexity should be ignored
	    	System.setProperty("org.graphstream.ui", "swing");
			Graph graph = new SingleGraph("demo");
			
			int maxnode;
			int source;
			Scanner scan2 = new Scanner(System.in);
			System.out.print ("\nWhat is the maximum node? ");
			maxnode = scan2.nextInt();
			
			System.out.print ("\nDefine the source node? ");
			source = scan2.nextInt();
			
			System.out.print ("\nDefine the number of shorest path to the hospital? ");
			source = scan2.nextInt();
			
			graph.setAttribute("ui.stylesheet", styleSheet);
			graph.setAutoCreate(true);
			graph.setStrict(false);
			graph.display();
			BufferedReader br = null; 
			int graph2DArr[][] = new int[maxnode][maxnode];
			  try {
		            br = new BufferedReader(new FileReader("karate/roadNet-PA_demo.txt"));
		            Scanner scan = new Scanner(br);
		            String ss = "";
		            while(scan.hasNextLine()) {
		          	  ss = scan.nextLine(); 
		                if(!ss.startsWith("#")) { 
		              	  String[] arr = ss.split("\\s+");
		              	  int node1 = Integer.parseInt(arr[0]);
		              	  int node2 = Integer.parseInt(arr[1]);
			              //	System.out.print(Integer.parseInt(arr[0]) + "_" + Integer.parseInt(arr[1]));
	
			              //	System.out.print("\n");
		              		if(node1 < maxnode && node2 < maxnode ) {
		              			graph2DArr[node1][node2] = 1;
		              		}
		              		
		              	  	  graph.addEdge(arr[0] + "_" + arr[1], arr[0],  arr[1]);
						
		  	    		} 
		                
		            }
		           
		        } catch (FileNotFoundException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        } catch (NumberFormatException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        } catch (IOException e1) {
		            // TODO Auto-generated catch block
		            e1.printStackTrace();
		        }finally {
		            try {
		                br.close();
		            }catch(Exception e1){
		                System.out.println(""+e1);
		            }
		        }

				for (Node node : graph) {
					node.setAttribute("ui.label", node.getId());
				}
				
			// End //
			
	        /* Let us create the example graph discussed above */
			
	        GraphModel_2DArrayForDJAlgo t = new GraphModel_2DArrayForDJAlgo(maxnode); 
	        t.dijkstra(graph2DArr, 0); 
	    } 

		protected static String styleSheet = "node {" + "	fill-color: black;" + "}" + "node.marked {"
				+ "	fill-color: red;" + "}";
}
