import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LZW {
    
    public static List<Integer> comprimir(String original) {
        Map<String, Integer> dicionario = new HashMap();
        
        for (int i = 0; i < 256; i++) {
            dicionario.put("" + (char)i, i);
        }
        
        String atual = "";
        List<Integer> resultado = new ArrayList();


        for (char c : original.toCharArray()) {
            String next = atual + c;
            if (dicionario.containsKey(next)) {
                atual = next;
            } else {
                resultado.add(dicionario.get(atual));
                dicionario.put(next, dicionario.size());
                atual = "" + c;
            }
        }
        
        if (!atual.isEmpty()) {
            resultado.add(dicionario.get(atual));
        }
        return resultado;
    }

    public static void main(String[] args) {
        LZW lzw = new LZW();
        String original = new String();

        try{

            original = lzw.lerArquivoDB();

          }catch(Exception e){
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