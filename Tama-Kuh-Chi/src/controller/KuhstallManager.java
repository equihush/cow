/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import allgemein.NotificationNames;
import model.*;
import view.*;
import notificationcenter.*;

public class KuhstallManager {

    private static class GUIs {
        private static KuhstallGUI KUHSTALL = new KuhstallGUI();
        private static KuhKaufenGUI KUH_KAUFEN = new KuhKaufenGUI();
    }

    private static class Models {
        private static final Kuhstall KUHSTALL = new Kuhstall();
    }
    

    public static void main(String[] args) {
        GUIs.KUHSTALL.setVisible(true);

        setupNotificationListoners();
        

    }

    private static void setupNotificationListoners() {

        // Go Lambda!
        NotificationCenter.shared.add(new Notification(NotificationNames.BOUGHT_NEW_COW, (object) -> {
            if (object instanceof Kuh) {
                Kuh kuh = (Kuh) object;
                Models.KUHSTALL.addKuh(kuh);
                
                GUIs.KUHSTALL.setNewCowData(kuh);
                GUIs.KUH_KAUFEN.clearTextFields();
            }
        }));
        
        
        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_BUY_COW_GUI_TO_FRONT, (nil) -> {
            GUIs.KUH_KAUFEN.setVisible(true);
        }));

         NotificationCenter.shared.add(new Notification(NotificationNames.SEND_BUY_COW_GUI_TO_BACKGROUND, (nil) -> {
            GUIs.KUH_KAUFEN.setVisible(false);
        }));
    }
}
