package UE01A04;

/**
 * Created by David on 16.10.2017.
 */
public class Rekursion {

    public void geradeZahlen(int i){
        if (i>20) return;
        if (i%2 == 0) System.out.println(i);
        geradeZahlen(++i);
    }
    /*
    * 4.1 Mit welcher Formel kann in einer Schleife, deren Laufvariable i mit 1 inkrementiert wird
        eine Folge ungerader Zahlen berechnet werden? Wie berechnet man gerade Zahlen?
        4.2 Wie kann man eine Folge programmieren, bei welcher das Vorzeichen mit jedem neuen
        Durchlauf wechselt, z.B. die Folge 1, -1, 1, -1,...
        4.3 Schauen Sie sich die Wertetabellen der Aufgaben 3.3 und 3.4 an. Durch welche
        mathematische Formel lassen sich die Werte ausdrücken und wie kann man die Folgen
        fortsetzen.
        4.4 Implementieren Sie eine Schleife, die es erlaubt die Werte für Amplitude und Frequenz
        für 16 Schwingungen zu berechnen. (Zusatzaufgabe)
    *
    * */
    public void main (String[] args){
        geradeZahlen(0);
    }
 }
