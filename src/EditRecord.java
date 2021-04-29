import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class EditRecord {
    public static void main(String[] args) {
        String filepath = "test.txt";
        String IDs = "1234";
        String newName = "Donald";
        String newAge = "30";

        editRecord(filepath, IDs, newName, newAge);

    }

    /* edits existing record
     * filepath = file to open, idSearch = id to match to, newName = new name to store, newAge = new age to store */
    public static void editRecord(String filepath, String idSearch, String newName, String newAge)
    {
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String ID = "";
        String name1 = "";
        String age = "";
        try
        {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            System.out.println(1);
            PrintWriter pw = new PrintWriter(bw);
            System.out.println(2);
            Scanner y = new Scanner(new File("C:\\Users\\Administrator\\IdeaProjects\\ood\\src\\test.txt"));
            System.out.println(3);
            y.useDelimiter("[,\n]");
            System.out.println(4);

            while(y.hasNext())
            {
                ID = y.next();
                name1 = y.next();
                age = y.next();
                System.out.println(5);

                if(ID.equals(idSearch))
                {
                    System.out.println(6);
                    pw.println(idSearch + "," + newName + "," + newAge);
                    System.out.println(7);
                }
                else
                {
                    pw.println(ID + "," + name1 + "," + age);
                }
                System.out.println(8);
            }
            System.out.println(9);
            y.close();
            System.out.println(10);
            pw.flush();
            pw.close();
            boolean bool = oldFile.delete();
            System.out.println("File deleted:" + bool);
            File dump = new File(filepath);
            newFile.renameTo(dump);
        }
        catch(Exception e)
        {
            System.out.println("File not found");
        }
    }
}


