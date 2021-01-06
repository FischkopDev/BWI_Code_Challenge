/**
 *
 * @author
 *      Timo Skrobanek
 *
 * @description
 *      Abgabe für den Programmierwettbewerb bei GetInIT und BWI.
 *
 * @date
 *      07.01.2021
 */
package de.home_skrobanek.algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {

    private ArrayList<Hardware> list = new ArrayList<>();
    private LKW lkw1, lkw2;

    //Liest die Eingaben des Benutzers
    private Scanner sc = new Scanner(System.in);

    public Algorithm(){
        System.out.println("LKW-Beladung wird berechnet.");
        System.out.println("Liste wird zusammengesetllt.");
        System.out.println();

        //LKW werden erstellt und erhalten ihre maximale Last und das Gewicht des Fahrers.
        lkw1 = new LKW(72400f, 1100000f);
        lkw2 = new LKW(85700f, 1100000f);

        System.out.println("Lagerliste aus der PDF verwenden ? <ja> / <nein>");
        System.out.println("Eingabe: ");

        String input = sc.nextLine();

        if(input.equalsIgnoreCase("ja")) {

            //Laden aus einer Datenbank könnte hiermit möglich sein.
            list.add(new Hardware(40, 2451, 205, "Notebook Büro 13\""));
            list.add(new Hardware(35, 2978, 420, "Notebook 14\""));
            list.add(new Hardware(80, 3625, 450, "Notebook Outdoor"));
            list.add(new Hardware(30, 717, 60, "Telefon Büro"));
            list.add(new Hardware(60, 988, 157, "Telefon Outdoor"));
            list.add(new Hardware(65, 1220, 220, "Mobiltelefon Heavy Duty"));
            list.add(new Hardware(40, 1405, 620, "Tablet Büro klein"));
            list.add(new Hardware(40, 1455, 250, "Tablet Büro groß"));
            list.add(new Hardware(45, 1690, 540, "Tablet outdoor klein"));
            list.add(new Hardware(68, 1980, 370, "Tablet outdoor groß"));

        }
        else{
            System.out.println("Füge jetzt Hardware hinzu und erstelle deine eigene Liste.");
            System.out.println("Es müssen mindestens 4 Gegenstände vorhanden sein.");
            hardwareHinzufuegen();

        }

        System.out.println("Verteilung wird berechnet.");

        //starten des Algorithmus
        berechneVerteilung();

        //Beladen der LKW's
        System.out.println("Erster LKW wird beladen:");
        lkwBeladen(lkw1);
        System.out.println("-------------------------");
        System.out.println("Zweiter LKW wird beladen:");
        lkwBeladen(lkw2);

        System.out.println("Ausgeben was noch im Lager ist? <ja> / <nein>");
        System.out.println("Eingabe: ");
        String line = sc.nextLine();

        if(line.equalsIgnoreCase("ja"))
            printList();

        //sc.close();
    }

    public void hardwareHinzufuegen(){
        try {
            System.out.println("Name der Hardware: ");
            String name = sc.nextLine();

        /*
            Umsetzung mithilfe von ParseInt, ParseFloat, ... weil ansonsten sc.nextLine() nicht mehr geht.
            Problem innerhalb des JDK:
            https://stackoverflow.com/questions/23450524/java-scanner-doesnt-wait-for-user-input
            "[...]The problem is that nextInt() does not consume the '\n'[...]"
        */
            System.out.println("Gewicht der Hardware (Gramm): ");
            float gewicht = Float.parseFloat(sc.nextLine());

            System.out.println("Nutzwert der Hardware: ");
            int nutzwert = Integer.parseInt(sc.nextLine());

            System.out.println("Anzahl der Hardware: ");
            int anzahl = Integer.parseInt(sc.nextLine());

            list.add(new Hardware(nutzwert, gewicht, anzahl, name));

            System.out.println("Weitere Hardware hinzufügen? <ja> / <nein>");
            System.out.println("Eingabe: ");

            String input = sc.nextLine();
            if (input.equalsIgnoreCase("ja")) {
                hardwareHinzufuegen();
            } else {
                System.out.println("In der Liste sind jetzt " + list.size() + " Objekte.");
            }
        }catch(Exception e){
            System.out.println("Bitte nur gültige Eingaben für Name, Anzahl, Nutzwert oder Gewicht eingeben.");
            hardwareHinzufuegen();
        }
    }

    /*
        In dieser Methode ist der eigentliche Algorithmus. Dieser
        besteht aus zwei bubble sorts und einer Überprüfung des Nutzwertes
        mit anschließender Entfernung aus der Liste.
     */
    public void berechneVerteilung(){

        /*
            In der ersten Schleife wird die Liste nach dem Nutzwert sortiert.
            Diese Sortierung folgt durch einen Bubble-Sort.
         */
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

        /*
            Im nächsten Teil der Methode wird die Liste auf Objekte überprüft,
            die denselben Nutzwert haben. Haben diese Objekte jetzt ein anderes
            Gewicht, wird das Objekt mit dem höheren Gewicht entfernt.
         */
        for(int i = 0; i < list.size()-1; i++){
            //prüfen des Objektes und dem nächsten in der Liste
            if(list.get(i).getNutzwert() == list.get(i+1).getNutzwert()){
                //Abgleich der Gewichte und entfernen des schwereren.
                if(list.get(i).getGewicht() > list.get(i+1).getGewicht())
                    list.remove(i);
                else if(list.get(i).getGewicht() == list.get(i+1).getGewicht())
                    continue;
                else
                    list.remove(i+1);
            }
        }

        /*
            Im letzten Schritt wird die Liste nach dem Gewicht sortiert.
            Dadurch wird sichergestellt, dass zuerst die Geräte mit höchstmöglichem
            Nutzwert und kleinsten Gewicht eingelagert werden. Durch das kleinere
            Gewicht kann man mehr mitnehmen und einen höheren Nutzwert erzielen.
         */
        n = list.size();
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

    }

    /**
        @param lkw
            Die Instanz eines LKW, welcher beladen werden soll.

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
                if (lkw.getMaxLast() > gewicht + list.get(i).getGewicht() && !list.isEmpty()) {
                    if(list.get(i).getAnzahl() > 0) {
                        gewicht += list.get(i).getGewicht();
                        list.get(i).setAnzahl(list.get(i).getAnzahl() - 1);
                        lkw.getList().add(new Hardware(list.get(i).getNutzwert(),list.get(i).getGewicht(),1,
                                list.get(i).getName()));
                    }
                    else
                        list.remove(i);
                }
                else{
                    toggle = false;
                    break;
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
