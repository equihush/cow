/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import model.*;
import view.*;
/**
 *
 * @author macbookpro15
 */
public class KuhstallManager {

    // GUIs
    private static KuhstallGUI kuhstallGUI = new KuhstallGUI();
    
    // models
    private static Kuhstall kuhstall = new Kuhstall();

    
    
    
    public static void main(String[] args) {
        kuhstallGUI.setVisible(true);
    }
}
