package model;

import errorHandling.*;
import static java.lang.Math.*;

/**
 *
 * @author c.schneider
 */
public class Kuh {

    // R12
    public static final double GEWICHT_MAX = 600;
    // geburtsgewicht einer Kuh
    public static final double GEWICHT_MIN = 35;
    public static final double WIRKUNGSGRAD_FUTTER_GEWICHTSZUNAHME_JUNGTIER = 0.8;
    public static final double WIRKUNGSGRAD_FUTTER_MILCHZUNAHME_ERWTIER = 0.5;
    public static final double FUTTERAUFNAHME_MAX = 55;
    // max milchmenge einer kuh im euter
    public static final double MILCHMENGE_MAX = 25L;

    private String name;
    private double gewicht;
    private double milchMenge;

    public Kuh(String Name, double Gewicht) {
        this.name = Name;
        this.gewicht = (gewicht >= Kuh.GEWICHT_MIN) ? gewicht : Kuh.GEWICHT_MIN;
    }

    public static Kuh makeCalf(String name) {
        return new Kuh(name, Kuh.GEWICHT_MIN);
    }
    

    public void feed(Double futterMenge) throws KuhStallException {

        if (milchMenge < Kuh.MILCHMENGE_MAX) {
            this.gewicht += (futterMenge <= Kuh.FUTTERAUFNAHME_MAX) ? futterMenge : Kuh.FUTTERAUFNAHME_MAX;
        } else {
            // Kuhstall R29
            throw new KuhStallException("Euter voll - kuh muss erst gemolken werden bevor neues Futter in Milch umgewandelt wird.");
        }

        if (gewicht <= Kuh.GEWICHT_MAX) {
            this.milchMenge += futterMenge * Kuh.WIRKUNGSGRAD_FUTTER_GEWICHTSZUNAHME_JUNGTIER;
        } else {
            this.milchMenge += futterMenge * Kuh.WIRKUNGSGRAD_FUTTER_MILCHZUNAHME_ERWTIER;
        }
        this.milchMenge = min(25.0, this.milchMenge);

    }

    public double milk() {
        double interneMilchmenge = this.milchMenge;
        this.milchMenge = 0;
        return interneMilchmenge;
    }

    // refactored into toString()
//    public String getDaten() {
//    }
    public String toString() {
        String daten = "\n---------------------\n"
                + "Name:    " + this.getName() + "\n"
                + "Gewicht: " + this.getGewicht() + "\n"
                + "Milchvorrat: " + this.getMilchMenge() + "\n";
        return daten;
    }

    public String toCSVString() {
        System.out.println("ERROR method not implemented");
        return "ERROR method not implemented";
    }

    
    // GETTER & SETTER
    
    public String getName() {
        return name;
    }
    public double getGewicht() {
        return gewicht + milchMenge;
    }
    public double getMilchMenge() {
        return milchMenge;
    }
}
