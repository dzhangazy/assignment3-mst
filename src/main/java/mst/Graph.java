package mst;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<String> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    // 👇 добавлено для graph_id
    private int graphId = 0;

    public void setGraphId(int id) { this.graphId = id; }
    public int getGraphId() { return graphId; }

    public void addVertex(String v) {
        if (!vertices.contains(v)) vertices.add(v);
    }

    public void addEdge(String from, String to, double weight) {
        edges.add(new Edge(from, to, weight));
    }

    public List<String> getVertices() { return vertices; }
    public List<Edge> getEdges() { return edges; }

    // Чтение из input.json
    public static List<Graph> fromJSON(String path) {
        List<Graph> graphs = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(new FileReader(path));
            JSONArray graphList = (JSONArray) obj.get("graphs");

            int idCounter = 1; // 👈 авто-нумерация графов

            for (Object o : graphList) {
                JSONObject gObj = (JSONObject) o;
                Graph g = new Graph();
                g.setGraphId(idCounter++); // 👈 присваиваем ID

                JSONArray vertices = (JSONArray) gObj.get("vertices");
                for (Object v : vertices) {
                    g.addVertex((String) v);
                }

                JSONArray edges = (JSONArray) gObj.get("edges");
                for (Object e : edges) {
                    JSONObject edgeObj = (JSONObject) e;
                    String from = (String) edgeObj.get("from");
                    String to = (String) edgeObj.get("to");
                    double w = ((Number) edgeObj.get("weight")).doubleValue();
                    g.addEdge(from, to, w);
                }

                graphs.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return graphs;
    }
}
