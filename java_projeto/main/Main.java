
public class Main {

  
  public static void main(String[] args) {

    String path = "C:/Users/lucas/Desktop/projetos_JAVA/CRUD+Exemplo_Read&&Create/IMDB-Movie-Data.csv";
    testecsv tcsv = new testecsv();
    tcsv.lerCSV_linha_a_linha(path);

    
    Arquivo<Livro> arqLivros;
    Arquivo<Cliente> arqClientes;
    Arquivo<Filme> arqFilmes;

    try {

      Livro l1 = new Livro("Eu, Robô", "Isaac Asimov", "9788576572008", 14.90F);
      Livro l2 = new Livro("Eu Sou a Lenda", "Richard Matheson", "9788576572718", 21.99F);
      Cliente c = new Cliente("José das Couves", "couves@gmail.com");

      // ESCRITA
      arqLivros = new Arquivo<>("livros", Livro.class.getConstructor());
      arqClientes = new Arquivo<>("clientes", Cliente.class.getConstructor());

      arqLivros.create(l1);
      arqLivros.create(l2);
      arqClientes.create(c);

      Livro l3 = arqLivros.read(2);
      Cliente c3 = arqClientes.read(1);
      Livro l4 = arqLivros.read(3);

      System.out.println(l3.toString());
      System.out.println(c3);
      System.out.println(l4);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}


/*  ABRINDO O CSV

  CODIGO EXEMPLO PARA MOSTRAR LEITURA LINHA A LINHA DO CSV

  public static void lerCSV_linha_a_linha(String pathCSV)
  {
  
      try {
  
          // CRIAMOS UM OBJETO FILEREADER
          // PASSANDO O PATH DO CSV COMO PARAMETRO
          FileReader filereader = new FileReader(pathCSV);
  
          // CRIAMOS O OBJETO CSV READER PASSANDO O FILEREADER COMO PARAMETRO
          // SETAMOS UM ARRAY DE STRING PARA RECEBER AS LINHAS DO CSV
          CSVReader csvReader = new CSVReader(filereader);
          String[] nextRecord;
  
          // VAMOS LER MINHA A LINHA UTILIZANDO O METODO READNEXT
          // E PRINTAMOS NA TELA
          while ((nextRecord = csvReader.readNext()) != null) {
              for (String cell : nextRecord) 
                  CSVParser dividir = new CSVParserBuilder().withSeparator(',').build();
                  CSVReader csvLeitor = new CSVReaderBuilder(filereader).withCSVParser(divifir).build();
                  System.out.print(cell + "\t");
              }
              System.out.println();
          }
      }
      catch (Exception e) {
          e.printStackTrace();
      }
  }






 */