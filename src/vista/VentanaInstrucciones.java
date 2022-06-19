/*

                 *´¨) 
                ¸.•´ ¸.•´¸.•*´¨) ¸.•*¨) 
                (¸.•´ (¸.•` ¤ Brandon Calderón Prieto  
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
 *  CLASE:     VentanaInstrucciones
 *  INTENCION: Ser la ventana donde estan las intrucciones del juego.
 *  RELACION:  NINGUNA 
 */

public class VentanaInstrucciones extends JFrame {
    public VentanaInstrucciones() {
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
