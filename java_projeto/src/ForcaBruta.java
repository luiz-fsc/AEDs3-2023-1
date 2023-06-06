import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ForcaBruta{

    public static void busca(String chave) {
    
        String filePath = "java_projeto\\dados\\filmes\\arquivo.db";
        byte[] searchData = {};
        try {
            searchData = chave.getBytes("UTF-8"); //transformo a chave passada como parâmetro em um array de bytes
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);

            byte[] buffer = new byte[1024];
            int bytesRead;
            int position = 0;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; i++) {
                    if (buffer[i] == searchData[0]) {
                        position = i;
                        int j;
                        for (j = 1; j < searchData.length; j++) {
                            if (i + j >= bytesRead || buffer[i + j] != searchData[j]) {
                                break;
                            }
                        }
                        if (j == searchData.length) {
                            System.out.println("Dados encontrados na posição: " + (position + j - searchData.length));
                            break;
                        }
                    }
                }
            }

            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
