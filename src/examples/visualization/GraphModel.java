package examples.visualization;
 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*; 
 
public class GraphModel {
	 // A utility function to add an edge in an 
    // undirected graph 
	public static ArrayList<ArrayList<Integer> >adj;
    public static void addEdge(ArrayList<ArrayList<Integer> > adj, 
                        int u, int v) 
    { 
        adj.get(u).add(v); 
        adj.get(v).add(u); 
    } 
    public static    int V = 1088092 ; 
    // A utility function to print the adjacency list 
    // representation of graph 
    static void printGraph(ArrayList<ArrayList<Integer> > adj) 
    { 
        for (int i = 0; i < adj.size(); i++) { 
            System.out.println("\nAdjacency list of vertex" + i); 
            System.out.print("head"); 
            for (int j = 0; j < adj.get(i).size(); j++) { 
                System.out.print(" -> "+adj.get(i).get(j)); 
            } 
            System.out.println(); 
        } 
    } 
  
    public static LinkedList<Integer> printShortestDistance( int s, int dest)
	{
	// predecessor[i] array stores predecessor of
	// i and distance array stores distance of i
	// from s
		int pred[] = new int[V];
		int dist[] = new int[V];

		// LinkedList to store path
		LinkedList<Integer> path = new LinkedList<Integer>();
		if (BFS(adj, s, dest, V, pred, dist) == false) {
		   System.out.println("Given source and destination" + 
		                                "are not connected");
		  
		}else {
		
			int crawl = dest;
			path.add(crawl);
			while (pred[crawl] != -1) {
			   path.add(pred[crawl]);
			   crawl = pred[crawl];
			}
			
			// Print distance
			System.out.println("Shortest path length is: " + dist[dest]);
			
			// Print path
			System.out.println("Path is ::");
			for (int i = path.size() - 1; i >= 0; i--) {
			   System.out.print(path.get(i) + " ");
			}
		}
		return path;
	}

 // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                                  int dest, int v, int pred[], int dist[])
    {
        // a queue to maintain queue of vertices whose
        // adjacency list is to be scanned as per normal
        // BFS algorithm using LinkedList of Integer type
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // boolean array visited[] which stores the
        // information whether ith vertex is reached
        // at least once in the Breadth first search
        boolean visited[] = new boolean[v];
 
        // initially all vertices are unvisited
        // so v[i] for all i is false
        // and as no path is yet constructed
        // dist[i] for all i set to infinity
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            dist[i] = Integer.MAX_VALUE;
            pred[i] = -1;
        }
 
        // now source is first to be visited and
        // distance from source to itself should be 0
        visited[src] = true;
        dist[src] = 0;
        queue.add(src);
 
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (visited[adj.get(u).get(i)] == false) {
                    visited[adj.get(u).get(i)] = true;
                    dist[adj.get(u).get(i)] = dist[u] + 1;
                    pred[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
 
                    // stopping condition (when we find
                    // our destination)
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
    
    
    // Driver Code 
    public GraphModel()
    { 
        // Creating a graph with 1088092  vertices 
     
        adj = new ArrayList<ArrayList<Integer> >(V); 

        for (int i = 0; i < V; i++) 
            adj.add(new ArrayList<Integer>()); 

		BufferedReader br = null; 
        
        try {
            br = new BufferedReader(new FileReader("karate/roadNet-PA_demo.txt"));
            br.readLine();
            Scanner scan = new Scanner(br);
            String ss = "";
            while(scan.hasNextLine()) {
          	  ss = scan.nextLine(); 
                if(!ss.startsWith("#")) { 
              	  String[] arr = ss.split("\\s+");
      				
                      addEdge(adj, Integer.parseInt(arr[0]),Integer.parseInt(arr[1]));
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

       
    } 
}
