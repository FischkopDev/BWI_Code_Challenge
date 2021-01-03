package de.home_skrobanek.algorithm;

import java.util.ArrayList;

public class Algorithm {

    private ArrayList<Hardware> list = new ArrayList<>();
    private LKW lkw1, lkw2;

    public Algorithm(){
        lkw1 = new LKW(72400f, 1100000f);
        lkw2 = new LKW(85700f, 1100000f);

        //Laden aus einer Datenbank könnte hiermit möglich sein.
        list.add(new Hardware(40, 2451,205,"Notebook Büro 13\""));
        list.add(new Hardware(35, 2978,420, "Notebook 14\""));
        list.add(new Hardware(80, 3625, 450, "Notebook Outdoor"));
        list.add(new Hardware(30, 717, 60,"Telefon Büro"));
        list.add(new Hardware(60, 988, 157, "Telefon Outdoor"));
        list.add(new Hardware(65, 1220, 220, "Mobiltelefon Heavy Duty"));
        list.add(new Hardware(40, 1405, 620, "Tablet Büro klein"));
        list.add(new Hardware(40, 1455, 250, "Tablet Büro groß"));
        list.add(new Hardware(45, 1690, 540, "Tablet outdoor klein"));
        list.add(new Hardware(68, 1980, 370, "Tablet outdoor groß"));


        //Sortiere die Liste nach dem Nutzwert
        sortiereNutzwert();

        //Überprüfe ob zwei Objekte denselben Nutzwert haben und entferne das Objekt mit mehr Gewicht
        checkNutzwert();

        //Sortiere nach Gewicht
        sortiereGewicht();

        //Beladen der LKW's
        lkwBeladen(lkw1);
        lkwBeladen(lkw2);

        System.out.println("Was noch im Lager liegt: ");
        printList();
    }

    public float getNutzwertDurchschnitt(){
        int gesamt = 0;
        float durchschnitt = 0;
        for(int i = 0; i < list.size(); i++){
            gesamt += list.get(i).getNutzwert();
        }

        durchschnitt = gesamt/list.size();

        return durchschnitt;
    }

    public void sortiereNutzwert(){
        int n = list.size();
        for (int i = 0; i < n-1; i++){
            for (int j = 0; j < n-i-1; j++) {
                if (list.get(j).getNutzwert() > list.get(j + 1).getNutzwert()) {
                    // swap arr[j+1] and arr[j]
                    Hardware temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
       // printList();
    }

    public void lkwBeladen(LKW lkw){
        float gewicht = 0;
        boolean toggle = true;
        while(toggle){
        for(int i = 0; i < list.size(); i++) {
            if (lkw.getMaxLast() > gewicht + list.get(i).getGewicht()) {
                if(list.get(i).getAnzahl() > 0) {
                    gewicht += list.get(i).getGewicht();
                    list.get(i).setAnzahl(list.get(i).getAnzahl() - 1);
                    lkw.getList().add(new Hardware(list.get(i).getNutzwert(),list.get(i).getGewicht(),1, list.get(i).getName()));
                }
                else
                    list.remove(i);
            }
            else{
                toggle = false;
            }
        }
        }

       // lkw.printLKWList();
        System.out.println("Gewicht der Ladung ist " + gewicht + "g | " +lkw.getMaxLast() + "g");
        System.out.println("Nutzwert der Ladung ist: " + lkw.getKumNutzwert());
        System.out.println();
    }

    public void checkNutzwert(){
        for(int i = 0; i < list.size()-1; i++){
            if(list.get(i).getNutzwert() == list.get(i+1).getNutzwert()){
                if(list.get(i).getGewicht() > list.get(i+1).getGewicht())
                    list.remove(i);
                else
                    list.remove(i+1);
            }
        }
    }

    public void sortiereGewicht(){
        int n = list.size();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getGewicht() > list.get(j + 1).getGewicht()) {
                    // swap arr[j+1] and arr[j]
                    Hardware temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        //printList();
    }

    public void printList(){
        for(int i = 0; i < list.size(); i++){
            System.out.println("Nutzwert: " +list.get(i).getNutzwert() + " | Gewicht: " + list.get(i).getGewicht() +
                    "g | Anzahl: " + list.get(i).getAnzahl() + " | Name: " + list.get(i).getName());
        }
    }

    public static void main(String[]args){
        new Algorithm();
    }
}
