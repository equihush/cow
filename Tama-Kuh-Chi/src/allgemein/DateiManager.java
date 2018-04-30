/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allgemein;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.KuhKaufenGUI;
import view.KuhstallGUI;

public class DateiManager {

    //String dateiname = "/Users/mac/Desktop/dateiname";
    
    private KuhstallGUI gui;
    private JFileChooser fileChooser;

    
    
    public DateiManager(KuhstallGUI gui) {
        this.gui = gui;
        
        File standardPfad = new File(".");
        fileChooser = new JFileChooser(standardPfad);


        FileNameExtensionFilter extFilter = new FileNameExtensionFilter(".csv-Datei (*.csv)", "csv");
        fileChooser.setFileFilter(extFilter);
    }
    
    
    public void selectFile() {
        
        int returnValue = fileChooser.showOpenDialog(gui);
        File file;
        
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                // dateiadapter leseDaten(file);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(gui, ex.getMessage());
            }
        }
    }
    
    
    public void saveFile() {
        int returnValue = fileChooser.showSaveDialog(gui);
        int save = JOptionPane.OK_OPTION;
        File file;
        
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            
            if(file.exists()) {
                save = JOptionPane.showConfirmDialog(gui.getRootPane(), "Soll die Datei überschrieben werden?", "", JOptionPane.YES_NO_OPTION);
                
            }
            if(save == JOptionPane.OK_OPTION) {
                try {
                    //adapter schreibe dateien(datei)
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(gui, ex.getMessage());
                }
            }
        }
    }
}











