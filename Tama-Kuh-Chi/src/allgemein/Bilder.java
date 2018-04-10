/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package allgemein;

import java.nio.file.Paths;
import javax.swing.ImageIcon;

/**
 *
 * @author macbookpro15
 */
public class Bilder {

    public static class Kuh {

        public static final ImageIcon STEHEND = getImageIcon(ImageNames.Kuh_.STEHT);
        public static final ImageIcon FRESSEND = getImageIcon(ImageNames.Kuh_.FRISST);
        public static final ImageIcon WIRD_GEMOLKEN = getImageIcon(ImageNames.Kuh_.WIRD_GEMOLKEN);

        
        private static class FolderNames {
            static String source = "src";
            static String images = "z_bilder";
        }

        private static class ImageNames {
            private static class Kuh_ {
                static String FRISST = "kuh - frisst.gif";
                static String WIRD_GEMOLKEN = "kuh - wird gemolken.gif";
                static String STEHT = "kuh - steht.gif";
            }

        }

        private static ImageIcon getImageIcon(String imageName) {
            String imgPathName = Paths.get(FolderNames.source, FolderNames.images, imageName).toString();
            return new ImageIcon(imgPathName);
        }
    }
}
