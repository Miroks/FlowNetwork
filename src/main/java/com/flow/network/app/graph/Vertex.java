package com.flow.network.app.graph;

import java.util.Objects;

public class Vertex {

    private final int vertexId;

    public Vertex(int vertexId) {
        this.vertexId = vertexId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return vertexId == vertex.vertexId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexId);
    }

    @Override
    public String toString(){
        return " "+vertexId;
    }
}
