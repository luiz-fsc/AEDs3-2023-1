import java.io.RandomAccessFile;
import java.io.File;

public class Arquivo{

  //Criação Variaveis
  RandomAccessFile arquivo;
  final int TAMANHO_CABECALHO = 4;

  //O Método cria a pasta dados e as subpastas das classes, verificando a existencia delas antes
  public Arquivo(String nomeArquivo) throws Exception {

    File f = new File("dados");
    if (!f.exists()) {
      f.mkdir();
    }
    f = new File("dados/" + nomeArquivo);
    if (!f.exists()) {
      f.mkdir();
    }
    arquivo = new RandomAccessFile("dados/" + nomeArquivo + "/arquivo.db", "rw");
    if (arquivo.length() == 0) {
      arquivo.writeInt(0);
    }
  }

  //Construtor vazio
  public Arquivo(){}
  

  //Método de inserção no arquivo
  public int create(Filme obj) throws Exception {

    arquivo = new RandomAccessFile("dados/" + "filmes" + "/arquivo.db", "rw"); //GAMBIARRA

    arquivo.seek(0);              //vamos para a primeira posição no arquivo
    int ultimoID = arquivo.readInt(); //Lemos o numero de registros no arquivo colocado que também condiz com o ultimo ID alocado
    int proximoID = ultimoID + 1;     //Criamos o proximo ID
    arquivo.seek(0);        //Voltamos para a posição 0 do arquivo
    arquivo.writeInt(proximoID);  //Escrevemos o novo ID

    arquivo.seek(arquivo.length()); //Vamos para o fim do arquivo

    //Inserimos o objeto no arquivo
    obj.setID(proximoID);
    byte[] ba = obj.toByteArray();
    arquivo.writeByte(' '); //escrevemos a lápide
    arquivo.writeInt(ba.length);  //escrevemos o tamanho do arquivo
    arquivo.write(ba);  //escrevemos o objeto de fato

    return proximoID;
  }

  public Filme read(int idProcurado) throws Exception {

    arquivo.seek(TAMANHO_CABECALHO); // pular o cabeçalho e se posicionar no primeiro registro
    byte lapide;
    int tam;
    Filme obj = new Filme(); //ativamos o construtor para o obj Filme
    byte[] ba;

    //esquanto não chegar no fim do arquivo
    while (arquivo.getFilePointer() < arquivo.length()) {
      lapide = arquivo.readByte();  //le a lapide
      tam = arquivo.readInt();  //le o tamanho do arquivo
      if (lapide == ' ') {  
        ba = new byte[tam]; //passa o objeto em bytes para o array ba
        arquivo.read(ba);   // lemos o objeto
        obj.fromByteArray(ba);  //transformamos em Int e String de novo
        if (obj.getID() == idProcurado) //verificamos se é o ID que queremos
          return obj;

      } else
        arquivo.skipBytes(tam); //caso lapide esteja marcada, salte esse objeto
    }
    return null;
  }

  public Filme update(Filme novoObj) throws Exception {

    arquivo.seek(TAMANHO_CABECALHO); // pular o cabeçalho e se posicionar no primeiro registro
    byte lapide;
    long posLapide = 0;
    int tam;
    Filme obj = new Filme(); //ativamos o construtor para o obj Filme
    byte[] ba;
    byte[] baNovo;

    while (arquivo.getFilePointer() < arquivo.length()) {

      arquivo.getFilePointer();  //guardamos a posição da lapide do objeto
      lapide = arquivo.readByte();  //le a lapide
      tam = arquivo.readInt();  //le o tamanho do arquivo

      if (lapide == ' ') {  
        ba = new byte[tam]; //passa o objeto em bytes para o array ba
        arquivo.read(ba);   // lemos o objeto
        obj.fromByteArray(ba);  //transformamos em Int e String de novo

        if (obj.getID() == novoObj.getID()) { //verificamos se é o ID que queremos
          baNovo = novoObj.toByteArray();
          if(baNovo.length <= tam) {
            arquivo.seek(posLapide + 2);   //vamos para a posicao da lapide + 2 ou seja logo após o tamanho do registro
            arquivo.write(baNovo);  //escrevemos o objeto de fato
          } else {
            arquivo.seek(posLapide);  //voltamos para a posicao da lapide do objeto com o ponteiro do arquivo
            arquivo.writeByte('*'); //atualizamos a lapide
            arquivo.seek(arquivo.length()); //Vamos para o fim do arquivo
            arquivo.writeByte(' '); //escrevemos a lápide
            arquivo.writeInt(baNovo.length);  //escrevemos o tamanho do arquivo
            arquivo.write(baNovo);  //escrevemos o objeto de fato
          }

          return obj;
        }

      } else
        arquivo.skipBytes(tam); //caso lapide esteja marcada, salte esse objeto
    }

    return null;

  } //FIM UPDATE
  

  public Filme delete(int idProcurado) throws Exception {

    arquivo.seek(TAMANHO_CABECALHO); // pular o cabeçalho e se posicionar no primeiro registro
    byte lapide;
    long posLapide;
    int tam;
    Filme obj = new Filme(); //ativamos o construtor para o obj Filme
    byte[] ba;

    //esquanto não chegar no fim do arquivo
    while (arquivo.getFilePointer() < arquivo.length()) {

      posLapide = arquivo.getFilePointer();  //guardamos a posição da lapide do objeto

      lapide = arquivo.readByte();  //le a lapide
      tam = arquivo.readInt();  //le o tamanho do arquivo

      if (lapide == ' ') {  
        ba = new byte[tam]; //passa o objeto em bytes para o array ba
        arquivo.read(ba);   // lemos o objeto 
        obj.fromByteArray(ba);  //transformamos em Int e String de novo
        
        if (obj.getID() == idProcurado) //verificamos se é o ID que queremos
          arquivo.seek(posLapide);  //voltamos para a posicao da lapide do objeto com o ponteiro do arquivo
          arquivo.writeByte('*'); //atualizamos a lapide
          return obj;

      } else
        arquivo.skipBytes(tam); //caso lapide esteja marcada, salte esse objeto
    }
    return null;
  }

}
