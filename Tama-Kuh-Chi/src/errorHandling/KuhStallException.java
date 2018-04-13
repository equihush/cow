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

    private String description = "";

    public KuhStallException(String description) {
        super();
        this.description = description;
    }

    public KuhStallException(KuhStallException e) {
        super();
        this.description = e.description;
    }

    
    // GETTER
    public String getDescription() {
        return description;
    }

}
