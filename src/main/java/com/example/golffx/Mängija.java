package com.example.golffx;

import java.util.ArrayList;
import java.util.List;

public class Mängija {
    private String nimi;
    private double HCP;
    private List<Integer> scorecard;



    public Mängija(String nimi, double HCP) {
        this.nimi = nimi;
        this.HCP = HCP;
        scorecard = new ArrayList<>();


    }

    public double getHCP() {
        return HCP;
    }
    public void lisatulemus(int tulemus){
        scorecard.add(tulemus);
    }
    public List<Integer> lõpptulemus(){
        for(Integer tulemus : scorecard){
            System.out.println(tulemus);
        }
        return scorecard;
    }

    public double löögikaugus (int rajapikkus, Golfikepp golfikepp){
        double täpsus = 100 - HCP;
        double juhus = Math.random()* 100 ;
        if (täpsus > juhus) {return golfikepp.getMaksimaalne_hea_pikkus();}
        else {return golfikepp.getMinimaalne_hea_pikkus();}
    }

    public String getNimi() {
        return nimi;
    }
}

