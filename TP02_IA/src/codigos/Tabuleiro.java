/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigos;

import java.util.ArrayList;

/**
 *
 * @author fabio
 */
public class Tabuleiro {

    private final int[][] tab;
    private final ArrayList<Integer> jogadas;

    public Tabuleiro(int[][] tab, ArrayList<Integer> jogadas) {
        this.tab = tab;
        this.jogadas = jogadas;
    }

    public boolean igual(int[][] mat) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] != mat[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getJogada() {
        if (jogadas.size() > 0) {
            return jogadas.remove(0);
        } else {
            return -1;
        }
    }

}
