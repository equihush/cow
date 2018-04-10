package z_solution;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package solution;
//
///**
// * modelliert eine Kuh mit Gewichtszunahme und Milchproduktion.
// * Standardgeburtsgewicht ist 35 kg. Maximal aufnehmbare Futtermenge ist 
// * 55 kg. Die Kuh kann zunehmen bis 600 kg. Ab diesem Gewicht erzeugt sie Milch.
// * Die maximal erzeugbare Milchmenge für eine Kuh ist 25 l.
// * Die Milch kann gemolken werden.
// *
// */
///*
//public class Kuh {
//
//    // Konstanten werden durch das Schlüsselwort final definiert
//    
//    
//    
//    
//    /**
//     * Legt das Mindestgewicht einer Kuh in kg fest.
//     */
//    public static final double MINGEWICHT = 35.0;
//    /**
//     * Legt das Maximalgewicht einer Kuh in kg fest.
//     */
//    public static final double MAXGEWICHT = 600.0;
//    /**
//     * Legt die maximale Futtermenge in kg fest, die eine Kuh verwerten kann.
//     */
//    public static final double MAXFUTTER = 55.0;
//    /**
//     * Legt die maximale Milchmenge in kg fest, die eine Kuh produzieren kann.
//     * Dabei wird angenommen, dass ein Liter Milch = 1 kg Milch.
//     */
//    public static final double MAXMILCHMENGE=25.0;
//    /**
//     * Legt den Faktor fest, mit dem ein Kalb das aufgenommene Futter in 
//     * Gewicht umsetzt.
//     */
//    public static final double UMSETZUNG_FUTTER_IN_GEWICHT=0.8;
//    /**
//     * Legt den Faktor fest, mit dem eine Kuh das aufgenommene Futter in 
//     * Milch umsetzt.
//     */
//    public static final double UMSETZUNG_FUTTER_IN_MILCHMENGE=0.5;
//
//    private static int lfdNr=0;
//
//    
//    private int id;
//    
//    private String name;
//    
//    private double gewicht=MINGEWICHT;  // in kg
//    
//     private double milchMenge=0;  // in litern
//
//    /**
//     * @return the lfdNr
//     */
//    public static int getLfdNr() {
//        return lfdNr;
//    }
//    
//    public static void setLfdNr(int pLfdNr){
//        if (pLfdNr > lfdNr) {
//            lfdNr=pLfdNr+1;
//        }
//    }
//
//    public Kuh(){
//        id=lfdNr;
//        lfdNr++;        
//    }
//
//    /**
//     * initialisiert eine Kuh mit dem Namen 'name' und dem
//     * Standardgeburtsgewicht. (Konstante MINGEWICHT)
//     * @param name der Name der Kuh.
//     * @see Kuh#MINGEWICHT
//     */
//    public Kuh(String name) {
//        this();
//        this.name = name;
//    }
//
//    /**
//     * initialisiert eine Kuh mit dem Namen 'name' und dem
//     * Gewicht 'gewicht'. Dabei muss das Gewicht zwischen dem minimalen und
//     * maximalen Gewicht liegen.
//     * @param name
//     * @param gewicht
//     * @see Kuh#MINGEWICHT
//     * @see Kuh#MAXGEWICHT
//     */
//    public Kuh(String name, double gewicht) {
//        this();
//        this.name = name;
//        this.setGewicht(gewicht);
//    }
//
//
//    /**
//     * gibt den Namen der Kuh zurück.
//     * @return the name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * setzt den Namen der Kuh.
//     * @param name the name to set
//     */
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * gibt das Gewicht der Kuh einschließlich dem Gewicht der Milchmenge zurück.
//     * @return the gewicht
//     */
//    public double getGewicht() {
//        return this.gewicht+this.milchMenge;
//    }
//
//    /**
//     * gibt die Menge der im Euter vorhandenen Milch zurück, ohne sie zu ändern.
//     * @return the Milch
//     */
//    public double getMilch() {
//        return milchMenge;
//    }
//
//    /**
//     * nimmt das Futter in kg entgegen und wandelt es in Körpergewicht oder
//     * Milch um.<br>
//     * Bis zum maximalen Körpergewicht wird die Nahrung entsprechend 
//     * der Konstante UMSETZUNG_FUTTER_IN_GEWICHT in 
//     * Körpergewicht umgewandelt. <br>
//     * Über dem maximalen Körpergewicht wird die Nahrung entsprechend der
//     * Konstante UMSETZUNG_FUTTER_IN_MILCHMENGE in Milch umgewandelt.
//     * @param futterInKg muss zwischen 0 und der maximalen Futtermenge liegen.
//     * @throws TamaKuhChiException, wenn das Euter der Kuh voll ist und mit dem  
//     * zur Verfügung gestellten Futter keine Milch mehr gebildet werden kann.
//     * @see Kuh#UMSETZUNG_FUTTER_IN_GEWICHT
//     * @see Kuh#UMSETZUNG_FUTTER_IN_MILCHMENGE
//     */
//    public void fressen(double futterInKg) 
//            throws TamaKuhChiException{
//        if(futterInKg>0){
//            if(futterInKg>MAXFUTTER){
//                futterInKg=MAXFUTTER;
//            }
//            if(this.gewicht<MAXGEWICHT){
//                this.setGewicht(this.gewicht + UMSETZUNG_FUTTER_IN_GEWICHT * futterInKg);
//            }
//            else{
//                this.setMilchMenge(futterInKg * UMSETZUNG_FUTTER_IN_MILCHMENGE);
//            }
//        }
//    }
//
//    /**
//     * gibt die Menge der erzeugten Milch zurück und setzt den Euterinhalt
//     * auf 0 l.
//     * @return die Milchmenge
//     */
//    public double melken(){
//        double milchrueck=this.milchMenge;
//        milchMenge=0;
//        return milchrueck;
//    }
//
//    /**
//     * setzt das Gewicht. Der Gewichtswert muss zwischen dem
//     * Standardgeburtsgewicht und dem Gewicht des ausgewachsenen Tieres liegen.
//     * @param gewicht the gewicht to set
//     * @see Kuh#MINGEWICHT
//     * @see Kuh#MAXGEWICHT
//     */
//    private void setGewicht(double gewicht) {
//        if (gewicht>MAXGEWICHT) {
//            this.gewicht=MAXGEWICHT;
//        } else if (gewicht<MINGEWICHT) {
//            this.gewicht = MINGEWICHT;
//        } else{
//            this.gewicht=gewicht;
//        }        
//    }
//
//    private void setMilchmengeOnce(double milchmenge){
//        if (milchmenge>MAXMILCHMENGE) {
//            this.milchMenge=MAXMILCHMENGE;
//        }else if(milchmenge<0){
//            this.milchMenge=0;
//        }else{
//            this.milchMenge=milchmenge;
//        }       
//    }
//    
//    /**
//     *
//     * @param milchMenge the milchMenge to set
//     */
//    private void setMilchMenge(double milchMenge) 
//            throws TamaKuhChiException {
//        if (this.milchMenge<MAXMILCHMENGE) {
//            this.milchMenge = this.milchMenge + milchMenge;
//            if (this.milchMenge > MAXMILCHMENGE) {
//                this.milchMenge = MAXMILCHMENGE;
//            }
//        } else {
//            throw new TamaKuhChiException(name+" muss erst gemolken werden");
//        }
//    }
//    
//    public double getBedarfProFuetterung(){
//        double bedarfProFuetterung=0;
//        if (this.gewicht>=Kuh.MAXGEWICHT) {
//            bedarfProFuetterung+=Kuh.MAXMILCHMENGE/Kuh.UMSETZUNG_FUTTER_IN_MILCHMENGE;
//        } else {
//            bedarfProFuetterung+=Kuh.MAXFUTTER;
//        }            
//        return bedarfProFuetterung;
//        
//    }
//
//    /**
//     * Gibt die internen Daten zurück.
//     * Die Daten werden in folgender Form ausgegeben:<br>
//     * ----------------------<br>
//     * Name:        Elsa<br>
//     * Gewicht:     600 kg<br>
//     * Milchvorrat: 10 l<br>
//     * @return die internen Daten.
//     */
//    public String getDaten(){
//        String daten="\n ----------------------\n"+
//                " ID:        "+this.getId()+"\n"+
//                " Name:        "+this.getName()+"\n"+
//                " Gewicht:     "+this.getGewicht()+"\n"+
//                " Milchvorrat: "+this.getMilch()+" l\n";
//        return daten;
//    }
//
//    /**
//     * Überschriebene toString Methode.
//     * Diese Methode gibt eine Kurzfassung der Kuhdaten im Format<br>
//     *  &nbsp;&nbsp;&nbsp;name;gewicht<br>
//     * zurück.
//     * @return Name und Gewicht.
//     */
//    @Override
//    public String toString(){
//        return name+";"+gewicht;
//    }
//
//    public String toCSVString(){
//        return id+";"+name+";"+gewicht+";"+milchMenge;
//    }
//    
//    public void setFromCsvString(String csvString){
//        String daten[]=csvString.split(";");
//        setId(Integer.parseInt(daten[0]));
//        setName(daten[1]);
//        setGewicht(Double.parseDouble(daten[2]));
//        setMilchmengeOnce(Double.parseDouble(daten[3]));
//    }
//
//    public static String strukturVonCSVString(){
//        return "ID;Name;Gewicht;Milchmenge";
//    }
//
//    /**
//     * @return the id
//     */
//    public int getId() {
//        return id;
//    }
//
//    /**
//     * @param id the id to set
//     */
//    private void setId(int id) {
//        this.id = id;
//        setLfdNr(id);
//    }
//
//}
