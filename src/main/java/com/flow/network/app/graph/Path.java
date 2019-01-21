package com.flow.network.app.graph;

import java.util.HashMap;
import java.util.Map;

public class Path {

    private Vertex start;
    private Map<Vertex, Vertex> parentToVertexMapping;

    public Path(Vertex firstElement) {
        parentToVertexMapping = new HashMap<> ();
        start = firstElement;
    }

    public void addToPath(Vertex vertex, Vertex parent) {
        parentToVertexMapping.put(vertex, parent);
    }

    public Map<Vertex, Vertex> getParent() {
        return parentToVertexMapping;
    }

    public boolean pathExists() {
        return !parentToVertexMapping.isEmpty();
    }

    public void clearPath() {
        this.parentToVertexMapping = new HashMap<>();
    }
}
