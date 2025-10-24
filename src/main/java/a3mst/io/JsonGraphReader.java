package a3mst.io;

import a3mst.model.Edge;
import a3mst.model.Graph;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.*;

public class JsonGraphReader {

    /**
     * Loads the first graph from a JSON file (default for single-run mode).
     */
    public static Graph load(String path) {
        try (FileReader fr = new FileReader(path)) {
            JsonObject root = JsonParser.parseReader(fr).getAsJsonObject();

            // main "graphs" array
            JsonArray graphs = root.getAsJsonArray("graphs");
            if (graphs == null || graphs.size() == 0)
                throw new IllegalArgumentException("No graphs found in input JSON");

            // take the first graph
            JsonObject graphObj = graphs.get(0).getAsJsonObject();

            return parseGraphObject(graphObj);

        } catch (Exception ex) {
            throw new RuntimeException("Failed to load graph JSON: " + ex.getMessage(), ex);
        }
    }

    /**
     * Parses a single graph object from JSON structure.
     * Used when iterating through multiple graphs.
     */
    public static Graph parseGraphObject(JsonObject graphObj) {
        // read vertex list
        JsonArray nodesArr = graphObj.getAsJsonArray("nodes");
        if (nodesArr == null) throw new IllegalArgumentException("Missing 'nodes' array");
        List<String> labels = new ArrayList<>();
        Map<String, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < nodesArr.size(); i++) {
            String label = nodesArr.get(i).getAsString();
            labels.add(label);
            indexOf.put(label, i);
        }

        // read edge list
        JsonArray edgesArr = graphObj.getAsJsonArray("edges");
        if (edgesArr == null) throw new IllegalArgumentException("Missing 'edges' array");
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < edgesArr.size(); i++) {
            JsonObject e = edgesArr.get(i).getAsJsonObject();
            String from = e.get("from").getAsString();
            String to = e.get("to").getAsString();
            double w = e.get("weight").getAsDouble();

            int u = indexOf.get(from);
            int v = indexOf.get(to);
            if (u == v) continue; // ignore self-loops
            if (u > v) { int t = u; u = v; v = t; } // keep consistent order
            edges.add(new Edge(u, v, w));
        }

        // return final Graph object
        return new Graph(labels, edges);
    }
}
