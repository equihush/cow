/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allgemein;

import java.util.Timer;
import java.util.TimerTask;
import notificationcenter.Block;

public 

class MyTimer {

    private Timer timer = new Timer();
    private Block action;
    
    public MyTimer() {
        
    }
    public MyTimer(Block action) {
        this.action = action;
    }

    
    public <T> void performAfter(int seconds, Block action, T object) {
        this.action = action;
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                action.process(object);
            }
        }, 1000 * seconds);
    }
    
    
    
}


