package examples.visualization;

import java.util.ArrayList;
import java.util.LinkedList;

import java.util.Queue;
import java.util.Stack;

public class Algorithm_multiBFS {
	 	public static int V;
	    public static GraphModel Model;
	    public static boolean visited[];
	    public static int dist[];
	    public Algorithm_multiBFS(int V, GraphModel M) {
	        this.V = V;
	        this.Model = M;
	        this.visited = new boolean[V];
	        this.dist = new int[V];
	    }

        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        
        public ArrayList<BFSResult_new> Multisource_BFS(GraphModel Model, LinkedList<Integer> q) 
	    { 
	    	ArrayList<BFSResult_new> BFSResultList = new ArrayList<BFSResult_new>();
	    	for(int z=0; z< V; z++) {

	        	BFSResult_new BFSResult = new BFSResult_new(z);
	        	BFSResultList.add(BFSResult);
	    	}
	        while(!q.isEmpty()) 
	        { 
	        	int k = q.remove();

	            ArrayList<Integer> targets = Model.adj.get(k).getTargetNodes();
	            for(int i =0; i< targets.size(); i++) 
	            { 
	            	
	                if(!visited[targets.get(i)]) 
	                { 
	                    // Pushing the adjacent unvisited vertices 
	                    // with distance from current source = this 
	                    // vertex's distance + 1 
	                	
	                    q.add(targets.get(i)); 
	                    dist[targets.get(i)] = dist[k] + 1;  
	                    visited[targets.get(i)] = true; 

		                BFSResultList.get(k).AddPaths(targets.get(i));
	                } 
                	
	            } 
	        } 
	        
	        return BFSResultList;
	    } 
	      
	      
	    // This function calculates the distance of each  
	    // vertex from nearest source 
        public void nearestTown(GraphModel Model,int n,int sources[],int s) 
	    { 
	        //Create a queue for BFS 
        	LinkedList<Integer> q = new LinkedList<Integer>(); 
	      
	        //Mark all the source vertices as visited and enqueue it  
	        for(int i = 0;i < s; i++) 
	        { 
	        
	            q.add(sources[i]); 
	            visited[sources[i]]=true; 
	        } 
	      
	        ArrayList<BFSResult_new> result = Multisource_BFS(Model,q); 
	      
	      
	        //Printing the distances 
	        for(int i = 0; i < n; i++) 
	        { 	
	        	ArrayList<Integer> r = result.get(i).getPaths();
	        	for(int j=0; j<r.size();j++) {

		            System.out.println(i + " " + dist[i] + " " + r.get(j) + "\n"); 
	        	}
	        	
	        	
	        } 
	      
	    } 
	      
}
