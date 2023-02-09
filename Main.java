import java.io.File;

public class Main {
  public static void main(String[] args) {

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