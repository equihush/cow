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
import errorHandling.KuhStallException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

        setupCowGUIListoners();
        setupKuhStallGUIListoners();
    }

    private static void setupCowGUIListoners() {

        NotificationCenter.shared.add(new Notification(NotificationNames.BOUGHT_NEW_COW, (object) -> {
            if (object instanceof Kuh) {
                Kuh kuh = (Kuh) object;
                try {
                    Models.KUHSTALL.addKuh(kuh);

                } catch (KuhStallException e) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + e.getDescription());
                }

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
    }

    private static void setupKuhStallGUIListoners() {

        // UPDATE
        NotificationCenter.shared.add(new Notification(NotificationNames.UPDATE_KUHSTALL_GUI, (nil) -> {
            double foodAmountInStock = Models.KUHSTALL.getLagerMengeFutter();
            double milkAmountInStock = Models.KUHSTALL.getLagerMengeMilch();
            double calculatedTotalAmountOfFood = Models.KUHSTALL.calculatedTotalAmountOfFood();
            Kuh currentCow = Models.KUHSTALL.getCurrentCow();
            int currentCowIndex = Models.KUHSTALL.getCurrentCowIndex();

            double milk = Models.KUHSTALL.getLagerMengeMilch();
            
            
            GUIs.KUHSTALL.getStoredFoodAmountLabel().setText("" + foodAmountInStock);
            GUIs.KUHSTALL.getDemandFoodLabel().setText("" + calculatedTotalAmountOfFood);
            GUIs.KUHSTALL.getMilkTankVolumeLabel().setText("" + milkAmountInStock);
            if (currentCow != null) {
                GUIs.KUHSTALL.updateWithCowData(currentCow, currentCowIndex, Models.KUHSTALL.getTotalCowsInStock());
            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.ADD_FOOD_TO_STORE, (object) -> {
            if (object instanceof Double) {
                double food = (Double) object;
                try {
                    Models.KUHSTALL.addFutterMenge(food);
                    GUIs.KUHSTALL.getStoredFoodAmountLabel().setText("" + Models.KUHSTALL.getLagerMengeFutter());

                    NotificationCenter.shared.processNotification(NotificationNames.UPDATE_KUHSTALL_GUI);

                } catch (KuhStallException e) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + e.getDescription());
                }
            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.DELIVER_MILK, (nil) -> {
            Models.KUHSTALL.deliverTotalMilkAmountInStock();

            NotificationCenter.shared.processNotification(NotificationNames.UPDATE_KUHSTALL_GUI);
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.FEED_COWS, (nil) -> {
            try {
                Models.KUHSTALL.feedAllCowsEqually();

                NotificationCenter.shared.processNotification(NotificationNames.UPDATE_KUHSTALL_GUI);

            } catch (KuhStallException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getDescription());
            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.MELK_ALL_COWS, (nil) -> {
            Models.KUHSTALL.milkAllCows();
            NotificationCenter.shared.processNotification(NotificationNames.UPDATE_KUHSTALL_GUI);
        }));

        // DATENSATZSTEUERUNG
        NotificationCenter.shared.add(new Notification(NotificationNames.SHOW_FIRST_COW_DATA, (nil) -> {
            Kuh cow = Models.KUHSTALL.getFirstCow();
            if (cow != null) {
                GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex(), Models.KUHSTALL.getTotalCowsInStock());
            }
        }));

        NotificationCenter.shared.add(
                new Notification(NotificationNames.SHOW_NEXT_COW_DATA, (nil) -> {
                    Kuh cow = Models.KUHSTALL.getNextCow();
                    if (cow != null) {
                        GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex(), Models.KUHSTALL.getTotalCowsInStock());
                    }
                }
                ));

        NotificationCenter.shared.add(
                new Notification(NotificationNames.SHOW_PREVIOUS_COW_DATA, (nil) -> {
                    Kuh cow = Models.KUHSTALL.getPreviousCow();
                    if (cow != null) {
                        GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex(), Models.KUHSTALL.getTotalCowsInStock());
                    }
                }
                ));

        NotificationCenter.shared.add(
                new Notification(NotificationNames.SHOW_LAST_COW_DATA, (nil) -> {
                    Kuh cow = Models.KUHSTALL.getLastCow();
                    if (cow != null) {
                        GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex(), Models.KUHSTALL.getTotalCowsInStock());
                    }
                }));

    }
}
