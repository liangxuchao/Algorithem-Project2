package examples.visualization;
 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*; 
 
public class GraphModel {
	// A utility function to find the vertex with minimum distance value, 
    // from the set of vertices not yet included in shortest path tree 
    static int V = 0; 
    public GraphModel(int maxnode) {
		this.V = maxnode;
	}

	int minDistance(int dist[], Boolean sptSet[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < V; v++) 
            if (sptSet[v] == false && dist[v] <= min) { 
                min = dist[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
  
    // A utility function to print the constructed distance array 
    void printSolution(int dist[]) 
    { 
        System.out.println("Vertex \t\t Distance from Source"); 
        for (int i = 0; i < V; i++) 
            System.out.println(i + " \t\t " + dist[i]); 
    } 
    void printSolution(int distance, int hospitalVertex) 
    { 
    	System.out.println("Nearest hospital from Source " + hospitalVertex + "\n"); 
    	System.out.println("Number of edge " + distance); 
        
    } 
    // Function that implements Dijkstra's single source shortest path 
    // algorithm for a graph represented using adjacency matrix 
    // representation 
    void dijkstra(int graph[][], int src) 
    { 
        int dist[] = new int[V]; // The output array. dist[i] will hold 
        // the shortest distance from src to i 
  
        // sptSet[i] will true if vertex i is included in shortest 
        // path tree or shortest distance from src to i is finalized 
        Boolean sptSet[] = new Boolean[V]; 
  
        // Initialize all distances as INFINITE and stpSet[] as false 
        for (int i = 0; i < V; i++) { 
            dist[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 
  
        // Distance of source vertex from itself is always 0 
        dist[src] = 0; 
  
        // Read hospital information
        int[] hospitalArr = hospitalArr();
        int arritem = hospitalArr[0];
        int arrindex = 0;
        int nearesthospital = -1;
        int shortestpathlength = -1;
        // Find shortest path for all vertices 
        for (int count = 0; count < V - 1; count++) { 
            // Pick the minimum distance vertex from the set of vertices 
            // not yet processed. u is always equal to src in first 
            // iteration. 
        	
	            int u = minDistance(dist, sptSet); 
	  
	            // Mark the picked vertex as processed 
	            sptSet[u] = true; 
	  
	            // Update dist value of the adjacent vertices of the 
	            // picked vertex. 
	            for (int v = 0; v < V; v++) {
	            	
	                // Update dist[v] only if is not in sptSet, there is an 
	                // edge from u to v, and total weight of path from src to 
	                // v through u is smaller than current value of dist[v] 
	                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) 
	                    dist[v] = dist[u] + graph[u][v]; 

	            }
	       
	         if(count == arritem) {
	   	    	 if(shortestpathlength == -1 || shortestpathlength > dist[count]) {
	   	         	shortestpathlength = dist[count];
	   	         	nearesthospital = arritem;
	   	         }
	   	         if(arrindex + 1 != hospitalArr.length) {
	   	         	
	   		            arritem =  hospitalArr[arrindex+1];
	   		            arrindex++;
	   	
	   	         }
	   	     }
	           
        } 
	   
        // print the constructed distance array 
        printSolution(shortestpathlength,nearesthospital); 
      //  printSolution(dist);
    }
    
    public int[] hospitalArr() {
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
		  
		  for (int index = 1; index < hospitalarr.length; index++)
			{
				int key = hospitalarr[index];
				int position = index;
				// Shift larger values to the right
				while (position > 0 && hospitalarr[position-1] - key > 0)
				{
					hospitalarr[position] = hospitalarr[position-1];
					position--;
				}
				hospitalarr[position] = key;
			}
		
		  return hospitalarr;
    }
  
}
