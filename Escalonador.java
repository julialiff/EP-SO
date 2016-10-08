import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Escalonador
{
  public static void main(String [] args)
  {
    //codigo aqui
    Scanner ler = new Scanner(System.in);
    String [] arquivos;
    arquivos = new String[11];
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
    arquivos[10] = "quantum.txt";

    String[][] processo;
    processo = new String[11][23];

    String nome = "01.txt";
    // System.out.printf("Informe o nome de arquivo texto:\n");
    // String nome = ler.nextLine();

    System.out.printf("\nConteúdo do arquivo texto:\n");

    int j = 0;
    for(int i = 0; i <= 10; i++){
      j = 0;
      nome = arquivos[i];
      System.out.println("Nome: " + nome + "[i][j] ["+i+"]["+j+"]");
      try {
        FileReader arq = new FileReader(nome);
        BufferedReader lerArq = new BufferedReader(arq);
        String linha = lerArq.readLine(); // lê a primeira linha
        processo[i][j] = linha;
        j++;

        while (linha != null) {
          System.out.printf("%s\n", linha);
          linha = lerArq.readLine(); // lê da segunda até a última linha
          processo[i][j] = linha;
          j++;
        }

        arq.close();
      } catch (IOException e) {
          System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
      }

      System.out.println();
    }
  }

}
