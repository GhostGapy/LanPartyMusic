package com.example.music_projekt1;

public class evidenca {
    private int id;
    private String ime;
    private String zdravniki;
    private String naslov;
    private String kraj;
    private String vrsta;

    public evidenca(int id, String ime, String zdravniki, String naslov, String kraj, String vrsta) {
        this.id = id;
        this.ime = ime;
        this.zdravniki = zdravniki;
        this.naslov = naslov;
        this.kraj = kraj;
        this.vrsta = vrsta;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getZdravniki() {
        return zdravniki;
    }

    public String getNaslov() {
        return naslov;
    }

    public String getKraj() {
        return kraj;
    }

    public String getVrsta() {
        return vrsta;
    }
}
