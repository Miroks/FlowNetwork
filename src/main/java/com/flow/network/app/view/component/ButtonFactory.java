package com.flow.network.app.view.component;

import javafx.scene.control.Button;

public class ButtonFactory {

    //create preffered button without Scene buider
    public static Button createBaseButton(String label) {
        Button button = new Button(label);
        button.setPrefSize(100, 20);
        return button;
    }
}
