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
 * @author guillermo
 */
public class Logica {

    double pivot_x = 50;
    double pivot_y = 300;

    boolean binarioActivo = false;

    double espacioEntreSimbolos = 15;
    CoordenadasSimbolos cs = new CoordenadasSimbolos();

    double movimientosDeLista = 0;
    //Para la activacion de los puntos de control
    int puntosControlActivo = 0;

    //variable para el tamano 
    double factor = 1;
    //Variables para la division
    boolean enDivision;
    int divisionesAgregadas = 0;
    Simbolo divisor;
    double diferenciaNumeradorDenominador = 0;
    int subidasDivision;
    int contadorReset = 0;

    //Variables para logica parentesis
    Simbolo ultimoParentesisCerrado;
    Simbolo divisionPrincipal;
    Simbolo simboloMasApartado = new Simbolo();
    Simbolo alturaAntesDeDivision = new Simbolo();

    boolean enPotencia = false;

    division d = new division();
    int panelAgregado = 0;

    //Variables para los parentesis
    ArrayList<Simbolo> ParentesisAbiertos = new ArrayList();
    boolean parentesisAgregadoANumerador = false;
    int alturaParentesis = 0;
    boolean bloqueoDivision = false;

    //Variables para potencias
    ArrayList<Simbolo> parentesisEnPotencia = new ArrayList();
    boolean Check = false;
    double pivot_yPrePotencia;
    boolean agregarParentesis =false;
    
    //Variables para operadores
    int contadorNegativos;

    //Clases
    FuncionesGraficadoras fg = new FuncionesGraficadoras();
    InterfazController context;
    FuncionesAuxiliares fa = new FuncionesAuxiliares();
    LogicaBinaria logicBin = new LogicaBinaria(context);

    public Logica(InterfazController context) {
        this.context = context;
    }

    protected void agregarSimbolo(GraphicsContext gc, int nSimbolo,
        ArrayList<Simbolo> lista_simbolos,
        Canvas Display) {
        //updateTags();
        logicBin.context = context;
        logicBin.factor = factor;
        
        if(nSimbolo == 11){
            contadorNegativos++;
        }else {
            contadorNegativos = 0;
        }

        if (context.lista_simbolos.isEmpty()) {
            simboloMasApartado.Ypos = context.pivot_y;
            simboloMasApartado.Xpos = context.pivot_x;
        }

        if (pivot_x > simboloMasApartado.Xpos) {
            simboloMasApartado.Xpos = pivot_x;
        }

        //System.out.println("Simbolo mas apartado: " + simboloMasApartado.Xpos);
        //System.out.println("En division:" + enDivision);
        //Iniciaci??n y declaraci??n de un simbolo general
        Simbolo s = new Simbolo();
        s.setXpos(pivot_x);
        s.setYpos(pivot_y);
        s.Xfactor = factor;
        s.Yfactor = factor;

        //Iniciaci??n de una forma general
        double[] forma;

        if (Check) {
            checkPotencias(nSimbolo);
        }

        switch (nSimbolo) {
            case -3: //punto
                if (enPotencia) {
                    forma = cs.puntoPot(pivot_x, pivot_y);
                } else {
                    forma = cs.punto(pivot_x, pivot_y);
                }
                s.setForma(forma);
                s.setValor(-3);
                s.setColor(context.colorNum);
                s.setTipo(-2);
                lista_simbolos.add(s);
                break;
            case -1: //Apertura de potencias
                s.setTipo(-1);
                s.setValor(-1);
                s.setColor(Color.rgb(0, 0, 0, 0));
                s.valorPrecedencia = 9; //Mayor precedencia
                s.asociatividad = 1;
                forma = cs.ceroPot(pivot_x, pivot_y);
                s.setForma(forma);
                d.borrarSimbolosDeNumeradoresParaPotencia(this);
                pivot_x = pivot_x - 10; //Para que el siguiente simbolo este mas cerca del ultimo agregado.
                lista_simbolos.add(s);

                if (context.lista_simbolos.get(context.lista_simbolos.size() - 2).valor != 18) {
                    System.out.println("No hay parentesis antes de");
                    pivot_yPrePotencia = pivot_y;
                } else {
                    System.out.println("Hay parentesis antes de");
                    for (int k = context.lista_simbolos.size() - 3; k >= 0; k--) {
                        System.out.println("^Simbolo: " + context.lista_simbolos.get(k));
                        if (context.lista_simbolos.get(k).tipo == 0) {
                            pivot_yPrePotencia = context.lista_simbolos.get(k).Ypos;
                            break;
                        }
                    }

                }
                Check = true;
                //fa.moverPivotIzquierda(this, espacioEntreSimbolos);
                break;
            case 0:
                if (enPotencia) {
                    forma = cs.ceroPot(pivot_x, pivot_y);
                } else {
                    forma = cs.cero(pivot_x, pivot_y);
                }
                s.setForma(forma);
                s.setValor(0);
                s.setColor(context.colorNum);
                s.setTipo(0);
                lista_simbolos.add(s);
                break;
            case 1:
                if (enPotencia) {
                    forma = cs.unoPot(pivot_x, pivot_y);
                } else {
                    forma = cs.uno(pivot_x, pivot_y);
                }
                s.setValor(1);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 2:
                if (enPotencia) {
                    forma = cs.dosPot(pivot_x, pivot_y);
                } else {
                    forma = cs.dos(pivot_x, pivot_y);
                }
                s.setValor(2);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 3:
                if (enPotencia) {
                    forma = cs.tresPot(pivot_x, pivot_y);
                } else {
                    forma = cs.tres(pivot_x, pivot_y);
                }
                s.setValor(3);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 4:
                if (enPotencia) {
                    forma = cs.cuatroPot(pivot_x, pivot_y);
                } else {
                    forma = cs.cuatro(pivot_x, pivot_y);
                }
                s.setValor(4);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 5:
                if (enPotencia) {
                    forma = cs.cincoPot(pivot_x, pivot_y);
                } else {
                    forma = cs.cinco(pivot_x, pivot_y);
                }
                s.setValor(5);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 6:
                if (enPotencia) {
                    forma = cs.seisPot(pivot_x, pivot_y);
                } else {
                    forma = cs.seis(pivot_x, pivot_y);
                }
                s.setValor(6);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 7:
                if (enPotencia) {
                    forma = cs.sietePot(pivot_x, pivot_y);
                } else {
                    forma = cs.siete(pivot_x, pivot_y);
                }
                s.setValor(7);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 8:
                if (enPotencia) {
                    forma = cs.ochoPot(pivot_x, pivot_y);
                } else {
                    forma = cs.ocho(pivot_x, pivot_y);
                }
                s.setValor(8);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 9: // 9
                if (enPotencia) {
                    forma = cs.nuevePot(pivot_x, pivot_y);
                } else {
                    forma = cs.nueve(pivot_x, pivot_y);
                }
                s.setValor(9);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 10: //Suma
                if (enPotencia) {
                    forma = cs.masPot(pivot_x, pivot_y);
                } else {
                    forma = cs.mas(pivot_x, pivot_y);
                }
                s.valorPrecedencia = 1;
                s.setValor(10);
                s.setTipo(1);
                s.setColor(context.colorOp);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 11: //Resta
                if (enPotencia) {
                    forma = cs.menosPot(pivot_x, pivot_y);
                } else {
                    forma = cs.menos(pivot_x, pivot_y);
                }
                s.valorPrecedencia = 1;
                s.setValor(11);
                s.setTipo(1);
                s.setColor(context.colorOp);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 12: //Multiplicacion
                if (enPotencia) {
                    forma = cs.multiplicarPot(pivot_x, pivot_y);
                } else {
                    forma = cs.multiplicar(pivot_x, pivot_y);
                }
                s.valorPrecedencia = 2;
                s.setValor(12);
                s.setTipo(1);
                s.setColor(context.colorOp);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 13: //division

                forma = cs.dividir(pivot_x, pivot_y);
                s.setValor(13);
                s.setTipo(1);
                s.valorPrecedencia = 2;
                s.setColor(context.colorOp);
                s.setForma(forma);
                d.nuevaDivision(this);
                d.crearLineaDivision(this, s);
                divisor = s;
                if (!enDivision) {
                    divisionPrincipal = s;
                }
                enDivision = true;
                divisionesAgregadas++;
                d.modLineaDivision = true;
                d.Numeradores.add(s);
                dimensionarParentesisAbiertos(gc);
                lista_simbolos.add(s);
                break;
            case 14: //Seno
                s.setValor(14);
                s.setTipo(2);
                s.valorPrecedencia = 11;
                s.setColor(context.colorOp);
                formaOperadorCientifico(this, 14, pivot_x, pivot_y, s);
                lista_simbolos.add(s);
                fa.moverPivotDerechaPotencia(this);
                fa.moverPivotDerechaPotencia(this);
                break;
            case 15: //Coseno
                s.setValor(15);
                s.setTipo(2);
                s.valorPrecedencia = 11;
                s.setColor(context.colorOp);
                formaOperadorCientifico(this, 15, pivot_x, pivot_y, s);
                lista_simbolos.add(s);
                fa.moverPivotDerechaPotencia(this);
                fa.moverPivotDerechaPotencia(this);
                break;
            case 16: //Tangente
                s.setValor(16);
                s.setTipo(2);
                s.valorPrecedencia = 11;
                s.setColor(context.colorOp);
                formaOperadorCientifico(this, 16, pivot_x, pivot_y, s);
                lista_simbolos.add(s);
                fa.moverPivotDerechaPotencia(this);
                fa.moverPivotDerechaPotencia(this);
                break;
            case 17: //Parentesis Abierto
                if (enPotencia) {
                    forma = cs.pAbiertoPot(pivot_x, pivot_y);
                    parentesisEnPotencia.add(new Simbolo());
                } else {
                    forma = cs.pAbierto(pivot_x, pivot_y);
                }
                bloqueoDivision = false;
                s.valorPrecedencia = 0;
                s.setValor(17);
                s.setTipo(2);
                s.setColor(context.colorOp);
                //System.out.println("xPos del parentesis abierto: " + s.Xpos);
                s.setForma(forma);
                lista_simbolos.add(s);
                ParentesisAbiertos.add(s);
                break;
            case 18: //Parentesis Cerrado
                s.setValor(18);
                s.setTipo(2);
                s.setColor(context.colorOp);
                s.enlace = ParentesisAbiertos.get(ParentesisAbiertos.size() - 1);
                fa.posicionarParentesisDeCierre(this, s);
                
                if (enPotencia) {
                    forma = cs.pCerradoPot(pivot_x, pivot_y);
                    if (!parentesisEnPotencia.isEmpty()) {
                        parentesisEnPotencia.remove(parentesisEnPotencia.size() - 1);
                    }
                } else {
                    forma = cs.pCerrado(pivot_x, pivot_y);
                }
                s.setForma(forma);
                s.setAlturaParentesis(ParentesisAbiertos.get(ParentesisAbiertos.size() - 1).getAlturaParentesis());

                lista_simbolos.add(s);
                ultimoParentesisCerrado = s;
                d.borrarSimbolosDeNumeradoresParaPotencia(this);

                if (ParentesisAbiertos.get(ParentesisAbiertos.size() - 1) == d.parentesisDeDivisionActivo) {
                    d.modLineaDivision = false;

                }
                ParentesisAbiertos.remove(ParentesisAbiertos.size() - 1); //Elimina el ultimo parentesis abierto

                if (ParentesisAbiertos.isEmpty()) {
                    d.Numeradores.clear();
                    enDivision = false;
                }
                break;
            case 19: //Factorial
                if (enPotencia) {
                    forma = cs.factorialPot(pivot_x, pivot_y);
                } else {
                    forma = cs.factorial(pivot_x, pivot_y);
                }
                s.setValor(19);
                s.valorPrecedencia = 2;
                s.asociatividad = 1;
                s.setTipo(2);
                s.setColor(context.colorOp);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 20: //grado
                if (enPotencia) {
                    forma = cs.gradoPot(pivot_x, pivot_y);
                } else {
                    forma = cs.grado(pivot_x, pivot_y);
                }
                s.valorPrecedencia = 3;
                s.asociatividad = 1;
                s.setValor(20);
                s.setTipo(2);
                s.setColor(context.colorOp);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
            case 21: //raiz
                if (enPotencia) {
                    forma = cs.raizPot(pivot_x, pivot_y);
                } else {
                    forma = cs.raiz(pivot_x, pivot_y);
                }
                s.valorPrecedencia = 3;
                s.setValor(21);
                s.setTipo(2);
                s.setColor(context.colorOp);
                s.setForma(forma);
                lista_simbolos.add(s);
                break;
        }
        d.centrarNumeradores(this);

        //Luego de insertar un simbolo, mueve el pivot hacia la derecha
        if (!enPotencia) {
            fa.moverPivotDerecha(this, s);
        } else {
            fa.moverPivotDerechaPotencia(this);
        }

        //Para activar los puntos de control de los simbolos
        if (puntosControlActivo == 1) {
            s.switchPuntosControl();
        }

        // Funciones graficadoras
        //  Se borra el contenido del canvas para redibujar sobre ella.
        ArrayList<Simbolo> se = lista_simbolos;
        if(agregarParentesis){
            context.textoSalida.setText(listaATexto(fa.agregarParentesis(se)));
        }else{
            context.textoSalida.setText(listaATexto(lista_simbolos));
        }

        if (panelAgregado == 1) {
            context.panelContext.setTextArea();
        }
        canvasABinario();
        dibujarSimbolos();
        //updateTags();
    }

    protected void dibujarSimbolos() {
        if (!binarioActivo) {
            fg.limpiarCanvas(context.gc, context.Display);
            fg.dibujarTodosLosSimbolos(context.gc, context.lista_simbolos);
            //dibujarPuntero();
        } else {
            fg.limpiarCanvas(context.gc, context.Display);
            fg.dibujarTodosLosSimbolos(context.gc, logicBin.lista_simbolos);
            //logicBin.dibujarPuntero(context.gc);
        }
    }
    
    protected void switchAgregarParentesis(){
        if(!agregarParentesis){
            agregarParentesis = true;
        }else{
            agregarParentesis = false;
        }
    }

    
    protected void switchPotencias() {
        if (context.lista_simbolos.get(context.lista_simbolos.size() - 1).valor != 18) {
            pivot_yPrePotencia = pivot_y;
        } else {
            for (int k = context.lista_simbolos.size() - 2; k >= 0; k--) {
                if (context.lista_simbolos.get(k).tipo == 0) {
                    pivot_yPrePotencia = context.lista_simbolos.get(k).Ypos;
                    break;
                }
            }

        }
    }

    protected void dibujarPuntero() {
        //context.gc.fillOval(pivot_x, pivot_y, 3, 3); //dibuja la posicion del puntero
        Simbolo p = new Simbolo();
        //Iniciaci??n y declaraci??n de un simbolo general
        Simbolo s = new Simbolo();
        s.setXpos(pivot_x);
        s.setYpos(pivot_y);
        s.Xfactor = factor;
        s.Yfactor = factor;

        //Iniciaci??n de una forma general
        double[] forma;

        if (enPotencia) {
            forma = cs.ceroPot(pivot_x, pivot_y);
            s.setForma(forma);
            s.setValor(0);
            s.setColor(Color.rgb(125, 125, 125, 0.2));
            s.setTipo(0);
            s.grosor = 2;
        } else {
            forma = cs.cero(pivot_x, pivot_y);
            s.setForma(forma);
            s.setValor(0);
            s.setColor(Color.rgb(125, 125, 125, 0.2));
            s.setTipo(0);
            s.grosor = 3;
        }
        s.dibujar_Simbolo(context.gc);
    }

    protected double[] getAlturaParentesisAbierto() {

        return ParentesisAbiertos.get(ParentesisAbiertos.size() - 1).getAlturaParentesis();
    }

    protected void cambiarTamano(double factor) {
        for (int i = 0; i < context.lista_simbolos.size(); i++) {
            context.lista_simbolos.get(i).Xfactor = factor;
            context.lista_simbolos.get(i).Yfactor = factor;
        }
        for (int i = 0; i < logicBin.lista_simbolos.size(); i++) {
            logicBin.lista_simbolos.get(i).Xfactor = factor;
            logicBin.lista_simbolos.get(i).Yfactor = factor;
        }

        dibujarSimbolos();
    }

    protected void resetEstado() {
        enDivision = false;
        enPotencia = false;
        Check = false;
        ParentesisAbiertos.clear();
        parentesisAgregadoANumerador = false;
        d.anchoAnterior = 0;
        context.pivot_x = context.pix;
        context.pivot_y = context.piy;
        pivot_x = 50;
        pivot_y = 300;
        movimientosDeLista = 0;
        subidasDivision = 0;
        context.textoSalida.setText("");

    }

    protected void bajarPivotADenominador() {
        fa.moverPivotAbajo(this, 44);
        fa.moverPivotADenominador(this);
    }

    protected void updateTags() {
        context.alturaDivision.setText("enDivision: " + enDivision);
        context.divisionActiva.setText("Subidas Division: " + subidasDivision);
        context.indicesNumeradores.setText("Parentesis abiertos: " + ParentesisAbiertos.size());
        context.movimientosDeLista.setText("Parentesis abiertos: " + ParentesisAbiertos.size());
    }

    protected void cambiarMovimientosListas() {
        if (ParentesisAbiertos.size() > 0) {
            movimientosDeLista++;
        }

    }

    protected void formaOperadorCientifico(Logica l, int valor, double pivot_x, double pivot_y, Simbolo s) {

        double[] forma;

        switch (valor) {
            case 14: //Operador Seno
                if (enPotencia) {
                    forma = cs.sPot(pivot_x, pivot_y); //Agrega la S
                    s.forma = forma;
                    s.moverIzquierda(0.5);
                    forma = cs.iPot(pivot_x, pivot_y); //Agregar I
                    s.concatenarForma(forma);
                    s.moverIzquierda(0.5);
                    forma = cs.nPot(pivot_x, pivot_y); //Agregar N
                    s.concatenarForma(forma);
                    s.moverDerecha(1);

                } else {
                    forma = cs.s(pivot_x, pivot_y); //Agrega la S
                    s.forma = forma;
                    s.moverIzquierda(1);
                    forma = cs.i(pivot_x, pivot_y); //Agregar I
                    s.concatenarForma(forma);
                    s.moverIzquierda(1);
                    forma = cs.n(pivot_x, pivot_y); //Agregar N
                    s.concatenarForma(forma);
                    s.moverDerecha(2);
                    l.pivot_x = l.pivot_x - l.espacioEntreSimbolos;
                }
                break;
            case 15: //Operador Coseno
                if (enPotencia) {
                    forma = cs.cPot(pivot_x, pivot_y); //Agrega la C
                    s.forma = forma;
                    s.moverIzquierda(0.5);
                    forma = cs.oPot(pivot_x, pivot_y); //Agregar O
                    s.concatenarForma(forma);
                    s.moverIzquierda(0.5);
                    forma = cs.sPot(pivot_x, pivot_y); //Agregar S
                    s.concatenarForma(forma);
                    s.moverDerecha(1);

                } else {
                    forma = cs.c(pivot_x, pivot_y); //Agrega la C
                    s.forma = forma;
                    s.moverIzquierda(1);
                    forma = cs.o(pivot_x, pivot_y); //Agregar O
                    s.concatenarForma(forma);
                    s.moverIzquierda(1);
                    forma = cs.s(pivot_x, pivot_y); //Agregar S
                    s.concatenarForma(forma);
                    s.moverDerecha(2);
                    l.pivot_x = l.pivot_x - l.espacioEntreSimbolos;
                }
                break;
            case 16: //Operador Tangente
                if (enPotencia) {
                    forma = cs.tPot(pivot_x, pivot_y); //Agrega la T
                    s.forma = forma;
                    s.moverIzquierda(0.5);
                    forma = cs.aPot(pivot_x, pivot_y); //Agregar A
                    s.concatenarForma(forma);
                    s.moverIzquierda(0.5);
                    forma = cs.nPot(pivot_x, pivot_y); //Agregar N
                    s.concatenarForma(forma);
                    s.moverDerecha(1);

                } else {
                    forma = cs.t(pivot_x, pivot_y); //Agrega la T
                    s.forma = forma;
                    s.moverIzquierda(1);
                    forma = cs.a(pivot_x, pivot_y); //Agregar A
                    s.concatenarForma(forma);
                    s.moverIzquierda(1);
                    forma = cs.n(pivot_x, pivot_y); //Agregar N
                    s.concatenarForma(forma);
                    s.moverDerecha(2);
                    l.pivot_x = l.pivot_x - l.espacioEntreSimbolos;
                }
                break;
        }
    }

    protected void moverListaHaciaIzquierda(ArrayList<Simbolo> lista_simbolos, double espacios) {
        for (int i = 0; i < lista_simbolos.size() - 1; i++) {
            lista_simbolos.get(i).moverIzquierda(espacios);
        }

    }

    protected void moverListaHaciaDerecha(ArrayList<Simbolo> lista_simbolos, int espacios) {
        for (int i = 0; i < lista_simbolos.size(); i++) {
            lista_simbolos.get(i).moverDerecha(espacios);
        }

    }

    protected void resetMovimientoLista() {
        if (context.lista_simbolos.get(context.lista_simbolos.size() - 1).getValor() == 18) {
            movimientosDeLista = 0;
        }
    }

    protected void dimensionarParentesisAbiertos(GraphicsContext gc) {
        if (enPotencia) {
            for (int i = 0; i < ParentesisAbiertos.size(); i++) {
                ParentesisAbiertos.get(i).dimensionarParentesis(gc, 0.5);
                ParentesisAbiertos.get(i).aumentarVecesDimensionado();
            }
        } else {
            for (int i = 0; i < ParentesisAbiertos.size(); i++) {
                ParentesisAbiertos.get(i).dimensionarParentesis(gc, 1);
                ParentesisAbiertos.get(i).aumentarVecesDimensionado();
            }
        }
    }

    protected void dimensionarParentesisAbiertosAbajo(GraphicsContext gc) {
        for (int i = 0; i < ParentesisAbiertos.size(); i++) {
            ParentesisAbiertos.get(i).dimensionarParentesisHaciaAbajo(1);
        }
    }

    protected void moverNumeradoresHaciaAbajo(ArrayList<Simbolo> lista_simbolos) {

        for (int i = lista_simbolos.size() - 2; i >= 0; i--) {
            if (lista_simbolos.get(i).getTipo() == 0) {
                lista_simbolos.get(i).moverArriba(-1);
            } else {
                break;
            }

        }

    }

    protected int bloqueadorOperadorMultiple(ArrayList<Simbolo> lista_simbolos) {
        int index = lista_simbolos.size() - 1;
        if (!lista_simbolos.isEmpty()) {
            if (index != -1) {
                if (lista_simbolos.get(index).tipo == 0 || lista_simbolos.get(index).tipo == 2) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 0;
            }

        } else {
            return 1;
        }
    }

    protected int bloqueadorSignoNegativo(ArrayList<Simbolo> lista_simbolos) {

        if (lista_simbolos.isEmpty()) {
            return 1;
        } else {
            if (lista_simbolos.size() > 0) {
                if (lista_simbolos.get(lista_simbolos.size() - 1).getTipo() == 0
                    || lista_simbolos.get(lista_simbolos.size() - 1).getTipo() == 2
                    || (lista_simbolos.get(lista_simbolos.size() - 1).getTipo() == 1
                    && lista_simbolos.get(lista_simbolos.size() - 2).getTipo() == 0)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
    }

    protected void switchPuntosControl(ArrayList<Simbolo> lista_simbolos, GraphicsContext gc, Canvas Display) {

        for (int i = 0; i < lista_simbolos.size(); i++) {
            lista_simbolos.get(i).switchPuntosControl();
        }

        for (int i = 0; i < logicBin.lista_simbolos.size(); i++) {
            logicBin.lista_simbolos.get(i).switchPuntosControl();
        }
        dibujarSimbolos();

        if (puntosControlActivo == 0) {
            puntosControlActivo = 1;
            logicBin.puntosControlActivo = 1;

        } else {
            puntosControlActivo = 0;
            logicBin.puntosControlActivo = 0;
        }

    }

    protected void text_debugger(ArrayList<Simbolo> lista_simbolos) {
        for (int i = 0; i < lista_simbolos.size(); i++) {
            Simbolo s = lista_simbolos.get(i);
            if (s.valor > 9) {
                if (s.valor == 10) {
                    System.out.print(" + ");

                }
                if (s.valor == 11) {
                    System.out.print(" - ");
                }
                if (s.valor == 12) {
                    System.out.print(" * ");
                }
                if (s.valor == 13) {
                    System.out.print(" / ");
                }
            } else {
                System.out.print(s.valor);
            }

        }
        System.out.println();
    }

    protected void canvasABinario() {
        ArrayList<Simbolo> lista_simbolos = context.lista_simbolos;
        Canvas DisplayBin = context.DisplayBin;
        GraphicsContext gc = context.gc;

        logicBin.resetEstado();

        int numeroAConvertir;
        String numeroConvertido;

        for (int i = 0; i < lista_simbolos.size(); i++) {
            if (lista_simbolos.get(i).getTipo() == 0) {
                //Si encuentra un numero, se itera hasta encontrar un tipo no numero (!= 0)
                // y se retornan los digitos como el numero entero.
                numeroAConvertir = fa.buscarNumero(lista_simbolos, i);
                //System.out.println("Numero a convertir: " + numeroAConvertir);
                //Transformaci??n de numero encontrado a binario.
                numeroConvertido = Integer.toBinaryString(numeroAConvertir);
                //System.out.println("Numero convertido: " + numeroConvertido);

                //Actualiza el indice del iterador seg??n los digitos encontrados.
                //System.out.println("indice antes de: "+i);
                if(numeroAConvertir >=0){
                    i = i + String.valueOf(numeroAConvertir).length() - 1;
                }else{
                    i = i + String.valueOf(numeroAConvertir).length()-2;
                }
                //System.out.println("indice despues de: "+i);
                //Simulacion de entrada por click de botones 0 y 1 para agregar
                // numeros al canvas.
                fa.numerosBinariosACanvas(this, numeroConvertido, gc, lista_simbolos, DisplayBin);

            } else {

                switch (lista_simbolos.get(i).getValor()) {
                    case -1: //Apertura de potencias
                        if (!logicBin.lista_simbolos.isEmpty()) {
                            if (!logicBin.enPotencia) {
                                logicBin.enPotencia = true;
                                fa.getAlturaSimbolo(logicBin);
                                fa.agregarSimboloBin(this, gc, lista_simbolos, DisplayBin,-1);
                            }
                        }
                        break;

                    default:
                        fa.agregarSimboloBin(this, gc, lista_simbolos, DisplayBin, lista_simbolos.get(i).valor);
                        break;
                }
            }

        }

    }

    protected String listaATexto(ArrayList<Simbolo> lista_simbolos) {

        String string = "";

        for (int i = 0; i < lista_simbolos.size(); i++) {
            Simbolo s = lista_simbolos.get(i);
            if (s.valor > 9 && s.valor != -1) {
                if (s.valor == 10) {
                    System.out.print("+");
                    string = string + "+";
                }
                if (s.valor == 11) {
                    System.out.print("-");
                    string = string + "-";
                }
                if (s.valor == 12) {
                    System.out.print("*");
                    string = string + "*";
                }
                if (s.valor == 13) {
                    System.out.print("/");
                    string = string + "/";
                }
                if (s.valor == 14) {
                    System.out.print("sin");
                    string = string + "sin";
                }
                if (s.valor == 15) {
                    System.out.print("cos");
                    string = string + "cos";
                }
                if (s.valor == 16) {
                    System.out.print("tan");
                    string = string + "tan";
                }
                if (s.valor == 17) {
                    System.out.print("(");
                    string = string + "(";
                }
                if (s.valor == 18) {
                    System.out.print(")");
                    string = string + ")";
                }
                if (s.valor == 19) {
                    System.out.print("!");
                    string = string + "!";
                }
                if (s.valor == 20) {
                    System.out.print("??");
                    string = string + "??";
                }
                if (s.valor == 21) {
                    System.out.print("Raiz");
                    string = string + "Raiz";
                }

            } else if (s.valor == -1) {
                System.out.print("^");
                string = string + "^";
            } else if (s.valor == -3) {
                System.out.print(".");
                string = string + ".";
            } else {
                System.out.print(s.valor);
                string = string + s.valor;
            }

        }
        System.out.println();

        return string;
    }

    protected void checkPotencias(int n) {

        if ((n < 0 || n > 9) && n != 17 && parentesisEnPotencia.isEmpty()) {
            enPotencia = false;
            Check = false;
            pivot_y = pivot_yPrePotencia;
        }

    }

}
