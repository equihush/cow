/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allgemein;

import java.util.Timer;
import java.util.TimerTask;
import view.KuhstallGUI;

/**
 *
 * @author mac
 */
public class MyTimerTask extends TimerTask {

    KuhstallGUI parent;
    
    @Override
    public void run() {
        // parent.setKuhImageIcon(Bilder.Kuh.stehend)
    }
    
    // TODO:  
    public static void fireSheduledTimerTask() {
        MyTimerTask ttask = new MyTimerTask();
        Timer t = new Timer();
                
        // feure Task nach 5 Sekunden
        t.schedule(ttask, 5000);
        

    }
}
