import java.io.FileReader;
import com.opencsv.*;

public class ParseCSV {
    public static void main(String[] args) {
    try {
      //csv file containing data
      String strFile = "C:\\Users\\lucas\\Desktop\\teste\\Pasta1.csv";
      CSVReader reader = new CSVReader(new FileReader(strFile));
      String [] nextLine;
      int lineNumber = 0;
      while ((nextLine = reader.readNext()) != null) {
        lineNumber++;
        System.out.println("Line # " + lineNumber);
    
        // nextLine[] is an array of values from the line
        System.out.println(nextLine[4] + "etc...");
      }
     }catch (Exception e) {
      e.printStackTrace();
    }
    }
}







