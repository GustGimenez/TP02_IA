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
        Tabuleiro tab = new Tabuleiro(3,3);
        tab.exibe();
        System.out.println("------------------");
        tab.embaralha(50);
        tab.exibe();
        System.out.println("------------------");
        tab.buscaH1();
        tab.exibe();
        System.out.println("------------------");
        System.out.println(tab.getNumJogadas());
    }
}
