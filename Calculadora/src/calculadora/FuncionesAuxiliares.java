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

    protected double getAlturaSimbolo(Logica l) {

        return l.pivot_y = conseguirUltimoSimbolo(l.context.lista_simbolos).getAlturaSimbolo() + 40;

    }

    protected double getAlturaSimbolo(LogicaBinaria l) {

        return l.pivot_y = conseguirUltimoSimbolo(l.lista_simbolos).getAlturaSimbolo() + 40;

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

    protected void printAllSymbols(ArrayList<Simbolo> lista) {
        for (int i = 0; i < lista.size(); i++) {

            System.out.print(lista.get(i).toString());
            System.out.print("|");
        }
    }

    protected int buscarNumero(ArrayList<Simbolo> lista_simbolos, int index) {
        String numero = "";
        if (index == 1) {
            if (lista_simbolos.get(index - 1).valor == 11) {
                numero = numero + "-";
            }
        } else if (index > 1) {
            if (lista_simbolos.get(index - 1).valor == 11 && (lista_simbolos.get(index - 2).tipo != 0 && lista_simbolos.get(index - 2).valor != 18)) {
                numero = numero + "-";
            }
        }
        for (int i = index; i < lista_simbolos.size(); i++) {
            if (lista_simbolos.get(i).tipo != 0) {
                break;
            } else {
                numero = numero + lista_simbolos.get(i).getValorString();
            }
        }
        return Integer.parseInt(numero);

    }

    protected ArrayList<Simbolo> parsingLista(ArrayList<Simbolo> lista_simbolos) {
        ArrayList<Simbolo> parseo = new ArrayList();

        int numero;

        //Falta identificar los numeros negativos.
        for (int i = 0; i < lista_simbolos.size(); i++) {
            if (lista_simbolos.get(i).tipo != 0) { //Si hay un operador se agrega directo
                parseo.add(lista_simbolos.get(i));
            } else {//Buscando mas numeros par agregar al numero total
                numero = buscarNumero(lista_simbolos, i);
                if (numero < 0) {
                    parseo.remove(parseo.size() - 1);
                    i = i + String.valueOf(numero).length() - 2;
                } else {
                    i = i + String.valueOf(numero).length() - 1;
                }
                Simbolo s = new Simbolo();
                s.valor = numero;
                s.resultado = numero;
                parseo.add(s);
            }

        }

        for (int k = 0; k < parseo.size(); k++) {
            System.out.println(parseo.get(k));
        }
        return parseo;
    }

    protected void imprimirLista(ArrayList<Simbolo> lista) {
        System.out.println("");
        for (int i = 0; i < lista.size(); i++) {
            Simbolo s = lista.get(i);

            if (s.tipo != 0) {
                if (s.valor == -1) {
                    System.out.print("^");
                }
                if (s.valor == 10) {
                    System.out.print("+");
                }
                if (s.valor == 11) {
                    System.out.print("-");
                }
                if (s.valor == 12) {
                    System.out.print("*");
                }
                if (s.valor == 13) {
                    System.out.print("/");
                }
                if (s.valor == 14) {
                    System.out.print("sin");
                }
                if (s.valor == 15) {
                    System.out.print("cos");
                }
                if (s.valor == 16) {
                    System.out.print("tan");
                }
                if (s.valor == 17) {
                    System.out.print("(");
                }
                if (s.valor == 18) {
                    System.out.print(")");
                }
                if (s.valor == 19) {
                    System.out.print("!");
                }
                if (s.valor == 20) {
                    System.out.print("??");
                }

            } else {
                System.out.print(" " + s.resultado);
            }
        }
    }

    protected int factorial(int i) {
        if (i <= 2) {
            return i;
        } else {
            return i * factorial(i - 1);
        }
    }

    protected void subCalcular(ArrayList<Simbolo> enEspera, int op) {
        Simbolo anterior = enEspera.get(enEspera.size() - 1);
        Simbolo anteAnterior = new Simbolo();
        if (enEspera.size() > 1) {
            anteAnterior = enEspera.get(enEspera.size() - 2);
        }
        Simbolo res = new Simbolo();
        switch (op) {
            case -4:
                //Negaci??n
                //System.out.print("-");
                res.resultado = anterior.resultado * -1;
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            case -1:
                //System.out.print("^");
                res.resultado = Math.pow(anteAnterior.resultado, anterior.resultado);
                enEspera.remove(enEspera.size() - 1);
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            case 10:
                //System.out.print("+");
                res.resultado = anteAnterior.resultado + anterior.resultado;
                enEspera.remove(enEspera.size() - 1);
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            case 11:
                //System.out.print("-");
                res.resultado = anteAnterior.resultado - anterior.resultado;
                enEspera.remove(enEspera.size() - 1);
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            case 12:
                //System.out.print("*");
                res.resultado = anteAnterior.resultado * anterior.resultado;
                enEspera.remove(enEspera.size() - 1);
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            case 13:
                //System.out.print("/");
                res.resultado = anteAnterior.resultado / anterior.resultado;
                enEspera.remove(enEspera.size() - 1);
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            case 14:
                //Seno
                //System.out.print("Sin");
                res.resultado = Math.sin(anterior.resultado);
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            case 15:
                //Coseno
                // System.out.print("Cos");
                res.resultado = Math.cos(anterior.resultado);
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            case 16:
                //Tan
                //System.out.print("Tan");
                res.resultado = Math.tan(anterior.resultado);
                enEspera.remove(enEspera.size() - 1);
                enEspera.add(res);
                break;
            default:
                break;
        }

        if (op == 19) { //Factorial
            //System.out.print("!");
            res.resultado = factorial((int) anterior.resultado);
            enEspera.remove(enEspera.size() - 1);
            enEspera.add(res);
        }

        if (op == 20) { // Grados
            System.out.print("??");
            res.resultado = Math.toRadians(anterior.resultado);
            enEspera.remove(enEspera.size() - 1);
            enEspera.add(res);
        }
        if (op == 21) { // Raiz
            System.out.print("raiz");
            res.resultado = Math.sqrt(anterior.resultado);
            enEspera.remove(enEspera.size() - 1);
            enEspera.add(res);
        }

        System.out.println("Subresultado: " + enEspera.get(enEspera.size() - 1).resultado);

    }

    protected double calcular(ArrayList<Simbolo> cadena) {

        //imprimirLista(cadena);
        //La idea es leer de un arreglo
        //Insertar a otro arreglo si es numero
        //Si encuentra un operador, usar los ultimos 2 numeros almacenados, eliminarlos y dejar el resultado.
        ArrayList<Simbolo> enEspera = new ArrayList(); //El otro arreglo

        for (int i = 0; i < cadena.size(); i++) {
            if (cadena.get(i).tipo == 0) { //Si encuentra un numero
                enEspera.add(cadena.get(i));
            } else {
                if (cadena.get(i).valor == -4) {
                    //System.out.print("^");
                    subCalcular(enEspera, -4);
                }
                if (cadena.get(i).valor == -1) {
                    //System.out.print("^");
                    subCalcular(enEspera, -1);
                }
                if (cadena.get(i).valor == 10) { //Suma
                    //System.out.print("+");
                    subCalcular(enEspera, 10);
                }
                if (cadena.get(i).valor == 11) { //Resta
                    //System.out.print("-");
                    subCalcular(enEspera, 11);
                }
                if (cadena.get(i).valor == 12) { //Multiplicaci??n
                    //System.out.print("*");
                    subCalcular(enEspera, 12);
                }
                if (cadena.get(i).valor == 13) { //Division
                    //System.out.print("/");
                    subCalcular(enEspera, 13);
                }
                if (cadena.get(i).valor == 14) { //Seno
                    //System.out.print("Sin");
                    subCalcular(enEspera, 14);
                }
                if (cadena.get(i).valor == 15) { //Coseno
                    //System.out.print("Cos");
                    subCalcular(enEspera, 15);
                }
                if (cadena.get(i).valor == 16) { //Tangente
                    //System.out.print("Tan");
                    subCalcular(enEspera, 16);
                }
                if (cadena.get(i).valor == 19) { //Factorial
                    //System.out.print("!");
                    subCalcular(enEspera, 19);
                }
                if (cadena.get(i).valor == 20) { //Grado
                    //System.out.print("??");
                    subCalcular(enEspera, 20);
                }
                if (cadena.get(i).valor == 21) { //Raiz
                    //System.out.print("??");
                    subCalcular(enEspera, 21);
                }

            }

        }

        //System.out.println(enEspera.get(0).resultado);
        return enEspera.get(0).resultado;

    }

    protected void getPrecedence(ArrayList<Simbolo> lista_simbolos, Logica l) {
        ArrayList<Simbolo> c = parsingLista(lista_simbolos);
        System.out.println("Lista Parseada:" + c);
        l.resetEstado();

        ArrayList<Simbolo> cola = new ArrayList();
        ArrayList<Simbolo> pilaOperadores = new ArrayList();

        for (int i = 0; i < c.size(); i++) {
            System.out.println("INICIO FOR-------------------------------");
            if (c.get(i).tipo == 0) { //Si es un numero
                System.out.println("A??adiendo:" + c.get(i));
                cola.add(c.get(i));
            } else { //Si es operador
                System.out.println("Indice:" + i + " Simbolo: " + c.get(i));
                if (c.get(i).valor == 18) {
                    //System.out.println("Indice:" + i);//Si es parentesis de cierre
                    System.out.println("Parentesis ) encontrado");
                    for (int j = pilaOperadores.size() - 1; j >= 0; j--) {
                        System.out.println("Evaluando:" + pilaOperadores.get(j));
                        if (pilaOperadores.get(j).valor == 17) { // Si es parentesis de apertura
                            System.out.println("Parentesis ( encontrado, eliminando");
                            pilaOperadores.remove(j);
                            j = -1; // Para romper ciclo
                        } else {
                            System.out.println("Transfiriendo:" + pilaOperadores.get(j));
                            cola.add(pilaOperadores.get(j));
                            pilaOperadores.remove(j);
                        }
                    }

                } else {
                    System.out.println("Indice:" + i + " Simbolo: " + c.get(i));

                    if (c.get(i).valor != 17) { // Si no es un parentesis de apertura
                        if (!pilaOperadores.isEmpty()) { //Si la pila no est?? vacia
                            int n = pilaOperadores.size() - 1;
                            while ((c.get(i).valorPrecedencia < pilaOperadores.get(n).valorPrecedencia
                                || (c.get(i).valorPrecedencia == pilaOperadores.get(n).valorPrecedencia
                                && c.get(i).asociatividad == 0 && pilaOperadores.get(n).asociatividad == 0))) {
                                cola.add(pilaOperadores.get(n));
                                pilaOperadores.remove(n);
                                n = n - 1;
                                if (n == -1) {
                                    break;
                                }
                            }

                        }

                    }

                    if (c.get(i).valor == 11) { //Identificando los negadores 
                        if (i == 0 && c.size() > 1) {
                            if (c.get(i + 1).valor == 17) { //Si el siguiente simbolo es un parentesis
                                c.get(i).valor = -4;
                                c.get(i).valorPrecedencia = 10;
                                c.get(i).asociatividad = 1;

                            }
                        } else if (i > 0 && c.size() > 2) { //Si el simbolo anterior es un operador y el siguiente es un parentesis
                            if (c.get(i - 1).tipo != 0 && c.get(i + 1).valor == 17) {
                                c.get(i).valor = -4;
                                c.get(i).valorPrecedencia = 10;
                                c.get(i).asociatividad = 1;
                            }
                        }
                    }
                    System.out.println("pilaOperadores antes de agregar"+pilaOperadores);
                    pilaOperadores.add(c.get(i));
                    System.out.println("pilaOperadores despues de agregar"+pilaOperadores);

                }
            }
            System.out.println("FIN FOR-------------------------------");
        }

        // /*
        if (!pilaOperadores.isEmpty()) {
            for (int i = pilaOperadores.size() - 1; i >= 0; i--) {
                cola.add(pilaOperadores.get(i));
            }
        }

        System.out.print(cola);

        // */
        //   (1-2)^4*(4*(5/((5-3)^2)))
        dibujarResultado(calcular(cola), l);
        l.context.precedencia.setText(cola.toString());
        //System.out.println(calcular(cola));

    }

    protected void dibujarResultado(double res, Logica l) {
        String resultadoRed = Double.toString(Math.round(res * 1000000.0) / 1000000.0);

        System.out.println("Raw res:" + res);
        l.context.lista_simbolos.clear(); //Borrando la lista principal
        System.out.println("Rounded res:" + resultadoRed);

        if (res != Double.POSITIVE_INFINITY) {
            String iterado;

            //Del resultado, parsearlo, verificar el char y en base a eso "apretar botones" de la calculadora
            for (int i = 0; i < resultadoRed.length(); i++) {
                iterado = Character.toString(resultadoRed.charAt(i));
                if (".".equals(iterado)) {
                    l.agregarSimbolo(l.context.gc, -3, l.context.lista_simbolos, l.context.Display);
                } else if ("-".equals(iterado)) {
                    l.agregarSimbolo(l.context.gc, 11, l.context.lista_simbolos, l.context.Display);
                } else if ("E".equals(iterado)) {
                    l.agregarSimbolo(l.context.gc, -1, l.context.lista_simbolos, l.context.Display);
                } else if (Integer.valueOf(iterado) >= 0 && Integer.valueOf(iterado) <= 9) {
                    l.agregarSimbolo(l.context.gc, Integer.valueOf(iterado), l.context.lista_simbolos, l.context.Display);
                }

            }

            /* Version con display de elevados
            String resString = Double.toString(res);

            //Del resultado, parsearlo, verificar el char y en base a eso "apretar botones" de la calculadora
            for (int i = 0; i < resString.length(); i++) {
                iterado = Character.toString(resString.charAt(i));
                if (".".equals(iterado)) {
                    l.agregarSimbolo(l.context.gc, -3, l.context.lista_simbolos, l.context.Display);
                } else if ("-".equals(iterado)) {
                    l.agregarSimbolo(l.context.gc, 11, l.context.lista_simbolos, l.context.Display);
                } else if ("E".equals(iterado)) {
                    l.agregarSimbolo(l.context.gc, -1, l.context.lista_simbolos, l.context.Display);
                    l.enPotencia = true;
                    for(int j  =i+1;j<resString.length();j++){
                        l.agregarSimbolo(l.context.gc, Integer.valueOf(Character.toString(resString.charAt(j))), l.context.lista_simbolos, l.context.Display);
                    }
                    break;
                } else if (Integer.valueOf(iterado) >= 0 && Integer.valueOf(iterado) <= 9) {
                    l.agregarSimbolo(l.context.gc, Integer.valueOf(iterado), l.context.lista_simbolos, l.context.Display);
                }
             */
        } else { //Si encuentra que el resultado es infinito, devuelve un -1
            l.agregarSimbolo(l.context.gc, 11, l.context.lista_simbolos, l.context.Display);
            l.agregarSimbolo(l.context.gc, 1, l.context.lista_simbolos, l.context.Display);
        }

        //l.canvasABinario();
        l.dibujarSimbolos();

    }

    protected void sanearLista(ArrayList<Simbolo> lista_simbolos) {
        lista_simbolos.removeIf(s -> (s.tipo == -1 && s.valor < -1));
    }

    protected ArrayList<Simbolo> agregarParentesis(ArrayList<Simbolo> lista_simbolos) {
        Simbolo pAbierto = new Simbolo();
        pAbierto.valor = 17;
        pAbierto.tipo = 2;

        Simbolo pCerrado = new Simbolo();
        pCerrado.valor = 18;
        pCerrado.tipo = 2;

        ArrayList<Simbolo> lista = new ArrayList<>(lista_simbolos);

        System.out.println("Lista:" + lista);

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Indice en "+i);
            if (lista.get(i).valor == 13) { //Si en encuentra una division
                lista.add(i, pCerrado); //A??ade un parentesis de cierre a la izquierda del indice
                System.out.println("Agregado parentesis cerrado en indice:" + i);
                System.out.println(lista);
                int contador = 0;
                //Busqueda de la posicion para el parentesis de apertura
                for (int j = i; j >= 0; j--) {
                    if (lista.get(j).valor == 17) { //parentesis de apertura
                        contador--;
                        if (contador == 0) { //Si hay desbalanceo de parentesis
                            lista.add(j, pAbierto);
                            System.out.println("Agregado parentesis abierto en indice:" + j);
                            System.out.println(lista);
                            break;
                        }
                    } else if (lista.get(j).valor == 18) {
                        contador++;
                    }

                    if (j == 0) { //Si se llega al principio de la lista sin encontrar parentesis de apertura
                        lista.add(j, pAbierto);
                        System.out.println("Agregado parentesis abierto en indice:" + j);
                        System.out.println(lista);
                    }
                }
                lista.add(i + 3, pAbierto);
                System.out.println("Agregado parentesis abierto en indice:" + (i + 3));
                System.out.println(lista);
                contador = 0;
                System.out.println("Evaluando para ingresar parentesis de cierre desde el indice:"+(i+3));
                for (int k = i + 3; k < lista.size(); k++) {
                    if (lista.get(k).valor == 17) { //Parentesis de apertura
                        contador++;
                    } else if (lista.get(k).valor == 18) { //Parentesis de cierre
                        contador--;

                    }
                    if (contador == 1 && lista.get(k).valor == 13) { //Si hay desbalanceo de parentesis (Se llega al inicio de una division)
                            lista.add(k, pCerrado);
                            System.out.println("Agregado parentesis cerrado en indice:" + k);
                            System.out.println(lista);
                            break;
                        }
                    
                    if (k == lista.size() - 1) {
                        lista.add(pCerrado);
                        System.out.println("Agregado parentesis cerrado en indice (fin lista):" + (k-1));
                        System.out.println(lista);
                        break;

                    }

                }
                System.out.println("Indice avanza a:"+(i + 2));
                System.out.println(lista);
                i = i + 2;

            }
        }
        return lista;
    }

}
