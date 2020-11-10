package examples.visualization;

import java.util.ArrayList;

public class BFSResult_new {
	 public int Rank;
	 public int Source;
	 public ArrayList<Integer> Paths;
	 
	 public BFSResult_new(int s) {
		 this.Paths = new ArrayList<Integer>();
		 this.Source = s;
		 
	 }
		public void AddPaths(int t) {
			this.Paths.add(t);
		}
		public ArrayList<Integer>  getPaths(){
			return this.Paths;
		}
}
