package com.flow.network.app.view;

import com.flow.network.app.view.handler.DragListener;
import com.flow.network.app.view.handler.EdgeSelector;
import com.flow.network.app.view.model.EdgeModel;
import com.flow.network.app.view.model.VertexModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.*;
import java.util.stream.Collectors;

public class GraphController {
    private DragListener dragListener;
    private EdgeSelector edgeSelector;
    private AnchorPane mainPane;
    private Set<VertexModel> vertices = new HashSet<>();
    private Map<String, VertexModel> vertexIdMapping = new HashMap<>();
    private List<EdgeModel> edges = new ArrayList<>();

    public GraphController() {
        edgeSelector = new EdgeSelector();
        dragListener = new DragListener();
        mainPane = new AnchorPane();
    }

    public void addCell(VertexModel vertex) {
        mainPane.getChildren().add(vertex);
        addEventHandlers(vertex);
        vertices.add(vertex);
        vertexIdMapping.put(vertex.getCellId(), vertex);
    }

    private void addEventHandlers(VertexModel vertex) {
        dragListener.makeDraggable(vertex);
        edgeSelector.makeClickable(vertex);
    }

    public void clearGraph() {
        mainPane.getChildren().clear();
        vertices.clear();
        edges.clear();
        vertexIdMapping.clear();
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public void createEdge() {
        List<VertexModel> selectedVertex = edgeSelector.getSelectedVertex();
        VertexModel source = selectedVertex.get(0);
        VertexModel target = selectedVertex.get(1);
        mainPane.getChildren().removeAll(source, target);
        EdgeModel edge = new EdgeModel(source, target);
        edges.add(edge);
        mainPane.getChildren().add(edge);
    }

    public List<String> getAllVertexIds() {
        return vertices.stream()
                .map(VertexModel::getCellId)
                .collect(Collectors.toList());
    }

    public Set<VertexModel> getVertices() {
        return vertices;
    }

    public List<EdgeModel> getEdges() {
        return edges;
    }

    public VertexModel getVertexById(String id) {
        return vertexIdMapping.get(id);
    }

    public EdgeSelector getEdgeSelector() {
        return edgeSelector;
    }

    public void clearEdgeSelection() {
        edgeSelector.clearEdgeSelection();
    }
}
