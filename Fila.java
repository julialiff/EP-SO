/**
 * Created by rnalm on 09/10/16.
 */

public class Fila<Item> {
    private No primeiro; // mais recente
    private No ultimo;  // mais antigo
    private int N;

    private class No {
        private Item item;
        private No proximo;
        private int lin;
        private int col;
    }

    public boolean vazio() {
        return primeiro == null;
    }

    public int tamanho() {
        return N;
    }

    public void inserir(Item item) {
        No antigoUltimo = ultimo;
        ultimo = new No();
        ultimo.item = item;
        ultimo.proximo = null;
        if (vazio()) primeiro = ultimo;
        else antigoUltimo.proximo = ultimo;
        N++;
    }

    public Item remover() {
        Item item = primeiro.item;
        primeiro = primeiro.proximo;
        N--;
        if (vazio()) ultimo = null;
        return item;
    }



}