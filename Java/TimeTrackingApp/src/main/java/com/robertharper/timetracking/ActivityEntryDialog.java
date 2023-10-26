package com.robertharper.timetracking;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.scene.control.DialogPane;

public class ActivityEntryDialog {

    private String activity = "";
    private Color color = Color.TRANSPARENT;

    public ActivityEntryDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Activity");
        dialog.setHeaderText("Enter your activity for this hour:");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        dialog.showAndWait().ifPresent(response -> {
            activity = response;
            // Here, you can add the color input logic
            // For simplicity, I'm setting a default color
            color = Color.rgb(255, 0, 0, 0.5); // Red with 50% opacity
        });
    }

    public String getActivity() {
        return activity;
    }

    public Color getColor() {
        return color;
    }
}
