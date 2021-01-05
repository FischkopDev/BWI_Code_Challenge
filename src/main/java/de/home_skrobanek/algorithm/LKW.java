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

import java.util.ArrayList;

/*
    Diese Datenklasse stellt den LKW dar und
    speichert jediglich wichtige Daten wie
    Gewicht und maximale Last.
 */
public class LKW {

    private float fahrerGewicht;//kg
    private float maxLast;//g
    private ArrayList<Hardware> list;

    public LKW(float fahrerGewicht, float maxLast) {
        this.fahrerGewicht = fahrerGewicht;
        this.maxLast = maxLast;

        list = new ArrayList<>();
    }

    /*
        Gibt in der Konsole aus, was im LKW geladen ist.
     */
    public void printLKWList(){
        for(int i = 0; i< list.size(); i++){
            System.out.println("Nutzwert: " +list.get(i).getNutzwert() + " | Gewicht: " + list.get(i).getGewicht() +
                    " | Anzahl: " + list.get(i).getAnzahl() + " | Name: " + list.get(i).getName());
        }

    }

    //gibt den kummulierten Nutzwert der Ladung aus
    public int getKumNutzwert(){
        int kumNutzwert = 0;
        for(int i = 0; i < list.size(); i++){
            kumNutzwert += list.get(i).getNutzwert();
        }
        return kumNutzwert;
    }

    public float getMaxLast(){
        return maxLast-fahrerGewicht;
    }

    public float getFahrerGewicht() {
        return fahrerGewicht;
    }

    public void setFahrerGewicht(float fahrerGewicht) {
        this.fahrerGewicht = fahrerGewicht;
    }

    public ArrayList<Hardware> getList() {
        return list;
    }

    public void setList(ArrayList<Hardware> list) {
        this.list = list;
    }
}
