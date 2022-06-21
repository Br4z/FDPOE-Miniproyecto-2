/*

                 *´¨) 
                ¸.•´ ¸.•´¸.•*´¨) ¸.•*¨) 
                (¸.•´ (¸.•` ¤ 
       .---. 
      /     \   calderon.brandon@correounivalle.edu.co     
      \.@-@./                 202125974 
      /`\_/`\   idrobo.sebastian@correounivalle.edu.co    
     //  _  \\                202122637
    | \     )|_        Ingeniería de sistemas
   /`\_`>  <_/ \
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
    private int cantidadBaldosas = 3;
    private int[][] tablero      = new int [4][3]; // Declare una matriz con 4 filas y 2 columnas
    
    public Ronda() {

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
