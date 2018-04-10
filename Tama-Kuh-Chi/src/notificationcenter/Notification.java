/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationcenter;

public class Notification {

    public static class Name {

        public String rawValue;

        public Name(String rawValue) {
            this.rawValue = rawValue;
        }
    }

    public Notification.Name name;
    public Object object;
    public Block action; // = (T) -> {};

    public Notification(Notification.Name name, Block action) {
        this.name = name;
        this.action = action;
    }

    public Notification(Notification.Name name, Object object) {
        this.name  = name;
        this.object = object;
    }
}
