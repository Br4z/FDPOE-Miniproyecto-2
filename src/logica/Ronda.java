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
    private int vidas            = 3;
    private int puntaje          = 0;
    private int aciertos         = 0;
    private int velocidadActual  = 1;
    private int cantidadBaldosas = 0;
    private int[][] tablero      = new int [4][2]; // Declare una matriz con 4 filas y 2 columnas
    
    public Ronda() {
        for (int i = 0; i < 3; i++) { // Añadimos las tres baldosas con las que simpre comenzamos
            this.addBaldosa();
        }
    }
    
    public boolean comprobarBaldosas(){
        int contador = 0;
        int baldosas[] = new int[cantidadBaldosas]; //Guardamos en esta colección las baldosas
        
        for (int[] columna: tablero){
            for (int baldosa: columna){
                baldosas[contador] = baldosa;
            }
        }
        
        contador = 0;
        
        //Aquí comprobaremos si existen dos baldosas "repetidas", y en caso de que lo sean, suma 1 al contador
        for (int baldosaComprobar: baldosas){
            for (int otrasBaldosas: baldosas){
                if (baldosaComprobar == otrasBaldosas){
                    contador++;
                }
            }
            
        }
        
        //Retorna por último si las baldosas se encuentran repetidas
        return (contador >=2);
    }
    
    public void addBaldosa() {       
        int randomRow = (int) (Math.random() * 3 + 1);
        int Column = 1; // Esta es la columna de las baldosas "interiores", primero
        // intentamos añadirla ahi

        while(tablero[randomRow][Column] != 0) { // Si en la posicion interior ya se encuentra
            // una baldosa, entonces intentamos con la posicion "exterior"
            if (tablero[randomRow][0] == 0){
                Column = 0;    
            } else { // Si en la posicion "exterior" tambien se encuentra algo, entonces proponemos
                // otra fila
                randomRow = (int) (Math.random() * 3 + 1);          
            } // Solo salimos del while si a las coordenadas donde apuntamos no hay una baldosa, es decir,
            // sea igual a 0
        }
        this.aumentarBaldosas();
        tablero[randomRow][Column] = (int) (Math.random() * 16 + 1);
    }

    public void removerBaldosa(){
        int randomRow = (int) (Math.random() * 3 + 1);
        int Column = 0; // Esta es la columna de las baldosas "exteriores", primero
        // intentamos quitarla de ahi
        
        while(tablero[randomRow][Column] == 0) { // Este while comprueba si en esa coordenada no 
            // hay baldosa
            if (tablero[randomRow][1] != 0) { // Si en la "exterior" no hay nada, intentamos con la 
                // interior
                Column = 1;              
            } else {
                randomRow = (int) (Math.random() * 3 + 1); // Proponemos otra fila, si esa fila no tiene
                // baldosas
            }
        }
        this.disminuirBaldosas();
        tablero[randomRow][Column] = 0;
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
}