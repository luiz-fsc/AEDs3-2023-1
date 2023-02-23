import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyCSVReader {

    static String path = "C:\\DataBases\\IMDB-Movie-Data.csv";
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
            String genre = field[2];
            String description = field[3];
            String director = field[4];
            String actors = field[5];
            String year = field[6];
            String runTime = field[7];
            String rating = field[8];
            String votes = field[9];
            String revenue = field[10];
            String metaScore = field[11];

        }

        br.close();

    }
     
    
    
}
