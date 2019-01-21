package com.flow.network.app.graph;

import java.util.Objects;

public class Edge {
    private final Vertex start;
    private final Vertex end;
    private int weight;

    public Edge(Vertex start, Vertex end) {
        this(start, end, 0);
    }

    public Edge(Vertex start, Vertex end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return start.equals(edge.start) &&
                end.equals(edge.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
