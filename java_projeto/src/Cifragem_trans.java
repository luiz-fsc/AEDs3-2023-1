import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


public class Cifragem_trans {
    
    // Função para criptografar uma mensagem usando a cifra de transposição
    public static String criptografia(String mensagem, int chave) {
        int columns = chave;
        int rows = (int) Math.ceil((double) mensagem.length() / columns);
        
        char[][] grid = new char[rows][columns];
        int index = 0;
        
        // Preenche a matriz com a mensagem
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (index < mensagem.length()) {
                    grid[i][j] = mensagem.charAt(index);
                    index++;
                } else {
                    grid[i][j] = ' ';
                }
            }
        }
        
        // Constrói a mensagem criptografada lendo as colunas da matriz
        StringBuilder mensagem_criptografada = new StringBuilder();
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                mensagem_criptografada.append(grid[i][j]);
            }
        }
        
        return mensagem_criptografada.toString();
    }
    
    // Função para descriptografar uma mensagem criptografada usando a cifra de transposição
    public static String decriptografia(String mensagem_criptografada, int chave) {
        int columns = chave;
        int rows = (int) Math.ceil((double) mensagem_criptografada.length() / columns);
        
        char[][] grid = new char[rows][columns];
        int index = 0;
        
        // Preenche a matriz com a mensagem criptografada
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                if (index < mensagem_criptografada.length()) {
                    grid[i][j] = mensagem_criptografada.charAt(index);
                    index++;
                } else {
                    grid[i][j] = ' ';
                }
            }
        }
        
        // Constrói a mensagem descriptografada lendo as linhas da matriz
        StringBuilder mensagem_decriptografada = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                mensagem_decriptografada.append(grid[i][j]);
            }
        }
        
        return mensagem_decriptografada.toString().trim();
    }
    
    public static void main(String[] args) {
        String mensagem = new String();
        try{

            mensagem = pegarTexto();

          }catch(Exception e){
            e.printStackTrace();
        }
        int chave = 4;

        System.out.println(mensagem);
        
        
        String criptografia = criptografia(mensagem, chave);
        System.out.println("Mensagem criptografada: " + criptografia);
        
        String decriptografia = decriptografia(criptografia, chave);
        System.out.println("Mensagem descriptografada: " + decriptografia);
        
    }

    public static String readDBFile() {
        String filepath = "dados\\filmes\\arquivo.db";
        StringBuilder content = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return content.toString();
    }

    public static String pegarTexto() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Insira o texto a ser criptografado: ");
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }



}
