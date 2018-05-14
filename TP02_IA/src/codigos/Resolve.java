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
public class Resolve {

    private final int[][] tab;
    private final int[][] reserva;
    private int zeroL;
    private int zeroC;
    private int numJogadas;
    private final int lin;
    private final int col;
    private Tabuleiro result;

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

    public int getLin() {
        return lin;
    }

    public int getCol() {
        return col;
    }

    public Resolve(int lin, int col) {
        this.tab = new int[lin][col];
        this.reserva = new int[lin][col];
        this.lin = lin;
        this.col = col;
        this.resetTab();
        this.copyArray(this.tab, this.reserva);
    }

    private void getJogadas(int[] jogadas, int zeroL, int zeroC) {
        int cont = 1;
        if (zeroL > 0) {
            jogadas[cont++] = this.CIMA;
        }
        if (zeroL < this.lin - 1) {
            jogadas[cont++] = this.BAIXO;
        }
        if (zeroC < this.col - 1) {
            jogadas[cont++] = this.DIREITA;
        }
        if (zeroC > 0) {
            jogadas[cont++] = this.ESQUERDA;
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
            this.getJogadas(jogadas, this.zeroL, this.zeroC);
            aux = jogadas[0];
            auxJog = (int) (rand.nextInt(aux - 1) + 1);
            this.fazJogada(jogadas[auxJog], this.tab, this.zeroL, this.zeroC);
            atualizaZero(jogadas[auxJog]);
            this.numJogadas++;
        }
    }

    public void buscaH1() {
        ArrayList<Tabuleiro> tabs = new ArrayList();
        this.numJogadas = 0;
        int[] jogadas = new int[5];
        this.getJogadas(jogadas, this.zeroL, this.zeroC);
        int auxJog;
        int[][] arrayAux = new int[this.lin][this.col];
        this.copyArray(tab, arrayAux);
        Tabuleiro anterior = null;
        Tabuleiro atual = new Tabuleiro(arrayAux, this.ordenaJogadasH1(jogadas));
        tabs.add(atual);
        atual.setPai(null);

        while (!this.verificaFim()) {
            auxJog = atual.getJogada();
            if (auxJog == -1) {
                for (Tabuleiro t : tabs) {
                    if (t.getJogadas().size() > 0) {
                        atual = t;
                        this.copyArray(atual.getTab(), this.tab);
                        this.atualizaZero();
                        break;
                    }
                }
                continue;
            }
            this.fazJogada(auxJog, this.tab, this.zeroL, this.zeroC);
            this.numJogadas++;
            this.atualizaZero(auxJog);

            //Prepara pra próxima iteração
            arrayAux = new int[this.lin][this.col];
            this.copyArray(this.tab, arrayAux);
            anterior = atual;
            atual = null;
            for (Tabuleiro t : tabs) {
                if (t.igual(arrayAux)) {
                    atual = t;
                    break;
                }
            }
            this.getJogadas(jogadas, this.zeroL, this.zeroC);
            if (atual == null) {
                atual = new Tabuleiro(arrayAux, this.ordenaJogadasH1(jogadas));
                atual.setPai(anterior);
                tabs.add(atual);
            }

        }
        this.result = atual;

    }

    public void buscaH2() {
        ArrayList<Tabuleiro> tabs = new ArrayList();
        this.numJogadas = 0;
        int[] jogadas = new int[5];
        this.atualizaZero();
        this.getJogadas(jogadas, this.zeroL, this.zeroC);
        int auxJog;
        int[][] arrayAux = new int[this.lin][this.col];
        this.copyArray(tab, arrayAux);
        Tabuleiro anterior;
        Tabuleiro atual;
        atual = new Tabuleiro(arrayAux, this.ordenaJogadasH2(jogadas));
        tabs.add(atual);
        atual.setPai(null);

        while (!this.verificaFim()) {
            auxJog = atual.getJogada();
            if (auxJog == -1) {
                for (Tabuleiro t : tabs) {
                    if (t.getJogadas().size() > 0) {
                        atual = t;
                        this.copyArray(atual.getTab(), this.tab);
                        this.atualizaZero();
                        break;
                    }
                }
                continue;
            }
            this.fazJogada(auxJog, this.tab, this.zeroL, this.zeroC);
            this.numJogadas++;
            this.atualizaZero(auxJog);

            //Prepara pra próxima iteração
            arrayAux = new int[this.lin][this.col];
            this.copyArray(this.tab, arrayAux);
            anterior = atual;
            atual = null;
            for (Tabuleiro t : tabs) {
                if (t.igual(arrayAux)) {
                    atual = t;
                    break;
                }
            }
            this.getJogadas(jogadas, this.zeroL, this.zeroC);
            if (atual == null) {
                atual = new Tabuleiro(arrayAux, this.ordenaJogadasH2(jogadas));
                atual.setPai(anterior);
                tabs.add(atual);
            }

        }

        this.result = atual;

    }

    public void buscaHPessoal() {
        ArrayList<Tabuleiro> tabs = new ArrayList();
        this.numJogadas = 0;
        int[] jogadas = new int[5];
        this.getJogadas(jogadas, this.zeroL, this.zeroC);
        int auxJog;
        int[][] arrayAux = new int[this.lin][this.col];
        this.copyArray(tab, arrayAux);
        Tabuleiro anterior = null;
        Tabuleiro atual = new Tabuleiro(arrayAux, this.ordenaJogadasHP(jogadas));
        tabs.add(atual);
        atual.setPai(null);

        while (!this.verificaFim()) {
            auxJog = atual.getJogada();
            if (auxJog == -1) {
                for (Tabuleiro t : tabs) {
                    if (t.getJogadas().size() > 0) {
                        atual = t;
                        this.copyArray(atual.getTab(), this.tab);
                        this.atualizaZero();
                        break;
                    }
                }
                continue;
            }
            this.fazJogada(auxJog, this.tab, this.zeroL, this.zeroC);
            this.numJogadas++;
            this.atualizaZero(auxJog);

            //Prepara pra próxima iteração
            arrayAux = new int[this.lin][this.col];
            this.copyArray(this.tab, arrayAux);
            anterior = atual;
            atual = null;
            for (Tabuleiro t : tabs) {
                if (t.igual(arrayAux)) {
                    atual = t;
                    break;
                }
            }
            this.getJogadas(jogadas, this.zeroL, this.zeroC);
            if (atual == null) {
                atual = new Tabuleiro(arrayAux, this.ordenaJogadasHP(jogadas));
                atual.setPai(anterior);
                tabs.add(atual);
            }

        }
        this.result = atual;

    }

    public void embaralha(int num) {
        int[] jogadas = new int[5];
        int aux, auxJog;
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            this.getJogadas(jogadas, this.zeroL, this.zeroC);
            aux = jogadas[0];
            auxJog = (int) (rand.nextInt(aux - 1) + 1);
            this.fazJogada(jogadas[auxJog], this.tab, this.zeroL, this.zeroC);
            this.atualizaZero(jogadas[auxJog]);
        }
        this.numJogadas = 0;
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

    private boolean fazJogada(int jog, int[][] tab, int zeroL, int zeroC) {
        int aux;
        boolean fezJog = false;

        aux = tab[zeroL][zeroC];
        switch (jog) {
            case CIMA:
                if (zeroL > 0) {
                    tab[zeroL][zeroC] = tab[zeroL - 1][zeroC];
                    tab[--zeroL][zeroC] = aux;
                    fezJog = true;
                }
                break;
            case BAIXO:
                if (zeroL < this.lin - 1) {
                    tab[zeroL][zeroC] = tab[zeroL + 1][zeroC];
                    tab[++zeroL][zeroC] = aux;
                    fezJog = true;
                }
                break;
            case DIREITA:
                if (zeroC < this.col - 1) {
                    tab[zeroL][zeroC] = tab[zeroL][zeroC + 1];
                    tab[zeroL][++zeroC] = aux;
                    fezJog = true;
                }
                break;
            case ESQUERDA:
                if (zeroC > 0) {
                    tab[zeroL][zeroC] = tab[zeroL][zeroC - 1];
                    tab[zeroL][--zeroC] = aux;
                    fezJog = true;
                }
                break;
        }
        return fezJog;
    }

    public void exibe() {
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                System.out.print(this.tab[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public final void copyArray(int[][] array, int[][] arrayCpy) {
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, arrayCpy[i], 0, array[i].length);
        }
    }

    private ArrayList<Integer> ordenaJogadasH1(int[] jogadas) {
        ArrayList<Integer> jogs = new ArrayList();
        int aux = jogadas[0];
        int[][] mat = new int[aux - 1][2];
        int[][] tabAux = new int[this.lin][this.col];

        for (int i = 1; i < aux; i++) {
            this.copyArray(this.tab, tabAux);
            this.fazJogada(jogadas[i], tabAux, zeroL, zeroC);
            mat[i - 1][0] = jogadas[i];
            mat[i - 1][1] = this.verificaDistancia(tabAux);
        }

        bubbleJogadas(mat);
        for (int i = 1; i < aux; i++) {
            jogs.add(mat[i - 1][0]);
        }

        return jogs;
    }

    private void bubbleJogadas(int[][] mat) {
        int aux;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 1; j < mat.length - i; j++) {
                if (mat[j - 1][1] > mat[j][1]) {
                    aux = mat[j - 1][1];
                    mat[j - 1][1] = mat[j][1];
                    mat[j][1] = aux;

                    aux = mat[j - 1][0];
                    mat[j - 1][0] = mat[j][0];
                    mat[j][0] = aux;
                }
            }
        }
    }

    private void atualizaZero() {
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                if (this.tab[i][j] == -1) {
                    this.zeroC = j;
                    this.zeroL = i;
                    return;
                }
            }
        }
    }

    public int getPassos() {
        int num = -1;
        Tabuleiro aux = this.result;
        while (aux != null) {
            aux = aux.getPai();
            num++;
        }
        return num;
    }

    private ArrayList<Integer> ordenaJogadasH2(int[] jogadas) {
        ArrayList<Integer> jogs = new ArrayList();
        int aux = jogadas[0]; // Numero de jogadas +1
        int[][] mat = new int[aux - 1][2]; // Matriz que ajuda organizar jogadas
        int[][] tabAux = new int[this.lin][this.col];  // Tabuleiro que simulará a primeira jogada
        int[][] tabAux2 = new int[this.lin][this.col];   // Tabuleiro que simulará a segunda jogada
        int[] zeroLC;// Coordenadas do zero apos a primeira jogada
        int[] jogadasAux = new int[5]; // Vetor com os possiveis movimentos para segunda jogada
        int min, minAux; //auxiliares para calcular o menor valor da segunda jogada

        for (int i = 1; i < aux; i++) {
            //Uma jogada a frente
            this.copyArray(this.tab, tabAux); // Copia pra não perder o tabuleiro
            this.fazJogada(jogadas[i], tabAux, zeroL, zeroC); // simula um movimento
            if (this.verificaDistancia(tabAux) == 0) { //verifica se o movimento gerá uma solução
                mat[i - 1][0] = jogadas[i];
                mat[i - 1][1] = 0;
                continue;
            }

            zeroLC = this.getZero(tabAux); // pega coordenadas do zero no tabuleiro após a jogada
            this.getJogadas(jogadasAux, zeroLC[0], zeroLC[1]); // Verifica quais movimentos estão disponíveis
            //Duas jogadas A frente
            min = Integer.MAX_VALUE;
            for (int j = 1; j < jogadasAux[0]; j++) {

                this.copyArray(tabAux, tabAux2); // copia para não perder tabuleiro

                this.fazJogada(jogadasAux[j], tabAux2, zeroLC[0], zeroLC[1]); // simula um movimento
                minAux = this.verificaDistancia(tabAux2); // verifica a distancia do tabuleiro após duas jogadas

                if (minAux < min) { // Armazena o melhor resultado que esse tabuleiro pode atingir
                    min = minAux;
                }
            }
            mat[i - 1][0] = jogadas[i]; // Armazena a jogada
            mat[i - 1][1] = min; // melhor resultados após 2 movimentos da jogada acima
        }

        bubbleJogadas(mat); // ordena jogadas

        //Adiciona no arraylist
        for (int i = 1; i < aux; i++) {
            jogs.add(mat[i - 1][0]);
        }

        return jogs;
    }

    private int[] getZero(int[][] tab) { // Recupera as coordenadas da peça branca
        int[] coords = new int[2];

        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                if (tab[i][j] == -1) {
                    coords[0] = i;
                    coords[1] = j;
                    return coords;
                }
            }
        }
        return null;
    }

    private ArrayList<Integer> ordenaJogadasHP(int[] jogadas) {// Basea-se no numeros de peças fora do lugar
        ArrayList<Integer> jogs = new ArrayList();
        int aux = jogadas[0];
        int[][] mat = new int[aux - 1][2];
        int[][] tabAux = new int[this.lin][this.col];

        for (int i = 1; i < aux; i++) {
            this.copyArray(this.tab, tabAux);
            this.fazJogada(jogadas[i], tabAux, zeroL, zeroC);
            mat[i - 1][0] = jogadas[i];
            mat[i - 1][1] = this.contaPecas(tabAux);
        }

        bubbleJogadas(mat);
        for (int i = 1; i < aux; i++) {
            jogs.add(mat[i - 1][0]);
        }

        return jogs;
    }

    private int contaPecas(int[][] tabAux) {
        int num = 0;
        for (int i = 0; i < this.lin; i++) {
            for (int j = 0; j < this.col; j++) {
                if (tabAux[i][j] != i * this.col + j && tabAux[i][j] != -1) {
                    num++;
                }
            }
        }
        return num;
    }

    public boolean jogaClique(int i, int j) {
        if (i == this.zeroL) {
            while (j != zeroC) {
                if (j > zeroC) {
                    this.fazJogada(this.DIREITA, tab, zeroL, zeroC);
                } else {
                    this.fazJogada(this.ESQUERDA, tab, zeroL, zeroC);
                }
                this.atualizaZero();
                this.numJogadas++;
            }
            return true;
        } else if (j == this.zeroC) {
            while (i != zeroL) {
                if (i > zeroL) {
                    this.fazJogada(this.BAIXO, tab, zeroL, zeroC);
                } else {
                    this.fazJogada(this.CIMA, tab, zeroL, zeroC);
                }
                this.atualizaZero();
                this.numJogadas++;
            }
            return true;
        }
        return false;

    }

    public void salvaReserva() {
        this.copyArray(tab, reserva);
    }

    public void recuperaReserva() {
        this.copyArray(reserva, tab);
        this.atualizaZero();
    }

    public void resetJogadas() {
        this.numJogadas = 0;
    }

    public void fazJogada(int i) {
        if (this.fazJogada(i, tab, zeroL, zeroC)) {
            this.numJogadas++;
        }
        this.atualizaZero();
    }

    private int getInv(int jogada) {
        switch (jogada) {
            case CIMA:
                return BAIXO;
            case BAIXO:
                return this.CIMA;
            case DIREITA:
                return this.ESQUERDA;
            case ESQUERDA:
                return this.DIREITA;
            default:
                return -1;
        }
    }

    public Tabuleiro setCam(Tabuleiro tab) {
       if(tab.getPai() == null){
           this.result = tab;
           return tab;
       }
       Tabuleiro aux = setCam(tab.getPai());
       aux.setPai(tab);
       return tab;
    }

    public void setCam() {
        this.setCam(this.result).setPai(null);
    }

    public Tabuleiro getResult() {
        return this.result;
    }

    public void setTab(int[][] tab) {
        this.copyArray(tab, this.tab);
        this.atualizaZero();
    }
}
