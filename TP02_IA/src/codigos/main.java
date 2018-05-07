/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigos;

/**
 *
 * @author fabio
 */
public class main {
    public static void main(String[] args) {
        Resolve tab = new Resolve(3,3);
        int[][] tabOriginal = new int[3][3];
        tab.exibe();
        System.out.println("------------------");
        tab.embaralha(50);
        tab.copyArray(tab.getTab(), tabOriginal);
        tab.exibe();
        System.out.println("--------EXECUÇÃO H1----------");
        tab.buscaH1();
        tab.exibe();
        tab.copyArray(tabOriginal, tab.getTab());
        System.out.println("--------EXECUÇÃO H2----------");
        tab.buscaH2();
        tab.exibe();
        System.out.println("------------------");
        System.out.println(tab.getNumJogadas());
    }
}
