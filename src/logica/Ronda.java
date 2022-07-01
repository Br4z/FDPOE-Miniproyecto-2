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

import java.util.Arrays;

/**
 *  CLASE:     Ronda
 *  INTENCION: Guardar la informacion que tiene un juego.
 *  RELACION:  NINGUNA 
 */


public class Ronda {
    // Como todas las rondas empiezan de la misma forma, le damos los atributos con valores
    private int changesNumber; // Numero de cambios para repetir una baldosa
    private int[] changedBaldosa = {-1, -1}; // Para simplificar otros metodos (especificamente en la busqueda), vamos
    // a establecerlo como atributo
    private int vidas            = 3;
    private int score            = 0;
    private int aciertos         = 0;
    private int cantidadBaldosas = 0;
    private int[][] tablero      = new int [4][2]; // Declare una matriz con 4 filas y 2 columnas
    
    public Ronda() {
        for (int i = 0; i < 3; i++) { // Añadimos las tres baldosas con las que simpre comenzamos
            increaseBaldosas();
        }
        changeAllBaldosas();
    }
          
    public boolean checkBaldosas(boolean isButtonPressed){
        boolean repeatedBaldosa = changesNumber == 0; // En nuestro planteamiento solo hay una baldosa
        // repetica si el numero de cambios es 0
 
        System.out.println("Cambios para una baldosa repetida " + changesNumber);
        // Comprobacion viable para VentanaJuego
        // Aquí comprobamos si hay dos baldosas repetidas en el tablero
        if(repeatedBaldosa) {
            if(isButtonPressed) { // Si hay y presiono el boton
                // Como acerto incrementamos la puntuacion y el numero de aciertos
                increaseScore(); 
                increaseAciertos();
                if(cantidadBaldosas <= 7) { // Si esta en 7 el ultimo incremento debe ser a 8
                    increaseBaldosas();
                }                
                return true;
            } else { // Si hay y no presiono el boton
                decreaseVidas();
                if(cantidadBaldosas > 3) {
                    decreaseBaldosas();
                }
                return true;    
            }
        } else { // Si no hay baldosas repetidas
            if(isButtonPressed){ // Si no hay, y presionó el botón
                decreaseVidas();
                if(cantidadBaldosas > 3){
                    decreaseBaldosas();
                }
                return false;
            } else { // Si no hay, y no presionó el botón
                return false;
            }
        }                      
    }
    
    public void changeABaldosa() { // Este es el metodo que cambia una sola baldosa, se ejecuta
        // constantemente junto con el timer
        reduceChangesNumber();
        
        int[] baldosasEnTablero = getArrayBaldosas();
        System.out.println(Arrays.toString(baldosasEnTablero));
        
        if(changesNumber == 0) { // Aquí repetiremos una baldosa
            int posicionAleatoria = (int) (Math.random() * cantidadBaldosas);
            int randomBaldosa = baldosasEnTablero[posicionAleatoria];
            
            
            // Seleccionamos una fila y una columna aleatorias
            int randomRow = (int) (Math.random() * 4);
            int randomColumn = (int) (Math.random() * 2);
        
            // Este while es para asegurar encontrar una baldosa (y que no sea la misma que ya se encuentra ahi)
            while(tablero[randomRow][randomColumn] == 0 || tablero[randomRow][randomColumn] == randomBaldosa) {
                randomRow = (int) (Math.random() * 4);
                randomColumn = (int) (Math.random() * 2);
            }        
            
            tablero[randomRow][randomColumn] = randomBaldosa;
            changedBaldosa[0] = randomRow;
            changedBaldosa[1] = randomColumn;            
        } else { // Cuando no ponemos una repetida   
            int randomRow = (int) (Math.random() * 4);
            int randomColumn = (int) (Math.random() * 2);
        
            // Este while es para asegurar encontrar una baldosa
            while(tablero[randomRow][randomColumn] == 0) {
                randomRow = (int) (Math.random() * 4);
                randomColumn = (int) (Math.random() * 2);
            }
            
            boolean repeticion = true;
            // Este otro while es para asegurarnos de no escoger una baldosa que ya se encuentra
            while(repeticion) {
                int randomBaldosa = (int) (Math.random() * 16 + 1); // Enumeramos las baldosas del 1 al 16
                
                for(int baldosa: baldosasEnTablero) {
                    if (randomBaldosa != baldosa) { 
                        repeticion = false;
                    } else {
                        repeticion = true; // Si llega a encontrar alguno iguano igual hacemos que el
                        // while siga
                        break;
                    }              
                }
                // Mientras no encuentre una baldosa que no se repite sobrescribira lo siguiente:
                changedBaldosa[0] = randomRow;
                changedBaldosa[1] = randomColumn;
                tablero[randomRow][randomColumn] = randomBaldosa;
            }
            
        }       
    }
    
    public void changeAllBaldosas() { // Metodo para cambiar todas las baldosas entre 
        setChangesNumber(); // Establecemos el numero de cambio necesario para que aparezca una baldosa
        // repetida
        // Limpiamos el tablero
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 2; j++) {
                tablero[i][j] = 0;
            }
        }
        
        int[] baldosas = {1, 2, 3, 4, 5, 6, 7, 8, 9 , 10, 11, 12, 13, 14, 15, 16};
        int randomRow = (int) (Math.random() * 4);
        int Column = 1; // Esta es la columna de las baldosas "interiores", primero
        // intentamos añadirla ahi
        
        int randonNumber;
        int baldosaAleatoria;
        
        // Bucle encargado de cambiar todas las baldosas
        for (int i = 0; i < cantidadBaldosas; i++) {
            while(tablero[randomRow][Column] != 0) { // Si en la posicion interior ya se encuentra
            // una baldosa, entonces intentamos con la posicion "exterior"
                if (tablero[randomRow][0] == 0) {
                    Column = 0;    
                } else { // Si en la posicion "exterior" tambien se encuentra algo, entonces proponemos
                // otra fila
                    randomRow = (int) (Math.random() * 4);
                    Column = 1;
                } // Solo salimos del while si a las coordenadas donde apuntamos no hay una baldosa, es decir,
                // sea igual a 0         
            }
                            
            // Se selecciona una posición aleatoria de baldosas
            randonNumber = (int) (Math.random() * 15 + 1);
            
            // Guarda el número de baldosa
            baldosaAleatoria = baldosas[randonNumber];
                
            // Si la baldosa "Ya fue tomada", repite el proceso hasta tomar una que no se encuentre ocupada,
            // con esto aseguramos que no haya baldosas repetidas en el primer cambio
            while(baldosaAleatoria == 0) {
                randonNumber = (int) (Math.random() * 15 + 1);            
                baldosaAleatoria = baldosas[randonNumber];
            }        
            // Añade la respectiva baldosa al tablero
            tablero[randomRow][Column] = baldosaAleatoria;
            // Como la tomamos, en baldosas la ponemos en 0
            baldosas[randonNumber] = 0;
        }           
    }
    
    public int getScore() {
        return score;
    }
    
    public void increaseScore() {        
        switch (cantidadBaldosas) {
            case 3 -> score += 5;
            case 4 -> score += 10;
            case 5 -> score += 20;
            case 6 -> score += 40;
            case 7 -> score += 75;
            default -> // Corresponde al caso de 8 baldosas
                score += 100;
        }       
        increaseAciertos();
    }
    
    public void increaseBaldosas() {
        cantidadBaldosas += 1;
    }
    
    public void decreaseBaldosas() { 
        cantidadBaldosas -= 1;
    }

    public int getCantidadBaldosas() {
        return cantidadBaldosas;
    }
    
    public void increaseAciertos() {
        aciertos += 1;
    }
    
    public int getAciertos() {
        return aciertos;
    }
    
    public void decreaseVidas(){
        vidas -= 1;
    }
    
    public int getVidas() {
        return vidas;
    }
    
    public void setChangesNumber(){
       changesNumber = (int) (Math.random() * 10); 
    }
    
    public void reduceChangesNumber(){
        changesNumber -= 1;
    }    

    public int[][] getTablero() {
        return tablero;
    }

    public int[] getChangedBaldosa() {
        return changedBaldosa;
    }
       
    public int[] getArrayBaldosas() {
        int[] baldosas = new int[cantidadBaldosas];
        int contador = 0;
        
        for(int[] row:tablero) {
            for(int baldosa:row) {
                if(baldosa != 0) {
                    baldosas[contador] = baldosa;
                    contador++;
                }
            }
        }
        return baldosas;
    }
}