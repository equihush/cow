package tama.kuh.chi;
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
    
    private ArrayList<Kuh> Stall=new ArrayList<>();
    
    public void kaufeKuh(Kuh kuh){
        
            Stall.add(kuh);
    }
    
    public void gebäreKuh(String name){
        Kuh neuesKalb = new Kuh(name, 35);
        Stall.add(neuesKalb);
        
    }
    
    
    
    public double ausliefern(){
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
