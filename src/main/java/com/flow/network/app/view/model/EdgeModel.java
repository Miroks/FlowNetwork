package com.flow.network.app.view.model;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class EdgeModel extends Group {

    private VertexModel source;
    private VertexModel target;
    private StringProperty weight = new SimpleStringProperty();

    public EdgeModel(VertexModel source, VertexModel target) {
        this.source = source;
        this.target = target;
        Line line = createAndBindLine(source, target);
        TextField weightField = createWeightInputAndBind(source, target);
        Text text = createEdgeDirectionAndBind(source, target, weightField);
        getChildren().addAll(line, weightField, source, target, text);
    }

    private Text createEdgeDirectionAndBind(VertexModel source, VertexModel target, TextField weightField) {
        Text text = new Text("From: " + source.getCellId() + " to: " + target.getCellId());
        text.layoutXProperty().bind(weightField.layoutXProperty());
        text.layoutYProperty().bind(weightField.layoutYProperty());
        weight.bind(weightField.textProperty());
        return text;
    }

    private TextField createWeightInputAndBind(VertexModel source, VertexModel target) {
        TextField weightField = getWeightView();
        DoubleBinding xWeightFieldProperty = Bindings.createDoubleBinding(() -> (source.layoutXProperty().get() + (target.layoutXProperty().get() - source.layoutXProperty().get()) / 2.0), source.layoutXProperty(), target.layoutXProperty());
        DoubleBinding yWeightFieldProperty = Bindings.createDoubleBinding(() -> (source.layoutYProperty().get() + (target.layoutYProperty().get() - source.layoutYProperty().get()) / 2.0), source.layoutYProperty(), target.layoutYProperty());
        weightField.layoutXProperty().bind(xWeightFieldProperty);
        weightField.layoutYProperty().bind(yWeightFieldProperty);
        weightField.setMaxWidth(30);
        return weightField;
    }

    private Line createAndBindLine(VertexModel source, VertexModel target) {
        Line line = new Line();
        line.startXProperty().bind(source.layoutXProperty().add(source.getBoundsInParent().getWidth() / 2.0));
        line.startYProperty().bind(source.layoutYProperty().add(source.getBoundsInParent().getHeight() / 2.0));

        line.endXProperty().bind(target.layoutXProperty().add(target.getBoundsInParent().getWidth() / 2.0));
        line.endYProperty().bind(target.layoutYProperty().add(target.getBoundsInParent().getHeight() / 2.0));
        return line;
    }

    private TextField getWeightView() {
        TextField textField = new TextField("1");
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        return textField;
    }

    public int getEdgeWeight() {
        return Integer.valueOf(weight.get());
    }

    public VertexModel getSource() {
        return source;
    }

    public VertexModel getTarget() {
        return target;
    }
}
