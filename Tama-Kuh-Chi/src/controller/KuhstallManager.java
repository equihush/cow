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
        
      
        NotificationCenter.shared.processNotification(NotificationNames.SHOW_FIRST_COW_DATA);


    }

    // Go Lambda!
    private static void setupNotificationListoners() {

        // KuhKaufenGUI
        NotificationCenter.shared.add(new Notification(NotificationNames.BOUGHT_NEW_COW, (object) -> {
            if (object instanceof Kuh) {
                Kuh kuh = (Kuh) object;
                Models.KUHSTALL.addKuh(kuh);

                NotificationCenter.shared.processNotification(NotificationNames.SHOW_LAST_COW_DATA);
                GUIs.KUH_KAUFEN.clearTextFields();
            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_BUY_COW_GUI_TO_FRONT, (nil) -> {
            GUIs.KUH_KAUFEN.setVisible(true);
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_BUY_COW_GUI_TO_BACKGROUND, (nil) -> {
            GUIs.KUH_KAUFEN.setVisible(false);
            System.out.println("recieved notivication: SEND_BUY_COW_GUI_TO_BACKGROUND");
        }));

        // KuhstallGUI 
        NotificationCenter.shared.add(new Notification(NotificationNames.SHOW_NEXT_COW_DATA, (nil) -> {
            Kuh cow = Models.KUHSTALL.getNextCow();
            if (cow != null) {
                GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex());
                System.out.println("recieved notivication: SHOW_NEXT_COW_DATA");

            }

        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.SHOW_PREVIOUS_COW_DATA, (nil) -> {
            Kuh cow = Models.KUHSTALL.getPreviousCow();
            if (cow != null) {
                GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex());
                System.out.println("recieved notivication: SHOW_PREVIOUS_COW_DATA");

            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.SHOW_LAST_COW_DATA, (nil) -> {
            Kuh cow = Models.KUHSTALL.getLastCow();
            if (cow != null) {
                GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex());
                System.out.println("recieved notivication: SHOW_LAST_COW_DATA");

            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.SHOW_FIRST_COW_DATA, (nil) -> {
            Kuh cow = Models.KUHSTALL.getFirstCow();
            if (cow != null) {
                GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex());
                System.out.println("recieved notivication: SHOW_FIRST_COW_DATA");

            }
        }));
    }
}
