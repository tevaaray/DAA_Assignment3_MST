package a3mst.model;

import java.util.*;

public class Graph {
    public final int n;                       // number of vertices
    public final List<String> labels;         // names of vertices
    public final List<Edge> edges;            // all edges
    public final List<List<Edge>> adj;        // adjacency list for Prim's algorithm

    public Graph(List<String> labels, List<Edge> edges) {
        this.labels = List.copyOf(labels);
        this.n = labels.size();
        this.edges = List.copyOf(edges);
        this.adj = new ArrayList<>(n);

        // create empty adjacency lists
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        // add edges in both directions (undirected graph)
        for (Edge e : edges) {
            adj.get(e.u).add(e);
            adj.get(e.v).add(new Edge(e.v, e.u, e.w));
        }
    }

    public int vertexCount() { return n; }
    public int edgeCount() { return edges.size(); }
}
