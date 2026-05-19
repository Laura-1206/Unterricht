package com.mycompany.ehks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        MainController controller = new MainController();

        VBox rootContent = new VBox(10);
        rootContent.setPadding(new javafx.geometry.Insets(20));
        rootContent.setMinWidth(1024);
        rootContent.setMinHeight(768);
        rootContent.setMaxHeight(Double.MAX_VALUE);
        rootContent.getChildren().add(controller.getView());

        ScrollPane scrollPane = new ScrollPane(rootContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(false);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        VBox root = new VBox(scrollPane);
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Elektronisches Hautkrebsscreening (eHKS)");
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}