/**
 *
 * @author
 *      Timo Skrobanek
 *
 * @description
 *      Abgabe für den Programmierwettbewerb bei GetInIT und BWI.
 *
 * @date
 *      05.01.2021
 */
package de.home_skrobanek.algorithm;

/*
    Mit dieser Klasse kann ein Gerät als
    Hardware für das Programm verwendbar
    gemacht werden. Hier werden jediglich
    wichtige Informationen dazu
    zwischengespeichert.
 */
public class Hardware {

    private int nutzwert;
    private float gewicht; //in Gramm
    private String name = "";
    private int anzahl;

    public Hardware(int nutzwert, float gewicht, int anzahl, String name) {
        this.nutzwert = nutzwert;
        this.gewicht = gewicht;
        this.anzahl = anzahl;
        this.name = name;
    }

    public int getKumNutzwert(){
        return nutzwert*anzahl;
    }

    public float getKumGewicht(){
        return gewicht*nutzwert;
    }

    public int getAnzahl(){
        return anzahl;
    }

    public void setAnzahl(int anzahl){
        this.anzahl = anzahl;
    }
    public float getGewicht(){
        return gewicht;
    }

    public int getNutzwert(){
        return nutzwert;
    }

    public String getName(){
        return name;
    }

}
