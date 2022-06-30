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
        setSize(720, 515); // Aqui es diferente el alto porque el la ventan empiza en los bordes, no en la imagen
        setTitle("Ados2a - Instrucciones");
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
        
        // A 200 pixeles de borde superior y a 30 pixeles del borde izquierdo
        nextLabel.setBounds(720 - 125, 200 - 50, 100, 100); // La imagen ya cuenta con pixeles "vacios"
        backLabel.setBounds(0, 200 - 50, 100, 100);
        exitLabel.setBounds(720 - 75, 10, 50, 50);
        
        myLibrary.addIcon(nextLabel, "arrow_right.png", 100, 100);
        
        myLibrary.addIcon(backLabel, "arrow_left.png", 100, 100);
        
        myLibrary.addIcon(exitLabel, "exit.png", 50, 50);
        
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
            File myImage = new File("src/imagenes/instrucciones/1.jpg");
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
            File myImage = new File("src/imagenes/instrucciones/1.jpg"); // Tenemos que inicializarla
            
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
                myImage = new File("src/imagenes/instrucciones/" + (imageNumber + 1) + ".jpg");
                imageNumber++; // Avanzamos una "diapositiva"
            } else if(elemento == backLabel) { // Corresponde al caso del backLabel
                if(imageNumber == 2) {
                    backLabel.setEnabled(false);
                    backLabel.setVisible(false);                    
                } else if (imageNumber == 4) {
                    nextLabel.setEnabled(true);
                    nextLabel.setVisible(true); 
                }
                myImage = new File("src/imagenes/instrucciones/" + (imageNumber - 1) + ".jpg");
                imageNumber--; // Retrocedemos una "diapositiva"
            } else { // Caso del exit
                VentanaInicio window = new VentanaInicio();
                dispose();
            }
            try {
                image = ImageIO.read(myImage); // Este método arroja una excepción, entonces tenemos que atraparla
            } catch(IOException exception) {
                System.out.println("Ha ocurrido un error !");
            }
            repaint(); // Volvemos a "pintar" la imagen         
        }
    }    
}
