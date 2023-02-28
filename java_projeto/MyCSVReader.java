import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MyCSVReader extends Arquivo{

    String path = "C:\\Users\\lucas\\Desktop\\java_projeto\\BD\\IMDB-Movie-Data.csv";
    Arquivo arq = new Arquivo();
    
    public void openArq() throws IOException{


        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        String line = "";
        
        br.readLine();//skip da linha de cabeçalho do csv
        line = br.readLine();//Primeira linha do arquivo csv que realmente contem dados uteis
        
        while(line != null){
            
            String[] field = line.split("\"");
            String title = field[0].split(",")[1];
            List<String> genres = Arrays.asList(field[1].split(","));


            String description = field[2].split(",")[1];
            String director = field[2].split(",")[2]; 
            List<String> actors = Arrays.asList(field[3].split(","));

            String[] values = field[4].split(",");
            int year = Integer.parseInt(values[1]);
            int runTime = Integer.parseInt(values[2]);
            float rating = Float.parseFloat(values[3]);
            int votes = Integer.parseInt(values[4]);
            float revenue = Float.parseFloat(values[5]);
            byte metaScore = Byte.parseByte(values[6]);

            try{
                Filme aux = new Filme(title, genres, description, director, actors, year, runTime, rating, votes ,revenue, metaScore);
                arq.create(aux);

            }catch(Exception e){
                e.printStackTrace();
            }

            line = br.readLine();

        }

        br.close();
        fr.close();
    }
}