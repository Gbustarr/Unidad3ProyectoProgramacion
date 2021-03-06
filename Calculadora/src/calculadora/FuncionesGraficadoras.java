/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author oknmi
 */
public class FuncionesGraficadoras {

    

    protected void dibujarTodosLosSimbolos(GraphicsContext gc, ArrayList<Simbolo> lista_simbolos) {
        for (int i = 0; i < lista_simbolos.size(); i++) {
            ////System.out.println("----------------");
            Simbolo s = lista_simbolos.get(i);
            s.dibujar_Simbolo(gc);
        }
        
    }

    protected void borrarTodo(GraphicsContext gc, Canvas Display, ArrayList<Simbolo> lista_simbolos, double pivot_x) {
        limpiarCanvas(gc, Display);
        lista_simbolos.clear();
        dibujarTodosLosSimbolos(gc, lista_simbolos);  
    }

    protected void limpiarCanvas(GraphicsContext gc, Canvas Display) {

        gc.clearRect(0, 0, Display.getWidth(), Display.getHeight());
    }
    
    protected void actualizarColores(GraphicsContext gc, ArrayList<Simbolo> lista_simbolos,Color numeros,Color Operadores,Canvas Display){
        for (int i = 0; i < lista_simbolos.size(); i++) {
            ////System.out.println("----------------");
            Simbolo s = lista_simbolos.get(i);
            if(s.tipo == 0){
                s.setColor(numeros);
            }else if(s.tipo > 0){
                s.setColor(Operadores);
            }
        }
        limpiarCanvas(gc, Display);
        dibujarTodosLosSimbolos(gc, lista_simbolos);
    }
}
