package com.example.golffx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GolfMäng extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage peaLava) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GolfMäng.class.getResource("valiValjak.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        peaLava.setTitle("Golfimäng");
        peaLava.setScene(scene);
        peaLava.show();
    }

}
