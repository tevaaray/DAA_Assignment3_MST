package a3mst;


import a3mst.io.JsonGraphReader;
import a3mst.model.*;
import a3mst.algo.*;

import java.io.FileWriter;
import java.nio.file.Path;
import java.util.*;

public class App {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java -jar daa-a3-mst.jar <path-to-graph.json>");
            System.exit(1);
        }
        String path = args[0];
        Graph g = JsonGraphReader.load(path);

        KruskalMST kr = new KruskalMST();
        PrimMST pr = new PrimMST();

        MSTResult rK = kr.compute(g);
        MSTResult rP = pr.compute(g, 0); // стартуем из вершины 0 (первый район)

        printResult(g, rK);
        printResult(g, rP);

        if (Math.abs(rK.totalCost - rP.totalCost) > 1e-9) {
            System.err.println("WARNING: MST costs differ! Kruskal=" + rK.totalCost + " Prim=" + rP.totalCost);
        }

        String csvName = "mst_metrics.csv";
        writeCsv(csvName, g, rK, rP);
        System.out.println("\nMetrics saved to: " + Path.of(csvName).toAbsolutePath());
    }

    private static void printResult(Graph g, MSTResult r) {
        System.out.println("\n=== " + r.algorithm + " MST ===");
        System.out.println("Vertices: " + r.nVertices + ", Edges: " + r.nEdges);
        System.out.println("Total cost: " + r.totalCost);
        System.out.println("Operations: " + r.opCount + ", Time (ms): " + r.timeMillis);
        System.out.println("MST edges (by vertex index and weight):");
        for (Edge e : r.mstEdges) {
            String u = g.labels.get(e.u);
            String v = g.labels.get(e.v);
            System.out.println("  " + u + " - " + v + "  (" + e.w + ")");
        }
    }

    private static void writeCsv(String file, Graph g, MSTResult... results) {
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("algorithm,vertices,edges,total_cost,op_count,time_ms,edges_list\n");
            for (MSTResult r : results) {
                String edgesStr = toEdgeListString(g, r.mstEdges);
                fw.write(String.join(",",
                        r.algorithm,
                        String.valueOf(r.nVertices),
                        String.valueOf(r.nEdges),
                        String.valueOf(r.totalCost),
                        String.valueOf(r.opCount),
                        String.valueOf(r.timeMillis),
                        "\"" + edgesStr + "\""
                ));
                fw.write("\n");
            }
        } catch (Exception ex) {
            System.err.println("Failed to write CSV: " + ex.getMessage());
        }
    }

    private static String toEdgeListString(Graph g, List<Edge> edges) {
        List<String> parts = new ArrayList<>();
        for (Edge e : edges) {
            parts.add(g.labels.get(e.u) + "-" + g.labels.get(e.v) + ":" + e.w);
        }
        return String.join(" | ", parts);
    }
}
