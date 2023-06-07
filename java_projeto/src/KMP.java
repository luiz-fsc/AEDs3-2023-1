import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class KMP {
    public static int[] criarTabelaPrefixo(String padrao) {
        //array de inteiros que vai conter os valores das letras
        int[] tabela_prefixo = new int[padrao.length()];
        int j = 0;
        int i = 1;
        //contrução da tabela de prefixos
        while (i < padrao.length()) {
            if (padrao.charAt(i) == padrao.charAt(j)) {
                tabela_prefixo[i] = j + 1;
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = tabela_prefixo[j - 1];
                } else {
                    tabela_prefixo[i] = 0;
                    i++;
                }
            }
        }
        printArray(tabela_prefixo);
        return tabela_prefixo;
    }

    public static void AlgKMP(String texto, String padrao) {
        int aux_encontrou = 0;
        int[] tabela_prefixo = criarTabelaPrefixo(padrao);
        int i = 0; // índice para o texto
        int j = 0; // índice para o padrão
        while (i < texto.length()) {
            if (texto.charAt(i) == padrao.charAt(j)) {
                i++;
                j++;
            }
            //caso onde chegamos ao fim do padrao portanto encontramos ele
            if (j == padrao.length()) {
                System.out.println("Padrão encontrado no índice " + (i - j));
                j = tabela_prefixo[j - 1];
                aux_encontrou = 1;
            //caso nao encontremos o padrao saltamos posicoes para continuar a busca
            } else if (i < texto.length() && texto.charAt(i) != padrao.charAt(j)) {
                if (j != 0) {
                    j = tabela_prefixo[j - 1];
                } else {
                    i++;
                }
            }
        }

        if(aux_encontrou == 0)
        System.out.println("Padrão não encontrado ");
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    //metodo para retornar o arquivo em string
    public static String lerArquivoDB() throws IOException {

        File arquivo = new File("dados\\filmes\\arquivo.db");
        
        StringBuilder sb = new StringBuilder();
        
        try (FileReader fr = new FileReader(arquivo)) {
            
            int c = fr.read();
            while (c != -1) {
                sb.append((char) c);
                c = fr.read();
            }
            
        }
        
        return sb.toString();
    }

    //metodo para colher a string que sera buscada pelo KMP
    //interface
    public static String pegarPadrao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o padrao procurado: ");
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }
}

