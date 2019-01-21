package com.flow.network.app.view;

import com.flow.network.app.graph.Edge;
import com.flow.network.app.graph.Vertex;
import com.flow.network.app.view.model.EdgeModel;
import com.flow.network.app.view.model.VertexModel;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectMapper {


    public static List<Edge> mapToEdges(Collection<EdgeModel> edgeModelList) {
        return edgeModelList.stream()
                .map(ObjectMapper::mapToEdge)
                .collect(Collectors.toList());
    }

    public static Edge mapToEdge(EdgeModel edgeModel) {
        return new Edge(mapToVertex(edgeModel.getSource()), mapToVertex(edgeModel.getTarget()), edgeModel.getEdgeWeight());
    }

    public static Set<Vertex> mapToVertices(Collection<VertexModel> vertexModelList) {
        return vertexModelList.stream()
                .map(ObjectMapper::mapToVertex)
                .collect(Collectors.toSet());
    }

    public static Vertex mapToVertex(VertexModel vertexModel) {
        return new Vertex(Integer.parseInt(vertexModel.getCellId()));
    }

}
