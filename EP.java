import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.FileWriter;
import java.io.File;

public class EP {
  public static void main(String [] args) {
    //codigo aqui
    FileWriter arquivo;

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
      Escalonador escalonador = new Escalonador(Integer.parseInt(lerQuantum.readLine()));
      arqQuantum.close();
      System.out.printf("Quantum: " + escalonador.getQuantum());
      
      arquivo = new FileWriter(new File("Log0" + escalonador.getQuantum() + ".txt"));

      String nomeArq;
      System.out.printf("\nConteúdo do arquivo texto:\n");
      for(int i = 0; i < 10; i++){
        nomeArq = arquivos[i];
        System.out.println("Nome: " + nomeArq);
        try {
          FileReader arq = new FileReader(nomeArq);
          BufferedReader lerArq = new BufferedReader(arq);
          String nomeProcesso = lerArq.readLine(); // lê a primeira linha
          String [] instrucoes = new String[21];
          int j = 0;

          String line = lerArq.readLine();
          while (line != null) {
            instrucoes[j] = new String(line);
            j++;
            line = lerArq.readLine();
          }

          escalonador.criaProcesso(nomeProcesso, instrucoes);

          arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
              e.getMessage());
        }

        System.out.println();
      }

      escalonador.executa();
    }
    catch (IOException e) {
          System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
    }
  }
}
