/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import allgemein.Bilder;
import allgemein.MyTimer;
import allgemein.NotificationNames;
import model.*;
import view.*;

import notificationcenter.*;
import errorHandling.KuhStallException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class KuhstallManager {

    private static class GUIs {

        private static KuhstallGUI KUHSTALL = new KuhstallGUI();
        private static KuhKaufenGUI KUH_KAUFEN = new KuhKaufenGUI();
        private static BearCalfGUI BEARCALF = new BearCalfGUI();
        private static CopyrightGUI COPYRIGHT = new CopyrightGUI();
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

        setupBuyCowGUIListoners();
        setupBearCalfGUIListoners();
        setupKuhStallGUIListoners();
        setupCopyrightGUIListoners();

        // übergabe des objekts als generischer typ
        Notification n = new Notification(NotificationNames.BOUGHT_NEW_COW, (obj) -> {
        });
        NotificationCenter.shared.add(n);

    }

    private static void setupCopyrightGUIListoners() {
        // [MENUITEM]
        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_COPYRIGHT_GUI_TO_FRONT, (nil) -> {
            GUIs.COPYRIGHT.setVisible(true);
        }));
        // [CANCEL]
        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_COPYRIGHT_GUI_TO_BACKGROUND, (nil) -> {
            GUIs.COPYRIGHT.setVisible(false);
        }));
    }

    private static void setupBuyCowGUIListoners() {
        // [MENUITEM]
        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_BUY_COW_GUI_TO_FRONT, (nil) -> {
            GUIs.KUH_KAUFEN.setVisible(true);
        }));
        // [CANCEL]
        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_BUY_COW_GUI_TO_BACKGROUND, (nil) -> {
            GUIs.KUH_KAUFEN.setVisible(false);
        }));
        // [BUY COW]
        NotificationCenter.shared.add(new Notification(NotificationNames.BOUGHT_NEW_COW, (object) -> {
            if (object instanceof Kuh) {
                Kuh kuh = (Kuh) object;
                try {
                    Models.KUHSTALL.addKuh(kuh);

                } catch (KuhStallException e) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                }

                NotificationCenter.shared.processNotification(NotificationNames.SHOW_LAST_COW_DATA);
                GUIs.KUH_KAUFEN.clearTextFields();
            }
        }));
    }

    public static void setupBearCalfGUIListoners() {
        // [MENUITEM]
        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_BORE_NEW_CALF_GUI_TO_FRONT, (nil) -> {
            GUIs.BEARCALF.setVisible(true);
        }));
        // [CANCEL]
        NotificationCenter.shared.add(new Notification(NotificationNames.SEND_BORE_NEW_CALF_GUI_TO_BACKGROUND, (nil) -> {
            GUIs.BEARCALF.setVisible(false);
        }));
        // [BORE NEW]
        NotificationCenter.shared.add(new Notification(NotificationNames.BORE_NEW_CALF, (object) -> {
            if (object instanceof String) {
                String name = (String) object;
                try {
                    Models.KUHSTALL.bearCalf(name);
                } catch (KuhStallException e) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                }

                NotificationCenter.shared.processNotification(NotificationNames.SHOW_LAST_COW_DATA);
                GUIs.BEARCALF.clearTextFields();
            }
        }));
    }

    private static void setupKuhStallGUIListoners() {

        // UPDATE
        NotificationCenter.shared.add(new Notification(NotificationNames.UPDATE_KUHSTALL_GUI, (obj) -> {
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
                    JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
                }
            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.DELIVER_MILK, (nil) -> {
            try {
                Models.KUHSTALL.deliverTotalMilkAmountInStock();
            } catch (KuhStallException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            }
            NotificationCenter.shared.processNotification(NotificationNames.UPDATE_KUHSTALL_GUI);
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.FEED_COWS, (nil) -> {
            try {
                Models.KUHSTALL.feedAllCowsEqually();
                NotificationCenter.shared.processNotification(NotificationNames.UPDATE_KUHSTALL_GUI);
                GUIs.KUHSTALL.performAnimation(Bilder.Kuh.FRESSEND, 5);

            } catch (KuhStallException e) {
                JOptionPane.showMessageDialog(null, "ERROR: " + e.getMessage());
            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.MELK_ALL_COWS, (nil) -> {
            Models.KUHSTALL.milkAllCows();
            NotificationCenter.shared.processNotification(NotificationNames.UPDATE_KUHSTALL_GUI);
            GUIs.KUHSTALL.performAnimation(Bilder.Kuh.WIRD_GEMOLKEN);
        }));

        // DATENSATZSTEUERUNG
        NotificationCenter.shared.add(new Notification(NotificationNames.SHOW_FIRST_COW_DATA, (nil) -> {
            Kuh cow = Models.KUHSTALL.getFirstCow();
            if (cow != null) {
                GUIs.KUHSTALL.updateWithCowData(cow, Models.KUHSTALL.getCurrentCowIndex(), Models.KUHSTALL.getTotalCowsInStock());
            }
        }));

        NotificationCenter.shared.add(new Notification(NotificationNames.SHOW_NEXT_COW_DATA, (nil) -> {
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
