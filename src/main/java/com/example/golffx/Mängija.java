package com.example.golffx;

import java.util.ArrayList;
import java.util.List;

public class Mängija {
    private String nimi;
    private double HCP;
    private List<Integer> scorecard;

    private List<Golfikepp> golfikepid;

    public Mängija(String nimi, double HCP, List<Golfikepp> golfikepid) {
        this.nimi = nimi;
        this.HCP = HCP;
        scorecard = new ArrayList<>();
        this.golfikepid = golfikepid;
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
    public int Maksimaalsepikkuseleidja(String c){
        for (Golfikepp golfikepp : golfikepid){
            if (golfikepp.getKepistring().equals(c.toLowerCase())){
                int hea_look_ulemine = golfikepp.getMaksimaalne_hea_pikkus();
                return hea_look_ulemine;
            }
        }
        return 0;
    }
    public int Minimaalsepikkuseleidja(String c){
        for (Golfikepp golfikepp : golfikepid){
            if (golfikepp.getKepistring().equals(c.toLowerCase())){
                int hea_look_alumine = golfikepp.getMinimaalne_hea_pikkus();
                return hea_look_alumine;
            }
        }
        return 0;
    }
    public boolean golfikeppOlemas(String c){
        for (Golfikepp golfikepp : golfikepid){
            if (golfikepp.getKepistring().equals(c.toLowerCase())){
                return true;
            }}
        return false;
    }
}

