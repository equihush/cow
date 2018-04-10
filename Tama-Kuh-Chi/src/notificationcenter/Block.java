/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationcenter;

@FunctionalInterface
public interface Block<T> {
    void process(T object);
}

