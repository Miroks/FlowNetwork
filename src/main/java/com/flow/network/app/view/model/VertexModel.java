package com.flow.network.app.view.model;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.Objects;

public class VertexModel extends Pane {

    private static int vertexId = 1;
    private String cellId;

    public VertexModel() {
        super();
        cellId = String.valueOf(vertexId++);
        StackPane stack = createVertexPane();
        getChildren().add(stack);
    }

    private StackPane createVertexPane() {
        Circle circle = createVertexShape();
        Text vertexLabel = createVertexLabel();
        StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, vertexLabel);
        return stack;
    }

    private Circle createVertexShape() {
        Circle circle = new Circle(30);
        circle.setId(cellId);
        circle.setStroke(Color.RED);
        circle.setFill(Color.RED);
        return circle;
    }

    private Text createVertexLabel() {
        Text text = new Text(cellId);
        text.setBoundsType(TextBoundsType.VISUAL);
        return text;
    }


    public String getCellId() {
        return cellId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexModel that = (VertexModel) o;
        return Objects.equals(cellId, that.cellId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellId);
    }
}
