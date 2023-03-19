/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.LinkedList;
import java.util.List;
import modelo.casilla;

/**
 *
 * @author Personal
 */
public class tablero {
    
        casilla[][] casillas;
    
        int numFilas;
        int numColumnas;
        int numMinas;

    public tablero(int numFilas, int numColumnas,int numMinas) {
        this.numFilas = numFilas;
        this.numMinas = numMinas;
        this.numColumnas = numColumnas;
        inicializarCasillas();
        
    }
    public void inicializarCasillas(){
        casillas=new casilla[this.numFilas][this.numColumnas];
        for(int i=0;i<casillas.length;i++){
            for (int j=0;j<casillas[i].length;j++){
                casillas[i][j]=new casilla(i,j);
            }
        }
        generarMinas();
    }
    
    private void generarMinas(){
        int minasGeneradas=0;
        while (minasGeneradas!=numMinas){
            int posTempFila=(int)(Math.random()*casillas.length);
            int posTempColumna=(int)(Math.random()*casillas[0].length);
            if (!casillas[posTempFila][posTempColumna].hayMina()){
                casillas[posTempFila][posTempColumna].setMina(true);
                minasGeneradas++;
            }
        }
        ActualizarMinasAlrededor();
        
    }
    
    public void imprimirTablero(){
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].hayMina()?"*":"0");;
            }
            System.out.println("");
        }
    }
    
    public void imprimirPistas(){
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                System.out.print(casillas[i][j].getNumMinasAlrededor());;
            }
            System.out.println("");
        }
    }
    private void ActualizarMinasAlrededor(){
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if(casillas[i][j].hayMina()){
                    List<casilla> casillasAlrededor=obtenerCasillasAlrededor(i, j);
                    casillasAlrededor.forEach((c)->c.incrementarMinasAlrededor());
                }   
            }
            
        }
    }
    private List<casilla> obtenerCasillasAlrededor(int posFila,int posColumna){
            List<casilla> listaCasillas=new LinkedList<>();
            for (int i = 0; i < 8; i++) {
                int tempPosFila=posFila;
                int tempPosColumna=posColumna;
                switch (i) {
                    case 0: tempPosFila--;break; //arriba
                    case 1: tempPosFila--;tempPosColumna++;break; //arriba derecha
                    case 2: tempPosColumna++;break; //derecha
                    case 3: tempPosColumna++;tempPosFila++;break; //derecha abajo
                    case 4: tempPosFila++;break; //abajo
                    case 5: tempPosFila++;tempPosColumna--;break; //abajo izquierda
                    case 6: tempPosColumna--;break; //izquierda
                    case 7: tempPosFila--;tempPosColumna--;break; //izquierda arriba
                }
                
                if(tempPosFila>=0 && tempPosFila<this.casillas.length
                        && tempPosColumna>=0 && tempPosColumna<this.casillas[0].length){
                    listaCasillas.add(this.casillas[tempPosFila][tempPosColumna]);
                }
        }
        return listaCasillas;    
    }
}
    
