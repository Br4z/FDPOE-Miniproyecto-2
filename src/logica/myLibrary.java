/*
                 *´¨) 
                ¸.•´ ¸.•´¸.•*´¨) ¸.•*¨) 
                (¸.•´ (¸.•` ¤ Brandon Calderón Prieto  
       .---.         
      /     \   calderon.brandon@correounivalle.edu.co     
      \.@-@./               
      /`\_/`\               202125974
     //  _  \\        
    | \     )|_        Ingeniería de sistemas
   /`\_`>  <_/ \
   \__/'---'\__/
 */

package logica;

import java.awt.Image;
import javax.swing.*;



/**
 *  CLASE:     Function
 *  INTENCION: Reutilizar codigo de una manera mas eficiente
 *  RELACION:  NINGUNA 
 */


public class myLibrary {
    public static void addIcon(JLabel label, String path, int xSize, int ySize) {
        ImageIcon labelImageIcon = new ImageIcon("src/imagenes/" + path);
        Icon labelIcon = new ImageIcon(labelImageIcon.getImage().getScaledInstance(xSize, ySize, Image.SCALE_DEFAULT));
        label.setIcon(labelIcon);
    }
}
