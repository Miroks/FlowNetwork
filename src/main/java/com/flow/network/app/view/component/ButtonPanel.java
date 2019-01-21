package com.flow.network.app.view.component;

import com.flow.network.app.view.GraphController;
import com.flow.network.app.view.model.VertexModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ButtonPanel {

    private HBox buttonPane;
    private GraphController graphController;

    public ButtonPanel(GraphController graphController) {
        this.graphController = graphController;
    }

    public Pane getButtonPane(Stage primaryStage) {
        initButtonPane(primaryStage);
        return buttonPane;
    }

    private void initButtonPane(Stage primaryStage) {
        buttonPane = new HBox();
        buttonPane.setPadding(new Insets(15, 12, 15, 12));
        buttonPane.setSpacing(10);
        buttonPane.setStyle("-fx-background-color: #336699;");
        buttonPane.getChildren().addAll(createNewVertexButton(), createAddEdgeButton(), createRunFlowButton(primaryStage), createClearGraphButton());
    }

    private Button createNewVertexButton() {
        Button button = ButtonFactory.createBaseButton("Add vertex");
        button.setOnAction(event -> {
            VertexModel vertexModel = new VertexModel();
            graphController.addCell(vertexModel);
            vertexModel.relocate(100, 100);
        });
        return button;
    }

    private Button createAddEdgeButton() {
        Button button = ButtonFactory.createBaseButton("Create edge");
        button.disableProperty().bind(graphController.getEdgeSelector().areTwoVertexSelectedProperty());

        button.setOnAction(event -> {
            graphController.createEdge();
            graphController.clearEdgeSelection();
        });

        return button;
    }

    private Button createRunFlowButton(Stage primaryStage) {
        Button button = ButtonFactory.createBaseButton("Run flow");
        button.setOnAction(event -> {
            AlgorithmStartView algorithmStartView = new AlgorithmStartView(primaryStage, graphController);
            algorithmStartView.showModal();
        });
        return button;
    }


    private Button createClearGraphButton() {
        Button button = ButtonFactory.createBaseButton("Clear graph");
        button.setOnAction(event -> graphController.clearGraph());
        return button;
    }

}
