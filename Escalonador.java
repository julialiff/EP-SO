import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Escalonador
{
  protected int quantum;

  public static void main(String [] args)
  {
    //codigo aqui
    Scanner ler = new Scanner(System.in);
    String [] arquivos;
    arquivos = new String[10];
    arquivos[0] = "01.txt";
    arquivos[1] = "02.txt";
    arquivos[2] = "03.txt";
    arquivos[3] = "04.txt";
    arquivos[4] = "05.txt";
    arquivos[5] = "06.txt";
    arquivos[6] = "07.txt";
    arquivos[7] = "08.txt";
    arquivos[8] = "09.txt";
    arquivos[9] = "10.txt";
    // arquivos[10] = "quantum.txt";

    LinkedList<Processo> prontos = new LinkedList<Processo>();
    LinkedList<Processo> bloqueados = new LinkedList<Processo>();

    try{
      FileReader arqQuantum = new FileReader("quantum.txt");
      BufferedReader lerQuantum = new BufferedReader(arqQuantum);
      this.quantum = Integer.parseInt(lerQuantum.readLine());
      arqQuantum.close();
      System.out.printf("Quantum: " + this.quantum);  

      String nome;
      
      System.out.printf("\nConteúdo do arquivo texto:\n");

      for(int i = 0; i < 10; i++){
        nome = arquivos[i];
        System.out.println("Nome: " + nome);
        String [] instrucoes = new String[21];
        int j = 0;
        try {
          FileReader arq = new FileReader(nome);
          BufferedReader lerArq = new BufferedReader(arq);
          String nome = lerArq.readLine(); // lê a primeira linha

          String line = lerArq.readLine();
          while (line != null) {
            instrucoes[i] = new String(line);
            j++;
            line = lerArq.readLine();
          }

          Processo novo = new Processo(instrucoes);
          if (novo.getEstado() == 0) {
            prontos.push(novo);
          }

          arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
              e.getMessage());
        }

        System.out.println();
      }
    }
    catch (IOException e) {
          System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
    }

    while(prontos.size() > 0 || bloqueados.size() > 0) {
      int roundRobin = (int)(Math.random() * prontos.size());

      Processo atual = prontos.remove(roundRobin);
      atual.executa();

      if (bloqueados.size() > 0) {
        ListIterator<Processo> bloqueadosIterator = bloqueados.ListIterator(0);
        while (bloqueadosIterator.hasNext()) {
          Processo bloqueadoAtual = bloqueadosIterator.next();
          bloqueadoAtual.espera();
        }
      }

      switch (atual.getEstado()) {
        case 0:
          prontos.add(atual);
          break;
        case 1:
          bloqueados.add(atual);
          break;
        case 2:
          // corre pro abraco
      }



    }
  }
}
