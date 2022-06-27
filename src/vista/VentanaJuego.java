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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import logica.*;
        
/**
 *  CLASE:     VentanaJuego
 *  INTENCION: Ser la ventana donde trascurre el juego.
 *  RELACION:  NINGUNA 
 */

public class VentanaJuego extends JFrame {
    //Ronda
    Ronda ronda;
    
    //JLabel
    private JLabel lblpuntuación;
    private JLabel[][] lblBaldosas;
    private JLabel lbl00;
    private JLabel lbl01;
    private JLabel lbl10;
    private JLabel lbl11;
    private JLabel lbl20;
    private JLabel lbl21;
    private JLabel lbl30;
    private JLabel lbl31;
    private JLabel lblPuntuacion;
    private JLabel lblVida1;
    private JLabel lblVida2;
    private JLabel lblVida3;
    private JLabel lblVolumen;
    
    //JButton
    private JButton btnBoton;

    //Timer
    private Timer timerTiempo;
    int count = 0;
    int delay = 1000;
    
    //JPanel --> ESTO ES DE PRUEBA
    private JPanel panel;
    
    //Files
    private File imagenBaldosa;
    
    
    public VentanaJuego() throws IOException {
        initializeComponents();        
        setSize(720, 480);
        setTitle("Ados2a - Juego");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null); // Posición de la ventana en el centro
        setResizable(false);
    }
    
    private void initializeComponents() throws IOException {
        //Establecemos el fondo
        Toolkit myScreen = Toolkit.getDefaultToolkit(); 
        // Para establecer el icono en la aplicación
        Image icon = myScreen.getImage("src/Imagenes/icon.png");
	setIconImage(icon);
        add(new background());
        
        
        //Obtenemos ruta absoluta para añadir baldosas
        String rutaArchivo = new File("").getAbsolutePath();
        
        //Concatemos la ruta absoluta de "Miniproyecto - 2" con la ruta de todos los .png a utilizar
        String baldosaRuta = rutaArchivo.concat("\\src\\imagenes\\baldosas\\");
        
        //Declaramos ronda
        ronda = new Ronda();
        
        //Declaramos Timer
        
        //Declaramos baldosas
        lbl00 = new JLabel();
        lbl01 = new JLabel();
        lbl10 = new JLabel();
        lbl11 = new JLabel();
        lbl20 = new JLabel();
        lbl21 = new JLabel();
        lbl30 = new JLabel();
        lbl31 = new JLabel();
        //Añadimos labels de baldosas a la respectiva matriz
        //0 = alejada del centro, 1 = cercana del centro
        lblBaldosas = new JLabel [4][2];
        lblBaldosas[0][0] = lbl00;
        lblBaldosas[0][1] = lbl01;
        lblBaldosas[1][0] = lbl10;
        lblBaldosas[1][1] = lbl11;
        lblBaldosas[2][0] = lbl20;
        lblBaldosas[2][1] = lbl21;
        lblBaldosas[3][0] = lbl30;
        lblBaldosas[3][1] = lbl31;
        //Obtenemos el tablero
        int[][] tablero = ronda.getTablero();
        //Este for se encarga de establecer la respectiva imágen de la baldosa
        //al respectivo label
        for (int row = 0; row < 4; row++){
            for (int column = 0; column < 2; column++){
                System.out.println("Aqui tienes: " + tablero[row][column]);
                if (tablero[row][column] != 0){
                    //Se establece la ruta a la imágen
                    String rutaImagenBaldosa = baldosaRuta + tablero[row][column] + ".png";
                    System.out.println(rutaImagenBaldosa);
                    //Se carga la imágen y se añade al respectivo label
                    imagenBaldosa = new File(rutaImagenBaldosa);
                    BufferedImage bufferedImagenBaldosa = ImageIO.read(imagenBaldosa);
                    ImageIcon iconBaldosa = new ImageIcon(bufferedImagenBaldosa);
                    lblBaldosas[row][column].setIcon(iconBaldosa);
                }
            }
        }
        
        //Declaramos puntuación
        lblPuntuacion = new JLabel("Puntación: 00000");
        lblPuntuacion.setFont(new Font("Century Gothic", Font.BOLD, 32));
        lblPuntuacion.setForeground(Color.GRAY);
        
        //Declaramos botón
        btnBoton = new JButton();
        
        panel = new JPanel();
        panel.setLayout(new GridLayout(4,4));
        panel.add(lblPuntuacion);
        panel.add(btnBoton);
        for (int row = 0; row < 4; row++){
            for (int column = 0; column < 2; column++){
                panel.add(lblBaldosas[row][column]);
            }
        }
        
        add(panel);
        
        lblPuntuacion.addMouseListener(new ManejadorDeEventos());
        

    }
    
    public void startTimer(int countPassed){
        ActionListener action = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                count++;
                System.out.println("Tiempo: " + count);
                if (count % 2 == 0){
                    ronda.changeBaldosa();
                    int[][] tablero = ronda.getTablero();
                    //Obtenemos ruta absoluta para añadir baldosas
        String rutaArchivo = new File("").getAbsolutePath();
                    //Concatemos la ruta absoluta de "Miniproyecto - 2" con la ruta de todos los .png a utilizar
        String baldosaRuta = rutaArchivo.concat("\\src\\imagenes\\baldosas\\");
        //Este for se encarga de establecer la respectiva imágen de la baldosa
        //al respectivo label
        for (int row = 0; row < 4; row++){
            for (int column = 0; column < 2; column++){
                System.out.println("Aqui tienes: " + tablero[row][column]);
                if (tablero[row][column] != 0){
                    //Se establece la ruta a la imágen
                    String rutaImagenBaldosa = baldosaRuta + tablero[row][column] + ".png";
                    System.out.println(rutaImagenBaldosa);
                    //Se carga la imágen y se añade al respectivo label
                    imagenBaldosa = new File(rutaImagenBaldosa);
                    BufferedImage bufferedImagenBaldosa = null;
                    try {
                        bufferedImagenBaldosa = ImageIO.read(imagenBaldosa);
                    } catch (IOException ex) {
                        Logger.getLogger(VentanaJuego.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ImageIcon iconBaldosa = new ImageIcon(bufferedImagenBaldosa);
                    lblBaldosas[row][column].setIcon(iconBaldosa);
                }
            }
        }
                    
                }
            }
        };
        timerTiempo = new Timer(delay, action);
        timerTiempo.setInitialDelay(0);
        timerTiempo.start();
        count = countPassed;
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
    
 
    private class ManejadorDeEventos implements ActionListener, MouseListener, KeyListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            JLabel elemento = (JLabel) e.getSource();
            
            if (elemento == lblPuntuacion){
                System.out.println("Hola");
                startTimer(0);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void keyTyped(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void keyPressed(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void keyReleased(KeyEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
        
        
        
        
    }
    
}

