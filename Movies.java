import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Movies{

    protected byte lapide;
    protected int ranking;
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
        this.lapide = -1;
        this.ranking = 0;
        this.title = "";
        this.genres = new ArrayList<String>();
        this.description = "";
        this.director = "";
        this.actors = new ArrayList<String>();
        c.set(Calendar.YEAR, 1000); // Ano simbólico para um objeto temporário
        this.runTime = 0;
        this.rating = 0;
        this.votes = 0;
        this.revenue = 0;
        this.metascore = 0;
    }
    
    Movies(int ranking, String title, List<String> genres, String description, String director, List<String> actors, int year, int runTime, float rating, int votes, float revenue, byte metascore){
        this.lapide = 1;
        this.ranking = ranking;
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


    public String toString(){
        return "Ranking: " + this.ranking
        + "\nTitle: " + this.title
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

        dos.writeByte(lapide);
        dos.writeInt(ranking);
        dos.writeUTF(title);
        dos.writeInt(genres.size());
        for(String genre : genres){
            dos.writeUTF(genre);
        }
        dos.writeUTF(description);
        dos.writeUTF(director);
        dos.writeInt(actors.size());
        for(String actor : actors){
            dos.writeUTF(actor);
        }
        dos.writeInt(c.get(Calendar.YEAR));
        dos.writeInt(runTime);
        dos.writeFloat(rating);
        dos. writeInt(votes);
        dos.writeFloat(revenue);
        dos.writeByte(metascore);

        return baos.toByteArray();

    }


    
}