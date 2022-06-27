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
 *  CLASE:     VentanaUtilidad
 *  INTENCION: Ser la ventana que explica la utilidad de este juego (beneficios para la mente).
 *  RELACION:  NINGUNA 
 */

public class VentanaUtilidad extends JFrame{
    private JLabel exitLabel = new JLabel("");
        
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
        
        setContentPane(new Background());
        setLayout(null); // Descativamos la distribucion por defecto
        
        exitLabel.setBounds(720 - 75, 10, 50, 50);
        
        ImageIcon exitImageIcon = new ImageIcon("src/imagenes/exit.png");
        Icon exitIcon = new ImageIcon(exitImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        exitLabel.setIcon(exitIcon);
        
        add(exitLabel);
        
        exitLabel.addMouseListener(new MyMouseListener());
    }
    
    private class Background extends JPanel {
        private Image image;
        
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
        public void mouseClicked(MouseEvent e) { // Como el exitLabel es lo unico que esta escuchado,
            // no son necesarios condicionales
            VentanaInicio window = new VentanaInicio();
            dispose();            
        }
    }    
}
