package a3mst;

import a3mst.io.JsonGraphReader;
import a3mst.model.*;
import a3mst.algo.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
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

        try (FileReader fr = new FileReader(path)) {
            JsonObject root = JsonParser.parseReader(fr).getAsJsonObject();
            JsonArray graphs = root.getAsJsonArray("graphs");

            String csvName = "mst_metrics.csv";
            try (FileWriter fw = new FileWriter(csvName)) {
                fw.write("graph_id,type,algorithm,vertices,edges,total_cost,op_count,time_ms,edges_list\n");

                for (int i = 0; i < graphs.size(); i++) {
                    JsonObject graphObj = graphs.get(i).getAsJsonObject();
                    int id = graphObj.get("id").getAsInt();
                    String type = graphObj.get("type").getAsString();

                    Graph g = JsonGraphReader.parseGraphObject(graphObj); // new helper method

                    KruskalMST kr = new KruskalMST();
                    PrimMST pr = new PrimMST();

                    MSTResult rK = kr.compute(g);
                    MSTResult rP = pr.compute(g, 0);

                    printResult(g, rK, id, type);
                    printResult(g, rP, id, type);

                    writeCsvLine(fw, g, rK, id, type);
                    writeCsvLine(fw, g, rP, id, type);
                }

                System.out.println("\nAll results saved to: " + Path.of(csvName).toAbsolutePath());
            }

        } catch (Exception e) {
            System.err.println("Error while reading graphs: " + e.getMessage());
        }
    }

    private static void printResult(Graph g, MSTResult r, int id, String type) {
        System.out.println("\n=== " + r.algorithm + " MST (" + type + " graph #" + id + ") ===");
        System.out.println("Vertices: " + r.nVertices + ", Edges: " + r.nEdges);
        System.out.println("Total cost: " + r.totalCost);
        System.out.println("Operations: " + r.opCount + ", Time (ms): " + r.timeMillis);
        System.out.println("MST edges:");
        for (Edge e : r.mstEdges) {
            String u = g.labels.get(e.u);
            String v = g.labels.get(e.v);
            System.out.println("  " + u + " - " + v + "  (" + e.w + ")");
        }
    }

    private static void writeCsvLine(FileWriter fw, Graph g, MSTResult r, int id, String type) throws Exception {
        String edgesStr = toEdgeListString(g, r.mstEdges);
        fw.write(String.join(",",
                String.valueOf(id),
                type,
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

    private static String toEdgeListString(Graph g, List<Edge> edges) {
        List<String> parts = new ArrayList<>();
        for (Edge e : edges) {
            parts.add(g.labels.get(e.u) + "-" + g.labels.get(e.v) + ":" + e.w);
        }
        return String.join(" | ", parts);
    }
}
