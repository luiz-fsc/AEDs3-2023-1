import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;

        System.out.println("Selecione uma das opcoes abaixo (digite apenas o numero correspondente): \n1 - Carregar base de dados \n2 - Ler um registro \n3 - Atualizar um registro \n4 - Deletar um registro \n0 - sair");
        
        option = Integer.parseInt(sc.nextLine());
        
        switch(option){
            case 0:
            System.out.println("Obrigado por usar o sistema!");
                break;
            case 1:
                //criar base de dados
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
    }
}
