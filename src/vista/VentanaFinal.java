/*

                 *´¨) 
                ¸.•´ ¸.•´¸.•*´¨) ¸.•*¨) 
                (¸.•´ (¸.•` ¤ 
       .---. 
      /     \   calderon.brandon@correounivalle.edu.co     
      \.@-@./                 202125974 
      /`\_/`\   idrobo.sebastian@correounivalle.edu.co    
     //  _  \\                202122637
    | \     )|_        Ingeniería de sistemas
   /`\_`>  <_/ \
   \__/'---'\__/
 */

package vista;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *  CLASE:     VentanaFinal
 *  INTENCION: Ser la ventana donde se presentan las estadistica del jugador en una "partida".
 *  RELACION:  NINGUNA 
 */

public class VentanaFinal extends JFrame {
    public VentanaFinal() {
        initializeComponents();        
        setSize(720, 480);
        setTitle("Ados2a - Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Posición de la ventana en el centro
        setResizable(false);
    }
    
    private void initializeComponents() {
        Toolkit myScreen = Toolkit.getDefaultToolkit(); 
        // Para establecer el icono en la aplicación
        Image icon = myScreen.getImage("src/Imagenes/icon.png");
        setIconImage(icon);
    }
}
