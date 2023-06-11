import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ForcaBruta {

    private static int numComp = 0;
    
    private static int buscaString(String texto, String chave) {
        
        int tamanhoTexto = texto.length();
        int tamanhoChave = chave.length();

        /* No bloco de código o "i" será o ponteiro que caminhará char por char do texto para achar a chave.
         * Ele caminha somente até a posição [tamanhoTexto - tamanhoChave] porque a partir dessa posição não
         * é mais possível achar a chave de busca de forma completo, pois o texto irá acabar antes.
        */
        for (int i = 0; i <= tamanhoTexto - tamanhoChave; i++) { 
            int j;
            //"j" percorre a chave e auxilia o "i" a percorrer o texto e fazer a comparação
            for (j = 0; j < tamanhoChave; j++) {
                if (texto.charAt(i + j) != chave.charAt(j)) {
                    numComp++;
                    break;
                }
                numComp++;
            }
            if (j == tamanhoChave) {
                return i;  // Encontrou a chave na posição i
            }
        }
        return -1;  // A chave não foi encontrada na string
    }

    //Metodo usado somente para controle da classe
    public static void controlaClasse(String chave){
        int pos = -2;
        long tempoExecucao = -1;
        try {
            String dados = lerArquivoDB();
            long tempoInicial = System.currentTimeMillis();
            pos = buscaString(dados, chave);
            long tempoFinal = System.currentTimeMillis();
            tempoExecucao = tempoFinal - tempoInicial;
        } catch (IOException e) {  
            e.printStackTrace();
        }

        if(pos == -2){
            System.out.println("Erro ao exucutar o programa");
        }else if(pos == -1){
            System.out.println("A chave não foi encontrada");
        }else{
            System.out.println("A chave foi encontrada no texto");
        }

        System.out.println("Numero de Comparações - Força Bruta: " + numComp);

        if(tempoExecucao != -1){
            System.out.println("Tempo de Execução - Força Bruta: " + tempoExecucao);
        }else{
            System.out.println("Erro tempo de execução");
        }
    }

    //metodo para retornar o arquivo em string
    private static String lerArquivoDB() throws IOException {

        File arquivo = new File("java_projeto\\dados\\filmes\\arquivo.db");
        
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

    //testes
    /*public static void main(String[] args) {
        controlaClasse("The Lord of the Rings: The Two Towers");
    }*/
}