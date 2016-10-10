import java.util.LinkedList;
import java.util.ListIterator;

public class Escalonador {
    protected int quantum;
    protected int total;
    LinkedList<Processo> prontos;
    LinkedList<Processo> bloqueados;

    public Escalonador(int quantum) {
        this.quantum = quantum;

        this.total = 0;
        this.prontos = new LinkedList<Processo>();
        this.bloqueados = new LinkedList<Processo>();
    }

    public void criaProcesso(String nome, String [] instrucoes) {
        Processo novo = new Processo(nome, instrucoes);
        if (novo.getEstado() == 0) {
            this.prontos.push(novo);
        }
    }

    public int getQuantum() {
        return this.quantum;
    }

    public void executa() {
        System.out.println("Comecou");
        while(this.prontos.size() > 0 || this.bloqueados.size() > 0) {
            this.total++;
            Processo atual = null;
            if (this.prontos.size() > 0) {
                System.out.println("Ha " + this.prontos.size() + " processos com estado 'pronto'");
                int roundRobin = (int)(Math.random() * this.prontos.size());
                atual = this.prontos.remove(roundRobin);
                atual.executa(this.quantum);

                System.out.println("Executando processo " + roundRobin + " - " + atual.getNome() + " - " + atual.getEstado());
            }

            if (this.bloqueados.size() > 0) {
                System.out.println("Ha " + this.bloqueados.size() + " com estado 'bloqueado'");
                ListIterator<Processo> bloqueadosIterator = this.bloqueados.listIterator(0);
                while (bloqueadosIterator.hasNext()) {
                    int indiceAtual = bloqueadosIterator.nextIndex();
                    Processo bloqueadoAtual = bloqueadosIterator.next();

                    bloqueadoAtual.espera();

                    if (bloqueadoAtual.getEstado() == 0) {
                        System.out.println("Processo " + bloqueadoAtual.getNome() + " está pronto agora");
                        this.prontos.push(this.bloqueados.remove(indiceAtual));
                    }
                }
            }

            if (atual != null) {
                switch (atual.getEstado()) {
                    case 0:
                        this.prontos.push(atual);
                        break;
                    case 1:
                        this.bloqueados.push(atual);
                        break;
                }
            }
        }

        System.out.println("Terminou");
        System.out.println("Foram necessarios " + total + " quanta para executar os processos");
    }

}