/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

/**
 *
 * @author Brandon Calderón <calderon.brandon@correounivalle.edu.co>
 * @author Sebastian Idrovo Avirama <idrobo.sebastian@correounivalle.edu.co>
 * @profesor Luis Yovany Romo Portilla
 * Clase que representa la ronda de juego respectiva
 */

public class Ronda {
    private int vidas;
    private int puntaje;
    private int aciertos;
    private int velocidadActual;
    private int cantidadBaldosas;
    
    public Ronda(){
        vidas = 3;
        puntaje = 0;
        aciertos = 0;
        velocidadActual = 1; //?
        cantidadBaldosas = 3;
    }
    
    public void aumentarPuntaje(){
        
        if(this.cantidadBaldosas == 3){
            this.puntaje += 5;
            
        } else if (this.cantidadBaldosas == 4){
            this.puntaje += 10;
            
        } else if (this.cantidadBaldosas == 5){
            this.puntaje += 20;
            
        } else if (this.cantidadBaldosas == 6){
            this.puntaje += 40;
            
        } else if (this.cantidadBaldosas == 7){
            this.puntaje += 75;
            
        } else if (this.cantidadBaldosas == 8){
            this.puntaje += 100;
            
        }
            
    }
    
    public void aumentarBaldosas(){
        this.cantidadBaldosas += 1;
    }
    
    public void disminuirBaldosas(){
        this.cantidadBaldosas -= 1;
    }
    
    public void aumentarAciertos(){
        this.aciertos += 1;
    }
    
    public void disminuirVidas(){
        this.vidas -= 1;
    }
    
    
}
