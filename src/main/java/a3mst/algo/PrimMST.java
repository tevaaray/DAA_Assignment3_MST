package a3mst.algo;

import a3mst.model.Edge;
import a3mst.model.Graph;
import a3mst.model.MSTResult;

import java.util.*;

public class PrimMST {

    private static class PQItem {
        int v, parent;
        double w;
        PQItem(int v, int parent, double w) { this.v = v; this.parent = parent; this.w = w; }
    }

    public MSTResult compute(Graph g, int start) {
        long ops = 0L;
        long t0 = System.nanoTime();

        boolean[] inMST = new boolean[g.n];
        double[] bestW = new double[g.n];
        int[] bestP = new int[g.n];
        Arrays.fill(bestW, Double.POSITIVE_INFINITY);
        Arrays.fill(bestP, -1);

        // priority queue for minimal edge selection
        PriorityQueue<PQItem> pq = new PriorityQueue<>(Comparator.comparingDouble(it -> it.w));
        bestW[start] = 0.0;
        pq.add(new PQItem(start, -1, 0.0)); ops++;

        List<Edge> mst = new ArrayList<>();
        double total = 0.0;

        while (!pq.isEmpty()) {
            PQItem cur = pq.poll(); ops++;
            int v = cur.v;
            if (inMST[v]) continue;
            inMST[v] = true;

            if (cur.parent != -1) {
                mst.add(new Edge(cur.parent, v, cur.w));
                total += cur.w;
            }

            // check neighbors
            for (Edge e : g.adj.get(v)) {
                int to = e.v;
                if (!inMST[to] && e.w < bestW[to]) {
                    bestW[to] = e.w; ops++;
                    bestP[to] = v;
                    pq.add(new PQItem(to, v, e.w)); ops++;
                }
            }
        }

        long t1 = System.nanoTime();
        long ms = (t1 - t0) / 1_000_000;

        return new MSTResult(
                "Prim",
                mst,
                total,
                g.n,
                g.edgeCount(),
                ops,
                ms
        );
    }
}
