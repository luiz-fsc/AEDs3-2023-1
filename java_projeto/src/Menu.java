import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Menu extends Arquivo{

      public int ativar() throws Exception {

        //Setando variáveis Arquivo + Scanner
        Arquivo arq = new Arquivo();
        Scanner sc = new Scanner(System.in);
        int option;

        //Start Menu
        System.out.print("Selecione uma das opcoes abaixo (digite apenas o numero correspondente): \n\n0 - sair \n1 - Ler um registro \n2 - Atualizar um registro \n3 - Deletar um registro \n4 - Repopular Banco de Dados \n5 - KMP\nEscolha:  ");
        
        option = Integer.parseInt(sc.nextLine());
        
        switch(option){
            case 0:
                System.out.println("Obrigado por usar o sistema!");
                break;

            case 1: //READ

                System.out.println("\nPor favor insira o id procurado:");
                int idProcurado = Integer.parseInt(sc.nextLine());

                try{
                    Filme registroProcurado = arq.read(idProcurado);
                    if(registroProcurado == null){
                        System.out.println("Registro não Encontrado!");
                    } else 
                    System.out.println(" \n"+ registroProcurado.toString());
                    
                  }catch(Exception e){
                    e.printStackTrace();
                }

                break;

            case 2: // UPDATE

                System.out.println("\nPor favor insira o id Filme para ser atualizado:");
                int idAtualizado= Integer.parseInt(sc.nextLine());
                
                System.out.println("\nAgora insira os atributos Filme:");
                Menu menuUpdate =  new Menu();
                Filme obj = menuUpdate.pegarDadosParaUpdate();

                try{
                    Filme registroAtualizado = arq.update(obj);
                    System.out.println(" \n"+ registroAtualizado.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }

                break;

            case 3: // DELETE

                System.out.println("\nPor favor insira o id que quer deletar:");
                int idDeletedo= Integer.parseInt(sc.nextLine());
                

                try{
                    Filme registroDeletado = arq.delete(idDeletedo);
                  }catch(Exception e){
                    e.printStackTrace();
                }
                break;

            case 4: // Repopular BD


                System.out.println("\nPupulando BD...");

                try {
                    MyCSVReader.openArq();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

                case 5: // KMP
                // Registrar o tempo inicial
                long tempoInicial = System.currentTimeMillis();

                try {
                    KMP kmp = new KMP();
                    String padrao = kmp.pegarPadrao();
                    String texto = new String();
                    try{

                        texto = kmp.lerArquivoDB();
            
                      }catch(Exception e){
                        e.printStackTrace();
                    }
                    kmp.AlgKMP(texto, padrao);

                    // Registrar o tempo final
                    long tempoFinal = System.currentTimeMillis();

                    // Calcular o tempo de execução em milissegundos
                    long tempoExecucao = tempoFinal - tempoInicial;

                    // Exibir o tempo de execução
                    System.out.println("Tempo de execução: " + tempoExecucao + " milissegundos");
    
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }   //SWITCH MENU

        

        return option;
    }

    public Filme pegarDadosParaUpdate() throws Exception {

            //Scanner
            Scanner sc = new Scanner(System.in);

            //Printamos na tela o atributo + lemos o que o usuário nos passa para os campos
            System.out.print("\n(int)rank: ");
            int rank = Integer.parseInt(sc.nextLine());

            System.out.print("\n(String)name: ");
            String name = (sc.nextLine());

            System.out.print("\n(int)year: ");
            int year = Integer.parseInt(sc.nextLine());

            System.out.print("\n(Float)rating: ");
            Float rating = Float.parseFloat(sc.nextLine());

            System.out.println("(List<String>)genres separados por virgula: ");
            List<String> genres = Arrays.asList(sc.nextLine().split(","));
            

            System.out.print("\n(String)certificate: ");
            String certificate = (sc.nextLine());

            System.out.print("\n(String)runTime: ");
            String runTime = (sc.nextLine());
            
            System.out.print("\n(long)budget: ");
            long budget = Long.parseLong(sc.nextLine());

            System.out.print("\n(long)boxOffice: ");
            long boxOffice = Long.parseLong(sc.nextLine());

            System.out.println("(List<String>)casts separados por virgula: ");
            List<String> casts = Arrays.asList(sc.nextLine().split(","));
            
            System.out.println("(List<String>)directors separados por virgula: ");
            List<String> directors = Arrays.asList(sc.nextLine().split(","));
            
            System.out.println("(List<String>)writers separados por virgula: ");
            List<String> writers = Arrays.asList(sc.nextLine().split(","));


            //Ativamos o Construtor do Objeto passando os dados
            Filme aux;
            aux = new Filme(rank, name, year, rating, genres, certificate, runTime, budget, boxOffice, casts, directors, writers);
            
            sc.close();

            //retornamos o filme ja construido
            return aux;
    }
}
