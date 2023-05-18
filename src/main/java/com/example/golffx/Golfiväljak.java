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
import java.io.FileNotFoundException;
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
    @FXML
    private Label löökLabel;
    @FXML
    private Label kaugusLabel;
    @FXML
    private Label lookideArv;

    private Rada rada;
    private Stage lava;
    private Scene stseen;
    private FXMLLoader loader;
    private List<Golfikepp> kepidList;
    private Queue<Rada> rajadList;
    private int jarg = 0;

    private Mängija mängija1;
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
        double kaugus = mängija1.löögikaugus(rada.getRajapikkus(),kepid.getSelectionModel().getSelectedItem());

        if ((radaBar.getProgress() + kaugus/rada.rajapikkus ) > 1.02){
            radaBar.setProgress(Math.abs(radaBar.getProgress() + kaugus/rada.rajapikkus - 2));
            löökLabel.setText("Pall langes august mööda, löögi kaugus oli " + Math.round(kaugus));
            lookideArv.setText("Löökide arv: " + ++jarg);
            kaugusLabel.setText("Kaugus august " + (Math.round(rada.rajapikkus - radaBar.getProgress()*rada.rajapikkus)));


        }

        else if (radaBar.getProgress() + kaugus/rada.rajapikkus >= 0.98 && radaBar.getProgress() + kaugus/rada.rajapikkus <= 1.02){
            vahetaRada();
            radaBar.setProgress(0);
            löökLabel.setText("Tabasid auku! Kokku läks " + ++jarg + " lööki." );
            lookideArv.setText("Löökide arv: 0");
            jarg = 0;
        }
        else {
            radaBar.setProgress(radaBar.getProgress() + kaugus/rada.rajapikkus);
            löökLabel.setText("Tubli, " + mängija1.getNimi() + ", löögi kaugus oli " + Math.round(kaugus));
            lookideArv.setText("Löökide arv: " + ++jarg);
            kaugusLabel.setText("Kaugus august " + (Math.round(rada.rajapikkus - radaBar.getProgress()*rada.rajapikkus)));
        }
    }
        private void vahetaRada() {
        rada = rajadList.poll();
        if (rada == null){
            radaLabel.setText("Väljak on läbi");
        }
        else {
        radaLabel.setText("Rada nr" + rada.getRajanumber());
        kaugusLabel.setText("kaugus august " + rada.rajapikkus);}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        try {
            kepidList = loeGolfikepid("golfikepp.txt");
            rajadList = loeRada("Golfiväljak1.txt");
            rada = rajadList.poll();
            mängija1 = loeMangija("praeguMängib.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        kaugusLabel.setText("kaugus august " + rada.rajapikkus);

        kepid.getItems().addAll(kepidList);
    }

    private Mängija loeMangija(String failinimi) {
        File fail = new File(failinimi);
        try (Scanner sc = new Scanner(fail,"UTF-8")){
            String rida = sc.nextLine();
            String [] tükid = rida.split(",");
            return new Mängija(tükid[0],Double.parseDouble(tükid[1]));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(failinimi + " on vales formaadis");
        }

    }

    public static List<Golfikepp> loeGolfikepid(String failinimi) throws IOException {
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tulemus;
    }
    public static Queue<Rada> loeRada(String failinimi) throws IOException {
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
