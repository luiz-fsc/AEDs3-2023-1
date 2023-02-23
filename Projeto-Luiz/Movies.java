import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Movies{

    //protected byte lapide;
    protected int id;
    protected String title;
    protected List<String> genres;
    protected String description;
    protected String director;
    protected List<String> actors;
    protected Calendar c = Calendar.getInstance();
    protected int runTime;
    protected float rating;
    protected int votes;
    protected float revenue;
    protected byte metascore;


    Movies(){
        //this.lapide = -1;
        this.title = "";
        this.genres = new ArrayList<String>();
        this.description = "";
        this.director = "";
        this.actors = new ArrayList<String>();
        c.set(Calendar.YEAR, 1000); // Ano simbólico para um objeto temporário
        this.runTime = -1;
        this.rating = -1;
        this.votes = -1;
        this.revenue = -1;
        this.metascore = -1;
    }
    
    Movies(int id, String title, List<String> genres, String description, String director, List<String> actors, int year, int runTime, float rating, int votes, float revenue, byte metascore){
        //this.lapide = 1;
        this.title = title;
        this.genres = genres;
        this.description = description;
        this.director = director;
        this.actors = actors;
        c.set(Calendar.YEAR, year);
        this.runTime = runTime;
        this.rating = rating;
        this.votes = votes;
        this.revenue = revenue;
        this.metascore = metascore;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }


    public String toString(){
        return "\nTitle: " + this.title
        + "\nGenres: " + this.genres.toString()
        + "\nDescription: " + this.description
        + "\nDirector: " + this.director
        + "\nActors: " + this.actors.toString()
        + "\nYear: " + this.c.get(Calendar.YEAR)
        + "\nRun Time: " + this.runTime
        + "\nRating: " + this.rating
        + "\nVotes: " + this.votes
        + "\nRevenue: " + this.revenue
        + "\nMetascore: " + this.metascore;
    }


    public byte[] toByteArray() throws IOException{

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        //dos.writeByte(lapide);
        dos.writeUTF(this.title);
        dos.writeInt(this.genres.size());
        for(String genre : this.genres){
            dos.writeUTF(genre);
        }
        dos.writeUTF(this.description);
        dos.writeUTF(this.director);
        dos.writeInt(this.actors.size());
        for(String actor : this.actors){
            dos.writeUTF(actor);
        }
        dos.writeInt(this.c.get(Calendar.YEAR));
        dos.writeInt(this.runTime);
        dos.writeFloat(this.rating);
        dos. writeInt(this.votes);
        dos.writeFloat(this.revenue);
        dos.writeByte(this.metascore);

        return baos.toByteArray();

    }

    public void fromByteArray(byte[] ba) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);
        
        int numGenres, numActors;

        id = dis.readInt();
        title = dis.readUTF();

    }


    
}