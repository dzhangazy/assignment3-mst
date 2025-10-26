package mst;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.*;

public class KruskalAlgorithm {

    public static class Result {
        List<Edge> mstEdges = new ArrayList<>();
        double totalCost;
        int operations;
        double execTime;

        // ✅ метод для преобразования в JSON
        public JSONObject toJSON() {
            JSONObject obj = new JSONObject();
            JSONArray edgesArray = new JSONArray();

            for (Edge e : mstEdges) {
                JSONObject edgeObj = new JSONObject();
                edgeObj.put("from", e.getFrom());
                edgeObj.put("to", e.getTo());
                edgeObj.put("weight", e.getWeight());
                edgesArray.add(edgeObj);
            }

            obj.put("mst_edges", edgesArray);
            obj.put("total_cost", totalCost);
            obj.put("operations_count", operations);
            obj.put("execution_time_ms", execTime);
            return obj;
        }
    }

    public Result run(Graph g) {
        long start = System.nanoTime();
        Result res = new Result();
        res.operations = 0;

        // Disjoint-set (Union-Find)
        Map<String, String> parent = new HashMap<>();
        for (String v : g.getVertices()) parent.put(v, v);

        Comparator<Edge> byWeight = Comparator.comparingDouble(Edge::getWeight);
        List<Edge> edges = new ArrayList<>(g.getEdges());
        edges.sort(byWeight);

        for (Edge e : edges) {
            res.operations++;
            String rootU = find(parent, e.getFrom());
            String rootV = find(parent, e.getTo());
            if (!rootU.equals(rootV)) {
                res.mstEdges.add(e);
                res.totalCost += e.getWeight();
                parent.put(rootU, rootV);
            }
        }

        res.execTime = (System.nanoTime() - start) / 1_000_000.0;
        return res;
    }

    private String find(Map<String, String> parent, String v) {
        if (!parent.get(v).equals(v)) {
            parent.put(v, find(parent, parent.get(v)));
        }
        return parent.get(v);
    }
}
