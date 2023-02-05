import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Movies{
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
}