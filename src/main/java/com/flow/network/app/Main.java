package com.flow.network.app;

import com.flow.network.app.view.component.ButtonPanel;
import com.flow.network.app.view.GraphController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private GraphController graphController = new GraphController();

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setCenter(graphController.getMainPane());
        ButtonPanel buttonPanel = new ButtonPanel(graphController);
        root.setTop(buttonPanel.getButtonPane(primaryStage));
        Scene scene = new Scene(root, 1024, 768);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
