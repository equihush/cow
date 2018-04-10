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
   
    public Kuh (String Name, double Gewicht){
        this.name = Name;
        if(Gewicht > MIN_GEWICHT){
            this.Gewicht = Gewicht;
        }
    }
    

    

    
    String name;    
    double Gewicht;
    double Milchmenge;
    
    
    public static final double MIN_GEWICHT = 35;
    public static final double MAX_GEWICHT = 600;
    public static final double MAX_MILCHMENGE = 25;
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public double fressen(Double futtermenge){
    double gewicht;    
    double neuesGesamtGewicht = 0;
    if(this.Gewicht < MAX_GEWICHT){ 
        if(futtermenge > 55 ){
        this.Gewicht = this.Gewicht + (0.8 * futtermenge) ;
        } else this.Gewicht = this.Gewicht + (0.8 * 55);
        
    if(this.Gewicht > 600 || this.Milchmenge < 25){
        if(futtermenge > 55){
            this.Milchmenge = this.Milchmenge + (0.5 * futtermenge);
        }
    }    
            
          
    neuesGesamtGewicht = this.Gewicht + this.Milchmenge;     
              
               
    }return neuesGesamtGewicht;
    
    
}

    public double getMilchmenge() {
        return Milchmenge;
    }
    
    public double melken(){
        double interneMilchmenge = this.Milchmenge;
        this.Milchmenge = 0;
        return interneMilchmenge;
    }
    
    

    
}
