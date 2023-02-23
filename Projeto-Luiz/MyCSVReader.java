import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MyCSVReader {

    static String path = "C:\\DataBases\\IMDB-Movie-Data.csv";
    static String line = "";

    public static void openArq() throws IOException{

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        br.readLine();
        line = br.readLine();
        while(line != null){
            
        }

        br.close();

    }
     
    
    
}
