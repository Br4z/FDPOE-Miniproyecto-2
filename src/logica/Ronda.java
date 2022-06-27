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

package logica;

/**
 *  CLASE:     Ronda
 *  INTENCION: Guardar la informacion que tiene un juego.
 *  RELACION:  NINGUNA 
 */

public class Ronda {
    // Como todas las rondas empiezan de la misma forma, le damos los atributos con valores
    private int changesNumber  = (int) (Math.random() * 9 + 1); // Numero de cambios para repetir una baldosa
    private int vidas            = 3;
    private int score            = 0;
    private int aciertos         = 0;
    private int velocidadActual  = 1;
    private int cantidadBaldosas = 0;
    private int[][] tablero      = new int [4][2]; // Declare una matriz con 4 filas y 2 columnas
    
    public Ronda() {
        for (int i = 0; i < 3; i++) { // Añadimos las tres baldosas con las que simpre comenzamos
            aumentarBaldosas();
        }
        cambiarTodasBaldosas();
    }
       
    public void removeABaldosa() { // Cuando perdemos removemos una baldosa
        int randomRow = (int) (Math.random() * 3);
        int Column = 0; // Esta es la columna de las baldosas "exteriores", primero
        // intentamos quitarla de ahi
        
        while(tablero[randomRow][Column] == 0) { // Este while comprueba si en esa coordenada no 
            // hay baldosa
            if (tablero[randomRow][1] != 0) { // Si en la "exterior" no hay nada, intentamos con la 
                // interior
                Column = 1;              
            } else {
                randomRow = (int) (Math.random() * 3); // Proponemos otra fila, si esa fila no tiene
                // baldosas
            }
        }
        disminuirBaldosas();
        tablero[randomRow][Column] = 0;
    }
    
    public boolean checkBaldosas(boolean botonPresionado){
        int repeatedBaldosa = 0; // El 0 significara que no se encontro baldosa repetida
        int counter = 0;
        int[] baldosas = new int[cantidadBaldosas]; // Guardamos en esta colección las baldosas
        
        for (int[] row: tablero) { // Recorremos filas
            for (int j = 0; j < 1; j++) { // Recorremos columnas
                int baldosa = row[j];
                baldosas[counter] = baldosa; // Estamos pasando una matriz 2D a una 1D
                counter++;
            }
        }        
         
        // Aquí comprobamos si existen dos baldosas "repetidas", y en caso de que lo sean, suma 1 al contador
        for (int i = 0; i < baldosas.length; i++) {
            for (int j = i + 1; j < baldosas.length; j++) {
                if (baldosas[j] == baldosas[i]) {
                    repeatedBaldosa = baldosas[j];
                    break; // En nuestra logica solo es posible una baldosa repetida, porque se 
                    // agregan de una en una
                }
            }           
        }
        // Comprobacion viable para VentanaJuego
        // Aquí comprobamos si hay dos baldosas repetidas en el tablero
        if (repeatedBaldosa != 0) {
            if(botonPresionado) { // Si hay y presiono el boton
                return true;
            } else { // Si hay y no presiono el boton
                disminuirVidas();
                disminuirBaldosas();
                return true;    
            }
        } else { // Si presiono el boton y no hay baldosas repetidas
            return false;
        }                      
    }
    
    public void changeBaldosa() {
        //Seleccionamos una sección y una columna aleatorias
        int randomRow = (int) (Math.random() * 3);
        int randomColumn = (int) (Math.random() * 1);
        
        // Este while es para asegurar encontrar una baldosa
        while(tablero[randomRow][randomColumn] == 0) {
            randomRow = (int) (Math.random() * 3);
            randomColumn = (int) (Math.random() * 1);
        }        

        reduceChangesNumber();
        
        int[] baldosasEnTablero = { };
      
        for (int[] row: tablero) {
            for (int baldosa: row) {                
                if (baldosa != 0){
                    baldosasEnTablero[baldosasEnTablero.length] = baldosa; // Usamos la misma longitud
                    // del array como indices
                } 
            }
        }
        
        if (changesNumber == 0) { // Cuando ponemos una repetida
            int posicionAleatoria = (int) (Math.random() * (cantidadBaldosas - 1)); // Restamos 1, porque
            // empezamos en una lista empezamos a contar desde el cero
            int randomBaldosa = baldosasEnTablero[posicionAleatoria];
            tablero[randomRow][randomColumn] = randomBaldosa;
        } else { // Cuando no ponemos una repetida           
            boolean repeticion = true;           
            
            while(repeticion) {
                int randomBaldosa = (int) (Math.random() * 16 + 1);
                
                for (int baldosa: baldosasEnTablero) {
                    if (randomBaldosa == baldosa){
                        break;
                    }
                    repeticion = false;
                }
                // Mientras no encuentre una baldosa que no se repite sobrescribira lo siguiente:
                tablero[randomRow][randomColumn] = randomBaldosa;
            }                       
        }       
    }
    
    public void cambiarTodasBaldosas(){
        int[] baldosas = {1, 2, 3, 4, 5, 6, 7, 8, 9 , 10, 11, 12, 13, 14, 15, 16};
        int randomRow = (int) (Math.random() * 3);
        int Column = 1; // Esta es la columna de las baldosas "interiores", primero
        // intentamos añadirla ahi
        int numeroBaldosa = 0;
        int baldosaAleatoria = 0;
        
        // Bucle encargado de cambiar todas las baldosas
        for (int i = 0; i < cantidadBaldosas; i++) {
            while(tablero[randomRow][Column] != 0) { // Si en la posicion interior ya se encuentra
            // una baldosa, entonces intentamos con la posicion "exterior"
                if (tablero[randomRow][0] == 0) {
                    Column = 0;    
                } else { // Si en la posicion "exterior" tambien se encuentra algo, entonces proponemos
                // otra fila
                    randomRow = (int) (Math.random() * 3);
                } // Solo salimos del while si a las coordenadas donde apuntamos no hay una baldosa, es decir,
                // sea igual a 0         
            }
                            
            // Se selecciona una posición aleatoria de baldosas[]
            numeroBaldosa = (int) (Math.random() * 15);
            
            // Guarda el número de baldosa
            baldosaAleatoria = baldosas[numeroBaldosa];
                
            // Si la baldosa "Ya fue tomada", repite el proceso hasta tomar una que no se encuentre ocupada,
            // con esto aseguramos que no haya baldosas repetidas en el primer cambio
            if (baldosaAleatoria == 0) {
                numeroBaldosa = (int) (Math.random() * 15);            
                baldosaAleatoria = baldosas[numeroBaldosa];
            }  
            
            // Añade la respectiva baldosa al tablero
            System.out.println("Baldosa: " + baldosaAleatoria);
            tablero[randomRow][Column] = baldosaAleatoria;           
            // La posición tomada de baldosas[], se vuelve 0 para indicar que la posición ya ha sido tomada
            baldosas[numeroBaldosa] = 0;
        }    
        aumentarBaldosas();
        tablero[randomRow][Column] = (int) (Math.random() * 16 + 1);
    }
    
    public void aumentarPuntaje() {        
        switch (cantidadBaldosas) {
            case 3 -> score += 5;
            case 4 -> score += 10;
            case 5 -> score += 20;
            case 6 -> score += 40;
            case 7 -> score += 75;
            default -> // Corresponde al caso de 8 baldosas
                score += 100;
        }       
        aumentarAciertos();
    }
    
    public void aumentarBaldosas(){
        cantidadBaldosas += 1;
    }
    
    public void disminuirBaldosas(){
        cantidadBaldosas -= 1;
    }
    
    public void aumentarAciertos(){
        aciertos += 1;
    }
    
    public int getAciertor() {
        return aciertos;
    }
    
    public void disminuirVidas(){
        vidas -= 1;
    }
    
    public int getVidas() {
        return vidas;
    }
    
    public void setChangesNumber(){
       changesNumber = (int) (Math.random()* 9 + 1); 
    }
    
    public void reduceChangesNumber(){
        changesNumber -= 1;
    }    

    public int[][] getTablero() {
        return tablero;
    }
    
}