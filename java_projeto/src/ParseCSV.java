import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;

public class MyCSVReader implements Arquivo {

    static String path = "C:/Users/lucas/Desktop/java_projeto/BD/IMDB-Movie-Data.csv";
    static String line = "";

    public static void openArq() throws IOException{
        
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        br.readLine();//skip da linha de cabeçalho do csv
        line = br.readLine();

        while(line != null){
            //FALTA TRATAR CADA UM DOS CAMPOS DE ACORDO COM SEUS TIPOS
            String[] field = line.split(",");
            String title = field[1];
            //String genre = field[2];
            String description = field[3];
            String director = field[4];
            //String actors = field[5];
            //String year = field[6];
            int runTime = Integer.parseInt(field[7]);
            float rating = Float.parseFloat(field[8]);
            int votes = Integer.parseInt(field[9]);
            float revenue = Float.parseFloat(field[10]);
            byte[] metaScore = field[11].getBytes();

            Filme film = new Filme(title, genre, description, director, actors, year, runTime, rating, votes, revenue, metascore);
            
        }

        br.close();

    }
     
    
    
}


