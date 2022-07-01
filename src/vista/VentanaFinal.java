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
 *  CLASE:     VentanaFinal
 *  INTENCION: Ser la ventana donde se presentan las estadística del jugador en una "partida".
 *  RELACION:  NINGUNA 
 */


public class VentanaFinal extends JFrame {
    private JLabel lblAciertos;
    private JLabel lblScore;
    private JLabel lblFailures  = new JLabel("3"); // Si la ventana se muestra es porque perdió las tres vidas
    private JLabel lblPlayAgain = new JLabel("");
    private JLabel lblClose     = new JLabel("");
    
    public VentanaFinal(int aciertos, int score) {
        lblAciertos = new JLabel(aciertos + ""); // Esta es una forma de convertir un entero a String
        lblScore = new JLabel(score + "");
        
        initializeComponents();        
        setSize(720, 480);
        setTitle("Ados2a - Final");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Posición de la ventana en el centro
        setResizable(false);
        
        lblAciertos.setBounds(325, 110, 150, 50);
        lblAciertos.setFont(new Font("Century Gothic", Font.BOLD, 42));
        lblAciertos.setForeground(Color.WHITE);
        
        lblScore.setBounds(410, 290, 150, 50);
        lblScore.setFont(new Font("Century Gothic", Font.BOLD, 42));
        lblScore.setForeground(Color.WHITE);
        
        lblFailures.setBounds(300, 200, 150, 50);
        lblFailures.setFont(new Font("Century Gothic", Font.BOLD, 42));
        lblFailures.setForeground(Color.WHITE);
        
        lblPlayAgain.setBounds( 720 / 2 / 2 - 75, 518 - 175, 150, 100);
        
        lblClose.setBounds(540 - 75, 518 - 175, 150, 100);
        
        myLibrary.addIcon(lblPlayAgain, "botones/botones final/volver_a_jugar.png", 150, 100);
        
        myLibrary.addIcon(lblClose, "botones/botones final/salir.png", 150, 100);
        
        add(lblAciertos);
        add(lblScore);
        add(lblFailures);
        add(lblPlayAgain);
        add(lblClose);
        
        lblPlayAgain.addMouseListener(new MyMouseListener());
        lblClose.addMouseListener(new MyMouseListener());
    }
    
    private void initializeComponents() {
        Toolkit myScreen = Toolkit.getDefaultToolkit(); 
        // Para establecer el icono en la aplicación
        Image icon = myScreen.getImage("src/imagenes/icon.png");
        setIconImage(icon);
        
        // Establecemos el fondo
        setContentPane(new Background());
        setLayout(null); // Descativamos la distribucion por defecto         
    }
    
    private class Background extends JPanel {
        private Image image;
        
        public Background() {
            // Aprovechando que declaramos la imagen como atributo podemos hacer esto
            File myImage = new File("src/imagenes/final_background.jpg");
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
        public void mouseEntered(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblPlayAgain) {
                myLibrary.addIcon(lblPlayAgain, "botones/botones final/volver_a_jugar_hover.png", 150, 100);                
            } else {
                myLibrary.addIcon(lblClose, "botones/botones final/salir_hover.png", 150, 100);
            }  
        }
        
        public void mouseExited(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblPlayAgain) {             
                myLibrary.addIcon(lblPlayAgain, "botones/botones final/volver_a_jugar.png", 150, 100);
            } else { // Caso para el lblClose
                myLibrary.addIcon(lblClose, "botones/botones final/salir.png", 150, 100);
            }            
        }
        
        public void mouseClicked(MouseEvent e) { // Ya que usamos el listener del mouse, sabremos que
            // presiono el botón cuando haga click en el
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblPlayAgain) {
                VentanaJuego window = new VentanaJuego();
                dispose();
            } else { // Caso de lblClose
                dispose();
            }             
        }
    }    
}