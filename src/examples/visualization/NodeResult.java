package examples.visualization;

import java.util.ArrayList;

public class NodeResult {
    public int NodeId;
    public ArrayList<ArrayList<Integer>> PathList;

    public  NodeResult(){};
    public NodeResult(int nodeId,  ArrayList<ArrayList<Integer>> pathList){
        this.NodeId=nodeId;
        this.PathList=pathList;
    }
}

