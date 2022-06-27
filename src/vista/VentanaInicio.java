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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *  CLASE:     VentanaInicio     
 *  INTENCION: Ser la primera ventana que ve el jugador (un tipo de menu).
 *  RELACION:  NINGUNA 
 */

public class VentanaInicio extends JFrame { 
    private JLabel howToPlayLabel  = new JLabel("");
    private JLabel playLabel       = new JLabel("");
    private JLabel whatIsForLabel  = new JLabel("");
        
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
        Image icon = myScreen.getImage("src/imagenes/icon.png");        
	setIconImage(icon);
        
        setContentPane(new Background());
        setLayout(null); // Descativamos la distribucion por defecto
        
        // Con un espacio entre botones de 67 pixeles:
        howToPlayLabel.setBounds(67, 240, 150, 100);
        playLabel.setBounds(284, 240, 150, 100); // 284 = 67 + 150 + 67
        whatIsForLabel.setBounds(501, 240, 150, 100); // 501 = 284 + 150 + 67
        
        ImageIcon playImageIcon = new ImageIcon("src/imagenes/botones/jugar.png");
        Icon playIcon = new ImageIcon(playImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
        playLabel.setIcon(playIcon);
      
        ImageIcon howToPlayImageIcon = new ImageIcon("src/imagenes/botones/como_jugar.png");
        Icon howToPlayIcon = new ImageIcon(howToPlayImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
        howToPlayLabel.setIcon(howToPlayIcon);
        
        ImageIcon whatIsForImageIcon = new ImageIcon("src/imagenes/botones/para_que_sirve.png");        
        Icon whatIsForIcon = new ImageIcon(whatIsForImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
        whatIsForLabel.setIcon(whatIsForIcon);
       
        add(playLabel);
        add(howToPlayLabel);
        add(whatIsForLabel);
        
        playLabel.addMouseListener(new MyMouseListener());
        howToPlayLabel.addMouseListener(new MyMouseListener());
        whatIsForLabel.addMouseListener(new MyMouseListener());
    }
    
    private class Background extends JPanel {
        private Image image;
        private Image icon;
        
        public Background() {
            // Aprovechando que declaramos la imagen como atributo podemos hacer esto
            File myImage = new File("src/imagenes/background.jpg");
            File myIcon = new File("src/imagenes/icon.png");
            try {
                image = ImageIO.read(myImage); // Este método arroja una excepción, entonces tenemos que atraparla
                icon = ImageIO.read(myIcon);
            } catch(IOException e) {
                System.out.println("Ha ocurrido un error !");
            }
        }
    
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);        
        
            g.drawImage(image, 0, 0, null);
            g.drawImage(icon, 720 / 2 - 125, 0, null); 
        }
    }
    
    private class MyMouseListener extends MouseAdapter {
        public void mouseEntered(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == playLabel) {
                ImageIcon playImageIcon = new ImageIcon("src/imagenes/botones/jugar_rollover.png");
                Icon playIcon = new ImageIcon(playImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
                playLabel.setIcon(playIcon);   
            } else if (elemento == whatIsForLabel) {
                ImageIcon whatIsForImageIcon = new ImageIcon("src/imagenes/botones/para_que_sirve_rollover.png");        
                Icon whatIsForIcon = new ImageIcon(whatIsForImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
                whatIsForLabel.setIcon(whatIsForIcon);
            } else {
                ImageIcon howToPlayImageIcon = new ImageIcon("src/imagenes/botones/como_jugar_rollover.png");
                Icon howToPlayIcon = new ImageIcon(howToPlayImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
                howToPlayLabel.setIcon(howToPlayIcon); 
            }  
        }
        
        public void mouseExited(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == playLabel) {
                ImageIcon playImageIcon = new ImageIcon("src/imagenes/botones/jugar.png");
                Icon playIcon = new ImageIcon(playImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
                playLabel.setIcon(playIcon); 
            } else if (elemento == whatIsForLabel) {
                ImageIcon whatIsForImageIcon = new ImageIcon("src/imagenes/botones/para_que_sirve.png");        
                Icon whatIsForIcon = new ImageIcon(whatIsForImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
                whatIsForLabel.setIcon(whatIsForIcon);
            } else { // Caso para el howToPlayButton
                ImageIcon howToPlayImageIcon = new ImageIcon("src/imagenes/botones/como_jugar.png");
                Icon howToPlayIcon = new ImageIcon(howToPlayImageIcon.getImage().getScaledInstance(150, 100, Image.SCALE_DEFAULT));
                howToPlayLabel.setIcon(howToPlayIcon);                 
            }            
        }
        
        public void mouseClicked(MouseEvent e) { // Ya que usamos el listener del mouse, sabremos que
            // presiono el boton cuando haga click en el
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == playLabel) {
                try {
                    VentanaJuego window = new VentanaJuego();
                } catch (IOException ex) {
                    Logger.getLogger(VentanaInicio.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            } else if (elemento == whatIsForLabel) {
                VentanaUtilidad window = new VentanaUtilidad();
                dispose();
            } else {
               VentanaInstrucciones window = new VentanaInstrucciones();
               dispose();
            }             
        }
    }
}    
