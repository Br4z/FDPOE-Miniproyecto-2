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
import javax.swing.border.Border;

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
    private JLabel  lbl00         = new JLabel();
    private JLabel  lbl01         = new JLabel();
    private JLabel  lbl10         = new JLabel();
    private JLabel  lbl11         = new JLabel();
    private JLabel  lbl20         = new JLabel();
    private JLabel  lbl21         = new JLabel();
    private JLabel  lbl30         = new JLabel();
    private JLabel  lbl31         = new JLabel();
    private JLabel  lblScoreTxt   = new JLabel("Puntación: 00000");
    private JLabel  lblVida1      = new JLabel();
    private JLabel  lblVida2      = new JLabel();
    private JLabel  lblVida3      = new JLabel();
    private JLabel  lblBoton      = new JLabel();    
    private JLabel  lblVolumen    = new JLabel();
    private boolean volumen       = true; // Este atributo es para llevar el control del volumen
    private JLabel  lblExit       = new JLabel();
    // Declaramos los timers
    private Timer timerBaldosas;
    private int delay = 1000;
    private Timer timerEspera;
    private int countEspera = 0;
    
      
    public VentanaJuego() {
        initializeComponents();        
        startTimerBaldosas();
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
        
        lblScoreTxt.setBounds(0, -30, 300, 100);
        lblBoton.setBounds(720 - 200, 510 - 150, 100, 100);
        lblVolumen.setBounds(10, 510 - 100, 50, 50);
        lblExit.setBounds(720 - 75, 10, 50, 50);
        
        lblVida1.setBounds(0, 100, 100, 100);
        lblVida2.setBounds(0, 200, 100, 100);
        lblVida3.setBounds(0, 300, 100, 100);
        setVidas();
        
        myLibrary.addIcon(lblBoton, "botones/botones juego/normal.png", 100, 100);
        
        myLibrary.addIcon(lblVolumen, "botones/botones juego/sound_on.png", 50, 50);
        
        myLibrary.addIcon(lblExit, "exit.png", 50, 50);
                 
        // Por primera vez le ponemos imágenes a las baldosas
        putImages();
        
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
        
        add(lblVida1);
        add(lblVida2);
        add(lblVida3);
        add(lblBoton);
        add(lblVolumen);
        add(lblExit);
        
        lblBoton.addMouseListener(new EventsManager());
        addKeyListener(new EventsManager()); // Tambien podemos presionar el boton con la barra espaciadora
        lblVolumen.addMouseListener(new EventsManager());
        lblExit.addMouseListener(new EventsManager());
    }
       
    // Método encargado de accionar el contador (timerBaldosas)
    private void startTimerBaldosas() {
        ActionListener action = (ActionEvent e) -> {
            boolean missedOportunity = ronda.checkBaldosas(false);
            setBordersNull(); // Para quitar los border azules
            if (missedOportunity) {
                // Se establecen los bordes de rojo
                setBordersRed();
                timerBaldosas.stop();
                startTimerEspera(0);
            } else {
                ronda.changeABaldosa();
                setBordersBlue(ronda.getChangedBaldosa());
                putImages();              
            }
        };
        // Establecemos el timer
        timerBaldosas = new Timer(delay, action); // Habiamos establecido el delay en 1 segundo
        timerBaldosas.setInitialDelay(0);
        timerBaldosas.start();
    }
    
    
    private void startTimerEspera(int countPassed) {
        ActionListener action = (ActionEvent e) -> {
            countEspera++;
            
            if (countEspera == 3) {
                setBordersNull();
                //ronda.removeABaldosa();
                // Reiniciamos el tablero
                ronda.changeAllBaldosas();
                putImages(); 
                setVidas();
            } else if (countEspera == 4) {
                ((Timer) e.getSource()).stop();
                timerBaldosas.start();
            }
        };
        timerEspera = new Timer(delay, action); // Aqui al accion tambien se ejecuta cada segundo
        timerEspera.setInitialDelay(0);
        timerEspera.start();
        countEspera = countPassed;
    }
    
    // Método encargado de cambiar las imágenes
    private void putImages(){
        int[][] tablero = ronda.getTablero();

        for (int i = 0; i < 4; i++){ // Filas
            for (int j = 0; j < 2; j++) { // Columnas
                if (tablero[i][j] != 0) {
                    // Se establece la ruta a la imágen
                    String pathBaldosaImage = "baldosas/"+ tablero[i][j] + ".png";

                    myLibrary.addIcon(lblBaldosas[i][j], pathBaldosaImage, 90, 90);
                    
                } else if (tablero[i][j] == 0){ // Si no hay baldosa
                    lblBaldosas[i][j].setIcon(null);
                }
            }
        }
    }
    
    // Método encargado de establecer/actualizar vidas
    private void setVidas() {
        // Obtenemos las vidas que tiene el jugador
        int vidasTotal = ronda.getVidas();
        
        // Declaramos los iconos de los estados de las vidas
        // fullVida
        ImageIcon fullVidaImageIcon = new ImageIcon("src/imagenes/vidas/con_vida.png");
        Icon fullVidaIcon = new ImageIcon(fullVidaImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        
        // emptyVida
        ImageIcon emptyVidaImageIcon = new ImageIcon("src/imagenes/vidas/sin_vida.png");
        Icon emptyVidaIcon = new ImageIcon(emptyVidaImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        
        switch (vidasTotal) {
            case 3 -> {
                lblVida1.setIcon(fullVidaIcon);
                lblVida2.setIcon(fullVidaIcon);
                lblVida3.setIcon(fullVidaIcon);
            }
            case 2 -> {
                lblVida1.setIcon(emptyVidaIcon);
                lblVida2.setIcon(fullVidaIcon);
                lblVida3.setIcon(fullVidaIcon);
            }
            case 1 -> {
                lblVida1.setIcon(emptyVidaIcon);
                lblVida2.setIcon(emptyVidaIcon);
                lblVida3.setIcon(fullVidaIcon);
            }
            case 0 -> {
                lblVida1.setIcon(emptyVidaIcon);
                lblVida2.setIcon(emptyVidaIcon);
                lblVida3.setIcon(emptyVidaIcon);
            VentanaFinal ventanaFinal = new VentanaFinal(ronda.getAciertos(), ronda.getScore());
                timerBaldosas.stop();
                timerEspera.stop();
                dispose();
            }
            default -> {
            }
        }
    }
    
    // Método encargado de establecer el puntaje
    private void setScore() {
        String score = String.valueOf(ronda.getScore());
        int length = score.length();
        
        // Switch encargado de añadirle los "ceros" restantes al puntaje y así ponerlos en
        // el lblScoreText
        switch (length) {
            case 1 ->                 {
                    String zeros = "000";
                    score = zeros + score;
                    lblScoreTxt.setText("Puntuación: " + score);
                }
            case 2 ->                 {
                    String zeros = "00";
                    score = zeros + score;
                    lblScoreTxt.setText("Puntuación: " + score);
                }
            case 3 ->                 {
                    String zeros = "0";
                    score = zeros + score;
                    lblScoreTxt.setText("Puntuación: " + score);
                }
            case 4 -> lblScoreTxt.setText("Puntuación: " + score);
            default -> {
                System.out.println("El puntaje ha superado las 4 cifras 0_0");
            }
        }
        
    }
    
    private void setBordersBlue(int[] changedBaldosa) { // Metodo para resaltar que baldosa esta cambiando
        Border border = BorderFactory.createLineBorder(Color.BLUE, 5);       
        int row = changedBaldosa[0];
        int column = changedBaldosa[1];
        
        lblBaldosas[row][column].setBorder(border);       
    }
    
    // Método encargado de establecer los bordes en rojo
    private void setBordersRed(){ 
        Border border = BorderFactory.createLineBorder(Color.RED, 5);
        
        for (int i = 0; i< 4; i++) { // Filas
            for (int j = 0; j < 2; j++) { // Columnas
                if (ronda.getTablero()[i][j] != 0) {
                    lblBaldosas[i][j].setBorder(border);   
                }
            }
        }
    }
    
    // Método encargado de establecer los bordes sin ningún color
    private void setBordersNull(){
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 2; column++) {
                if (ronda.getTablero()[row][column] != 0) {
                    lblBaldosas[row][column].setBorder(null);   
                }
            }
        }
    }
    
    private void setBordersGreen() { // Este metodo solo lo invocamos si estamos seguros de que hay dos
        // baldosas repetidas
        int counter = 0;
        int[][] tablero = ronda.getTablero();
        int row1 = 0;
        int row2 = 0;
        int column1 = 0;
        int column2 = 0;
        
        for (int[] baldosas : tablero){
            for (int baldosa : baldosas){
                if (counter != 2){
                    row1 = 0;
                    row2 = 0;
                    counter = 0;
                }
                    for (int row = 0; row < 4; row++){
                        for (int column = 0; column < 2; column++){
                            if ((tablero[row][column] == baldosa) && (baldosa!= 0) && (counter != 2)){
                                counter++;
                                if (counter == 1){
                                    row1 = row;
                                    column1 = column;
                                } else if (counter == 2){
                                    row2 = row;
                                    column2 = column;
                            }
                        }
                    }
                }
            }
        }
        
        
        System.out.println("1");
        System.out.println("row: " + row1 + "- column: " + column1);
        System.out.println("2");
        System.out.println("row: " + row2 + "- column: " + column2);
        
        Border border = BorderFactory.createLineBorder(Color.GREEN, 5);
        lblBaldosas[row1][column1].setBorder(border);
        lblBaldosas[row2][column2].setBorder(border);
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
            
            if(elemento == lblBoton && timerBaldosas.isRunning()) {
                myLibrary.addIcon(lblBoton, "botones/botones juego/hover.png", 100, 100);
            }
        }
        
        public void mouseExited(MouseEvent e) {
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton && timerBaldosas.isRunning()) {
                myLibrary.addIcon(lblBoton, "botones/botones juego/normal.png", 100, 100);
            }
        }
        
        public void mousePressed(MouseEvent e) { // Cuando se oprime el clic del mouse
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton && timerBaldosas.isRunning()) {
                myLibrary.addIcon(lblBoton, "botones/botones juego/pressed.png", 100, 100);
            }            
        }     
        
        public void mouseReleased(MouseEvent e) { // Cuando se suelta el clic del mouse
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton && timerBaldosas.isRunning()) {
                myLibrary.addIcon(lblBoton, "botones/botones juego/normal.png", 100, 100);
            }              
        }    
        
        public void mouseClicked(MouseEvent e) { // Cuando se presiona y suelta el clic del mouse
            JLabel elemento = (JLabel) e.getSource(); // Solo estamos escuchando a labels
            
            if(elemento == lblBoton && timerBaldosas.isRunning()) {
                // Detiene el tiempo por un momento
                timerBaldosas.stop();
                startTimerEspera(0);
                
                // Verifica en que situacion se presiono el boton
                boolean correctOportunity = ronda.checkBaldosas(true);
                if (correctOportunity){
                    ronda.increaseScore();
                    setScore();
                    if(ronda.getCantidadBaldosas() <= 7){
                        ronda.increaseBaldosas();
                    }
                    putImages();
                    setVidas();
                    setBordersGreen();
                    
                } else {
                    setBordersRed();
                    putImages();
                }
            } else if(elemento == lblExit) {
                VentanaInicio window = new VentanaInicio();
                dispose();
            } else if(elemento == lblVolumen) {
                String mode;
                if(volumen) {
                    volumen = false;
                    mode = "off.png";
                } else {
                    volumen = true;
                    mode = "on.png";
                }               
                myLibrary.addIcon(lblVolumen, "botones/botones juego/sound_" + mode, 50, 50);                
            }            
        }
        
        @Override
        public void keyPressed(KeyEvent e) { // Cuando se presiona la tecla 
            int keyCode = e.getKeyCode();
            
            if(keyCode == 32 && timerBaldosas.isRunning()) {
                myLibrary.addIcon(lblBoton, "botones/botones juego/pressed.png", 100, 100); 
                
                // "Pausa" el juego
                timerBaldosas.stop();
                startTimerEspera(0);
                
                // Verifica
                boolean correctOportunity = ronda.checkBaldosas(true);
                if (correctOportunity) {
                    ronda.increaseScore();
                    setScore();
                    if(ronda.getCantidadBaldosas() <= 7) {
                        ronda.increaseBaldosas();
                    }
                    putImages();
                    setVidas();
                    setBordersGreen();
                    
                } else {
                    // Se establecen los bordes de rojo
                    setBordersRed();
                    putImages();
                    timerBaldosas.stop();
                    startTimerEspera(0);
                }                                
            }
        }
        
        @Override
        public void keyReleased(KeyEvent e) { // Cuando se suelta la tecla
            int keyCode = e.getKeyCode();

            if(keyCode == 32 && timerBaldosas.isRunning()) {
                myLibrary.addIcon(lblBoton, "botones/botones juego/normal.png", 100, 100);
            }            
        }   
        
        @Override
        public void keyTyped(KeyEvent e) { // Cuando se unde y se suelta la tecla
            
        }                  
    }   
}

