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
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import logica.*;
        
/**
 *  CLASE:     VentanaJuego
 *  INTENCION: Ser la ventana donde trascurre el juego.
 *  RELACION:  NINGUNA 
 */


public class VentanaJuego extends JFrame {
    // Declaramos ronda 
    Ronda ronda = new Ronda();    
    // Vamos a usar labels para poner las imagenes de las baldosas y para el texto
    private JLabel[][] lblBaldosas; // Vamos usar una matriz para gestionarlo mejor
    // Declaramos baldosas
    private boolean pressedButton = false;
    private JLabel  lbl00         = new JLabel();
    private JLabel  lbl01         = new JLabel();
    private JLabel  lbl10         = new JLabel();
    private JLabel  lbl11         = new JLabel();
    private JLabel  lbl20         = new JLabel();
    private JLabel  lbl21         = new JLabel();
    private JLabel  lbl30         = new JLabel();
    private JLabel  lbl31         = new JLabel();
    private JLabel  lblScoreTxt   = new JLabel("Puntación: 00000");
    private JLabel  lblVida1;
    private JLabel  lblVida2;
    private JLabel  lblVida3;
    private JLabel  lblBoton      = new JLabel();    
    private JLabel  lblVolumen    = new JLabel();
    private boolean volumen       = true; // Este atributo es para llevar el control del volumen
    private JLabel  lblExit       = new JLabel();
    
   
    private Timer timer;
    int count = 0;
    int delay = 1000;
    
      
    public VentanaJuego() {
        startTimer(1);
        initializeComponents();        
        setSize(720, 515); // Aqui es diferente el alto porque el la ventan empiza en los bordes, no en la imagen
        setTitle("Ados2a - Juego");
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
        
                  
        // Añadimos labels de baldosas a la respectiva matriz
        // 0 = alejada del centro (exterior) y 1 = cercana del centro (interior)
        lblBaldosas = new JLabel [4][2];
        lblBaldosas[0][0] = lbl00;
        lblBaldosas[0][1] = lbl01;
        lblBaldosas[1][0] = lbl10;
        lblBaldosas[1][1] = lbl11;
        lblBaldosas[2][0] = lbl20;
        lblBaldosas[2][1] = lbl21;
        lblBaldosas[3][0] = lbl30;
        lblBaldosas[3][1] = lbl31;
        
        lblBoton.setBounds(720 - 200, 510 - 150, 100, 100);
        lblVolumen.setBounds(10, 510 - 100, 50, 50);
        lblExit.setBounds(720 - 75, 10, 50, 50);
      
        ImageIcon botonImageIcon = new ImageIcon("src/imagenes/botones/botones juego/normal.png");
        Icon botonIcon = new ImageIcon(botonImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        lblBoton.setIcon(botonIcon);
        
        ImageIcon volumenImageIcon = new ImageIcon("src/imagenes/botones/botones juego/sound_on.png");
        Icon volumenIcon = new ImageIcon(volumenImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lblVolumen.setIcon(volumenIcon);        
        
        ImageIcon exitImageIcon = new ImageIcon("src/imagenes/exit.png");
        Icon exitIcon = new ImageIcon(exitImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        lblExit.setIcon(exitIcon);
        

        
        // Por primera vez le ponemos imágenes a las baldosas
        changeImages();
        
        lblScoreTxt.setFont(new Font("Century Gothic", Font.BOLD, 32));
        lblScoreTxt.setForeground(Color.GRAY);
                
        add(lblScoreTxt);
        add(lblBoton);
        
        // Le asignamos las posiciones a las baldosas
        // Arriba:
        lblBaldosas[0][0].setBounds(720 / 2 - 45, 15, 90, 90);
        lblBaldosas[0][1].setBounds(720 / 2 - 45, 105, 90, 90); // 105 = 15 + 90
        // Derecha:
        lblBaldosas[1][0].setBounds(720 / 2 + 135, 480 / 2 - 45, 90, 90);
        lblBaldosas[1][1].setBounds(720 / 2 + 45, 480 / 2 - 45, 90, 90); // 135 = 45 + 90
        // Abajo:
        lblBaldosas[2][0].setBounds(720 / 2 - 45, 480 - 105, 90, 90); // 105 = 15 + 90
        lblBaldosas[2][1].setBounds(720 / 2 - 45, 480 - 195, 90, 90); // 195 = 105 + 90
        // Izquierda:
        lblBaldosas[3][0].setBounds(720 / 2 - 135, 480 / 2 - 45, 90, 90); // 135 = 45 + 90
        lblBaldosas[3][1].setBounds(720 / 2 - 225, 480 / 2 - 45, 90, 90); // 135 = 45 + 90        
        
        // Añadimos las baldosas al frame
        for(JLabel[] baldosas : lblBaldosas) {
            for(JLabel baldosa: baldosas) {
                add(baldosa);
            }
        }
    
        add(lblBoton);
        add(lblVolumen);
        add(lblExit);
        
        lblBoton.addMouseListener(new EventsManager());
        addKeyListener(new EventsManager()); // Tambien podemos presionar el boton con la barra espaciadora
        lblVolumen.addMouseListener(new EventsManager());
        lblExit.addMouseListener(new EventsManager());
    }
    
    private void makeAChange() {
        ronda.changeBaldosa();
    }
    
    // Método encargado de accionar el contador (timer)
    private void startTimer(int countPassed) {
        ActionListener action = (ActionEvent e) -> {
            count++;
            // Condicional encargado de cambiar las baldosas
            if (count % 1 == 0) {
                makeAChange();       
                // Cambiamos las respectivas imágenes de baldosas
                changeImages();
            }
        };
        // Establecemos el timer
        timer = new Timer(delay, action);
        timer.setInitialDelay(0);
        timer.start();
        count = countPassed;
    }
    
    public void changeImages(){
        int[][] tablero = ronda.getTablero();
        String path = "src/imagenes/baldosas/";

        for (int row = 0; row < 4; row++){
            for (int column = 0; column < 2; column++){
                if (true) { //tablero[row][column] != 0
                    // Se establece la ruta a la imágen
                    String rutaImagenBaldosa = path + "1.png";//+ tablero[row][column] + ".png"; // Accedemos al numero de la baldosa

                    ImageIcon baldosaImageIcon = new ImageIcon(rutaImagenBaldosa);
                    Icon BaldosaIcon = new ImageIcon(baldosaImageIcon.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
                    lblBaldosas[row][column].setIcon(BaldosaIcon);
                }
            }
        }
    }
       
    private class Background extends JPanel {
        private Image image;
        
        public Background() {
            // Aprovechando que declaramos la imagen como atributo podemos hacer esto
            File myImage = new File("src/Imagenes/game_background.jpg");
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
    
 
    private class EventsManager extends MouseAdapter implements KeyListener {

        public void mouseEntered(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton) {
                ImageIcon botonImageIcon = new ImageIcon("src/imagenes/botones/botones juego/hover.png");
                Icon botonIcon = new ImageIcon(botonImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                lblBoton.setIcon(botonIcon); 
            }
        }
        
        public void mouseExited(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton) {
                ImageIcon botonImageIcon = new ImageIcon("src/imagenes/botones/botones juego/normal.png");
                Icon botonIcon = new ImageIcon(botonImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                lblBoton.setIcon(botonIcon); 
            }
        }
        
        public void mousePressed(MouseEvent e) { // Cuando se oprime el clic del mouse
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton) {
                ImageIcon botonImageIcon = new ImageIcon("src/imagenes/botones/botones juego/pressed.png");
                Icon botonIcon = new ImageIcon(botonImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                lblBoton.setIcon(botonIcon); 
            }            
        }     
        
        public void mouseReleased(MouseEvent e) { // Cuando se suelta el clic del mouse
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton) {
                ImageIcon botonImageIcon = new ImageIcon("src/imagenes/botones/botones juego/normal.png");
                Icon botonIcon = new ImageIcon(botonImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                lblBoton.setIcon(botonIcon); 
            }              
        }    
        
        public void mouseClicked(MouseEvent e) { // Cuando se presiona y suelta el clic del mouse
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton) {
                
            } else if(elemento == lblExit) {
                VentanaInicio window = new VentanaInicio();
                dispose();
            } else if(elemento == lblVolumen) {
                String path = "src/imagenes/botones/botones juego/sound_";
                String mode;
                if(volumen) {
                    volumen = false;
                    mode = "off.png";
                } else {
                    volumen = true;
                    mode = "on.png";
                }               
                ImageIcon volumenImageIcon = new ImageIcon(path + mode);
                Icon volumenIcon = new ImageIcon(volumenImageIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
                lblVolumen.setIcon(volumenIcon);                
            }           
        }
        
        @Override
        public void keyPressed(KeyEvent e) { // Cuando se presiona la tecla 
            int keyCode = e.getKeyCode();
            
            if(keyCode == 32) {
                ImageIcon botonImageIcon = new ImageIcon("src/imagenes/botones/botones juego/pressed.png");
                Icon botonIcon = new ImageIcon(botonImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                lblBoton.setIcon(botonIcon);                 
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) { // Cuando se suelta la tecla
            int keyCode = e.getKeyCode();

            if(keyCode == 32) {
                ImageIcon botonImageIcon = new ImageIcon("src/imagenes/botones/botones juego/normal.png");
                Icon botonIcon = new ImageIcon(botonImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
                lblBoton.setIcon(botonIcon);                 
            }            
        }   
        
        @Override
        public void keyTyped(KeyEvent e) { // Cuando se unde y se suelta la tecla
            
        }                  
    }   
}

