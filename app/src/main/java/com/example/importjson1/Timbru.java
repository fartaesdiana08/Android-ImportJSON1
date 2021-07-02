package com.example.importjson1;

import java.io.Serializable;
import java.util.Date;

public class Timbru implements Serializable {
    private String denumire;
    private int calitate;
    private int pret; //seekbar
    private String categorie; //radiogrup;
    private int dataAchizite; //datepicker
    private String culoare; //spinner

    public Timbru(String denumire, int calitate, int pret, String categorie, int dataAchizite, String culoare) {
        this.denumire = denumire;
        this.calitate = calitate;
        this.pret = pret;
        this.categorie = categorie;
        this.dataAchizite = dataAchizite;
        this.culoare = culoare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getCalitate() {
        return calitate;
    }

    public void setCalitate(int calitate) {
        this.calitate = calitate;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getDataAchizite() {
        return dataAchizite;
    }

    public void setDataAchizite(int dataAchizite) {
        this.dataAchizite = dataAchizite;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    @Override
    public String toString() {
        return "Timbru{" +
                "denumire='" + denumire + '\'' +
                ", calitate=" + calitate +
                ", pret=" + pret +
                ", categorie='" + categorie + '\'' +
                ", dataAchizite=" + dataAchizite +
                ", culoare='" + culoare + '\'' +
                '}';
    }
}
