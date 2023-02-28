import java.io.IOException;
import java.util.Scanner;

public class Menu extends Arquivo{

      public void ativar() throws IOException {

        //Setando variáveis Arquivo + Scanner
        Arquivo arq = new Arquivo();
        Scanner sc = new Scanner(System.in);
        int option;

        //Start Menu
        System.out.println("Selecione uma das opcoes abaixo (digite apenas o numero correspondente): \n1 - Ler um registro \n2 - Atualizar um registro \n3 - Deletar um registro \n0 - sair");
        
        option = Integer.parseInt(sc.nextLine());
        
        switch(option){
            case 0:
                System.out.println("Obrigado por usar o sistema!");
                break;

            case 1: 
                System.out.println("\nPor favor insira o id procurado:");
                int idProcurado = Integer.parseInt(sc.nextLine());

                try{
                    Filme registroProcurado = arq.read(idProcurado);
                    registroProcurado.toString();
                  }catch(Exception e){
                    e.printStackTrace();
                }

                break;

            case 2:
                //atualizar registro
                break;

            case 3:
                System.out.println("\nPor favor insira o id que quer deletar:");
                int idDeletedo= Integer.parseInt(sc.nextLine());
                
                try{
                    Filme registroDeletado = arq.delete(idDeletedo);
                    registroDeletado.toString();
                  }catch(Exception e){
                    e.printStackTrace();
                }
                break;

        }   //SWITCH MENU

        sc.close();

    }
}
