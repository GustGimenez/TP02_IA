/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Gustavo
 */
public class Resolve {

    private final int[][] tab;
    private final ArrayList<Integer> jogBanidas;
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

    public Resolve(int lin, int col) {
        this.tab = new int[lin][col];
        this.lin = lin;
        this.col = col;
        this.jogBanidas = new ArrayList();
        this.resetTab();
    }

    private void getJogadas(int[] jogadas) {
        int cont = 1;
        if (this.zeroL > 0 && !this.jogBanidas.contains(this.CIMA)) {
            jogadas[cont++] = this.CIMA;
        }
        if (this.zeroL < this.lin - 1 && !this.jogBanidas.contains(this.BAIXO)) {
            jogadas[cont++] = this.BAIXO;
        }
        if (this.zeroC > 0 && !this.jogBanidas.contains(this.ESQUERDA)) {
            jogadas[cont++] = this.ESQUERDA;
        }
        if (this.zeroC < this.col - 1 && !this.jogBanidas.contains(this.DIREITA)) {
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

    private void atualizaZero(int jogada) {
        switch (jogada) {
            case CIMA:
                this.zeroL--;
                break;
            case BAIXO:
                this.zeroL++;
                break;
            case DIREITA:
                this.zeroC++;
                break;
            case ESQUERDA:
                this.zeroC--;

        }
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
            this.fazJogada(jogadas[auxJog], this.tab, this.zeroL, this.zeroC);
            atualizaZero(jogadas[auxJog]);
            this.numJogadas++;
        }
    }

    public void buscaH1() {
        ArrayList<Tabuleiro> tabs = new ArrayList();
        int[] jogadas = new int[5];
        this.getJogadas(jogadas);
        
        tabs.add(new Tabuleiro(this.tab, this.ordenaJogadas(jogadas)));
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
            this.fazJogada(jogadas[auxJog], this.tab, this.zeroL, this.zeroC);
            this.atualizaZero(jogadas[auxJog]);
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

    private void fazJogada(int jog, int[][] tab, int zeroL, int zeroC) {
        int aux;
        aux = tab[zeroL][zeroC];
        switch (jog) {
            case CIMA:
                tab[zeroL][zeroC] = tab[zeroL - 1][zeroC];
                tab[--zeroL][zeroC] = aux;
                break;
            case BAIXO:
                tab[zeroL][zeroC] = tab[zeroL + 1][zeroC];
                tab[++zeroL][zeroC] = aux;
                break;
            case DIREITA:
                tab[zeroL][zeroC] = tab[zeroL][zeroC + 1];
                tab[zeroL][++zeroC] = aux;
                break;
            case ESQUERDA:
                tab[zeroL][zeroC] = tab[zeroL][zeroC - 1];
                tab[zeroL][--zeroC] = aux;
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

    private boolean verificaLoop(ArrayList<Integer> historico, int jogada) {
        int num = this.lin * this.col;
        if (num % 2 == 1) {
            num--;
        }
        if (historico.size() > 2 * (num - 1)) {
            historico.remove(0);
        }
        historico.add(this.getPeca(jogada));
        for (int i = 2; i < this.lin * this.col; i = i + 2) {
            if (i * i - 1 > historico.size()) {
                return false;
            }
            if (checaLoop(historico, i)) {
                historico.remove(historico.size() - 1);
                return true;
            }
        }
        return false;
    }

    private boolean checaLoop(ArrayList<Integer> historico, int num) {
        int n1 = num - 1;
        int n2 = 2 * n1;
        int offset = historico.size() - n2;
        int[][] jogadas = new int[2][n1];
        int aux;
        for (int i = 0; i < 2; i = i + n1) {
            aux = i / n1;
            for (int j = i; j < i + n1; j++) {
                jogadas[aux][j - i] = historico.get(offset + j);
            }
        }

        for (int i = 0; i < n1; i++) {
            aux = jogadas[0][i];
            for (int j = 1; j < 2; j++) {
                if (jogadas[j][i] != aux) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getPeca(int direcao) {
        switch (direcao) {
            case CIMA:
                return this.tab[this.zeroL - 1][this.zeroC];
            case BAIXO:
                return this.tab[this.zeroL + 1][this.zeroC];
            case DIREITA:
                return this.tab[this.zeroL][this.zeroC + 1];
            case ESQUERDA:
                return this.tab[this.zeroL][this.zeroC - 1];
            default:
                return -1;
        }
    }

    private ArrayList<Integer> ordenaJogadas(int[] jogadas) {
        ArrayList<Integer> jogs = new ArrayList();
        int aux = jogadas[0];
        int[][] mat = new int[aux-1][2];
        int[][] tabAux = new int[this.lin][this.col];
        
        for (int i = 1; i < aux; i++) {
            this.fazJogada(jogadas[i], tabAux, zeroL, zeroC);
            mat[0][i-1] = jogadas[i];
            mat[1][i-1] = this.verificaDistancia(tabAux);
        }
        
        bubbleJogadas(mat);
        for (int i = 1; i < aux; i++) {
           jogs.add(mat[i][0]);
        }
        
        return jogs;
    }

    private void bubbleJogadas(int[][] mat) {
        int aux;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 1; j < mat.length-i; j++) {
                if(mat[j-1][1] > mat[j][1]){
                    aux = mat[j-1][1];
                    mat[j-1][1] = mat[j][1];
                    mat[j][1] = aux;
                    
                    aux = mat[j-1][0];
                    mat[j-1][0] = mat[j][0];
                    mat[j][0] = aux;
                }
            }
        }
    }

}
