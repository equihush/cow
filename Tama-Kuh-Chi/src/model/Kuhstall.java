package model;

import java.util.ArrayList;

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

    private static final double MAX_FUTTERMENGE = 20000;
    private static final double MAX_MILCHMENGE = 500;

    private double futterimStall = 0;
    private double milchimStall = 0;

    private ArrayList<Kuh> kuehe = new ArrayList<>();

    private int currentCowIndex = 0;

    public Kuhstall() {
        kuehe.add(new Kuh("elsa", 100.0));
        kuehe.add(new Kuh("pepe", 80.0));
        kuehe.add(new Kuh("milka", 200.0));
        kuehe.add(new Kuh("fleck", 500));
        setCurrentCowIndex(kuehe.size() -1);

    }

    public void addKuh(Kuh kuh) {
        kuehe.add(kuh);
    }

    public int getCurrentCowIndex() {
        return currentCowIndex;
    }

    public void setCurrentCowIndex(int index) {
        this.currentCowIndex = index;
        System.out.println("Kuhstall.setCurrentCowIndex - index: " + index);
    }

    public Kuh getKuhByIndex(int index) {
        if (index >= 0 && index < kuehe.size()) {
            return kuehe.get(index);
        }
        // TODO: Throw exeption
        return null;
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
        System.out.println("Kuhstall.hasPreviousCow - currentCowIndex: " + currentCowIndex);
        return !kuehe.isEmpty() && currentCowIndex > 0;
    }

    public void gebäreKuh(String name) {
        Kuh neuesKalb = new Kuh(name, 35);
        kuehe.add(neuesKalb);

    }

    public double ausliefern() {
        double aktMilchstand = this.milchimStall;
        this.milchimStall = 0;
        return aktMilchstand;
    }

    public static double getMAX_FUTTERMENGE() {
        return MAX_FUTTERMENGE;
    }

    public static double getMAX_MILCHMENGE() {
        return MAX_MILCHMENGE;
    }

    public double getFutterimStall() {
        return futterimStall;
    }

    public double getMilchimStall() {
        return milchimStall;
    }

}
