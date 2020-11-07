package examples.visualization;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;

public class Demo {
	
	
	
	public static void main(String args[]) {
		System.setProperty("org.graphstream.ui", "swing");
		Graph graph = new SingleGraph("demo");

		graph.setAttribute("ui.stylesheet", styleSheet);
		graph.setAutoCreate(true);
		graph.setStrict(false);
		graph.display();
		int lineNumber = 1;
		GraphModel Model = new GraphModel();
		for(int i=0; i< Model.adj.size(); i++) {

			
			for(int j=0; j< Model.adj.get(i).size(); j++) {
				graph.addEdge(i + "_" + Model.adj.get(i).get(j), Integer.toString(i),  Integer.toString(Model.adj.get(i).get(j)));
				
			}	
			
			
			lineNumber++;  
		}
		
		for (Node node : graph) {
			node.setAttribute("ui.label", node.getId());
		}
		// Test with source and dest variable to see shortest path
		 int source = 107;
		 int distsize = 0;
		 int[] hospitalarr = new int[distsize];
		 BufferedReader br = null; 
	        
		  try {
	            br = new BufferedReader(new FileReader("karate/hospital.txt"));
	         
	            Scanner scan = new Scanner(br);
	            String FirstLine = br.readLine();
	            String[] FirstLinearr = FirstLine.split("\\s+");
  			    distsize = Integer.parseInt(FirstLinearr[1]);
	            hospitalarr = new int[distsize];
	            
  				for(int i=0; i<distsize; i++) {
  					
		            if(scan.hasNextLine()) {
		          	  hospitalarr[i] = Integer.parseInt(scan.nextLine());
		          	
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
		 
		 LinkedList<Integer> shortestpath = Model.printNearestHospitalPath(source, distsize ,hospitalarr);
		 //
		for(int i=0; i<shortestpath.size()-1;i++) {
			String firstid = shortestpath.get(i) + "_" + shortestpath.get(i+1);
			String secondid = shortestpath.get(i+1) + "_" + shortestpath.get(i);
		
			if(graph.getEdge(firstid) != null) {
				graph.getEdge(firstid).addAttribute("ui.style", "fill-color:green;");
			}
			
			if(graph.getEdge(secondid) != null) {
				graph.getEdge(secondid).addAttribute("ui.style", "fill-color:green;");
			}
			
		}
	
	}

	protected static String styleSheet = "node {" + "	fill-color: black;" + "}" + "node.marked {"
			+ "	fill-color: red;" + "}";
}
