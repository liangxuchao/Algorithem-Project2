package examples.visualization;

import java.util.ArrayList;
import java.util.LinkedList;

public class Algorithm {
    public static int V;
    public static GraphModel Model;

    public Algorithm(int V, GraphModel M) {
        this.V = V;
        this.Model = M;
    }

    public ArrayList<ArrayList<Integer>> printTopNShortest(int s, int n){

        int pred[] = new int[V];
        int dist[] = new int[V];
        ArrayList<ArrayList<Integer>> paths = new ArrayList<>();
        int count=0;

        ArrayList<BFSResult> BFSResult = BFS2(Model, s, V, pred, dist);
        if (BFSResult.size()==0) {
            return paths;
        }else{
            for(examples.visualization.BFSResult result: BFSResult){
                count++;
                ArrayList<Integer> path = new ArrayList<>();
                int crawl = result.Target;
                path.add(crawl);
                while (pred[crawl] != -1) {
                    path.add(pred[crawl]);
                    crawl = pred[crawl];
                }

                paths.add(path);
                if(count >= n)
                    break;
            }
        }

        return paths;
    }


    // a modified version of BFS that stores predecessor
    // of each vertex in array pred
    // and its distance from source in array dist
    private ArrayList<BFSResult> BFS2(GraphModel Model, int src
            , int v, int pred[], int dist[]) {
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
        int rank=1;
        ArrayList<BFSResult> results = new ArrayList<>();

        int[] returnArr = new int[2];
        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();


            for (int i = 0; i < Model.adj.get(u).gettargetNodes().size(); i++) {
                if (visited[Model.adj.get(u).gettargetNodes().get(i)] == false) {
                    visited[Model.adj.get(u).gettargetNodes().get(i)] = true;
                    dist[Model.adj.get(u).gettargetNodes().get(i)] = dist[u] + 1;
                    pred[Model.adj.get(u).gettargetNodes().get(i)] = u;
                    queue.add(Model.adj.get(u).gettargetNodes().get(i));

                    int target = Model.adj.get(u).gettargetNodes().get(i);
                    if (Model.adj.get(target).getishospital() == true && dist[target] > 0) {

                        BFSResult result = new BFSResult();
                        result.Rank = rank;
                        result.Target=target;
                        rank++;
                        results.add(result);
                    }
                }
            }
        }

        return results;
    }
}
