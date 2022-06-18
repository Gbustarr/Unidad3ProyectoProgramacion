/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.Arrays;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Guillermo
 */
public class Simbolo {

    double Xfactor = 1;//Tamaño
    double Yfactor = 1;
    double Xpos;
    double Ypos;
    double[] forma;
    Color color = Color.GREEN;
    int tipo; //0 = numero, 1= operador, 2 = operador especial
    int valor;
    int enDivision = 0;
    int longitudDivision = 0;
    int puntosControl = 0;
    boolean parentesisDimensionado = false;
    boolean bloqueParentesis = false;

    int vecesParentesisDimensionado = 0;

    Simbolo enlace;

    int grosor = 2;

    private static double espacio = 15;

    public Simbolo(double Xpos, double Ypos, double[] forma) {
        this.Xpos = Xpos;
        this.Ypos = Ypos;
        this.forma = forma;
    }

    public Simbolo() {
    }
    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/canvas/GraphicsContext.html

    protected void dibujar_Simbolo(GraphicsContext gc) {

        //System.out.println("Simbolo: " +this.valor);
        for (int i = 0; i < this.forma.length; i = i + 4) {
            gc.setStroke(this.color);
            gc.setLineWidth(grosor); //Cambia el tamaño de las lineas
            gc.strokeLine(((this.forma[i]) * Xfactor),
                this.forma[i + 1] * Yfactor,
                (this.forma[i + 2]) * Xfactor,
                this.forma[i + 3] * Yfactor);

            //Puntos de control
            if (puntosControl != 0) {
                this.graficarPuntosControl(gc, i);
            }

            /*
            System.out.print("Linea(("+(this.forma[i])*Xfactor);
            System.out.print(","+(this.forma[i+1]*Yfactor));
            System.out.print("),("+(this.forma[i+2])*Xfactor);
            System.out.print(","+(this.forma[i+3]*Yfactor)+")) ");
             */
        }
        //System.out.println();
    }

    protected void aumentarVecesDimensionado() {
        this.vecesParentesisDimensionado++;
    }

    protected void setBloqueParentesis() {
        this.bloqueParentesis = true;
    }

    protected boolean getBloqueParentesis() {
        return this.bloqueParentesis;
    }

    protected void setParentesisDimensionado() {
        this.parentesisDimensionado = true;
    }

    protected boolean getParentesisDimensionado() {
        return this.parentesisDimensionado;
    }

    protected void graficarPuntosControl(GraphicsContext gc, int i) {

        int tamano = 4;
        if (this.valor >= 0) {

            gc.fillOval(((this.forma[i]) * Xfactor) - (tamano / 2), (this.forma[i + 1] * Yfactor) - (tamano / 2), tamano, tamano);
            gc.fillOval(((this.forma[i + 2]) * Xfactor) - (tamano / 2), (this.forma[i + 3] * Yfactor) - (tamano / 2), tamano, tamano);

        }
    }

    protected void switchPuntosControl() {

        if (puntosControl == 0) {
            puntosControl = 1;
        } else {
            puntosControl = 0;
        }
    }

    protected void divisionFinal(double xFinal) {
        this.forma[2] = xFinal;
    }

    protected void setDimensionLineaDivision(double inicio, double termino) {
        this.forma[0] = inicio;
        this.forma[2] = termino;
    }

    protected void dimensionarParentesis(GraphicsContext gc, double incremento) {
        this.forma[1] = this.forma[1] - (44 * incremento);
        this.forma[3] = this.forma[3] - (44 * incremento);
        this.forma[5] = this.forma[5] - (44 * incremento);
    }

    protected double[] getAlturaParentesis() {
        double[] altura = {this.forma[1], this.forma[3], this.forma[5],
            this.forma[7], this.forma[9], this.forma[11]}; //Cordenadas Y del parentesis
        return altura;
    }

    protected void setAlturaParentesis(double[] altura) {
        this.forma[1] = altura[0];
        this.forma[3] = altura[1];
        this.forma[5] = altura[2];
        this.forma[7] = altura[3];
        this.forma[9] = altura[4];
        this.forma[11] = altura[5];
    }

    public double getXFactor() {
        return Xfactor;
    }

    public double getYFactor() {
        return Yfactor;
    }

    public void setXFactor(double factor) {
        this.Xfactor = factor;
    }

    public void setYFactor(double factor) {
        this.Yfactor = factor;
    }

    public double getXpos() {
        return Xpos;
    }

    public void setXpos(double Xpos) {
        this.Xpos = Xpos;
    }

    public double getYpos() {
        return Ypos;
    }

    public void setYpos(double Ypos) {
        this.Ypos = Ypos;
    }

    public void setForma(double[] forma) {
        this.forma = forma;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getValor() {
        return valor;
    }

    public String getValorString() {
        return String.valueOf(valor);
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    protected void concatenarForma(double[] formaAdicional) {
        int forma1len = forma.length;
        int forma2len = formaAdicional.length;

        double[] formaFinal = new double[forma1len + forma2len];
        System.arraycopy(this.forma, 0, formaFinal, 0, forma1len);
        System.arraycopy(formaAdicional, 0, formaFinal, forma1len, forma2len);

        this.forma = formaFinal;
    }

    protected void moverArriba(double factor) {
        for (int i = 0; i < this.forma.length; i = i + 2) {
            this.forma[i + 1] = this.forma[i + 1] - (22 * factor); // Coordenada Y
        }
    }

    protected void moverAbajo(double factor) {
        for (int i = 0; i < this.forma.length; i = i + 2) {
            this.forma[i + 1] = this.forma[i + 1] + (22 * factor); // Coordenada Y
        }
    }

    protected void dimensionarParentesisHaciaAbajo(double factor) {
        this.forma[7] = this.forma[7] + (44 * factor);
        this.forma[9] = this.forma[9] + (44 * factor);
        this.forma[11] = this.forma[11] + (44 * factor);
    }

    protected void moverIzquierda(double factor) {
        for (int i = 0; i < this.forma.length; i = i + 2) {
            this.forma[i] = this.forma[i] - (espacio * factor);  // Coordenada X
        }
    }

    protected void moverDerecha(double factor) {
        for (int i = 0; i < this.forma.length; i = i + 2) {
            this.forma[i] = this.forma[i] + (espacio * factor);  // Coordenada X
        }
    }

    @Override
    public String toString() {

        switch (this.valor) {
            case -2:
                return "";
            case -1:
                return "^";
            case 10:
                return "+";
            case 11:
                return "-";
            case 12:
                return "*";
            case 13:
                return "/";
            case 14:
                return "Sin";
            case 15:
                return "Cos";
            case 16:
                return "Tan";
            case 17:
                return "(";
            case 18:
                return ")";
            case 19:
                return "!";
            case 20:
                return "°";
            default:
                return String.valueOf(this.valor);
        }

        //return Arrays.toString(this.forma);
    }
}
