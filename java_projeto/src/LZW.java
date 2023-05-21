import java.util.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class LZW {
    
    public static void comprimir(String original) {
        
        Map<String, Integer> dicionario = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dicionario.put(String.valueOf((char) i), i);
        }
        
        //em "atual" será armazenada a maior string existente no dicionário
        String atual = "";
        //em "resultado" será armazenado a lista de códigos da saida do algoritmo
        List<Integer> resultado = new ArrayList<Integer>();
        //tamDicionario guarda o tamanho do dicionário
        int tamDicionário = 256;


        for (char c : original.toCharArray()) {
            //em "next" será armazenada a maior string possível existente no dicionário + o caractere seguinte(o que causou a falha na busca pelo dicionário)
            String next = atual + c;
            if (dicionario.containsKey(next)) {
                //se a string for achada no dicionário, atualizar atual para continuar a busca da maior string possível
                atual = next;
            } else {
                //adiciono ao resultado o código da maior string achada no dicionário e armazenada em "atual"
                resultado.add(dicionario.get(atual));
                //adiciono ao dicionário a maior string achada no dicionário + o char seguinte
                dicionario.put(next, tamDicionário);
                tamDicionário++;
                //atualizo "atual" para armazenar o próximo char fora da ultima string achada no dicionário e continuar o algoritmo
                atual = "" + c;
            }
        }
        
        //se em atual ainda houver alguma string, adiciono o codigo dela à resultado
        if (!atual.isEmpty()) {
            resultado.add(dicionario.get(atual));
        }

        //escrevendo os codigos de saida do algoritmo em um arquivo
        try (DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("java_projeto\\comprimidos\\arqComprimido.bin"))) {
            for (Integer cod : resultado) { //para todo cod existente na lista de inteiros resultado
                outputStream.writeInt(cod); //escrever no arquivo em binario
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Compactação finalizada!");

    }

    public static void descomprimir(List<Integer> compressed){
        String path = "java_projeto\\comprimidos\\arqComprimido.bin";

        Map<Integer, String> dicionario = new HashMap<>();
        for (int i = 0; i < 256; i++) {
            dicionario.put(i, String.valueOf((char) i));
        }

        int tamDicionario = 256;
        String current = dicionario.get(compressed.get(0));
        StringBuilder resultado = new StringBuilder(current);

        
        for (int i = 1; i < compressed.size(); i++) {
            int code = compressed.get(i);

            String entry;
            if (dicionario.containsKey(code)) {
                entry = dicionario.get(code);
            } else if (code == tamDicionario) {
                entry = current + current.charAt(0);
            } else {
                throw new IllegalArgumentException("Invalid compressed code: " + code);
            }

            resultado.append(entry);
            dicionario.put(tamDicionario, current + entry.charAt(0));
            tamDicionario++;
            current = entry;
        }

        System.out.println(resultado.toString());
    }

    public static String lerArquivoDB() throws IOException { 

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

    public static void main(String[] args) {
        String original = new String();
        
        //armazenamos em "original" o conteudo em formato de string de todo o arquivo de dados
        try{
            original = lerArquivoDB();
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(original);
        comprimir(original);
    }
}