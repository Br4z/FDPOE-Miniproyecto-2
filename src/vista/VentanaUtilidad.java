/*

                 *´¨) 
                ¸.•´ ¸.•´¸.•*´¨) ¸.•*¨) 
                (¸.•´ (¸.•` ¤ 
       .---.     calderon.brandon@correounivalle.edu.co
      /     \                 202125974
      \.@-@./    idrobo.sebastian@correounivalle.edu.co             
      /`\_/`\                 202122637
     //  _  \\         Ingeniería de sistemas          
    | \     )|_               Profesor
   /`\_`>  <_/ \      Luis Yovany Romo Portilla         
   \__/'---'\__/     
 */

package vista;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import logica.myLibrary;

/**
 *  CLASE:     VentanaUtilidad
 *  INTENCION: Ser la ventana que explica la utilidad de este juego (beneficios para la mente).
 *  RELACION:  NINGUNA 
 */


public class VentanaUtilidad extends JFrame{
    private JLabel exitLabel = new JLabel();
        
    public VentanaUtilidad() {
        initializeComponents();        
        setSize(720, 515); // Aqui es diferente el alto porque el la ventan empiza en los bordes, no en la imagen
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
        
        // Establecemos el fondo
        setContentPane(new Background());
        setLayout(null); // Descativamos la distribucion por defecto
        
        exitLabel.setBounds(720 - 75, 10, 50, 50);
        
        myLibrary.addIcon(exitLabel, "exit.png", 50, 50);
        
        add(exitLabel);
        
        exitLabel.addMouseListener(new MyMouseListener());
    }
    
    private class Background extends JPanel {
        private Image image;
        
        public Background() {
            // Aprovechando que declaramos la imagen como atributo podemos hacer esto
            File myImage = new File("src/imagenes/utilidad_background.jpg");
            try {
                image = ImageIO.read(myImage); // Este método arroja una excepción, entonces tenemos que atraparla
            } catch(IOException e) {
                System.out.println("Ha ocurrido un error !");
            }
        }
    
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);        
        
            g.drawImage(image, 0, 0, null);
        }
    }
    
    private class MyMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) { // Como el exitLabel es lo unico que esta escuchado,
            // no son necesarios condicionales
            VentanaInicio window = new VentanaInicio();
            dispose();            
        }
    }    
}
