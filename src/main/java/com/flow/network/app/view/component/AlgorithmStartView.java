package com.flow.network.app.view.component;

import com.flow.network.app.graph.FlowNetworkAlgorithm;
import com.flow.network.app.graph.Graph;
import com.flow.network.app.graph.Vertex;
import com.flow.network.app.view.GraphController;
import com.flow.network.app.view.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlgorithmStartView {

    private Stage parentStage;
    private GraphController graphController;

    public AlgorithmStartView(Stage parentStage, GraphController graphController) {
        this.parentStage = parentStage;
        this.graphController = graphController;
    }

    public void showModal() {
        Stage modalStage = initStage();
        HBox buttonPanel = createButtonsPanel(modalStage);
        Scene modalScene = new Scene(buttonPanel, 500, 50);
        modalStage.setScene(modalScene);
        modalStage.show();
    }

    private HBox createButtonsPanel(Stage modalStage) {
        HBox buttonsPanel = new HBox();
        ObservableList<String> vertices = FXCollections.observableArrayList(graphController.getAllVertexIds());
        Label sourceLabel = new Label("Id source Vertex: ");
        Label targetLabel = new Label("Id target Vertex: ");
        ComboBox sourceVertexCombobox = createVertexCombobox(vertices, 0);
        ComboBox targetVertexCombobox = createVertexCombobox(vertices, 1);
        buttonsPanel.getChildren().addAll(sourceLabel, sourceVertexCombobox, targetLabel, targetVertexCombobox, createRunButton(modalStage, sourceVertexCombobox, targetVertexCombobox));
        return buttonsPanel;
    }

    private ComboBox createVertexCombobox(ObservableList<String> vertices, int selectedIndex) {
        ComboBox sourceVertexCombobox = new ComboBox(vertices);
        sourceVertexCombobox.getSelectionModel().select(selectedIndex);
        return sourceVertexCombobox;
    }

    private Stage initStage() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(parentStage);
        return dialog;
    }

    private Button createRunButton(Stage stage, ComboBox sourceVertexCombobox, ComboBox targetVertexCombobox) {
        Button button = ButtonFactory.createBaseButton("Run");
        button.setOnAction(event -> {
            Graph graph = new Graph(ObjectMapper.mapToEdges(graphController.getEdges()), ObjectMapper.mapToVertices(graphController.getVertices()));
            Vertex startVertex = getVertexFromComboboxSelection(sourceVertexCombobox);
            Vertex endVertex = getVertexFromComboboxSelection(targetVertexCombobox);

            int maxFlow = FlowNetworkAlgorithm.executeFordFulkerson(graph, startVertex, endVertex);
            stage.close();
            showResult(maxFlow);
        });
        return button;
    }

    private void showResult(int maxFlow) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "MAX FLOW = " + maxFlow, ButtonType.OK);
        alert.showAndWait();
    }

    private Vertex getVertexFromComboboxSelection(ComboBox comboBox) {
        String selectedId = comboBox.getSelectionModel().getSelectedItem().toString();
        return ObjectMapper.mapToVertex(graphController.getVertexById(selectedId));
    }


}
