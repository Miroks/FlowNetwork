package com.flow.network.app.graph;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Graph {

    private Map<Edge, Integer> weightToEdgeMapping;
    private Set<Vertex> vertices;

    public Graph(List<Edge> edges, Set<Vertex> vertices) {
        this.weightToEdgeMapping = edges.stream()
            .collect(Collectors.toMap(Function.identity(), Edge::getWeight));
        this.vertices = vertices;
    }

    public Map<Edge, Integer> getWeightToEdgeMapping() {
        return weightToEdgeMapping;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }
}
