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

/**
 *  CLASE:     VentanaInstrucciones
 *  INTENCION: Ser la ventana donde estan las intrucciones del juego.
 *  RELACION:  NINGUNA 
 */

public class VentanaInstrucciones extends JFrame {
    private Image image;
    private int imageNumber = 1; // Con esto voy a controlar las "diapositivas"
    private JLabel nextLabel = new JLabel();
    private JLabel backLabel = new JLabel();
    private JLabel exitLabel = new JLabel();
    
    public VentanaInstrucciones() {
        initializeComponents();        
        setSize(720, 480);
        setTitle("Ados2a - Instrucciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Posición de la ventana en el centro
        setResizable(true);
    }
    
    private void initializeComponents() {
        Toolkit myScreen = Toolkit.getDefaultToolkit(); 
        // Para establecer el icono en la aplicación
        Image icon = myScreen.getImage("src/Imagenes/icon.png");
	setIconImage(icon);
        
        // Establecemos el fondo
        setContentPane(new Background());
        setLayout(null); // Descativamos la distribucion por defecto
        
        // A 200 pixeles de borde superior y a 30 pixeles del borde izquierdo
        nextLabel.setBounds(720 - 125, 200 - 50, 100, 100); // La imagen ya cuenta con pixeles "vacios"
        backLabel.setBounds(0, 200 - 50, 100, 100);
        exitLabel.setBounds(720 - 75, 10, 50, 50);
        
        ImageIcon nextImageIcon = new ImageIcon("src/imagenes/arrow_right.png");
        Icon nextIcon = new ImageIcon(nextImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        nextLabel.setIcon(nextIcon);
        
        ImageIcon backImageIcon = new ImageIcon("src/imagenes/arrow_left.png");
        Icon backIcon = new ImageIcon(backImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        backLabel.setIcon(backIcon);
        
        ImageIcon exitImageIcon = new ImageIcon("src/imagenes/exit.png");
        Icon exitIcon = new ImageIcon(exitImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        exitLabel.setIcon(exitIcon);
        
        // Empezamos en la primer diapositiva, entonces la flecha derecha va a estar deshabilitada
        backLabel.setEnabled(false);
        backLabel.setVisible(false);
        
        add(nextLabel);
        add(backLabel);
        add(exitLabel);
        
        nextLabel.addMouseListener(new MyMouseListener());
        backLabel.addMouseListener(new MyMouseListener());
        exitLabel.addMouseListener(new MyMouseListener());
    }
    
    private class Background extends JPanel {       
        public Background() {
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
        
            g.drawImage(image, 0, 0, null);
        }
    }
    private class MyMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels            
            File myImage = new File("src/Imagenes/background.jpg"); // Tenemos que inicializarla
            
            if(elemento == nextLabel) {
                switch (imageNumber) {
                    case 1 -> {
                        backLabel.setEnabled(true);
                        backLabel.setVisible(true);
                    }
                    case 3 -> {                
                        nextLabel.setEnabled(false);
                        nextLabel.setVisible(false);
                    }
                    default -> {
                    }                        
                }
                myImage = new File("src/imagenes/instrucciones/" + imageNumber + 1 + ".jpg");
            } else if(elemento == backLabel){ // Corresponde al caso del backLabel
                if(imageNumber == 2) {
                    backLabel.setEnabled(false);
                    backLabel.setVisible(false);                    
                } else if (imageNumber == 4) {
                    nextLabel.setEnabled(true);
                    nextLabel.setVisible(true); 
                }
                myImage = new File("src/imagenes/instrucciones/" + (imageNumber - 1) + ".jpg");
            } else { // Caso del exit
                VentanaInicio window = new VentanaInicio();
                dispose();
            }
            try {
                image = ImageIO.read(myImage); // Este método arroja una excepción, entonces tenemos que atraparla
            } catch(IOException exception) {
                System.out.println("Ha ocurrido un error !");
            }
        }
    }    
}
