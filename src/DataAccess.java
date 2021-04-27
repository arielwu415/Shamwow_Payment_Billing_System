import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;
public class DataAccess {
    public static void main(String[] args) {
        String filepath = "test.txt";
        String searchTerm = "1234";
        String newValue = "1324";
        saveRecord("1234","jon","12",filepath);
        saveRecord("1434","bob","12",filepath);
        saveRecord("1244","joseph","12",filepath);
        query(filepath, searchTerm);
        editRecord(filepath, searchTerm, newValue);

    }
    private static Scanner x;

    public static void saveRecord(String ID, String name, String age, String filepath)
    {
        try
        {
            FileWriter fw = new FileWriter(filepath, true);

            BufferedWriter bw = new BufferedWriter(fw);

            PrintWriter pw = new PrintWriter(bw);

            pw.println(ID+","+name+","+age);
            pw.flush();
            pw.close();

            System.out.println("Saved");

        }
        catch(Exception E)
        {

        }
    }

    public static void query(String filepath, String searchTerm)
    {
        boolean found = false;
        String ID = "";
        String name1 = "";
        String age = "";
        try {
            x = new Scanner(new File(filepath));

            x.useDelimiter("[,\n]");

            while (x.hasNext() && !found) {
                ID = x.next();
                name1 = x.next();
                age = x.next();

                if (ID.equals(searchTerm)) {
                    found = true;
                }
            }
            if(found){
                System.out.println("ID:" + ID + "Name" + name1 + "Age: " + age);
            }
            else {
                System.out.println("Record not found");
            }
            x.close();
        } catch (Exception e) {
            System.out.println("Error");

        }
    }

    public static void editRecord(String filepath, String idSearch, String newValue)
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
            PrintWriter pw = new PrintWriter(bw);
            Scanner y = new Scanner(new File(filepath));
            y.useDelimiter("[,\n]");

            while(y.hasNext())
            {
                ID = y.next();
                name1 = y.next();
                age = y.next();

                if(ID.equals(idSearch))
                {
                    pw.print(newValue + "," + name1 + "," + age);
                }
                else
                {
                    pw.print(ID + "," + name1 + "," + age);
                }
            }
            y.close();
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
