package a3mst.model;

import java.util.*;

public class MSTResult {
    public final String algorithm;   // algorithm name
    public final List<Edge> mstEdges; // edges in MST
    public final double totalCost;    // total MST cost
    public final int nVertices;
    public final int nEdges;
    public final long opCount;        // number of key operations
    public final double timeMillis;   // execution time in ms

    public MSTResult(String algorithm, List<Edge> mstEdges, double totalCost,
                     int nVertices, int nEdges, long opCount, double timeMillis) {
        this.algorithm = algorithm;
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.nVertices = nVertices;
        this.nEdges = nEdges;
        this.opCount = opCount;
        this.timeMillis = timeMillis;
    }
}
