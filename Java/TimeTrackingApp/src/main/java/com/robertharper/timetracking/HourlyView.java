package com.robertharper.timetracking;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HourlyView {

    private VBox vbox = new VBox(10);

    public HourlyView() {
        for (int i = 0; i < 24; i++) {
            final int hour = i;
            Button hourButton = new Button(hour + ":00 - " + (hour + 1) + ":00");
            hourButton.setPrefWidth(200);
            hourButton.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    // Double-click detected, open activity entry dialog
                    System.out.println("Double-clicked on hour: " + hour);
                }
            });
            vbox.getChildren().add(hourButton);
        }
    }

    public Scene getScene() {
        return new Scene(vbox, 250, 500);
    }
}
