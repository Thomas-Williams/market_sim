import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class CsvWriterString{
    private static final String NEW_LINE_SEPARATOR = "\n";
    public static void writeCsvFile( String text, double fileNumber) {
        String fileName = "Lproject_1_output" + fileNumber + ".csv";
        try {
            BufferedWriter output = new BufferedWriter( new FileWriter(fileName, true) );
            output.append(text);
            
            output.append(NEW_LINE_SEPARATOR);
            output.flush();
            output.close();
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }
    }
}