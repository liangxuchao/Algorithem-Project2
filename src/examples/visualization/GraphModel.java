package examples.visualization;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GraphModel {
	public static ArrayList<NodeModel>adj = new ArrayList<>();
	
    public static void addEdge(ArrayList<NodeModel> adj, 
                        int u, int v) 
    { 
        adj.get(u).Addtarget(v); 
        adj.get(v).Addtarget(u); 
    } 
    
    public static int V = 0 ; 
 	public GraphModel(int maxnode, String inputfilename)
    { 
         //Creating a graph with  vertices
    	this.V = maxnode;

        for (int i = 0; i < V; i++) {
        	 NodeModel n = new NodeModel(i,false);
        	 adj.add(n);
        }

        hospitalArr();
		BufferedReader br = null; 
        
        try {
            br = new BufferedReader(new FileReader(inputfilename));
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
 	
	 public static void hospitalArr() {
	   	 int distsize = 0;
		
			 BufferedReader br = null; 
		        
			  try {
		            br = new BufferedReader(new FileReader("karate/hospital.txt"));
		         
		            Scanner scan = new Scanner(br);
		            String FirstLine = br.readLine();
		            String[] FirstLinearr = FirstLine.split("\\s+");
				    distsize = Integer.parseInt(FirstLinearr[1]);
		          
		            
					for(int i=0; i<distsize; i++) {
						
			            if(scan.hasNextLine()) {
			            	int nexInt = Integer.parseInt(scan.nextLine());
			          	  if(nexInt<= V ) {

			          		adj.get(nexInt).Setishospital(true);
			          	  }
			          	  
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
