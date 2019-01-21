package com.flow.network.app.view.handler;

import com.flow.network.app.view.model.VertexModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.LinkedList;
import java.util.List;

public class EdgeSelector {

    private List<VertexModel> selectedVertex = new LinkedList<>();
    private BooleanProperty areTwoVertexSelected = new SimpleBooleanProperty(true);

    public EdgeSelector() {
    }

    public void makeClickable(VertexModel vertex) {
        vertex.setOnMouseClicked(event -> {
            if(!isSecondaryMouseButton(event)) return;

            if(selectedVertex.contains(vertex)) {
                vertex.setStyle("");
                selectedVertex.remove(vertex);
            } else {
                vertex.setStyle("-fx-background-color: #333333");

                if(selectedVertex.size() == 2) {
                    Node removedNode = selectedVertex.remove(0);
                    removedNode.setStyle("");
                }
                selectedVertex.add(vertex);
            }
            areTwoVertexSelected.setValue(selectedVertex.size() != 2);
        });
    }

    private boolean isSecondaryMouseButton(MouseEvent event) {
        return event.getButton() == MouseButton.SECONDARY;
    }

    public List<VertexModel> getSelectedVertex() {
        return selectedVertex;
    }

    public BooleanProperty areTwoVertexSelectedProperty() {
        return areTwoVertexSelected;
    }

    public void clearEdgeSelection() {
        selectedVertex.forEach(vertex -> vertex.setStyle(""));
        selectedVertex.clear();
        areTwoVertexSelected.setValue(selectedVertex.size() != 2);
    }
}
