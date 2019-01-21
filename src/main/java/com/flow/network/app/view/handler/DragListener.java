package com.flow.network.app.view.handler;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DragListener {

    private double x;
    private double y;
    EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
        if (!isPrimaryMouseButton(event)) return;
        Node node = (Node) event.getSource();
        x = node.getBoundsInParent().getMinX() - event.getScreenX();
        y = node.getBoundsInParent().getMinY() - event.getScreenY();
    };
    EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
        if (!isPrimaryMouseButton(event)) return;
        Node node = (Node) event.getSource();
        double offsetX = event.getScreenX() + x;
        double offsetY = event.getScreenY() + y;
        node.relocate(offsetX, offsetY);
    };

    public DragListener() {
    }

    public void makeDraggable(Node node) {
        node.setOnMousePressed(onMousePressedEventHandler);
        node.setOnMouseDragged(onMouseDraggedEventHandler);
    }

    private boolean isPrimaryMouseButton(MouseEvent event) {
        return event.getButton() == MouseButton.PRIMARY;
    }
}
