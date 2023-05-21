import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LZW {
    
    public static List<Integer> comprimir(String original) {
        Map<String, Integer> dicionario = new HashMap<String, Integer>();

        //Inicializar o dicionário (com símbolos básicos)
        for (int i = 0; i < 256; i++) {
            dicionario.put("" + (char)i, i);
        }
        
        //em "atual" será armazenada a maior string existente no dicionário
        String atual = "";
        //em "resultado" será armazenado a lista de códigos da saida do algoritmo
        List<Integer> resultado = new ArrayList<Integer>();


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
                dicionario.put(next, dicionario.size());
                //atualizo "atual" para armazenar o próximo char fora da ultima string achada no dicionário e continuar o algoritmo
                atual = "" + c;
            }
        }
        
        //se em atual ainda houver alguma string, adiciono o codigo dela à resultado
        if (!atual.isEmpty()) {
            resultado.add(dicionario.get(atual));
        }
        return resultado;
    }

    public static void main(String[] args) {
        String original = new String();

        try{
            original = lerArquivoDB();
        }catch (Exception e){
            e.printStackTrace();
        }

        List<Integer> str_comprimida = comprimir(original);
        System.out.println("str_comprimida: " + str_comprimida);
    }


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

}