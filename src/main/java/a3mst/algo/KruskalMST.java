package a3mst.algo;

import a3mst.model.Edge;
import a3mst.model.Graph;
import a3mst.model.MSTResult;

import java.util.*;

public class KruskalMST {

    public MSTResult compute(Graph g) {
        long ops = 0L; // operation counter for algorithmic actions
        long t0 = System.nanoTime(); // start time

        // Step 1: sort all edges by weight in ascending order
        List<Edge> sorted = new ArrayList<>(g.edges);
        sorted.sort((a, b) -> {
            // each comparison between edge weights is one operation
            return Double.compare(a.w, b.w);
        });

        // Step 2: initialize Union-Find and start building MST
        UnionFind uf = new UnionFind(g.n); // used to detect cycles
        List<Edge> mst = new ArrayList<>(); // will store edges of MST
        double total = 0.0; // total weight of MST

        // iterate through sorted edges
        for (Edge e : sorted) {
            int u = e.u, v = e.v;

            // if edge connects two different sets -> add it to MST
            if (uf.union(u, v)) {
                mst.add(e);
                total += e.w;

                // stop if MST already has (n - 1) edges
                if (mst.size() == g.n - 1) break;
            }
        }

        // add all union/find operations to total operation count
        ops += uf.opCount;

        // measure execution time
        long t1 = System.nanoTime();
        double ms = (t1 - t0) / 1_000_000.0;

        // return result with all collected data
        return new MSTResult(
                "Kruskal",   // algorithm name
                mst,          // list of MST edges
                total,        // total weight of MST
                g.n,          // number of vertices
                g.edgeCount(),// number of edges
                ops,          // number of operations
                ms            // execution time in ms
        );
    }
}
