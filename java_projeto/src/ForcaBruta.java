import java.io.FileInputStream;
import java.io.IOException;

public class ForcaBruta{

    public void busca(String chave) {
    
        String filePath = "java_projeto\\dados\\filmes\\arquivo.db";
        byte[] searchData = chave.getBytes();//transformo a chave passada como parâmetro em um array de bytes

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