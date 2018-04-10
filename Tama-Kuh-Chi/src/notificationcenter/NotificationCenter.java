/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationcenter;

import java.util.ArrayList;
import java.util.List;


public class NotificationCenter {

    // Properties
    private List<Notification> notifications = new ArrayList<>();

    // Singleton
    public static NotificationCenter shared = new NotificationCenter();

    // enshure there is only one instance
    private NotificationCenter() {
    }

    public void processNotification(Notification.Name name) {
        for (Notification notification : notifications) {
            if (notification.name.rawValue.equals(name.rawValue)) {
                notification.action.process(null);
            }
        }
    }
    
    public <T> void processNotification(Notification.Name name, T object) {
        for (Notification notification : notifications) {
            if (notification.name.rawValue.equals(name.rawValue)) {
                notification.action.process(object);
            }
        }
    }
    

    public void add(Notification notification) {
        notifications.add(notification);
    }

    public void remove(Notification notification) {
        notifications.remove(notification);
    }

}

