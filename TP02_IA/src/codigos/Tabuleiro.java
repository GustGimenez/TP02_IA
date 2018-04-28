/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigos;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Gustavo
 */
public class Tabuleiro {

    private final int[][] tab;
    private int zeroL;
    private int zeroC;
    private int numJogadas;
    private final int lin;
    private final int col;

    private final int CIMA = 0;
    private final int BAIXO = 1;
    private final int DIREITA = 2;
    private final int ESQUERDA = 3;

    public int[][] getTab() {
        return tab;
    }

    public int getZeroL() {
        return zeroL;
    }

    public int getZeroC() {
        return zeroC;
    }

    public int getNumJogadas() {
        return numJogadas;
    }

    public Tabuleiro(int lin, int col) {
        this.tab = new int[lin][col];
        this.lin = lin;
        this.col = col;

        this.resetTab();
    }

    private void getJogadas(int[] jogadas) {
        int cont = 1;
        if (this.zeroL > 0) {
            jogadas[cont++] = this.CIMA;
        }
        if (this.zeroL < this.lin - 1) {
            jogadas[cont++] = this.BAIXO;
        }
        if (this.zeroC > 0) {
            jogadas[cont++] = this.ESQUERDA;
        }
        if (this.zeroC < this.col - 1) {
            jogadas[cont++] = this.DIREITA;
        }

        jogadas[0] = cont;
    }

    public boolean verificaFim() {
        int num;
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                num = i * this.col + j;
                if (this.tab[i][j] != num) {
                    if (i != (this.lin - 1) || (j != this.col - 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int verificaDistancia(int[][] tabuleiro) {
        int num = 0;
        int auxI, auxJ;

        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                if (tabuleiro[i][j] != -1) {
                    auxI = tabuleiro[i][j] / this.lin;
                    auxJ = tabuleiro[i][j] % this.col;
                    num += Math.abs(i - auxI + j - auxJ);
                }
            }
        }
        return num;
    }

    public void buscaCega() {
        this.numJogadas = 0;
        int[] jogadas = new int[5];
        int aux, auxJog;
        Random rand = new Random();
        while (!this.verificaFim()) {
            this.getJogadas(jogadas);
            aux = jogadas[0];
            auxJog = (int) (rand.nextInt(aux - 1) + 1);
            this.fazJogada(jogadas[auxJog], this.tab);
            this.numJogadas++;
        }
    }

    public void buscaH1() {
        ArrayList<Integer> historico = new ArrayList();
        this.numJogadas = 0;
        int[] jogadas = new int[5];
        int[][] tabAux = new int[this.lin][this.col];
        int aux, auxJog = 0, min, numAux;
        
        while (!this.verificaFim()) {
            this.getJogadas(jogadas);
            aux = jogadas[0];
            min = Integer.MAX_VALUE;
            for (int i = 1; i < aux; i++) {
                this.copyArray(tab, tabAux);
                this.fazJogada(jogadas[i], tabAux);
                numAux = this.verificaDistancia(tabAux);
                if (numAux < min) {
                    auxJog = i;
                    min = numAux;
                }
            }
            this.verificaLoop(historico, jogadas[auxJog]);
            this.fazJogada(jogadas[auxJog], this.tab);
            this.numJogadas++;
        }
    }

    public void buscaH2() {

    }

    public void buscaHPessoal() {

    }

    public void embaralha(int num) {
        int[] jogadas = new int[5];
        int aux, auxJog;
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            this.getJogadas(jogadas);
            aux = jogadas[0];
            auxJog = (int) (rand.nextInt(aux - 1) + 1);
            this.fazJogada(jogadas[auxJog],this.tab);
        }
    }

    public final void resetTab() {
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                this.tab[i][j] = i * (this.col) + j;
            }
        }
        this.zeroC = this.col - 1;
        this.zeroL = this.lin - 1;
        this.tab[this.zeroC][this.zeroL] = -1;
    }

    private void fazJogada(int jog, int[][] tab) {
        int aux;
        aux = tab[this.zeroL][this.zeroC];
        switch (jog) {
            case CIMA:
                tab[this.zeroL][this.zeroC] = tab[this.zeroL - 1][this.zeroC];
                tab[--this.zeroL][this.zeroC] = aux;
                break;
            case BAIXO:
                tab[this.zeroL][this.zeroC] = tab[this.zeroL + 1][this.zeroC];
                tab[++this.zeroL][this.zeroC] = aux;
                break;
            case DIREITA:
                tab[this.zeroL][this.zeroC] = tab[this.zeroL][this.zeroC + 1];
                tab[this.zeroL][++this.zeroC] = aux;
                break;
            case ESQUERDA:
                tab[this.zeroL][this.zeroC] = tab[this.zeroL][this.zeroC - 1];
                tab[this.zeroL][--this.zeroC] = aux;
                break;
        }
    }

    public void exibe() {
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.tab[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private void copyArray(int[][] array, int[][] arrayCpy) {
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, arrayCpy[i], 0, array[i].length);
        }
    }

    private void verificaLoop(ArrayList<Integer> historico, int jogada) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
