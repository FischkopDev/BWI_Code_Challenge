<h3>Programmierwettbewerb</h3>
<p>Das ist meine Abgabe zum Wettbewerb. Hier werde ich die Anwendung
und den Aufbau meines Algorithmus erklären.</p>

<h4>Aufbau</h4>
<p>Zuerst wird die Liste aller benötigten Teile geladen. Dafür habe ich die 
Liste in der gegebenen PDF verwendet. Als nächstes sortiere ich die Liste nach
dem Nutzwert wodurch das Gerät mit dem höchsten Nutzwert an erster Stelle steht.
Danach vergleiche ich Objekte, die denselben Nutzwert haben und finde heraus, welches Objekt
eine geringere Masse hat. Das Objekt mit der geringeren Masse bleibt in der Liste, während
das mit der höheren aus der Liste entfernt wird. Anschließend sortiere ich die
"neue" Liste nach dem Gewicht der einzelnen Teile und gehe dann so oft die Liste durch,
bis der LKW beladen ist. Zum Schluss kann der Benutzer entscheiden, ob die Ladeliste
ausgegeben werden soll oder nicht. Als letzte Aktion wird dann noch der Nutzwert und das Gewicht 
beider LKW ausgegeben.</p>

<h4>Verwendung</h4>
<p>Das Programm kann einfach in der Konsole ausgeführt werden und erklärt dem Nutzer während
der Nutzung wie es funktioniert bzw welche EIngaben vom Benutzer erwartet werden. Beim
Github Release ist somit eine Jar und eine für das jeweilige Betriebssystem
ausführbare batch/sh Datei vorhanden.</p>

<h4>Programminformationen</h4>
<p>Für das Projekt habe ich das JDK 14 verwendet. Kompatibilität sollte
jedoch bis JDK/JRE 1.8 bestehen. Das Projekt wurde in Intelij als Gradle Projekt
umgesetzt.</p>

<h4>Author</h4>

<p>Das Programm wurde von mir geschrieben. Bei Problemen oder Ideen kann man mich unter folgenden 
Möglichkeiten erreichen:<br><br>Timo Skrobanek<br>
Email: timo@home-skrobanek.de<br>Discord: Dr.Fischkopf#6752</p>
