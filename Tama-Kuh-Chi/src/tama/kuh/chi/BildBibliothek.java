package tama.kuh.chi;

import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author c.schneider
 */
public class BildBibliothek {
    
    public final String[] bildpfade = {
        "/Users/c.schneider/NetBeansProjects/StallSIM/src/kuh_frisst.gif",
        "/Users/c.schneider/NetBeansProjects/StallSIM/src/kuh_steht.gif",
        "/Users/c.schneider/NetBeansProjects/StallSIM/src/kuh_wirdgemolken.gif"
        
        
    
};
    public final int INDEX_KUH_STEHT = 1;
    public final int INDEX_KUH_FRISST = 2;
    public final int INDEX_KUHWIRDGEMOLKEN =3;
    
    public final ImageIcon IMG_KUH_STEHT = new ImageIcon(getClass().getResource(bildpfade[INDEX_KUH_STEHT]));
    public final ImageIcon IMG_KUH_FRISST = new ImageIcon(getClass().getResource(bildpfade[INDEX_KUH_FRISST]));
    public final ImageIcon IMG_KUH_MELKEN = new ImageIcon(getClass().getResource(bildpfade[INDEX_KUHWIRDGEMOLKEN]));
    
    
    
    
    
    
}
