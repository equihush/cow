/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errorHandling;

/**
 *
 * @author macbookpro15
 */
public class KuhStallException extends Exception {

    public KuhStallException(Throwable cause) {
        super(cause);
    }
    
    public KuhStallException(String message) {
        super(message);
    }
    
    public KuhStallException() {
        super("Unbekannte Kuhstall-Exeption");
    }
}
