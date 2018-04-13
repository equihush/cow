package model;

import allgemein.NotificationNames;
import java.util.ArrayList;
import static java.lang.Math.*;
import java.util.Optional;

import errorHandling.*;
import notificationcenter.NotificationCenter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author c.schneider
 */
public class Kuhstall {

    // Menge an Futter in kg die max im Kuhstall eingelagert werden kann.
    private static final double LAGERVOLUMEN_FUTTER_MAX = 20000;
    private static final double LAGERVOLUMEN_MILCH_MAX = 500;
    private static final double STALLPLAETZE_KUEHE_MAX = 20;

    private ArrayList<Kuh> kuehe = new ArrayList<>();
    private double lagerMengeFutter = 0;
    private double lagerMengeMilch = 0;

    // for zapping throw 'kuehe' data
    private int currentCowIndex = 0;

    public Kuhstall() {
        kuehe.add(new Kuh("elsa", 100.0));
        kuehe.add(new Kuh("pepe", 80.0));
        kuehe.add(new Kuh("milka", 200.0));
        kuehe.add(new Kuh("fleck", 500));
        setCurrentCowIndex(kuehe.size() - 1);

    }

    public void addFutterMenge(double futterMenge) throws KuhStallException {

        boolean didReachMaxCapacity = false;
        lagerMengeFutter += futterMenge;
        lagerMengeFutter = min(20_000/*kg*/, lagerMengeFutter);

        if (didReachMaxCapacity) {
            throw new KuhStallException("Maximales Lagervolumen an Futter erreicht.");
        }
    }

    public void addKuh(Kuh kuh) throws KuhStallException {
        if (kuehe.size() < Kuhstall.STALLPLAETZE_KUEHE_MAX) {
            kuehe.add(kuh);
        } else {
            throw new KuhStallException("Keine freien Plätze für neue Kühe im Stall.");
        }
    }

    public void bearCalf(String name) {
        kuehe.add(Kuh.makeCalf(name));
    }

    public double deliverTotalMilkAmountInStock() {
        double amountOfMilkToDeliver = this.lagerMengeMilch;
        this.lagerMengeMilch = 0;

        NotificationCenter.shared.processNotification(NotificationNames.UPDATE_KUHSTALL_GUI);

        return amountOfMilkToDeliver;
    }

    // R16
    public void feedAllCowsEqually() throws KuhStallException {
        if (lagerMengeFutter <= 0) {
            throw new KuhStallException("kein futter mehr im lager - bitte neues kaufen bevor erneut gefüttert werden kann");
        } else {

            // max FutterMenge auf alle Kühe gleichmäßig aufteilen
            double amountFoodPerCow = lagerMengeFutter / kuehe.size();

            amountFoodPerCow = min(amountFoodPerCow, Kuh.FUTTERAUFNAHME_MAX);
            
            for (Kuh kuh : kuehe) {
                try {
                    kuh.feed(amountFoodPerCow);
                    lagerMengeFutter -= amountFoodPerCow;

                } catch (KuhStallException e) {
                    e.printStackTrace();
                    throw e;
                }
            }

        }

    }

    public double calculatedTotalAmountOfFood() {
        return Kuh.FUTTERAUFNAHME_MAX * kuehe.size();
    }

    public void milkAllCows() {
        Optional<Double> milk = 
                kuehe.stream()
                .map(cow -> cow.milk())
                .reduce((x, y) -> x+y);
        
        if (milk.isPresent()) {
            lagerMengeMilch += milk.get();
        }
    }

    // Datensatzsteuerung
    public Kuh getCurrentCow() {
        if (currentCowIndex < kuehe.size()) {
            return kuehe.get(currentCowIndex);
        }
        return null;
    }

    public int getCurrentCowIndex() {
        return currentCowIndex;
    }

    public void setCurrentCowIndex(int index) {
        this.currentCowIndex = index;
    }

    public Kuh getKuhByIndex(int index) {
        if (index >= 0 && index < kuehe.size()) {
            return kuehe.get(index);
        }
        // TODO: Throw exeption
        return null;
    }
    
    public int getTotalCowsInStock() {
        return kuehe.size();
    }

    public Kuh getNextCow() {
        if (hasNextCow()) {
            currentCowIndex += 1;
            return getKuhByIndex(currentCowIndex);
        }
        return null;
    }

    public Kuh getPreviousCow() {
        if (hasPreviousCow()) {
            currentCowIndex -= 1;
            return getKuhByIndex(currentCowIndex);
        }
        return null;
    }

    public Kuh getFirstCow() {
        currentCowIndex = 0;
        return !kuehe.isEmpty() ? kuehe.get(0) : null;
    }

    public Kuh getLastCow() {
        if (!kuehe.isEmpty()) {
            currentCowIndex = kuehe.size() - 1;
            return kuehe.size() > 1 ? kuehe.get(kuehe.size() - 1) : getFirstCow();
        }
        return null;
    }

    public boolean isEmpty() {
        return kuehe.isEmpty();
    }

    public boolean hasNextCow() {
        return !kuehe.isEmpty() && currentCowIndex < (kuehe.size() - 1);
    }

    public boolean hasPreviousCow() {
        return !kuehe.isEmpty() && currentCowIndex > 0;
    }

    // SETTER & GETTER
    public double getLAGERVOLUMEN_FUTTER_MAX() {
        return LAGERVOLUMEN_FUTTER_MAX;
    }

    public double getLAGERVOLUMEN_MILCH_MAX() {
        return LAGERVOLUMEN_MILCH_MAX;
    }

    public double getLagerMengeFutter() {
        return lagerMengeFutter;
    }

    public double getLagerMengeMilch() {
        return lagerMengeMilch;
    }
}
