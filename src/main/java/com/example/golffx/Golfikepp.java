package com.example.golffx;

public class Golfikepp {
    private String kepistring;
    private int maksimaalne_hea_pikkus;
    private int minimaalne_hea_pikkus;

    public Golfikepp(String kepistring, int maksimaalne_hea_pikkus, int minimaalne_hea_pikkus) {
        this.kepistring = kepistring;
        this.maksimaalne_hea_pikkus = maksimaalne_hea_pikkus;
        this.minimaalne_hea_pikkus = minimaalne_hea_pikkus;
    }

    public String getKepistring() {
        return kepistring;
    }
    public int getMaksimaalne_hea_pikkus() {
        return maksimaalne_hea_pikkus;
    }
    public int getMinimaalne_hea_pikkus() {
        return minimaalne_hea_pikkus;
    }

    @Override
    public String toString() {
        return  kepistring;
    }
}
