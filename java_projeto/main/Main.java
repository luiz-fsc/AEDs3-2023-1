public class Main {

  public static void main(String[] args) {

    //SETANDO VARIAVEIS ARQUIVO E MYCSVREADER
    Arquivo arqFilmes;
    MyCSVReader csvReader = new MyCSVReader();

    try {

      //Criamos o arquivo para conter os registros + o Banco de dados em Binário
      arqFilmes = new Arquivo("filmes");

      //Abrimos o CSV populando o BD Binário
      csvReader.openArq();

      //Ativamos o Menu com as opções para o usuário
      Menu menu = new Menu();
      menu.ativar();
      
    } catch (Exception e) {
      e.printStackTrace();
    }

  } //fim main
  
}
