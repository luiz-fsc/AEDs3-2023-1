/*
 * Falta: Problemas com ArrayList e com Calendar no metodo fromByteArray
 * Metodo update
 * testagem metodo delete e update
 * testagem completa
 * Método toString está gerando problemas
 * 
 * Erro na conversão dos dados:
 *      -> error: incompatible types: List<String> cannot be converted to String
 */


import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.text.DecimalFormat;


public class Filme{

    protected int idFilmes; 
    protected String title;
    protected List<String> genres;
    protected String description;
    protected String director;
    protected List<String> actors;
    protected Calendar calendar = Calendar.getInstance();
    protected int runTime;
    protected float rating;
    protected int votes;
    protected float revenue;
    protected byte metascore;


    public Filme( String title, List<String> genres, String description, String director, List<String> actors, int year, int runTime, float rating, int votes, float revenue, byte metascore) throws Exception{
        this.title = title;
        this.genres = genres;
        this.description = description;
        this.director = director;
        this.actors = actors;
        this.calendar.set(Calendar.YEAR, year);
        this.runTime = runTime;
        this.rating = rating;
        this.votes = votes;
        this.revenue = revenue;
        this.metascore = metascore;
    }


    public Filme() {
        this.idFilmes = -1;
        this.title = "";
        this.genres = new ArrayList<String>();
        this.description = "";
        this.director = "";
        this.actors = new ArrayList<String>();
        this.calendar.set(Calendar.YEAR, 1000);
        this.runTime = -1;
        this.rating = -1;
        this.votes = -1;
        this.revenue = -1;
        this.metascore = -1;
    }

    public void setID(int id) {
        this.idFilmes = id;
    }

    
      public int getID() {
        return this.idFilmes;
    }
    
    /* 
    @override
    public String toString() {
        return 
        + "\nTitle: " + this.title
        + "\nGenres: " + this.genres.toString()
        + "\nDescription: " + this.description
        + "\nDirector: " + this.director
        + "\nActors: " + this.actors.toString()
        + "\nYear: " + this.calendar.get(Calendar.YEAR)
        + "\nRun Time: " + this.runTime
        + "\nRating: " + this.rating
        + "\nVotes: " + this.votes
        + "\nRevenue: " + this.revenue
        + "\nMetascore: " + this.metascore;
    }

    */

    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeUTF(this.title);
        dos.writeInt(this.genres.size());
        for(String genre : genres){
            dos.writeUTF(genre);
        }
        dos.writeUTF(this.description);
        dos.writeUTF(this.director);
        dos.writeInt(this.actors.size());
        for(String actor : actors){
            dos.writeUTF(actor);
        }
        dos.writeInt(calendar.get(Calendar.YEAR));
        dos.writeInt(this.runTime);
        dos.writeFloat(this.rating);
        dos.writeInt(this.votes);
        dos.writeFloat(this.revenue);
        dos.writeByte(this.metascore);

        return baos.toByteArray();

    }

    public void fromByteArray(byte[] ba) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        this.idFilmes = dis.readInt();
        this.title = dis.readUTF();
        //genre
        this.description = dis.readUTF();
        this.director = dis.readUTF();
        //actors
        //calendar
        this.runTime = dis.readInt();
        this.rating = dis.readFloat();
        this.votes = dis.readInt();
        this.revenue = dis.readFloat();
        this.metascore = dis.readByte();
    }

}
