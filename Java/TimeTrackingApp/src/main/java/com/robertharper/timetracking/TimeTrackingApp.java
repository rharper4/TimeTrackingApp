package com.robertharper.timetracking;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TimeTrackingApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(10); // VBox with spacing of 10
        root.setAlignment(Pos.CENTER); // Center the contents

        // Date Picker
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select a Date");

        // Time Picker (Using a TextField for simplicity)
        TextField timePicker = new TextField();
        timePicker.setPromptText("HH:mm"); // Format: Hours:Minutes

        // Activity Entry
        TextField activityField = new TextField();
        activityField.setPromptText("Enter Activity");

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            // Handle the submission logic here
            String date = datePicker.getValue().toString();
            String time = timePicker.getText();
            String activity = activityField.getText();
            System.out.println("Date: " + date + ", Time: " + time + ", Activity: " + activity);
        });

        // Exit Button
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        root.getChildren().addAll(datePicker, timePicker, activityField, submitButton, exitButton);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Time Tracking App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
