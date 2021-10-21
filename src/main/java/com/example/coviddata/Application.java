package com.example.coviddata;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 866, 645);
        ConvertApiIntoJsonFile.makeJsonFile("https://covid-api.mmediagroup.fr/v1/cases");
        stage.setTitle("Covid Data");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}