package miscellaneous;

import java.util.LinkedList;

public class Graph {
    public int v;//顶点的个数
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v;i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void  addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }
}
