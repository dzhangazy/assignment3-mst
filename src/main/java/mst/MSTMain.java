package mst;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.util.List;

public class MSTMain {
    public static void main(String[] args) {
        List<Graph> graphs = Graph.fromJSON("src/main/resources/input.json");
        JSONArray resultsArray = new JSONArray();

        for (Graph g : graphs) {
            System.out.println("Processing Graph #" + g.getGraphId() + "...");

            JSONObject result = new JSONObject();
            result.put("graph_id", g.getGraphId());

            // Входные данные
            JSONObject inputStats = new JSONObject();
            inputStats.put("vertices", g.getVertices().size());
            inputStats.put("edges", g.getEdges().size());
            result.put("input_stats", inputStats);

            // Prim Algorithm
            PrimAlgorithm prim = new PrimAlgorithm();
            JSONObject primResult = prim.run(g).toJSON();
            result.put("prim", primResult);

            // Kruskal Algorithm
            KruskalAlgorithm kruskal = new KruskalAlgorithm();
            JSONObject kruskalResult = kruskal.run(g).toJSON();
            result.put("kruskal", kruskalResult);

            resultsArray.add(result);
        }

        JSONObject finalOutput = new JSONObject();
        finalOutput.put("results", resultsArray);

        try (FileWriter file = new FileWriter("src/main/resources/output.json")) {
            file.write(finalOutput.toJSONString());
            System.out.println("✅ Results written to src/main/resources/output.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
