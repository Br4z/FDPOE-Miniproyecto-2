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
    private int contadorBaldosa  = (int) (Math.random()* 9 + 1);
    private int vidas            = 3;
    private int puntaje          = 0;
    private int aciertos         = 0;
    private int velocidadActual  = 1;
    private int cantidadBaldosas = 0;
    private int[][] tablero      = new int [4][2]; // Declare una matriz con 4 filas y 2 columnas
    
    public Ronda() {
        for (int i = 0; i < 3; i++) { // Añadimos las tres baldosas con las que simpre comenzamos
            this.aumentarBaldosas();
        }
        cambiarTodasBaldosas();
    }
       
    public void removerBaldosa(){
        int randomRow = (int) (Math.random() * 3 + 0);
        int Column = 0; // Esta es la columna de las baldosas "exteriores", primero
        // intentamos quitarla de ahi
        
        while(tablero[randomRow][Column] == 0) { // Este while comprueba si en esa coordenada no 
            // hay baldosa
            if (tablero[randomRow][1] != 0) { // Si en la "exterior" no hay nada, intentamos con la 
                // interior
                Column = 1;              
            } else {
                randomRow = (int) (Math.random() * 3 + 0); // Proponemos otra fila, si esa fila no tiene
                // baldosas
            }
        }
        this.disminuirBaldosas();
        tablero[randomRow][Column] = 0;
    }
    
    public boolean comprobarBaldosas(boolean botonPresionado){
        int contador = 0;
        int baldosas[] = new int[cantidadBaldosas]; //Guardamos en esta colección las baldosas
        
        for (int[] columna: tablero){
            for (int baldosa: columna){
                baldosas[contador] = baldosa;
            }
        }
        
        contador = 0;
        
        //Aquí comprobamos si existen dos baldosas "repetidas", y en caso de que lo sean, suma 1 al contador
        for (int baldosaComprobar: baldosas){
            for (int otrasBaldosas: baldosas){
                if (baldosaComprobar == otrasBaldosas){
                    contador++;
                }
            }
            
        }
        
        //Aquí comprobamps si hay dos baldosas repetidas en el tablero o no
        //Si hay dos baldosas repetidas y el botón se encuentra presionado, solo retorna true
        if (contador >= 2 && botonPresionado){
            return true;
        
        //Si hay dos baldosas repetidas pero el botón no se encuentra presionado, retorna true y resta una vida
        } else if (contador >= 2 && !botonPresionado){
            disminuirVidas();
            disminuirBaldosas();
            return true;
        
        //Si no hay baldosas repetidas, retorna false
        } else if (contador < 2){
            return false;
            
        }
        
        return (contador >=2);
    }
    
    public void cambiarBaldosa(){
        //Seleccionamos una sección y una columna aleatorias
        int randomRow = (int) (Math.random() * 3 + 0);
        int randomColumn = (int) (Math.random() * 1 + 0);
        
        //En caso de que la sección y columna aleatorias no tengan ninguna baldosa,
        //este proceso se repetirá hasta que obtenga una baldosa
        while(tablero[randomRow][randomColumn] == 0){
            randomRow = (int) (Math.random() * 3 + 0);
            randomColumn = (int) (Math.random() * 1 + 0);
        }
        
        disminuirContadorBaldosa();
        int[] baldosasDisponibles = {};
        int contadorBaldosasDisponibles = 0;
        
        for (int[] columna: tablero){
            for (int baldosa: columna){
                
                if (baldosa != 0){
                    baldosasDisponibles[contadorBaldosasDisponibles] = baldosa;
                    contadorBaldosasDisponibles++;
                } 
            }
        }

        if (contadorBaldosa == 0){
            int posicionAleatoria = (int) (Math.random() * (cantidadBaldosas-1) + 0);
            int randomBaldosa = baldosasDisponibles[posicionAleatoria];
            tablero[randomRow][randomColumn] = randomBaldosa;
        } else {
            
            int randomBaldosa = (int) (Math.random() * (16) + 1);
            boolean repeticion = false;
            
            for (int baldosa: baldosasDisponibles){
                if (randomBaldosa == baldosa){
                    repeticion = true;
                    break;
                }
                repeticion = false;
            }
            
            while(repeticion){
                randomBaldosa = (int) (Math.random() * (16) + 1);
                repeticion = false;
                
                for (int baldosa: baldosasDisponibles){
                    if (randomBaldosa == baldosa){
                        repeticion = true;
                        break;
                    }
                    repeticion = false;
                }
            }
            
            tablero[randomRow][randomColumn] = randomBaldosa;
        }
        
        /*
        //Seleccionamos una sección y una columna aleatorias
        int randomRow = (int) (Math.random() * 3 + 0);
        int randomColumn = (int) (Math.random() * 1 + 0);
        
        //En caso de que la sección y columna aleatorias no tengan ninguna baldosa,
        //este proceso se repetirá hasta que obtenga una baldosa
        while(tablero[randomRow][randomColumn] == 0){
            randomRow = (int) (Math.random() * 3 + 0);
            randomColumn = (int) (Math.random() * 1 + 0);
        }
        
        tablero[randomRow][randomColumn] = baldosa;
        */
    }
    
    public void cambiarTodasBaldosas(){
        establecerContadorBaldosa();
        
        int[] baldosas = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int randomRow = (int) (Math.random() * 3 + 0);
        int Column = 1; // Esta es la columna de las baldosas "interiores", primero
        // intentamos añadirla ahi
        
        //Bucle encargado de cambiar todas las baldosas
        for (int i = 0; i < cantidadBaldosas; i++){
            while(tablero[randomRow][Column] != 0) { // Si en la posicion interior ya se encuentra
            // una baldosa, entonces intentamos con la posicion "exterior"
            if (tablero[randomRow][0] == 0){
                Column = 0;    
            } else { // Si en la posicion "exterior" tambien se encuentra algo, entonces proponemos
                // otra fila
                randomRow = (int) (Math.random() * 3 + 0);          
            } // Solo salimos del while si a las coordenadas donde apuntamos no hay una baldosa, es decir,
            // sea igual a 0
            
            //se selecciona una posición aleatoria de baldosas[]
            int numeroBaldosa = (int) (Math.random() * 15 + 0); 
            //Guarda el número de baldosa
            int baldosaAleatoria = baldosas[numeroBaldosa];
            
            //Si la baldosa "Ya fue tomada", repite el proceso hasta tomar una que no se encuentre ocupada
            if (baldosaAleatoria == 0){
                numeroBaldosa = (int) (Math.random() * 15 + 0);            
                baldosaAleatoria = baldosas[numeroBaldosa];
            }
            
            //Añade la respectiva baldosa al tablero
            tablero[randomRow][Column] = baldosaAleatoria;
            
            //La posición tomada de baldosas[], se vuelve 0 para indicar que la posición ya ha sido tomada
            baldosas[numeroBaldosa] = 0;
            }  
        }

        this.aumentarBaldosas();
        tablero[randomRow][Column] = (int) (Math.random() * 16 + 1);
    }
    
    public void aumentarPuntaje() {        
        if(cantidadBaldosas == 3) {
            puntaje += 5;         
        } else if (cantidadBaldosas == 4) {
            puntaje += 10;           
        } else if (cantidadBaldosas == 5) {
            puntaje += 20;           
        } else if (cantidadBaldosas == 6) {
            puntaje += 40;            
        } else if (cantidadBaldosas == 7) {
            puntaje += 75;            
        } else { // Corresponde al caso de 8 baldosas
            puntaje += 100;            
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
    
    public void disminuirVidas(){
        vidas -= 1;
    }
    
    public void establecerContadorBaldosa(){
       contadorBaldosa = (int) (Math.random()* 9 + 1); 
    }
    
    public void disminuirContadorBaldosa(){
        contadorBaldosa -= 1;
    }
}