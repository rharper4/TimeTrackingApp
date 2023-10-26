package com.robertharper.timetracking;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeTrackingApp extends Application {

    private final ComboBox<String> monthComboBox = new ComboBox<>();
    private final GridPane calendarGrid = new GridPane();
    private LocalDate currentDate = LocalDate.now();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setupMonthComboBox();
        setupCalendarGrid();

        BorderPane root = new BorderPane();
        root.setTop(monthComboBox);
        root.setCenter(calendarGrid);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Time Tracking App");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void setupMonthComboBox() {
        monthComboBox.getItems().addAll(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        );
        monthComboBox.setValue(currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.US));
        monthComboBox.setOnAction(event -> {
            int selectedMonth = monthComboBox.getSelectionModel().getSelectedIndex() + 1;
            currentDate = LocalDate.of(currentDate.getYear(), selectedMonth, 1);
            setupCalendarGrid();
        });
    }

    private void setupCalendarGrid() {
        calendarGrid.getChildren().clear();
        calendarGrid.setAlignment(Pos.CENTER);
        calendarGrid.setHgap(1);
        calendarGrid.setVgap(1);
        calendarGrid.setStyle("-fx-background-color: black;");

        int daysInMonth = currentDate.lengthOfMonth();
        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), day);
            Text dayText = new Text(String.valueOf(day));
            dayText.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    showDetailsDialog(date);
                }
            });

            StackPane cell = new StackPane(dayText);
            cell.setStyle("-fx-background-color: white;");
            GridPane.setVgrow(cell, Priority.ALWAYS);
            GridPane.setHgrow(cell, Priority.ALWAYS);
            cell.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

            calendarGrid.add(cell, (day - 1) % 7, (day - 1) / 7);
        }
    }

    private void showDetailsDialog(LocalDate date) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Enter Details for " + date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US) + ", " + date);
        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);

        TextArea detailsArea = new TextArea();
        detailsArea.setPromptText("Enter activities for " + date);
        detailsArea.setWrapText(true);

        vbox.getChildren().add(detailsArea);

        dialog.getDialogPane().setContent(vbox);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String details = detailsArea.getText();
                System.out.println("Activities for " + date + ": " + details);
            }
        });
    }
}
