import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente implements Registro {
  protected int idCliente;
  protected String nome;
  protected String email;

  public Cliente(String t, String a) {
    this.idCliente = -1;
    this.nome = t;
    this.email = a;
  }

  public Cliente() {
    this.idCliente = -1;
    this.nome = "";
    this.email = "";
  }

  public void setID(int id) {
    this.idCliente = id;
  }

  public int getID() {
    return this.idCliente;
  }

  public String toString() {

    return "\nID....: " + this.idCliente + "\nNome: " + this.nome + "\nEmail.: " + this.email;
  }

  public byte[] toByteArray() throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    DataOutputStream dos = new DataOutputStream(baos);
    dos.writeUTF(nome);
    dos.writeUTF(email);
    dos.writeInt(idCliente);
    return baos.toByteArray();
  }

  public void fromByteArray(byte[] ba) throws IOException {
    ByteArrayInputStream bais = new ByteArrayInputStream(ba);
    DataInputStream dis = new DataInputStream(bais);
    nome = dis.readUTF();
    email = dis.readUTF();
    idCliente = dis.readInt();
  }

}