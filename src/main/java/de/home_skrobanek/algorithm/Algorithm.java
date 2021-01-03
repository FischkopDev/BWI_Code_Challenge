package de.home_skrobanek.algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {

    private ArrayList<Hardware> list = new ArrayList<>();
    private LKW lkw1, lkw2;

    private Scanner sc = new Scanner(System.in);

    public Algorithm(){
        System.out.println("LKW-Beladung wird berechnet.");
        System.out.println("Liste wird zusammengesetllt.");
        System.out.println();

        //LKW werden erstellt und erhalten ihre maximale Last und das Gewicht des Fahrers.
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
        System.out.println("Erster LKW wird beladen:");
        lkwBeladen(lkw1);
        System.out.println("Zweiter LKW wird beladen:");
        lkwBeladen(lkw2);

        System.out.println("Was noch im Lager liegt: ");
        printList();

        sc.close();
    }

    /*
        Sortiert die temporäre Liste nach dem Nutzwert um in der nächsten Methode jeweils
        die Objekte mit gleichem Nutzwert zu vergleichen.
     */
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

    /*
        Mit dieser Methode werden die LKW's beladen. Die sortierte liste wird solange
        durchgegangen, bis die Lastgrenze des LKW erreicht wurde
        (Gewicht des Fahrers wurde beachtet). Die Objekte werden einzeln hinzugefügt und daraufhin
        aus der temporären Liste entfernt.
        Nachdem der LKW beladen ist, wird das Ergebnis ausgegeben und der Nutzer kann entscheiden,
        ob eine Liste mit allen Objekten im LKW ausgegeben werden soll.
        Nach dieser Entscheidung wird das Gewicht und der Nutzwert der Ladung angezeigt.
     */
    public void lkwBeladen(LKW lkw){
        float gewicht = 0; //Das Gewicht der Ladung
        boolean toggle = true; //Solange "true" wird der LKW beladen

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

        System.out.println("Ladeliste anzeigen lassen: <ja> / <nein>");
        System.out.println("Eingabe: ");

        String line = sc.nextLine();
        if(line.equalsIgnoreCase("ja"))
            lkw.printLKWList();

        System.out.println("Gewicht der Ladung ist " + (gewicht/1000) + "kg | " +(lkw.getMaxLast()/1000) + "kg");
        System.out.println("Nutzwert der Ladung ist: " + lkw.getKumNutzwert());
        System.out.println();
    }

    /*
        Hier wird überprüft, ob zwei Gegenstände denselben Nutzwert haben. Ist das der Fall,
        wird das Gewicht beider Komponenten überprüft. Daraufhin wird das Objekt mit höherem
        Gewicht aus der Liste entfernt.
     */
    public void checkNutzwert(){
        for(int i = 0; i < list.size()-1; i++){
            //prüfen des Objektes und dem nächsten in der Liste
            if(list.get(i).getNutzwert() == list.get(i+1).getNutzwert()){
                //Abgleich der Gewichte und entfernen des schwereren.
                if(list.get(i).getGewicht() > list.get(i+1).getGewicht())
                    list.remove(i);
                else
                    list.remove(i+1);
            }
        }
    }

    /*
        Sortieren mithilfe des bubble sorts. Hier wird nach dem Gewicht sortiert
        um die leichtesten Gegenstände an erster Stelle zu haben.
     */
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

    /*
        Ausgabe der Liste aller Teile die im Lager sind. Die Liste ist eine temporäre Liste und wird
        im Verlauf des Programmes verändert.
     */
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
