import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException {

        //Setando o arquivo principal de armazenamento de dados
        final String arqdb = "filmes.db";

        //Organização inicial para funcionamento do programa
        //====================================================================================
        RandomAccessFile raf = new RandomAccessFile(arqdb, "rw");
        raf.writeInt(0);//escrevo zero para auxiliar na geração dos proximos ids das contas
        Scanner sc = new Scanner(System.in);
        int option;
        //====================================================================================


        System.out.println("Selecione uma das opcoes abaixo (digite apenas o numero correspondente): \n1 - Carregar base de dados \n2 - Ler um registro \n3 - Atualizar um registro \n4 - Deletar um registro \n0 - sair");
        
        option = Integer.parseInt(sc.nextLine());
        
        switch(option){
            case 0:
            System.out.println("Obrigado por usar o sistema!");
                break;
            case 1:
                try {
                    MyCSVReader.openArq();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case 2: 
                //ler um registro
                break;
            case 3:
                //atualizar registro
                break;
            case 4:
                //deletar registro
                break;
        }
        sc.close();
        raf.close();

    }
}
