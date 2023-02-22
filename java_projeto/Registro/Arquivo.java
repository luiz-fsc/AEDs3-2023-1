import java.io.RandomAccessFile;
import java.io.File;
import java.lang.reflect.Constructor;

public class Arquivo<T extends Registro> {

  //Criação Variaveis
  RandomAccessFile arquivo;
  Constructor<T> construtor;
  final int TAMANHO_CABECALHO = 4;

  //O Método cria a pasta dados e as subpastas das classes, verificando a existencia delas antes
  public Arquivo(String nomeArquivo, Constructor<T> c) throws Exception {

    File f = new File("dados");
    if (!f.exists()) {
      f.mkdir();
    }
    f = new File("dados/" + nomeArquivo);
    if (!f.exists()) {
      f.mkdir();
    }
    arquivo = new RandomAccessFile("dados/" + nomeArquivo + "/arquivo.db", "rw");
    construtor = c;
    if (arquivo.length() == 0) {
      arquivo.writeInt(0);
    }
  }

  //Método de inserção no arquivo
  public int create(T obj) throws Exception {

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

  public T read(int idProcurado) throws Exception {

    arquivo.seek(TAMANHO_CABECALHO); // pular o cabeçalho e se posicionar no primeiro registro
    byte lapide;
    int tam;
    T obj = construtor.newInstance(); //ativamos o construtor para o obj T
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

  public T delete(int idProcurado) throws Exception {

    arquivo.seek(TAMANHO_CABECALHO); // pular o cabeçalho e se posicionar no primeiro registro
    byte lapide;
    long posLapide;
    int tam;
    T obj = construtor.newInstance(); //ativamos o construtor para o obj T
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
          return null;

      } else
        arquivo.skipBytes(tam); //caso lapide esteja marcada, salte esse objeto
    }
    return null;
  }

}
