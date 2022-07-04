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
 * @author Guillermo Bustamante <bustarr.dev@gmail.com>
 */
public class LogicaBinaria {

    ArrayList<Simbolo> lista_simbolos = new ArrayList();
    double espacioEntreSimbolos = 15;
    Simbolo simboloMasApartado = new Simbolo();
    double pivot_x = 50;
    double pivot_y = 300;
    double factor = 1;  
    boolean enDivision;
    boolean enPotencia;
    Simbolo divisor;
    int divisionesAgregadas = 0;

    InterfazController context;

    CoordenadasSimbolos cs = new CoordenadasSimbolos();
    FuncionesGraficadoras fg = new FuncionesGraficadoras();
    division d = new division();
    Simbolo divisionPrincipal;

    //Variables para los parentesis
    Simbolo ultimoParentesisCerrado;
    ArrayList<Simbolo> ParentesisAbiertos = new ArrayList();
    boolean parentesisAgregadoANumerador = false;
    int alturaParentesis = 0;
    boolean bloqueoDivision = false;
    //Para la activacion de los puntos de control
    int puntosControlActivo = 0;

    FuncionesAuxiliares fa = new FuncionesAuxiliares();
    Simbolo alturaAntesDeDivisionBin;
    
    //Variables para potencias
    ArrayList<Simbolo> parentesisEnPotencia = new ArrayList();
    boolean Check = false;

    double pivot_yPrePotencia;

    public LogicaBinaria(InterfazController context) {
        this.context = context;
    }

    protected void agregarSimbolo(GraphicsContext gc, int nSimbolo,
        ArrayList<Simbolo> lista_simbolos,
        Canvas Display) {
        //updateTags();

        if (this.lista_simbolos.isEmpty()) {
            simboloMasApartado.Ypos = pivot_y;
            simboloMasApartado.Xpos = pivot_x;
        }

        if (pivot_x > simboloMasApartado.Xpos) {
            simboloMasApartado.Xpos = pivot_x;
        }

        //System.out.println("En division:" + enDivision);
        //Iniciación y declaración de un simbolo general
        Simbolo s = new Simbolo();
        s.setXpos(pivot_x);
        s.setYpos(pivot_y);
        s.Xfactor = factor;
        s.Yfactor = factor;

        //Iniciación de una forma general
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
                this.lista_simbolos.add(s);
                break;
            case -1:
                s.setTipo(-1);
                s.setValor(-1);
                s.setColor(Color.rgb(0, 0, 0, 0));
                 s.valorPrecedencia = 9; //Mayor precedencia
                s.asociatividad = 1;
                forma = cs.ceroPot(pivot_x, pivot_y);
                s.setForma(forma);
                d.borrarSimbolosDeNumeradoresParaPotencia(this);
                pivot_x = pivot_x - 10;
                
                this.lista_simbolos.add(s);
                if (this.lista_simbolos.get(this.lista_simbolos.size() - 2).valor != 18) {
                    System.out.println("No hay parentesis antes de");
                    pivot_yPrePotencia = pivot_y;
                } else {
                    System.out.println("Hay parentesis antes de");
                    for (int k = this.lista_simbolos.size() - 3; k >= 0; k--) {
                        System.out.println("^Simbolo: " + this.lista_simbolos.get(k));
                        if (this.lista_simbolos.get(k).tipo == 0) {
                            pivot_yPrePotencia = this.lista_simbolos.get(k).Ypos;
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
                this.lista_simbolos.add(s);
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
                this.lista_simbolos.add(s);
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
                this.lista_simbolos.add(s);
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
                this.lista_simbolos.add(s);
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
                this.lista_simbolos.add(s);
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
                this.lista_simbolos.add(s);
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
                this.lista_simbolos.add(s);
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
                this.lista_simbolos.add(s);
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
                this.lista_simbolos.add(s);
                break;
            case 9:
                if (enPotencia) {
                    forma = cs.nuevePot(pivot_x, pivot_y);
                } else {
                    forma = cs.nueve(pivot_x, pivot_y);
                }
                s.setValor(9);
                s.setTipo(0);
                s.setColor(context.colorNum);
                s.setForma(forma);
                this.lista_simbolos.add(s);
                break;
            case 10:
                if (enPotencia) {
                    forma = cs.masPot(pivot_x, pivot_y);
                } else {
                    forma = cs.mas(pivot_x, pivot_y);
                }
                s.setValor(10);
                s.setTipo(1);
                s.setColor(context.colorOp);
                s.setForma(forma);
                this.lista_simbolos.add(s);
                break;
            case 11:
                if (enPotencia) {
                    forma = cs.menosPot(pivot_x, pivot_y);
                } else {
                    forma = cs.menos(pivot_x, pivot_y);
                }
                s.setValor(11);
                s.setTipo(1);
                s.setColor(context.colorOp);
                s.setForma(forma);
                this.lista_simbolos.add(s);
                break;
            case 12:
                if (enPotencia) {
                    forma = cs.multiplicarPot(pivot_x, pivot_y);
                } else {
                    forma = cs.multiplicar(pivot_x, pivot_y);
                }
                s.setValor(12);
                s.setTipo(1);
                s.setColor(context.colorOp);
                s.setForma(forma);
                this.lista_simbolos.add(s);
                break;
            case 13: //division

                forma = cs.dividir(pivot_x, pivot_y);
                s.setValor(13);
                s.setTipo(1);
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
                d.modLineaDivisionBin = true;
                d.NumeradoresBin.add(s);
                dimensionarParentesisAbiertos(gc);
                this.lista_simbolos.add(s);
                break;
            case 14: //Seno
                s.setValor(14);
                s.setTipo(2);
                s.setColor(context.colorOp);
                formaOperadorCientifico(this, 14, pivot_x, pivot_y, s);
                this.lista_simbolos.add(s);
                fa.moverPivotDerechaPotencia(this);
                fa.moverPivotDerechaPotencia(this);
                break;
            case 15: //Coseno
                s.setValor(15);
                s.setTipo(2);
                s.setColor(context.colorOp);
                formaOperadorCientifico(this, 15, pivot_x, pivot_y, s);
                this.lista_simbolos.add(s);
                fa.moverPivotDerechaPotencia(this);
                fa.moverPivotDerechaPotencia(this);
                break;
            case 16: //Tangente
                s.setValor(16);
                s.setTipo(2);
                s.setColor(context.colorOp);
                formaOperadorCientifico(this, 16, pivot_x, pivot_y, s);
                this.lista_simbolos.add(s);
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
                s.setValor(17);
                s.setTipo(2);
                s.setColor(context.colorOp);
                s.setForma(forma);
                this.lista_simbolos.add(s);
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

                this.lista_simbolos.add(s);
                ultimoParentesisCerrado = s;
                d.borrarSimbolosDeNumeradoresParaPotencia(this);

                if (ParentesisAbiertos.get(ParentesisAbiertos.size() - 1) == d.parentesisDeDivisionActivoBin) {
                    d.modLineaDivisionBin = false;
                }
                ParentesisAbiertos.remove(ParentesisAbiertos.size() - 1); //Elimina el ultimo parentesis abierto

                if (ParentesisAbiertos.isEmpty()) {
                    d.NumeradoresBin.clear();
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
                s.setTipo(2);
                s.setColor(context.colorOp);
                s.setForma(forma);
                this.lista_simbolos.add(s);
                break;
            case 20:
                if (enPotencia) {
                    forma = cs.gradoPot(pivot_x, pivot_y);
                } else {
                    forma = cs.grado(pivot_x, pivot_y);
                }
                s.setValor(20);
                s.setTipo(2);
                s.setColor(context.colorOp);
                s.setForma(forma);
                this.lista_simbolos.add(s);
                break;
            case 21:
                if (enPotencia) {
                    forma = cs.raizPot(pivot_x, pivot_y);
                } else {
                    forma = cs.raiz(pivot_x, pivot_y);
                }
                s.valorPrecedencia = 2;
                s.setValor(21);
                s.setTipo(2);
                s.setColor(context.colorOp);
                s.setForma(forma);
                this.lista_simbolos.add(s);
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
        /*
        fg.limpiarCanvas(gc, Display);
        fg.dibujarTodosLosSimbolos(gc, this.lista_simbolos);
        dibujarPuntero(gc);
         */
        //updateTags();
        //System.out.println("Pivot_x: Simbolo = "+pivot_x+":"+s.valor);
    }

    protected void dibujarPuntero(GraphicsContext gc) {
        //context.gc.fillOval(pivot_x, pivot_y, 3, 3); //dibuja la posicion del puntero
        Simbolo p = new Simbolo();
        //Iniciación y declaración de un simbolo general
        Simbolo s = new Simbolo();
        s.setXpos(pivot_x);
        s.setYpos(pivot_y);
        s.Xfactor = factor;
        s.Yfactor = factor;

        //Iniciación de una forma general
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
        s.dibujar_Simbolo(gc);
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

    protected void resetEstado() {
        enDivision = false;
        enPotencia = false;
        ParentesisAbiertos.clear();
        parentesisAgregadoANumerador = false;
        d.anchoAnterior = 0;
        pivot_x = 50;
        pivot_y = 300;

        lista_simbolos.clear();

    }

    protected void formaOperadorCientifico(LogicaBinaria l, int valor, double pivot_x, double pivot_y, Simbolo s) {

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
    protected void checkPotencias(int n) {

        if ((n < 0 || n > 9)&& n != 17 && parentesisEnPotencia.isEmpty()) {
            enPotencia = false;
            Check = false;
            pivot_y = pivot_yPrePotencia;
        }

    }
}
