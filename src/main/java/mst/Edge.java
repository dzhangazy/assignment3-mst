package mst;

public class Edge {
    private String from;
    private String to;
    private double weight;

    // ✅ Конструктор, который ожидался
    public Edge(String from, String to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // --- Геттеры
    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return from + " - " + to + " (" + weight + ")";
    }
}