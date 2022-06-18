/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Guillermo
 */
public class FuncionesAuxiliares {
    
    protected void moverPivotDerecha(Logica l,Simbolo s){
        
        if(s.getValor() > 13 && s.getValor() <17){ //seno coseno tangente
            l.pivot_x = l.pivot_x + (l.espacioEntreSimbolos*3);
            l.d.listaMovimientosHaciaDerecha.set(l.d.listaMovimientosHaciaDerecha.size()-1, 
            l.d.listaMovimientosHaciaDerecha.get(l.d.listaMovimientosHaciaDerecha.size()-1) + 3);
            l.d.movimientosDerecha = l.d.movimientosDerecha + 3;
        }else{
            l.pivot_x = l.pivot_x + l.espacioEntreSimbolos;
            l.d.listaMovimientosHaciaDerecha.set(l.d.listaMovimientosHaciaDerecha.size()-1, 
            l.d.listaMovimientosHaciaDerecha.get(l.d.listaMovimientosHaciaDerecha.size()-1) + 1);
            l.d.movimientosDerecha = l.d.movimientosDerecha + 1;
        }
    }
    
    protected void moverPivotDerecha(LogicaBinaria l,Simbolo s){
        
        if(s.getValor() > 13 && s.getValor() <17){ //seno coseno tangente
            l.pivot_x = l.pivot_x + (l.espacioEntreSimbolos*3);
            l.d.listaMovimientosHaciaDerechaBin.set(l.d.listaMovimientosHaciaDerechaBin.size()-1, 
            l.d.listaMovimientosHaciaDerechaBin.get(l.d.listaMovimientosHaciaDerechaBin.size()-1) + 3);
            l.d.movimientosDerechaBin = l.d.movimientosDerechaBin + 3;
        }else{
            l.pivot_x = l.pivot_x + l.espacioEntreSimbolos;
            l.d.listaMovimientosHaciaDerechaBin.set(l.d.listaMovimientosHaciaDerechaBin.size()-1, 
            l.d.listaMovimientosHaciaDerechaBin.get(l.d.listaMovimientosHaciaDerechaBin.size()-1) + 1);
            l.d.movimientosDerechaBin = l.d.movimientosDerechaBin + 1;
        }
    }
    
    protected void moverPivotDerechaPotencia(Logica l){
        l.pivot_x = l.pivot_x + (l.espacioEntreSimbolos/2);
            l.d.listaMovimientosHaciaDerecha.set(l.d.listaMovimientosHaciaDerecha.size()-1, 
                l.d.listaMovimientosHaciaDerecha.get(l.d.listaMovimientosHaciaDerecha.size()-1) + 0.7);
    }
    
    protected void moverPivotDerechaPotencia(LogicaBinaria l){
        l.pivot_x = l.pivot_x + (l.espacioEntreSimbolos/2);
            l.d.listaMovimientosHaciaDerechaBin.set(l.d.listaMovimientosHaciaDerechaBin.size()-1, 
                l.d.listaMovimientosHaciaDerechaBin.get(l.d.listaMovimientosHaciaDerechaBin.size()-1) + 0.7);
    }
    
    
    protected void alturaEnPotencia(Logica l){
        if(l.enPotencia){
            l.pivot_y = l.ultimoParentesisCerrado.forma[1]+40;
        }else{
            l.pivot_y = l.ultimoParentesisCerrado.Ypos;
        }
        
    }
    
    protected void alturaEnPotencia(LogicaBinaria l){
        if(l.enPotencia){
            l.pivot_y = l.ultimoParentesisCerrado.forma[1]+40;
        }else{
            l.pivot_y = l.ultimoParentesisCerrado.Ypos;
        }
        
    }
    
    protected void moverPivotArriba(Logica l,double distancia){
        
        l.pivot_y = l.pivot_y -distancia;
    }
    
    protected void moverPivotAbajo(Logica l,double distancia){
        
        l.pivot_y = l.pivot_y +distancia;
    }
    
    protected void moverPivotADenominador(Logica l){
        
        if(!l.ParentesisAbiertos.isEmpty()){
            l.pivot_x = l.ParentesisAbiertos.get(l.ParentesisAbiertos.size()-1).Xpos;
        }else{
            l.pivot_x = l.context.lista_simbolos.get(0).Xpos-l.espacioEntreSimbolos;
        }
    }
    
    protected void moverPivotADenominador(LogicaBinaria l){
        
        if(!l.ParentesisAbiertos.isEmpty()){
            l.pivot_x = l.ParentesisAbiertos.get(l.ParentesisAbiertos.size()-1).Xpos;
        }else{
            l.pivot_x = l.lista_simbolos.get(0).Xpos-l.espacioEntreSimbolos;
        }
    }

    
    
    protected void moverPivotIzquierda(Logica l, double distancia){
        l.pivot_x = l.pivot_x - distancia;
    }
    
    protected Simbolo conseguirUltimoSimbolo(ArrayList<Simbolo> lista_simbolos){
        
        if(!lista_simbolos.isEmpty()){
            return lista_simbolos.get(lista_simbolos.size()-1);
        }else{
            return null;
        }
    }
    
    protected int buscarNumero(ArrayList<Simbolo> lista_simbolos,int index){
        String numero = "";
        for(int i = index;i < lista_simbolos.size(); i++){
            if(lista_simbolos.get(i).tipo != 0){
                break;
            }else{
                numero = numero + lista_simbolos.get(i).getValorString();
            }
        }
        return Integer.parseInt(numero);
    
    } 
    //Recorre el string del numero binario convertido y los imprime en el canvas secundario
    protected void numerosBinariosACanvas(Logica l, String nBinario,GraphicsContext gc,ArrayList<Simbolo> lista_simbolos,Canvas DisplayBin){
        
        for(int i = 0; i < nBinario.length();i++){
            //System.out.println("->"+nBinario.charAt(i));
            l.logicBin.agregarSimbolo(gc, Integer.parseInt(Character.toString(nBinario.charAt(i))), lista_simbolos, DisplayBin);
        }
       
    }
    
    protected void agregarSimboloBin(Logica l,GraphicsContext gc,ArrayList<Simbolo> lista_simbolos,Canvas DisplayBin,int valor){
        l.logicBin.agregarSimbolo(gc, valor, lista_simbolos, DisplayBin);
    }
    
    protected void posicionarParentesisDeCierre(Logica l, Simbolo s) {
        int contador = 1;
        double coordenadaXmasLejana = l.pivot_x;
        
        for(int i = l.context.lista_simbolos.size()-1; i >= 0; i--){
            if(l.context.lista_simbolos.get(i).valor == 18){
                contador++;
            }
            if(l.context.lista_simbolos.get(i).valor == 17){
                contador--;
            }
            if(contador == 0){
                break;
            }else{
                if(l.context.lista_simbolos.get(i).Xpos > coordenadaXmasLejana){
                    coordenadaXmasLejana = l.context.lista_simbolos.get(i).Xpos;
                }
            }
            
        }
        
        s.Xpos = coordenadaXmasLejana;
        l.pivot_x = coordenadaXmasLejana;
        
    }
    
    protected void posicionarParentesisDeCierre(LogicaBinaria l, Simbolo s) {
        int contador = 1;
        double coordenadaXmasLejana = l.pivot_x;
        
        for(int i = l.lista_simbolos.size()-1; i >= 0; i--){
            if(l.lista_simbolos.get(i).valor == 18){
                contador++;
            }
            if(l.lista_simbolos.get(i).valor == 17){
                contador--;
            }
            if(contador == 0){
                break;
            }else{
                if(l.lista_simbolos.get(i).Xpos > coordenadaXmasLejana){
                    coordenadaXmasLejana = l.lista_simbolos.get(i).Xpos;
                }
            }
            
        }
        
        s.Xpos = coordenadaXmasLejana;
        l.pivot_x = coordenadaXmasLejana;
        
    }
    
    protected void calcularResultado(Logica l){
        ArrayList<Simbolo> lista_simbolosHomogenea = new ArrayList();
        ArrayList<Simbolo> lista_simbolos = l.context.lista_simbolos;
        
    }
    
    
    protected void printAllSymbols(ArrayList<Simbolo> lista){
        for (int i = 0; i < lista.size(); i++) {
            
            System.out.print(lista.get(i).toString());
            System.out.print("|");
        }
    }
    
}
