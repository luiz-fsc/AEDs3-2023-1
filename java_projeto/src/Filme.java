/*
 * Falta: Problemas com ArrayList e com Calendar no metodo fromByteArray
 * testagem metodo delete e update
 * testagem completa
 * Método toString está gerando problemas
 * metodo para converter rumtime de string para int
 */


import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;


public class Filme{

    protected int idFilmes;
    protected int rank; 
    protected String name;
    protected Calendar calendar = Calendar.getInstance();
    protected float rating;
    protected List<String> genres;
    protected String certificate;
    protected String run_time;
    protected long budget;
    protected long box_office;
    protected List<String> casts;
    protected List<String> directors;
    protected List<String> writers;


    public Filme( int rank, String name, int year, float rating, List<String> genres, String certificate, String run_time, long budget, long box_office, List<String> casts, List<String> directors, List<String> writers) throws Exception{
        this.rank = rank;
        this.name = name;
        this.calendar.set(Calendar.YEAR, year);
        this.rating = rating;
        this.genres = genres;
        this.certificate = certificate;
        this.run_time = run_time;
        this.budget = budget;
        this.box_office = box_office;
        this.casts = casts;
        this.directors = directors;
        this.writers = writers;
    }


    public Filme() {

        this.idFilmes = -1;
        this.rank = -1;
        this.name = "";
        this.calendar.set(Calendar.YEAR, 0);
        this.rating = -1;
        this.genres = new ArrayList<String>();
        this.certificate = "";
        this.run_time = "";
        this.budget = -1;
        this.box_office = -1;
        this.casts = new ArrayList<String>();
        this.directors = new ArrayList<String>();
        this.writers = new ArrayList<String>();
    }

    public void setID(int id) {
        this.idFilmes = id;
    }

    
    public int getID() {
        return this.idFilmes;
    }
    
     
    @Override
    public String toString() {
        return "Rank: " + this.rank
        + "\nName: " + this.name
        + "\nYear: " + this.calendar.get(Calendar.YEAR)
        + "\nRating: " + this.rating
        + "\nGenres: " + this.genres.toString()
        + "\nCertificate: " + this.certificate
        + "\nRun Time: " + this.run_time
        + "\nGenres: " + this.genres.toString()
        + "\nBudget: " + this.budget
        + "\nBox Office: " + this.box_office
        + "\nCasts: " + this.casts.toString()
        + "\nDirectors: " + this.directors.toString()
        + "\nWriters: " + this.writers.toString();
    }

    public byte[] toByteArray() throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(this.rank);
        dos.writeUTF(this.name);
        dos.writeInt(calendar.get(Calendar.YEAR));
        dos.writeFloat(this.rating);
        dos.writeInt(this.genres.size());
        for(String genre : genres){
            dos.writeUTF(genre);
        }
        dos.writeUTF(this.certificate);
        dos.writeUTF(this.run_time);
        dos.writeLong(this.budget);
        dos.writeLong(this.box_office);
        dos.writeInt(this.casts.size());
        for(String cast : casts){
            dos.writeUTF(cast);
        }
        dos.writeInt(this.directors.size());
        for(String director : directors){
            dos.writeUTF(director);
        }
        dos.writeInt(this.writers.size());
        for(String writer : writers){
            dos.writeUTF(writer);
        }

        return baos.toByteArray();

    }

    public void fromByteArray(byte[] ba) throws IOException {

        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        int numGenres, numCasts, numDirectors, numWriters;

        this.rank = dis.readInt();
        this.name = dis.readUTF();
        this.calendar.set(Calendar.YEAR, dis.readInt());
        this.rating = dis.readFloat();
        numGenres = dis.readInt();
        for(int i = 0; i < numGenres; i++){
            this.genres.add(dis.readUTF());
        }
        this.certificate = dis.readUTF();
        this.run_time = dis.readUTF();
        this.budget = dis.readLong();
        this.box_office = dis.readLong();
        numCasts = dis.readInt();
        for(int i = 0; i < numCasts; i++){
            this.casts.add(dis.readUTF());
        }
        numDirectors = dis.readInt();
        for(int i = 0; i < numDirectors; i++){
            this.directors.add(dis.readUTF());
        }
        numWriters = dis.readInt();
        for(int i = 0; i < numWriters; i++){
            this.writers.add(dis.readUTF());
        }
    }

}
