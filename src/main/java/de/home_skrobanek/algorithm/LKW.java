package de.home_skrobanek.algorithm;

import java.util.ArrayList;

public class LKW {

    private float fahrerGewicht;//kg
    private float maxLast;//g
    private ArrayList<Hardware> list;

    public LKW(float fahrerGewicht, float maxLast) {
        this.fahrerGewicht = fahrerGewicht;
        this.maxLast = maxLast;

        list = new ArrayList<>();
    }

    public void printLKWList(){
        for(int i = 0; i< list.size(); i++){
            System.out.println("Nutzwert: " +list.get(i).getNutzwert() + " | Gewicht: " + list.get(i).getGewicht() +
                    " | Anzahl: " + list.get(i).getAnzahl() + " | Name: " + list.get(i).getName());
        }

    }

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
