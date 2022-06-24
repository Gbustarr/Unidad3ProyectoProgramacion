/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Guillermo
 */
public class FuncionesAuxiliares {

    protected void moverPivotDerecha(Logica l, Simbolo s) {

        if (s.getValor() > 13 && s.getValor() < 17) { //seno coseno tangente
            l.pivot_x = l.pivot_x + (l.espacioEntreSimbolos * 3);
            l.d.listaMovimientosHaciaDerecha.set(l.d.listaMovimientosHaciaDerecha.size() - 1,
                    l.d.listaMovimientosHaciaDerecha.get(l.d.listaMovimientosHaciaDerecha.size() - 1) + 3);
            l.d.movimientosDerecha = l.d.movimientosDerecha + 3;
        } else {
            l.pivot_x = l.pivot_x + l.espacioEntreSimbolos;
            l.d.listaMovimientosHaciaDerecha.set(l.d.listaMovimientosHaciaDerecha.size() - 1,
                    l.d.listaMovimientosHaciaDerecha.get(l.d.listaMovimientosHaciaDerecha.size() - 1) + 1);
            l.d.movimientosDerecha = l.d.movimientosDerecha + 1;
        }
    }

    protected void moverPivotDerecha(LogicaBinaria l, Simbolo s) {

        if (s.getValor() > 13 && s.getValor() < 17) { //seno coseno tangente
            l.pivot_x = l.pivot_x + (l.espacioEntreSimbolos * 3);
            l.d.listaMovimientosHaciaDerechaBin.set(l.d.listaMovimientosHaciaDerechaBin.size() - 1,
                    l.d.listaMovimientosHaciaDerechaBin.get(l.d.listaMovimientosHaciaDerechaBin.size() - 1) + 3);
            l.d.movimientosDerechaBin = l.d.movimientosDerechaBin + 3;
        } else {
            l.pivot_x = l.pivot_x + l.espacioEntreSimbolos;
            l.d.listaMovimientosHaciaDerechaBin.set(l.d.listaMovimientosHaciaDerechaBin.size() - 1,
                    l.d.listaMovimientosHaciaDerechaBin.get(l.d.listaMovimientosHaciaDerechaBin.size() - 1) + 1);
            l.d.movimientosDerechaBin = l.d.movimientosDerechaBin + 1;
        }
    }

    protected void moverPivotDerechaPotencia(Logica l) {
        l.pivot_x = l.pivot_x + (l.espacioEntreSimbolos / 2);
        l.d.listaMovimientosHaciaDerecha.set(l.d.listaMovimientosHaciaDerecha.size() - 1,
                l.d.listaMovimientosHaciaDerecha.get(l.d.listaMovimientosHaciaDerecha.size() - 1) + 0.7);
    }

    protected void moverPivotDerechaPotencia(LogicaBinaria l) {
        l.pivot_x = l.pivot_x + (l.espacioEntreSimbolos / 2);
        l.d.listaMovimientosHaciaDerechaBin.set(l.d.listaMovimientosHaciaDerechaBin.size() - 1,
                l.d.listaMovimientosHaciaDerechaBin.get(l.d.listaMovimientosHaciaDerechaBin.size() - 1) + 0.7);
    }

    protected void alturaEnPotencia(Logica l) {
        if (l.enPotencia) {
            l.pivot_y = l.ultimoParentesisCerrado.forma[1] + 40;
        } else {
            l.pivot_y = l.ultimoParentesisCerrado.Ypos;
        }

    }

    protected void alturaEnPotencia(LogicaBinaria l) {
        if (l.enPotencia) {
            l.pivot_y = l.ultimoParentesisCerrado.forma[1] + 40;
        } else {
            l.pivot_y = l.ultimoParentesisCerrado.Ypos;
        }

    }

    protected void moverPivotArriba(Logica l, double distancia) {

        l.pivot_y = l.pivot_y - distancia;
    }

    protected void moverPivotAbajo(Logica l, double distancia) {

        l.pivot_y = l.pivot_y + distancia;
    }

    protected void moverPivotADenominador(Logica l) {

        if (!l.ParentesisAbiertos.isEmpty()) {
            l.pivot_x = l.ParentesisAbiertos.get(l.ParentesisAbiertos.size() - 1).Xpos;
        } else {
            l.pivot_x = l.context.lista_simbolos.get(0).Xpos - l.espacioEntreSimbolos;
        }
    }

    protected void moverPivotADenominador(LogicaBinaria l) {

        if (!l.ParentesisAbiertos.isEmpty()) {
            l.pivot_x = l.ParentesisAbiertos.get(l.ParentesisAbiertos.size() - 1).Xpos;
        } else {
            l.pivot_x = l.lista_simbolos.get(0).Xpos - l.espacioEntreSimbolos;
        }
    }

    protected void moverPivotIzquierda(Logica l, double distancia) {
        l.pivot_x = l.pivot_x - distancia;
    }

    protected Simbolo conseguirUltimoSimbolo(ArrayList<Simbolo> lista_simbolos) {

        if (!lista_simbolos.isEmpty()) {
            return lista_simbolos.get(lista_simbolos.size() - 1);
        } else {
            return null;
        }
    }

    protected int buscarNumero(ArrayList<Simbolo> lista_simbolos, int index) {
        String numero = "";
        for (int i = index; i < lista_simbolos.size(); i++) {
            if (lista_simbolos.get(i).tipo != 0) {
                break;
            } else {
                numero = numero + lista_simbolos.get(i).getValorString();
            }
        }
        return Integer.parseInt(numero);

    }

    //Recorre el string del numero binario convertido y los imprime en el canvas secundario
    protected void numerosBinariosACanvas(Logica l, String nBinario, GraphicsContext gc, ArrayList<Simbolo> lista_simbolos, Canvas DisplayBin) {

        for (int i = 0; i < nBinario.length(); i++) {
            //System.out.println("->"+nBinario.charAt(i));
            l.logicBin.agregarSimbolo(gc, Integer.parseInt(Character.toString(nBinario.charAt(i))), lista_simbolos, DisplayBin);
        }

    }

    protected void agregarSimboloBin(Logica l, GraphicsContext gc, ArrayList<Simbolo> lista_simbolos, Canvas DisplayBin, int valor) {
        l.logicBin.agregarSimbolo(gc, valor, lista_simbolos, DisplayBin);
    }

    protected void posicionarParentesisDeCierre(Logica l, Simbolo s) {
        int contador = 1;
        double coordenadaXmasLejana = l.pivot_x;

        for (int i = l.context.lista_simbolos.size() - 1; i >= 0; i--) {
            if (l.context.lista_simbolos.get(i).valor == 18) {
                contador++;
            }
            if (l.context.lista_simbolos.get(i).valor == 17) {
                contador--;
            }
            if (contador == 0) {
                break;
            } else {
                if (l.context.lista_simbolos.get(i).Xpos > coordenadaXmasLejana) {
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

        for (int i = l.lista_simbolos.size() - 1; i >= 0; i--) {
            if (l.lista_simbolos.get(i).valor == 18) {
                contador++;
            }
            if (l.lista_simbolos.get(i).valor == 17) {
                contador--;
            }
            if (contador == 0) {
                break;
            } else {
                if (l.lista_simbolos.get(i).Xpos > coordenadaXmasLejana) {
                    coordenadaXmasLejana = l.lista_simbolos.get(i).Xpos;
                }
            }

        }

        s.Xpos = coordenadaXmasLejana;
        l.pivot_x = coordenadaXmasLejana;

    }

    protected void calcularResultado(Logica l) {
        ArrayList<Simbolo> lista_simbolosHomogenea = new ArrayList();
        ArrayList<Simbolo> lista_simbolos = l.context.lista_simbolos;

    }

    protected void printAllSymbols(ArrayList<Simbolo> lista) {
        for (int i = 0; i < lista.size(); i++) {

            System.out.print(lista.get(i).toString());
            System.out.print("|");
        }
    }

    protected ArrayList<Simbolo> parsingLista(ArrayList<Simbolo> lista_simbolos) {
        ArrayList<Simbolo> parseo = new ArrayList();

        int numero;

        //Falta identificar los numeros negativos.
        for (int i = 0; i < lista_simbolos.size(); i++) {
            if (lista_simbolos.get(i).tipo != 0) {

                parseo.add(lista_simbolos.get(i));
            } else {//Buscando mas numeros par agregar al numero total
                numero = buscarNumero(lista_simbolos, i);
                i = i + String.valueOf(numero).length() - 1;
                Simbolo s = new Simbolo();
                s.valor = numero;
                parseo.add(s);
            }

        }

        for (int k = 0; k < parseo.size(); k++) {
            System.out.println(parseo.get(k));
        }
        return parseo;
    }

    protected void calcular(ArrayList<Simbolo> cadena) {

        
        ArrayList<Double> doubles = new ArrayList();

        for (int i = 0; i < cadena.size(); i++) {
            doubles.add((double) cadena.get(i).valor);
        }

        for (int i = 0; i < doubles.size(); i++) {
            System.out.println(doubles.get(i));
            if (doubles.get(i) == (double)10) { //Suma
                System.out.println("Suma detectada");
                double anterior = doubles.get(i-1);
                double anteAnterior = doubles.get(i-2);
                double res = anterior + anteAnterior;
                
                doubles.remove(i-1);
                doubles.remove(i-1);
                doubles.add(i-1, res);
                i = i -2;
                
            }

        }
        
        System.out.println("Contenido de Double:");
        for(int i = 0;i< doubles.size();i++){
            System.out.println(doubles.get(i));
        }

        /*
        for(int i = 0;i<cadena.size();i++){
        
        System.out.println(cadena.get(i).valor);
        }
        
        for(int i = 0; i < cadena.size() ; i++){
            if(cadena.get(i).tipo == 0){
                pila.add(cadena.get(i));
            }else{
                switch (cadena.get(i).valor) {
                    case -1:
                        {
                            //elevado
                            Simbolo anterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo AnteAnterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo res = new Simbolo();
                            System.out.println("Elevando "+AnteAnterior.valor
                            +" a "+anterior.valor);
                            res.resultado = Math.pow(AnteAnterior.valor, anterior.valor);
                            pila.push(res);
                            break;
                        }
                    case 10:
                        {
                            //suma
                            Simbolo anterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo AnteAnterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo res = new Simbolo();
                            System.out.println("Sumando "+AnteAnterior.valor
                            +" + "+anterior.valor);
                            res.resultado = (AnteAnterior.valor + anterior.valor);
                            pila.push(res);
                            break;
                        }
                    case 11:
                        {
                            //resta
                            Simbolo anterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo AnteAnterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo res = new Simbolo();
                            System.out.println("Restando "+AnteAnterior.valor
                            +" - "+anterior.valor);
                            res.resultado = (AnteAnterior.valor - anterior.valor);
                            pila.push(res);
                            break;
                        }
                    case 12:
                        {
                            //multiplicacion
                            Simbolo anterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo AnteAnterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo res = new Simbolo();
                            System.out.println("Multiplicando "+AnteAnterior.valor
                            +" x "+anterior.valor);
                            res.resultado = (AnteAnterior.valor * anterior.valor);
                            pila.push(res);
                            break;
                        }
                    case 13:
                        {
                            //division
                            Simbolo anterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo AnteAnterior = (Simbolo)pila.peek();
                            pila.pop();
                            Simbolo res = new Simbolo();
                            System.out.println("DIviendo "+AnteAnterior.valor
                            +" /  "+anterior.valor);
                            res.resultado = ((double)AnteAnterior.valor / (double)anterior.valor);
                            pila.push(res);
                            break;
                        }
                    default:
                        break;
                }
            }
        }
        
        System.out.println("Almacenamiento en pila: ");
        for(int i = 0;i<pila.size();i++){
        
        System.out.println(pila.get(i));
        }
        
        System.out.println("Resultado: ");
        
        Simbolo re = (Simbolo) pila.peek();
         */
    }

    protected ArrayList<Simbolo> getPrecedence(ArrayList<Simbolo> lista_simbolos) {
        ArrayList<Simbolo> c = parsingLista(lista_simbolos);

        ArrayList<Simbolo> cola = new ArrayList();
        ArrayList<Simbolo> pilaOperadores = new ArrayList();

        for (int i = 0; i < c.size(); i++) {
            if (c.get(i).tipo == 0) { //Si es un numero
                System.out.println("Añadiendo:" + c.get(i));
                cola.add(c.get(i));
            } else { //Si es operador
                System.out.println("Indice:" + i);
                if (c.get(i).valor == 18) {
                    System.out.println("Indice:" + i);//Si es parentesis de cierre
                    System.out.println("Parentesis ) encontrado");
                    for (int j = pilaOperadores.size() - 1; j >= 0; j--) {
                        System.out.println("Evaluando:" + pilaOperadores.get(j));

                        if (pilaOperadores.get(j).valor == 17) { // Si es parentesis de apertura
                            System.out.println("Parentesis ( encontrado, eliminando");
                            pilaOperadores.remove(j);
                            j = -1;
                        } else {
                            System.out.println("Transfiriendo:" + pilaOperadores.get(j));
                            cola.add(pilaOperadores.get(j));
                            pilaOperadores.remove(j);
                        }
                    }

                } else {
                    System.out.println("Operador:" + c.get(i).valor);
                    if (!pilaOperadores.isEmpty()) {
                        if (c.get(i).valorPrecedencia < pilaOperadores.get(pilaOperadores.size() - 1).valorPrecedencia && c.get(i).valor != 17) {
                            cola.add(pilaOperadores.get(pilaOperadores.size() - 1));
                            pilaOperadores.remove(pilaOperadores.size() - 1);
                            pilaOperadores.add(c.get(i));
                        } else {
                            pilaOperadores.add(c.get(i));
                        }
                    } else {
                        pilaOperadores.add(c.get(i));
                    }
                }
            }
        }

        /*
        if(!pilaOperadores.isEmpty()){
            for (int i = pilaOperadores.size()-1; i >=0 ; i--) {
            cola.add(pilaOperadores.get(i));
        }
           
         */
        //   (1-2)^4*(4*(5/((5-3)^2)))
        calcular(cola);
        //System.out.println(calcular(cola));

        return cola;
    }

}
