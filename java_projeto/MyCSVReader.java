import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class MyCSVReader {

    private static int i;
    static String path = "C:\\DataBases\\IMDB-Top-250-Movies.csv";
    
    public static void openArq() throws Exception{

        i = 0;
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        
        br.readLine();//skip da linha de cabeçalho do csv
        line = br.readLine();//Primeira linha do arquivo csv que realmente contem dados uteis
        
        while(line != null){
            String[] brLine = line.split(",");
            int rank = Integer.parseInt(campos(brLine));
            //System.out.print(i + " " + rank);
            String name = campos(brLine);
            //System.out.println(" " + i + " " + name);
            int year = Integer.parseInt(campos(brLine));
            //System.out.println(i + " " + year);
            Float rating = Float.parseFloat(campos(brLine));
            //System.out.println(i + " " + rating);
            List<String> genres = Arrays.asList(campos(brLine).split(","));
            //System.out.println(Genre.toString());
            String certificate = campos(brLine);
            //System.out.println(i + " " + certificate);
            String runTime = campos(brLine);
            //System.out.println(i + " " + runTime);
            long budget = Long.parseLong(campos(brLine));
            //System.out.println(i + " " + budget);
            long boxOffice = Long.parseLong(campos(brLine));
            //System.out.println(i + " " + boxOffice);
            List<String> casts = Arrays.asList(campos(brLine).split(","));
            //System.out.println(casts.toString());
            List<String> directors = Arrays.asList(campos(brLine).split(","));
            //System.out.println(directors.toString());
            List<String> writers = Arrays.asList(campos(brLine).split(","));
            //System.out.println(writers.toString());
            
            Filme aux = new Filme(rank, name, year, rating, genres, certificate, runTime, budget, boxOffice, casts, directors, writers);

            Arquivo arq = new Arquivo();
            arq.create(aux);

            i = 0;
            line = br.readLine();
        }

        br.close();
        fr.close();
    }

    public static String campos(String[] vet){
        String campo = vet[i];
        if(vet[i].charAt(0) == '\"'){
            i++;
            while(vet[i].charAt(vet[i].length()-1) != '\"'){
                campo += "," + vet[i];
                i++;
            }
            campo += "," + vet[i];
        }else if(vet[i].equals("Not Available")){
            campo = "0";
        }
        i++;
        campo = campo.replaceAll("\"", "");
        campo = campo.replaceAll("\\$", "");
        return campo;
    }
}
