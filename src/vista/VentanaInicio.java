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
 *  CLASE:     VentanaInicio     
 *  INTENCION: Ser la primera ventana que ve el jugador (un tipo de menu).
 *  RELACION:  NINGUNA 
 */


public class VentanaInicio extends JFrame { 
    private JLabel lblHowToPlay  = new JLabel();
    private JLabel lblPlay       = new JLabel();
    private JLabel lblWhatIsFor  = new JLabel();
        
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
        
        // Establecemos el fondo
        setContentPane(new Background());
        setLayout(null); // Descativamos la distribucion por defecto
        
        // Con un espacio entre botones de 67 pixeles:
        lblHowToPlay.setBounds(67, 240, 150, 100);
        lblPlay.setBounds(284, 240, 150, 100); // 284 = 67 + 150 + 67
        lblWhatIsFor.setBounds(501, 240, 150, 100); // 501 = 284 + 150 + 67
        
        myLibrary.addIcon(lblHowToPlay, "botones/botones inicio/como_jugar.png", 150, 100);
        
        myLibrary.addIcon(lblPlay, "botones/botones inicio/jugar.png", 150, 100);
                  
        myLibrary.addIcon(lblWhatIsFor, "botones/botones inicio/para_que_sirve.png", 150, 100);
       
        add(lblPlay);
        add(lblHowToPlay);
        add(lblWhatIsFor);
        
        lblPlay.addMouseListener(new MyMouseListener());
        lblHowToPlay.addMouseListener(new MyMouseListener());
        lblWhatIsFor.addMouseListener(new MyMouseListener());
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
            
            if(elemento == lblHowToPlay) {
                myLibrary.addIcon(lblHowToPlay, "botones/botones inicio/como_jugar_hover.png", 150, 100);                
            } else if (elemento == lblPlay) {                
                myLibrary.addIcon(lblPlay, "botones/botones inicio/jugar_hover.png", 150, 100);
            } else {
                myLibrary.addIcon(lblWhatIsFor, "botones/botones inicio/para_que_sirve_hover.png", 150, 100);
            }  
        }
        
        public void mouseExited(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblHowToPlay) {             
                myLibrary.addIcon(lblHowToPlay, "botones/botones inicio/como_jugar.png", 150, 100);
            } else if (elemento == lblPlay) {
                myLibrary.addIcon(lblPlay, "botones/botones inicio/jugar.png", 150, 100);
            } else { // Caso para el howToPlayButton
                myLibrary.addIcon(lblWhatIsFor, "botones/botones inicio/para_que_sirve.png", 150, 100);
            }            
        }
        
        public void mouseClicked(MouseEvent e) { // Ya que usamos el listener del mouse, sabremos que
            // presiono el boton cuando haga click en el
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblPlay) {
                VentanaJuego window = new VentanaJuego();
                dispose();
            } else if (elemento == lblWhatIsFor) {
                VentanaUtilidad window = new VentanaUtilidad();
                dispose();
            } else { // Caso de howToPlay
               VentanaInstrucciones window = new VentanaInstrucciones();
               dispose();
            }             
        }
    }
}    
