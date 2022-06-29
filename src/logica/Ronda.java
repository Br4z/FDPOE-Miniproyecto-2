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
            increaseBaldosas();
        }
        cambiarTodasBaldosas();
    }
       
    public void removeABaldosa() { // Cuando perdemos removemos una baldosa
        int randomRow = (int) (Math.random() * 4 + 0);
        int Column = 0; // Esta es la columna de las baldosas "exteriores", primero
        // intentamos quitarla de ahi
        
        while(tablero[randomRow][Column] == 0) { // Este while comprueba si en esa coordenada no 
            // hay baldosa
            if (tablero[randomRow][1] != 0) { // Si en la "exterior" no hay nada, intentamos con la 
                // interior
                Column = 1;              
            } else {
                randomRow = (int) (Math.random() * 4 + 0); // Proponemos otra fila, si esa fila no tiene
                // baldosas
                Column = 0;
            }
        }
        tablero[randomRow][Column] = 0;
    }
    
    public boolean checkBaldosas(boolean pressedButton){
        int repeatedBaldosa = 0; // El 0 significara que no se encontro baldosa repetida
        int counter = 0;
        System.out.println();
        int[] baldosas = new int[cantidadBaldosas]; // Guardamos en esta colección las baldosas
        
        for (int[] row: tablero) { // Recorremos filas
            for (int baldosa: row){ // Recorremos columnas
                if (baldosa != 0){
                    baldosas[counter] = baldosa; // Estamos pasando una matriz 2D a una 1D
                    counter++;
                }
            }
        }
         
        // Aquí comprobamos si existen dos baldosas "repetidas", y en caso de que lo sean, suma 1 al contador
        for (int i = 0; i < baldosas.length; i++) {
            repeatedBaldosa = 0;
            for (int j = 0; j < baldosas.length; j++) {
                if (baldosas[i] == baldosas[j]) {
                    repeatedBaldosa++;
                }
                if (repeatedBaldosa >= 2){
                    break;
                }
            }
            if (repeatedBaldosa >= 2){
                break;
            }
        }
        // Comprobacion viable para VentanaJuego
        // Aquí comprobamos si hay dos baldosas repetidas en el tablero
        if (repeatedBaldosa >= 2) {
            if(pressedButton) { // Si hay y presiono el boton
                return true;
            } else { // Si hay y no presiono el boton
                System.out.println("Ups, decreaseBaldosas");
                decreaseVidas();
                if (cantidadBaldosas > 3){
                    decreaseBaldosas();
                }
                return true;    
            }
        } else { //Si no hay baldosas repetidas
            if (pressedButton){ //Si no hay, y presionó el botón
                decreaseVidas();
                if (cantidadBaldosas > 3){
                    decreaseBaldosas();
                }
                return false;
            } else { //Si no hay, y no presionó el botón
                return false;
            }
        }                      
    }
    
    public void changeBaldosa() {
        reduceChangesNumber();
        
        int[] baldosasEnTablero = new int[cantidadBaldosas];
        int contador = 0;
      
        for (int[] row: tablero) {
            for (int baldosa: row) {                
                if (baldosa != 0){
                    baldosasEnTablero[contador] = baldosa; 
                    contador++;
                } 
            }
        }
        
        if (changesNumber == 0) { // Aquí pondremos una baldosa que se repita
            int posicionAleatoria = (int) (Math.random() * (cantidadBaldosas) + 0); // Restamos 1, porque
            // empezamos en una lista empezamos a contar desde el cero
            int randomBaldosa = baldosasEnTablero[posicionAleatoria];
            
            //Verificamos que la baldosa repetida no la vaya a poner en el mismo lugar de la baldosa origen
            // Seleccionamos una fila y una columna aleatorias
            int randomRow = (int) (Math.random() * 4 + 0);
            int randomColumn = (int) (Math.random() * 2 + 0);
        
            // Este while es para asegurar encontrar una baldosa
            while(tablero[randomRow][randomColumn] == 0 || tablero[randomRow][randomColumn] == randomBaldosa) {
                randomRow = (int) (Math.random() * 4 + 0);
                randomColumn = (int) (Math.random() * 2 + 0);
            }        
            
            tablero[randomRow][randomColumn] = randomBaldosa;
            System.out.println("Baldosa aleatoria!!!");
            
        } else { // Cuando no ponemos una repetida   
            int randomRow = (int) (Math.random() * 4 + 0);
            int randomColumn = (int) (Math.random() * 2 + 0);
        
            // Este while es para asegurar encontrar una baldosa
            while(tablero[randomRow][randomColumn] == 0) {
                randomRow = (int) (Math.random() * 4 + 0);
                randomColumn = (int) (Math.random() * 2 + 0);
            }
            
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
        setChangesNumber();
        for (int m = 0; m < 4; m++){
            for (int n = 0; n < 2; n++){
                tablero[m][n] = 0;
            }
        }
        int[] baldosas = {1, 2, 3, 4, 5, 6, 7, 8, 9 , 10, 11, 12, 13, 14, 15, 16};
        int randomRow = (int) (Math.random() * 4 + 0);
        int Column = 1; // Esta es la columna de las baldosas "interiores", primero
        // intentamos añadirla ahi
        int numeroBaldosa;
        int baldosaAleatoria;
        
        // Bucle encargado de cambiar todas las baldosas
        for (int i = 0; i < cantidadBaldosas; i++) {
            while(tablero[randomRow][Column] != 0) { // Si en la posicion interior ya se encuentra
            // una baldosa, entonces intentamos con la posicion "exterior"
                if (tablero[randomRow][0] == 0) {
                    Column = 0;    
                } else { // Si en la posicion "exterior" tambien se encuentra algo, entonces proponemos
                // otra fila
                    randomRow = (int) (Math.random() * 4 + 0);
                    Column = 1;
                } // Solo salimos del while si a las coordenadas donde apuntamos no hay una baldosa, es decir,
                // sea igual a 0         
            }
                            
            // Se selecciona una posición aleatoria de baldosas[]
            numeroBaldosa = (int) (Math.random() * 15 + 1);
            
            // Guarda el número de baldosa
            baldosaAleatoria = baldosas[numeroBaldosa];
                
            // Si la baldosa "Ya fue tomada", repite el proceso hasta tomar una que no se encuentre ocupada,
            // con esto aseguramos que no haya baldosas repetidas en el primer cambio
            while(baldosaAleatoria == 0){
                numeroBaldosa = (int) (Math.random() * 15 + 1);            
                baldosaAleatoria = baldosas[numeroBaldosa];
            }
            
            // Añade la respectiva baldosa al tablero
            tablero[randomRow][Column] = baldosaAleatoria;
            // La posición tomada de baldosas[], se vuelve 0 para indicar que la posición ya ha sido tomada
            baldosas[numeroBaldosa] = 0;
        }    
        //increaseBaldosas();
        //tablero[randomRow][Column] = (int) (Math.random() * 16 + 1);
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
    
    public int getAciertor() {
        return aciertos;
    }
    
    public void decreaseVidas(){
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

    public int getAciertos() {
        return aciertos;
    }
    
    //ESTE ES DE PRUEBA, BORRAR PARA EL FINAL
    public void mostrarTablero(){
        for (int m = 0; m < 4; m++){
            for (int n = 0; n < 2; n++){
                //tablero[m][n] = 0;
                System.out.println("Tablero: [" + m + "][" + n + "] = " + tablero[m][n]);
            }
        }
    }
}