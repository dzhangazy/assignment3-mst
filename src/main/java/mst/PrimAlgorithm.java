package mst;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.*;

public class PrimAlgorithm {

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

        Set<String> visited = new HashSet<>();
        List<Edge> allEdges = new ArrayList<>(g.getEdges());
        res.operations = 0;

        if (g.getVertices().isEmpty()) return res;

        String startVertex = g.getVertices().get(0);
        visited.add(startVertex);

        while (visited.size() < g.getVertices().size()) {
            Edge best = null;
            for (Edge e : allEdges) {
                if (visited.contains(e.getFrom()) && !visited.contains(e.getTo())
                        || visited.contains(e.getTo()) && !visited.contains(e.getFrom())) {
                    if (best == null || e.getWeight() < best.getWeight()) best = e;
                }
                res.operations++;
            }

            if (best != null) {
                res.mstEdges.add(best);
                res.totalCost += best.getWeight();
                visited.add(best.getFrom());
                visited.add(best.getTo());
            } else break;
        }

        res.execTime = (System.nanoTime() - start) / 1_000_000.0;
        return res;
    }
}
