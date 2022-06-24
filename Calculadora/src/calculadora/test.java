/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import java.util.ArrayList;

/**
 *
 * @author guillermo
 */
public class test {
    
    public static void main(String[] args){
        FuncionesAuxiliares fa = new FuncionesAuxiliares();
        
        ArrayList<Simbolo> s = new ArrayList();
        
        Simbolo a = new Simbolo();
        a.valor = 1;
        a.tipo = 0;
        s.add(a);
        
        Simbolo b = new Simbolo();
        b.valor = 13;
        b.tipo = 1;
        s.add(a);
        
        Simbolo c = new Simbolo();
        c.valor = 13;
        c.tipo = 0;
        s.add(a);
        
        fa.getPrecedence(s);
    
    }
}
