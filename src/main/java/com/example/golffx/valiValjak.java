package com.example.golffx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class valiValjak {
    private Stage lava;
    private Scene stseen;
    private FXMLLoader loader;

    private ObservableList<String> valikud = FXCollections.observableArrayList("Väljak 1", "Väljak 2");
    @FXML
    private ChoiceBox golfivaljakud;

    @FXML
    private void initialize(){
        golfivaljakud.setValue(valikud.get(0));
        golfivaljakud.setItems(valikud);
    }

    @FXML
    private void valiKeppNupp(ActionEvent sündmus) throws IOException {
        loader = new FXMLLoader(GolfMäng.class.getResource("golfiValjak.fxml"));
        lava =(Stage) ((Node)sündmus.getSource()).getScene().getWindow();
        stseen = new Scene(loader.load());
        lava.setScene(stseen);
        lava.show();

    };


}
