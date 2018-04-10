/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c.schneider
 */
public class Kuh {
    
//    public static void setLfdNr(int lfdNr){
//        if(lfdNr>lfdNr){
//            lfdNr = lfdNr + 1;
//        }
//    }
    
    public Kuh(){
        
        id=lfdNr;
        lfdNr++;
    }
   
    public Kuh (String Name, double Gewicht){
        this();
        this.name = Name;
        if(Gewicht > MIN_GEWICHT){
            this.gewicht = Gewicht;
        }
    }
    

    

    
    String name;    
    double gewicht;
    double milchmenge;
    private int lfdNr = 0;
    private int id = 0;

   
    
    
    public static final double MIN_GEWICHT = 35;
    public static final double MAX_GEWICHT = 600;
    public static final double MAX_MILCHMENGE = 25;
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void fressen(Double futtermenge){        
   
    if(this.gewicht < MAX_GEWICHT){ 
        if(futtermenge > 55 ){
        this.gewicht = this.gewicht + (0.8 * futtermenge) ;
        } else this.gewicht = this.gewicht + (0.8 * 55);
        
    if(this.gewicht > 600 || this.milchmenge < 25){
        if(futtermenge > 55){
            this.milchmenge = this.milchmenge + (0.5 * futtermenge);
            }
        }    
            
          
    }  
    
    
    
}
    
    public String getDaten(){
        String daten = "\n-------------------------------------------\n" +
                "Name:    " + this.getName() + "\n" +
                "Gewicht: " + this.getGewicht() + "\n" +
                "Milchvorrat: " + this.getMilchmenge() + "\n";
        return daten;
    }

    public double getMilchmenge() {
        return milchmenge;
    }
    
     public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }
    
    public double melken(){
        double interneMilchmenge = this.milchmenge;
        this.milchmenge = 0;
        return interneMilchmenge;
    }
    
    public String toString(){
        return name + "," + gewicht;
    }
    
    public String toCSVString(){
        return id+";"+name+";"+gewicht+";"+milchmenge;
    }

    
}
