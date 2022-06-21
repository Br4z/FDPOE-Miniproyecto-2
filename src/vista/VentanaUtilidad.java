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

import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *  CLASE:     VentanaUtilidad
 *  INTENCION: Ser la ventana que explica la utilidad de este juego (beneficios para la mente).
 *  RELACION:  NINGUNA 
 */

public class VentanaUtilidad extends JFrame{
    public VentanaUtilidad() {
        initializeComponents();        
        setSize(720, 480);
        setTitle("Ados2a - Utilidad");
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
