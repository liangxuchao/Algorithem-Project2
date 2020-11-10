package examples.visualization;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.graphstream.graph.ElementNotFoundException;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.GraphParseException;

public class DisplayGraph {
	public DisplayGraph(GraphModel Model, ArrayList<ArrayList<Integer>> topNList, int MAX_NODE,int source) {
		System.setProperty("org.graphstream.ui", "swing");
		Graph graph = new SingleGraph("demo");

		graph.setAttribute("ui.stylesheet", "url('styles/style.css')");
		graph.setAutoCreate(true);
		graph.setStrict(false);
		graph.display();
		
		ArrayList<Integer> hospitalarr = new ArrayList<Integer>();
		
		for(int i=0; i< Model.adj.size(); i++) {

			for(int j=0; j< Model.adj.get(i).getTargetNodes().size(); j++) {
				graph.addEdge(i + "_" + Model.adj.get(i).getTargetNodes().get(j), Integer.toString(i),  Integer.toString(Model.adj.get(i).getTargetNodes().get(j)));
				
			}	
			if(Model.adj.get(i).getIsHospital() == true) {
				hospitalarr.add(i);
			}
		}
		
		for (int j=0; j< hospitalarr.size(); j++) {
			
			if(hospitalarr.get(j) <= MAX_NODE) {
				graph.getNode(hospitalarr.get(j).toString()).setAttribute("ui.class", "important");
			}
			
		}
		for (Node node : graph) {
			node.setAttribute("ui.label", node.getId());
			
		}
		graph.getNode(Integer.toString(source)).setAttribute("ui.class", "source");
		
		for (ArrayList<Integer> item : topNList) {
            int pathCount = item.size();
           
            for (int i=0; i<pathCount - 1; i++) {
                
                String firstid = item.get(i) + "_" + item.get(i+1);
    			String secondid = item.get(i+1) + "_" + item.get(i);
    			
    			if(graph.getEdge(firstid) != null) {
    				graph.getEdge(firstid).addAttribute("ui.style", "fill-color:green;");
    			}
    			
    			if(graph.getEdge(secondid) != null) {
    				graph.getEdge(secondid).addAttribute("ui.style", "fill-color:green;");
    			}
            }
        }

		
		
	}
	

}
