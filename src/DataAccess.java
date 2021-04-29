import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class DataAccess
{
    private static Scanner x;

    //CAN DELETE: TEST UTILITY
    public static void main(String[] args) { 
        String filepath = "test.txt";
        String searchTerm = "1234";
        //String newValue = "1324";
        //String[] input1 = {"1234","noj","21","ASU"};
        //editRecord(filepath, input1);
        System.out.println(sumColumn(filepath, 3, 5));
        
    }
    

    /* adds new line into file
     * data[] = array to be added, filepath = file to be added */
    public static void saveRecord(String[] data, String filepath)
    {
        try
        {
            FileWriter fw = new FileWriter(filepath, true);

            BufferedWriter bw = new BufferedWriter(fw);

            PrintWriter pw = new PrintWriter(bw);

            //pw.println(ID+","+name+","+age+","+country);
            
            pw.print(data[0]);

            for(int i = 1; i < data.length; i ++)
            {
                pw.print("," + data[i]);
            }
            pw.println("");
            pw.flush();
            pw.close();

            System.out.println("Saved");

        }
        catch(Exception E)
        {

        }
    }


    /* Searches through line by line to find id, then returns specified id
     * filepath = file to open, idSearch = id to match to, columnNumber = which column to return, fields = number of fields */
    public static String query(String filepath, String idSearch, int columnNumber, int fields)
    {
        String[] storeData = new String[fields]; 
        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            while(x.hasNext())
            {
        
                for(int i = 0; i < storeData.length; i++)
                {
                    storeData[i] = x.next();
                }

                if(storeData[0].equals(idSearch))
                {
                    x.close();
                    return storeData[columnNumber];
                }
                else
                {
                    x.close();
            }
        } 
        }catch (Exception e) {
            return("Error");

        }
        return ("Not Found");
    }


    /* replaces row corresponding to id given in newValues[0] with corresponding values in newValues[i]
     * -1 is taken to be remain the same
     * filepath = file to open, newValues[] = array containing new values or -1 */
    public static void editRecord(String filepath, String[] newValues) 
    {
        String tempFile = "temp.txt";
        File oldFile = new File(filepath);
        File newFile = new File(tempFile);
        String[] storeData = new String[newValues.length]; 
        try
        {
            FileWriter fw = new FileWriter(tempFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            Scanner y = new Scanner(new File(filepath));
            y.useDelimiter("[,\n]");

            while(y.hasNext())
            {
        
                for(int i = 0; i < storeData.length; i++)
                {
                    storeData[i] = y.next();
                }

                    pw.print(newValues[0]);

                    for(int i = 1; i < storeData.length; i ++)
                    {
                        if(newValues[i] == "-1")
                        {
                            pw.print("," + storeData[i]);
                        }
                        else
                        {
                            pw.print("," + newValues[i]);
                        }
                    }
                    pw.println("");
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


    /* sums total of any given row
     * filepath = file to open; idSearch = which row to sum, fields = # of fields */
    public static int sumRow(String filepath, String idSearch, int fields)
    {
        String[] storeData = new String[fields]; 
        int sum = 0; 
        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            while(x.hasNext())
            {
                sum = 0;
                storeData[0] = x.next();
                for(int i = 1; i < storeData.length; i++)
                {
                    storeData[i] = x.next().strip();
                    sum = sum + Integer.parseInt(storeData[i]);
                }
                if(storeData[0].equals(idSearch))
                {
                    x.close();
                    return sum;
                }
            }
        } 
        catch (Exception e) {
        }
        x.close();
        return sum;
    }


    /* sums total of any given column; filepath = file to open;
     * filepath = file to open, columnNum = which column to sum, fields = # of fields */
    public static int sumColumn(String filepath, int columnNum, int fields)
    {
        String[] storeData = new String[fields]; 
        int sum = 0; 
        try {
            x = new Scanner(new File(filepath));
            x.useDelimiter("[,\n]");
            while(x.hasNext())
            {
                for(int i = 0; i < storeData.length; i++)
                {
                    storeData[i] = x.next().strip();
                    if(i == columnNum)
                    { 
                        sum = sum + Integer.parseInt(storeData[i]);
                    }
                }
                
            }
        } 
        catch (Exception e) {
        }
        x.close();
        return sum;
    }
}
