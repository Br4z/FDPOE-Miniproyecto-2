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
 *  CLASE:     VentanaInicio     
 *  INTENCION: Ser la primera ventana que ve el jugador (un tipo de menu).
 *  RELACION:  NINGUNA 
 */

public class VentanaInicio extends JFrame{
    public VentanaInicio() {
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
        add(new background());
    }
    
    class background extends JPanel {
        private Image image;
        
        public background() {
            // Aprovechando que declaramos la imagen como atributo podemos hacer esto
            File myImage = new File("src/Imagenes/background.jpg");
            try {
                image = ImageIO.read(myImage); // Este método arroja una excepción, entonces tenemos que atraparla
            } catch(IOException e) {
                System.out.println("Ha ocurrido un error !");
            }
        }
    
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);        
        
            g.drawImage(image, 0, 0, null); // El bucle la vuelve a dibujar en esa posición    
        }
    }
}

    
