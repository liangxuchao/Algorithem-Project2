package examples.visualization;

import java.util.ArrayList;

public class NodeModel {
	private int nodeid;
	private boolean ishospital;
	private ArrayList<Integer> targetNodes;
	
	public NodeModel(int nodeid, boolean ishospital) {
		this.nodeid = nodeid;
		this.ishospital = ishospital;
		this.targetNodes = new ArrayList<Integer>();
	}
	
	public void Addtarget(int t) {
		this.targetNodes.add(t);
	}
	
	public void Setishospital(boolean s) {
		this.ishospital = s;
	}
	
	public ArrayList<Integer> gettargetNodes(){
		return this.targetNodes;
	}
	
	public boolean getishospital(){
		return this.ishospital;
	}
}
