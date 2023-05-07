package com.example.golffx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Golfiväljak implements Initializable {
    @FXML
    private ListView<Golfikepp> kepid;
    @FXML
    private Label radaLabel = new Label("Väljak1");
    @FXML
    private ProgressBar radaBar;
    private Rada rada;
    private Stage lava;
    private Scene stseen;
    private FXMLLoader loader;
    private List<Golfikepp> kepidList;
    private Queue<Rada> rajadList;
    private Golfikepp valik;
    @FXML
    private void valiValijakNupp(ActionEvent sündmus) throws IOException {
        loader = new FXMLLoader(GolfMäng.class.getResource("valiValjak.fxml"));
        lava =(Stage) ((Node)sündmus.getSource()).getScene().getWindow();
        stseen = new Scene(loader.load());
        lava.setScene(stseen);
        lava.show();

    };
    @FXML
    private void golfilöök() throws IOException {
        radaBar.setProgress(radaBar.getProgress() + 0.1);
        if (radaBar.getProgress() >= 0.999){
            vahetaRada();
            radaBar.setProgress(0);
        }
    }
        private void vahetaRada() {
        rada = rajadList.poll();
        radaLabel.setText("Rada nr" + rada.getRajanumber());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        try {
            kepidList = loeGolfikepid("golfikepp.txt");
            rajadList = loeRada("Golfiväljak1.txt");
            rada = rajadList.poll();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        kepid.getItems().addAll(kepidList);
        valik = kepid.getSelectionModel().getSelectedItem();
    }
    public static List<Golfikepp> loeGolfikepid(String failinimi) throws Exception {
        List<Golfikepp> tulemus = new ArrayList<>();
        File fail = new File(failinimi);
        try (Scanner sc = new java.util.Scanner(fail, "UTF-8")) {
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                String[] tükid = rida.split(", ");

                String kepistring = tükid[3];
                int maxPikkus = Integer.parseInt(tükid[1]);
                int minPikkus = Integer.parseInt(tükid[2]);

                tulemus.add(new Golfikepp(kepistring, maxPikkus, minPikkus));
            }
        }
        return tulemus;
    }
    public static Queue<Rada> loeRada(String failinimi) throws Exception {
        Queue<Rada> tulemus = new LinkedList<>();
        File fail = new File(failinimi);
        try (Scanner sc = new java.util.Scanner(fail, "UTF-8")) {
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                String[] tükid = rida.split(", ");

                String rajanr = tükid[0];
                int rajapikkus = Integer.parseInt(tükid[1]);
                int rajapar = Integer.parseInt(tükid[2]);

                tulemus.add(new Rada(rajanr, rajapikkus, rajapar));
            }
        }
        return tulemus;
    }
}
