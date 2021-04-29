import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class WriteToCSV
{
    public static void main(String[] args) {
        String ID = "4314";
        String name = "Steve";
        String age = "24";
        String filepath = "C:\\Users\\Administrator\\IdeaProjects\\ood\\src\\test.txt";
        saveRecord(ID, name, age, filepath);
    }

    public static void saveRecord(String ID, String name, String age, String filepath) {
        try{
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(ID+","+name+","+age);
            pw.flush(); //makes sure all data is written to the file
            pw.close(); //closes the file stream

            System.out.println("Record saved");

        } catch(Exception E) {
            System.out.println("Record not saved");
        }
    }
}
